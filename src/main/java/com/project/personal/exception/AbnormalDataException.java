package com.project.personal.exception;

import com.project.personal.enums.CommonCode;

public class AbnormalDataException extends BaseException{

    /**
     * 資料異常[%s]
     */
    public AbnormalDataException(String message) {
        super(CommonCode.N2002, message);
    }

}
