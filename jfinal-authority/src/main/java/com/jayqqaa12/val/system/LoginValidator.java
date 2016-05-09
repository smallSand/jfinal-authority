package com.jayqqaa12.val.system;

import java.util.concurrent.atomic.AtomicInteger;

import com.jayqqaa12.common.Code;
import com.jayqqaa12.jbase.jfinal.ext.JsonValidator;
import com.jayqqaa12.jbase.util.RSA;
import com.jayqqaa12.shiro.ShiroCache;
import com.jfinal.core.Controller;

public class LoginValidator extends JsonValidator
{

	@Override
	protected void validate(Controller c)
	{
		validateRequiredString("key",Code.NULL);
		
		String key = c.getPara("key");
		String [] result = RSA.decryptUsernameAndPwd(key);

		
		AtomicInteger retryCount = (AtomicInteger) ShiroCache.getCacheManager().getCache("passwordRetryCache")
				.get(result[0]);
		// 错误2次密码 需要验证 验证码
		if (retryCount != null && retryCount.incrementAndGet() > 1) {
			if (!c.validateCaptcha("code")) addError(Code.CAPTCHA_ERROR);;
		}

	}

 

}
