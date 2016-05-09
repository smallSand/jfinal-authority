package com.jayqqaa12.ctrl.system;

import com.jayqqaa12.jbase.jfinal.ext.ctrl.JsonController;
import com.jayqqaa12.model.TreeKit;
import com.jayqqaa12.model.system.Res;
import com.jayqqaa12.model.system.Role;
import com.jayqqaa12.shiro.ShiroCache;
import com.jayqqaa12.shiro.ShiroInterceptor;
import com.jfinal.ext.route.ControllerBind;

@ControllerBind(controllerKey = "/system/res")
public class ResCtrl extends JsonController<Res> {

	public void tree() {
		Integer pid = getParaToInt("id");
		Integer passId = getParaToInt("passId");
		int type = getParaToInt("type", Res.TYPE_MEUE);
		sendJson("tree", Res.dao.getTree(pid, type, passId));
	}

	/**
	 * 返回给前端权限控制
	 */
	public void urls() {

		setJsonData("urls", Res.dao.getUrls());
		setJsonData("auth_urls", Res.dao.getAuthUrls());
		sendJson();
	}

	public void ztree() {
		sendJson("ztree", Res.dao.getZTree());
	}

	public void list() {
		Integer pid = getParaToInt("id");

		if (pid != null)  setJsonData("parent", Res.dao.findById(pid));
		setJsonData("list", Res.dao.list(pid));

		sendJson();
	}

	public void delete() {
		sendJson(TreeKit.deleteByIdAndPid(Res.dao, getParaToInt("id")));
		removeAuthorization();
	}

	public void batchDelete() {

		sendJson(Res.dao.batchDelete(getPara("ids")));
	}

	public void save() {
		Res res = getModel();
		boolean result = false;

		if (res.getId() == null) {
			result = res.save();
			Role.dao.grant(1, res.getId() + "");
		} else result = res.update();

		removeAuthorization();

		sendJson(result);
	}

	private void removeAuthorization() {
		ShiroCache.clearAuthorizationInfoAll();
		ShiroInterceptor.updateUrls();
	}

}
