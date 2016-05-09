package com.jayqqaa12.ctrl.system;

import org.apache.shiro.SecurityUtils;

import com.jayqqaa12.common.Consts;
import com.jayqqaa12.jbase.jfinal.ext.ctrl.JsonController;
import com.jayqqaa12.jbase.jfinal.ext.spring.Inject;
import com.jayqqaa12.model.system.User;
import com.jayqqaa12.shiro.ShiroCache;
import com.jayqqaa12.val.system.UserValidator;
import com.jfinal.aop.Before;
import com.jfinal.ext.route.ControllerBind;

@ControllerBind(controllerKey = "/system/user")
public class UserCtrl extends JsonController<User> {


	public void list() {
		sendJson("list", User.dao.list(getParaToInt("page"), getParaToInt("count")));
	}

	@Override
	public void delete() {
		sendJson(User.dao.deleteById(getPara("id")));
	}

	public void freeze() {
		sendJson(User.dao.changeStaus(getParaToInt("id"), getParaToInt("status")));
	}

	@Before(value = { UserValidator.class })
	public void save() {
		User u = getModel();

		if (u.getId() != null) u.encrypt().update();
		else SecurityUtils.getSubject().getSession().setAttribute(Consts.SESSION_EAMIL_USER, u);

		Integer[] role_ids = getParaValuesToInt("role_ids");

		sendJson(User.dao.grant(role_ids, getModel().getInt("id")));

		ShiroCache.clearAuthorizationInfoAll();
	}

	public void getUser() {

		sendJson(User.dao.getUser(getPara("id")));

	}

}
