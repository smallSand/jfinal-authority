package com.jayqqaa12.ctrl.system;

import com.jayqqaa12.jbase.jfinal.ext.ctrl.JsonController;
import com.jayqqaa12.jbase.jfinal.ext.spring.Inject;
import com.jayqqaa12.model.system.Bug;
import com.jfinal.ext.route.ControllerBind;

@ControllerBind(controllerKey = "/system/bug")
public class BugCtrl extends JsonController<Bug> {

	public void list() {
		sendJson("list", Bug.dao.findAll());
	}

	public void status() {
		sendJson(getModel().updateAndModifyDate());
	}

	public void save() {
		boolean result =false;
		Bug bug = getModel();
		if (bug.getId() == null) result= bug.saveAndCreateDate();
		else  result =bug.updateAndModifyDate();
		sendJson(result);
	}

	public void delete() {
		sendJson(Bug.dao.deleteById(getPara("id")));
	}

}
