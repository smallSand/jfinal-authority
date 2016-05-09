package com.jayqqaa12.ctrl.system;

import java.util.UUID;

import javax.mail.MessagingException;

import com.jayqqaa12.common.Code;
import com.jayqqaa12.common.Consts;
import com.jayqqaa12.jbase.jfinal.ext.ctrl.JsonController;
import com.jayqqaa12.jbase.sdk.util.ShiroExt;
import com.jayqqaa12.model.system.User;
import com.jayqqaa12.service.EmailService;
import com.jayqqaa12.val.system.EmailExistVal;
import com.jayqqaa12.val.system.EmailVal;
import com.jfinal.aop.Before;
import com.jfinal.ext.route.ControllerBind;

@ControllerBind(controllerKey = "/system/email")
public class EmailCtrl extends JsonController {

	public void sendValEmail() throws MessagingException {

		User u = ShiroExt.getSessionAttr(Consts.SESSION_EAMIL_USER);
		String email = getPara("email");
		if (u != null) email = u.getStr("email");

		String uuid = UUID.randomUUID().toString();
		EmailService.sendValidatorEmail(email, uuid);

		ShiroExt.setSessionAttr("uuid", uuid);

		renderJson();
	}

	/**
	 * 因为session 超时 前台也无法操作了 所以无需处理错误码
	 */

	@Before(value = { EmailVal.class })
	public void valEmail() {
		User u = ShiroExt.getSessionAttr(Consts.SESSION_EAMIL_USER);

		u.encrypt().saveAndCreateDate();
		ShiroExt.setSessionAttr(Consts.SESSION_EAMIL_USER, null);

		renderJson();

	}

	@Before(value = { EmailExistVal.class })
	public void existEmail() {
		sendJson();
	}

}
