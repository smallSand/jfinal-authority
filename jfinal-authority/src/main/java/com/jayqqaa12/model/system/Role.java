package com.jayqqaa12.model.system;

import java.util.ArrayList;
import java.util.List;

import com.jayqqaa12.auto.base.system.BaseRole;
import com.jayqqaa12.jbase.jfinal.ext.util.ListUtil;
import com.jayqqaa12.model.TreeKit;
import com.jayqqaa12.shiro.ShiroCache;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;

public class Role extends BaseRole<Role>
{
	private static final long serialVersionUID = -5747359745192545106L;
	public final static Role dao = new Role();

	public List<String> getResUrl(String name)
	{
		return TreeKit.getAttr(dao,sql("system_res.getResUrl"), "url", name);

	}

	public List<Role> getRole(Object uid)
	{
		return find(sql("system_role.getRole"), uid);
	}

	@Override
	public List<Role> findAll()
	{
		List<Role> list =find(sql("system_role.list"));

		for (Role r : list)
		{
			List<Res> res = Res.dao.getRes(r.getId());
			r.put("res_ids", ListUtil.listToString(res, "id"));
			r.put("res_names", ListUtil.listToString(res, "name"));
		}

		return list;
	}

	public List<Tree> getTree(Integer id, Integer passId)
	{
		// 根据用户角色来获取 列表
		List<Tree> trees = new ArrayList<Tree>();

		for (Role res : getChild(id))
		{
			if (res.getId().equals(passId)) continue;
			Tree tree = new Tree(res.getInt("id"), res.getInt("pid"), res.getStr("name"), res.getStr("iconCls"), res, false);

			tree.children = getTree(res.getInt("id"), passId);
			if (!tree.children.isEmpty()) tree.changeState();

			trees.add(tree);
		}
		return trees;
	}

	public List<Role> getChild(Integer id)
	{
		if (id == null) return findAllByWhere(" where pid is null order by seq");
		return findAllByWhere(" where pid = ? order by seq ", id);

	}

	public boolean grant(int roleId, String resIds)
	{
		boolean result=false;
		if (StrKit.notBlank(resIds)) result= Res.dao.batchAdd(roleId, resIds);
		ShiroCache.clearAuthorizationInfoAll();
		return result;
	}

	/***
	 * 批量授权 会先删除所有权限再授权
	 * 
	 * @param roleId
	 * @param resIds
	 * @return
	 */
	public boolean batchGrant(Object roleId, String resIds)
	{
		boolean result = Db.deleteById("system_role_res", "role_id", roleId);
		if (StrKit.notBlank(resIds)) result = Res.dao.batchAdd(roleId, resIds);

		ShiroCache.clearAuthorizationInfoAll();

		return result;
	}

}
