package com.jayqqaa12.val.system;

import com.jayqqaa12.common.Code;
import com.jayqqaa12.jbase.jfinal.ext.JsonValidator;
import com.jayqqaa12.model.system.User;
import com.jfinal.core.Controller;

public class EmailExistVal extends JsonValidator {

	@Override
	protected void validate(Controller c) {

		if (User.dao.findByEmail(c.getPara("email")) != null) addError(Code.EMAIL_EXIT);
	}

}
