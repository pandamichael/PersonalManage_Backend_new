package com.project.personal.exception;

import com.project.personal.enums.CommonCode;

public class RequiredParameterMissingException extends BaseException {

	/**
	 * 缺少必填參數[%s]
	 */
	public RequiredParameterMissingException(String message) {
		super(CommonCode.N2004, message);
	}

}
