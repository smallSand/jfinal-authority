package com.jayqqaa12.val.system;

import com.jayqqaa12.common.Code;
import com.jayqqaa12.jbase.jfinal.ext.JsonValidator;
import com.jayqqaa12.jbase.jfinal.ext.spring.SpringUtils;
import com.jayqqaa12.model.system.User;
import com.jfinal.core.Controller;

public class UserValidator extends JsonValidator {

	@Override
	protected void validate(Controller c) {

		if(c.getPara("user.id")==null&&User.dao.findByEmail(c.getPara("user.email"))!=null){
			addError(Code.EMAIL_EXIT);
		}

	}

}
