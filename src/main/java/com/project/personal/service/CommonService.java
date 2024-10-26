package com.project.personal.service;

import com.project.personal.dto.request.common.LoginRequest;
import com.project.personal.dto.request.common.LoginResponse;
import com.project.personal.exception.BaseException;

public interface CommonService {

    LoginResponse login(LoginRequest loginRequest) throws BaseException;
}
