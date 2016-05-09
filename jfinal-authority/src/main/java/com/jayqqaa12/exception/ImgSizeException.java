package com.jayqqaa12.exception;

import com.jayqqaa12.common.Code;
import com.jayqqaa12.jbase.jfinal.ext.exception.ErrorCodeException;

public class ImgSizeException extends ErrorCodeException {

	private static final long serialVersionUID = -688373205913613854L;

	public ImgSizeException( ) {
		super(Code.IMG_SIZE_ERROR);
	}


}
