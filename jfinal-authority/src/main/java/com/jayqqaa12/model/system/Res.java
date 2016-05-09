package com.jayqqaa12.model.system;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import com.google.common.collect.Lists;
import com.jayqqaa12.auto.base.system.BaseRes;
import com.jayqqaa12.jbase.jfinal.ext.util.ListUtil;
import com.jayqqaa12.jbase.jfinal.ext.util.ModelKit;
import com.jayqqaa12.jbase.sdk.util.ShiroExt;
import com.jayqqaa12.model.TreeKit;
import com.jfinal.plugin.activerecord.Db;

public class Res extends BaseRes<Res> {
	private static final long serialVersionUID = 9204284399513186930L;
	public final static Res dao = new Res();
 
	public static final int TYPE_MEUE = 1;
	public static final int TYPE_PERMISSION = 2;

	public List<Res> list(Integer pid) {
		List<Res> counts = find(sql("system_res.getChildSize"));
		List<Res> list = null;
		if (pid == null) list = findAllByWhere("where pid is null ");
		else list = findAllByWhere("where pid= ?", pid);

		for (Res r : list) {
			Res c = (Res) ModelKit.getValue("pid", r.getId(), counts);
			if (c != null) r.put("childSize", c.get("c"));
		}

		return list;
	}

	/**
	 * 转化为 easyui Tree 对象
	 * 
	 * @param type
	 * @return
	 */
	public List<Tree> getTree(Integer pid, int type, Integer passId) {
		// 根据用户角色来获取 列表
		List<Tree> trees = new ArrayList<Tree>();

		for (Res res : getChild(pid, type)) {

			if (res.getInt("id").equals(passId)) continue;

			Tree tree = new Tree(res.getInt("id"), res.getInt("pid"), res.getStr("name"), res.getStr("iconCls"), res.getAttrs(), false);
			tree.children = getTree(res.getInt("id"), type, passId);
			if (!tree.children.isEmpty()) tree.changeState();

			trees.add(tree);
		}

		return trees;
	}

	public List<ZTree> getZTree() {
		List<ZTree> list = Lists.newArrayList();

		for (Res r : super.findAll()) {
			ZTree zt = new ZTree(r.getInt("id"), r.getStr("name"), r.getInt("pid"));

			if (zt.id == 0) zt.setDisCheck(true);
			list.add(zt);

		}
		return list;
	}

	public List<String> getUrls() {
		return TreeKit.getAttr(dao,sql("system_res.getUrls"), "url");
	}

	public List<String> getAuthUrls() {
		ShiroExt ext = new ShiroExt();
		List<String> list = Lists.newArrayList();

		for (String url : getUrls()) {
			if (ext.hasPermission(url)) list.add(url);
		}

		return list;

	}

	public List<Res> getChild(Integer id, Integer type) {
		ShiroExt ext = new ShiroExt();
		List<Res> list = null;

		if (type == null) return findAllByWhere("where pid =?", id);
		else if (id == null && type == TYPE_MEUE) list = findAllByWhere(" where  pid is null and type =? order by seq ", type);
		else if (id == null && type == TYPE_PERMISSION) list = findAllByWhere("where pid is null order by seq");
		else if (type == TYPE_MEUE) list = findAllByWhere(" where  pid =? and type =? order by seq ", id, type);
		else if (type == TYPE_PERMISSION) list = findAllByWhere(" where  pid =? order by seq  ", id);


		if (id == null) return list;
		else if (TYPE_PERMISSION == type) return list;
		else {
			ListIterator<Res> itor = list.listIterator();
			while (itor.hasNext()) {
				Res r = itor.next();
				if (r.getStr("url") == null) continue;
				if (!ext.hasPermission(r.getStr("url"))) {
					itor.remove();

				}
			}
		}

		return list;
	}

	/***
	 * 通过 role id 获得 res
	 * 
	 * @param r
	 * @return
	 */
	public List<Res> getRes(Object id) {
		return find(sql("system_res.getRes"), id);

	}

	public boolean batchAdd(Object roleId, String resIds) {
		Object[][] params = ListUtil.stringToArray(roleId, resIds);

		Db.batch("insert into system_role_res(role_id,res_id)  values(?,?)", params, params.length);

		return true;
	}

}
