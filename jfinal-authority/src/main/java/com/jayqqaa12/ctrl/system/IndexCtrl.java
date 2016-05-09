package com.jayqqaa12.ctrl.system;

import java.security.interfaces.RSAPublicKey;

import org.apache.commons.codec.binary.Hex;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import com.jayqqaa12.common.Code;
import com.jayqqaa12.common.Consts;
import com.jayqqaa12.jbase.jfinal.ext.ctrl.JsonController;
import com.jayqqaa12.jbase.sdk.util.ShiroExt;
import com.jayqqaa12.jbase.util.RSA;
import com.jayqqaa12.model.system.Log;
import com.jayqqaa12.model.system.User;
import com.jayqqaa12.val.system.LoginValidator;
import com.jfinal.aop.Before;
import com.jfinal.ext.route.ControllerBind;

/***
 * 
 * 月落斜阳 灯火阑珊
 * 
 * @author 12
 * 
 */
@ControllerBind(controllerKey = "/")
public class IndexCtrl extends JsonController {

	private static com.jfinal.log.Log logger = com.jfinal.log.Log.getLog(IndexCtrl.class);

	public void rsa() {

		RSAPublicKey publicKey = RSA.getDefaultPublicKey();
		String modulus = new String(Hex.encodeHex(publicKey.getModulus().toByteArray()));
		String exponent = new String(Hex.encodeHex(publicKey.getPublicExponent().toByteArray()));

		setJsonData("modulus", modulus);
		setJsonData("exponent", exponent);
		setJsonData("email", getCookie("email"));

		if (SecurityUtils.getSubject().isAuthenticated()) {

			setJsonData(Consts.SESSION_USER, (User) ShiroExt.getSessionAttr(Consts.SESSION_USER));
			setJsonCode(Code.LOGING);
		}
		sendJson();
	}

	public void jump() {
		Log.dao.insert(this, Log.EVENT_VISIT);
		render("jump.html");
	}

	@Before({ LoginValidator.class })
	public void login() throws Exception  {
		
		String[] result = RSA.decryptUsernameAndPwd(getPara("key"));

		UsernamePasswordToken token = new UsernamePasswordToken(result[0], result[1]);
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			token.setRememberMe(false);
			subject.login(token);
			User u = User.dao.findByEmail(result[0]);
			subject.getSession(true).setAttribute(Consts.SESSION_USER, u);
		}
		if (getParaToBoolean("remember")) {
			setCookie("email", result[0], 60 * 60 * 24 * 7);
		} else removeCookie("email");

		Log.dao.insert(this, Log.EVENT_LOGIN);

		sendJson();
	}

	public void logout() {
		try {
			Subject subject = SecurityUtils.getSubject();
			subject.logout();
			sendJson();

		} catch (AuthenticationException e) {
			logger.error(e.getMessage());
		}
	}

	public void captcha() {

		renderCaptcha();

	}

}
