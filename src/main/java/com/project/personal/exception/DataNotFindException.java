package com.project.personal.exception;

import com.project.personal.enums.CommonCode;

public class DataNotFindException extends BaseException{

    /**
     * 查無資料[%s]
     */
    public DataNotFindException(String message) {
        super(CommonCode.N2001, message);
    }

}
