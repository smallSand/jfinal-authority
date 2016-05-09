package com.jayqqaa12.exception;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;

import com.jayqqaa12.common.Code;
import com.jayqqaa12.jbase.jfinal.ext.exception.JsonExceptionInterceptor;
import com.jayqqaa12.jbase.jfinal.ext.exception.NullParamException;
import com.jfinal.aop.Invocation;

/***
 * 
 * 使用 api 接口的时候用
 * 
 * @author 12
 *
 */
public class MyJsonExceptionIntercepter extends JsonExceptionInterceptor {

	@Override
	protected void handleError(Invocation inv, Exception exception) {

		try {
			throw exception;
		} catch (UnknownAccountException e) {
			addError(inv, Code.USER_NOT_EXIT);
			e.printStackTrace();
		} catch (IncorrectCredentialsException e) {
			addError(inv, Code.USER_PWD_ERROR);
			e.printStackTrace();
		} catch (LockedAccountException e) {
			addError(inv, Code.USER_FRRZE);
			e.printStackTrace();
		} catch (ExcessiveAttemptsException e) {
			addError(inv, Code.USER_ATTEMPT);
			e.printStackTrace();
		} catch (AuthenticationException e) {
			addError(inv, Code.USER_AUTH);
			e.printStackTrace();
		} catch (Exception e) {
			addError(inv, Code.SERVER_ERROR);
			e.printStackTrace();
		}

	}

}
