package com.jayqqaa12.exception;

import com.jayqqaa12.common.Code;
import com.jayqqaa12.jbase.jfinal.ext.exception.ErrorCodeException;

public class FileTypeException extends  ErrorCodeException {

	private static final long serialVersionUID = 6385974840380305315L;

	public FileTypeException( ) {
		super(Code.FILE_TYPE_ERROR);
	}

}
