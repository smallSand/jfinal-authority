package com.jayqqaa12.model;

import java.util.ArrayList;
import java.util.List;

import com.jayqqaa12.jbase.jfinal.ext.model.Model;
import com.jfinal.plugin.activerecord.Db;

/***
 * 
 * @author 12
 *
 * @param <M>
 */
public class TreeKit {

 

	/***
	 * 把 model 转化为 list 找到其中的单个属性
	 * 
	 * @param sql
	 * @param attr
	 * @return
	 */

	public static List<String> getAttr(Model<? extends Model>  dao, String sql, String attr, Object ... param) {
		List<String> list = new ArrayList<String>();

		for (Model t : dao.find(sql, param)) {
			list.add(t.getStr(attr));
		}
		return list;

	}

 
	/***
	 * 删除自己的同时 删除 所有 子节点 属性名 必需为pid
	 * 
	 */
	public static boolean deleteByIdAndPid(Model<? extends Model> dao, Object id) {
		boolean result = dao.deleteById(id);

		List<Model> list = (List<Model>) dao.findAllByWhere("where pid=?", id);

		for (Model m : list) {
			 deleteByIdAndPid(dao,m.getId());

			Db.update("delete from " + dao.TABLENAME + " where pid=? ", id);
		}

		return result;
	}

	 
	public static boolean pidIsChild(Model<? extends Model> dao,Object id, Integer pid) {
		boolean result = false;
		if (pid != null) {
			List<Model>  list = (List<Model>) dao.findAllByWhere(" where  pid =?  ", id);

			if (list.size() == 0) result = false;

			for (Model r : list) {
				if (pid.equals(r.getId())) {
					result = true;
					return result;
				} else {
					if (pidIsChild(dao,r.getId(), pid)) {
						result = true;
						return result;
					}
				}

			}
		}

		return result;

	}

	 

}
