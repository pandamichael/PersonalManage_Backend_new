package com.project.personal.exception;


import com.project.personal.enums.CommonCode;


public class RequestDataFormatException extends BaseException {

	/**
	 * 請求參數錯誤[%s]
	 */
	public RequestDataFormatException(String message) {
		super(CommonCode.N2003, message);
	}

}
