package com.jayqqaa12.ctrl.system;

import com.jayqqaa12.jbase.jfinal.ext.ctrl.JsonController;
import com.jayqqaa12.model.TreeKit;
import com.jayqqaa12.model.system.Role;
import com.jayqqaa12.shiro.ShiroCache;
import com.jayqqaa12.val.system.RoleValidator;
import com.jfinal.aop.Before;
import com.jfinal.ext.route.ControllerBind;

@ControllerBind(controllerKey = "/system/role")
public class RoleCtrl extends JsonController<Role> {


	public void list() {
		
		renderJson(Role.dao.findAll());
	
	}
	
//	public void tree() {
//		Integer pid = getParaToInt("id");
//		Integer passId = getParaToInt("passId");
//		renderJson(Role.dao.getTree(pid, passId));
//
//	}

	@Before(value = { RoleValidator.class })
	public void save() {
		Role role = getModel();
		String res_ids = getPara("res_ids");

		if (role.getId() != null) sendJson(role.update());
		else sendJson(role.save());

		Role.dao.batchGrant(role.getId(), res_ids);

		ShiroCache.clearAuthorizationInfoAll();
	}

	public void delete() {
		int id = getParaToInt("id");
		sendJson(TreeKit.deleteByIdAndPid(Role.dao,id));
	}

}
