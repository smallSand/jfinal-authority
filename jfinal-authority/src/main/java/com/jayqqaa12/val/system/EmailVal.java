package com.jayqqaa12.val.system;

import com.jayqqaa12.common.Code;
import com.jayqqaa12.jbase.jfinal.ext.JsonValidator;
import com.jayqqaa12.jbase.sdk.util.ShiroExt;
import com.jayqqaa12.model.system.User;
import com.jfinal.core.Controller;

public class EmailVal extends JsonValidator {

	@Override
	protected void validate(Controller c) {

		String uuid = ShiroExt.getSessionAttr("uuid");
		if (!c.getPara("val").equals(uuid))addError(Code.EMAIL_VAL_ERROR);  
		
	}

}
