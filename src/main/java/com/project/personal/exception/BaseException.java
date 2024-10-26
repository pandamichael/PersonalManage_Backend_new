package com.project.personal.exception;

import com.project.personal.enums.CommonCode;
import lombok.Data;

@Data
public class BaseException extends RuntimeException{

    private CommonCode commonCode;

    private Object[] args;

    public BaseException(CommonCode code, Object... args) {
        super();
        this.commonCode = code;
        this.args = args;
    }

    public BaseException setArgs(Object... args) {
        this.args = args;
        return this;
    }
}
