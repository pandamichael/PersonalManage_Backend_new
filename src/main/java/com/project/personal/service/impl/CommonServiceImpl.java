package com.project.personal.service.impl;

import com.project.personal.dto.request.common.LoginRequest;
import com.project.personal.dto.request.common.LoginResponse;
import com.project.personal.enums.CommonCode;
import com.project.personal.exception.BaseException;
import com.project.personal.mapper.UserMapper;
import com.project.personal.model.UserModel;
import com.project.personal.service.CommonService;
import com.project.personal.util.PasswordUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private HttpSession session;

    @Override
    public LoginResponse login(LoginRequest request) throws BaseException {
        String email = request.getEmail();
        UserModel model = userMapper.selectByEmail(email);
        if(model == null) {
            throw new BaseException(CommonCode.N3001);
        }

        validatePasswordCorrect(request.getPassword(), model.getPassword());

        setUserSession(session, model);

        return setLoginResponse(model);
    }

    private void validatePasswordCorrect(String rawPassword, String encodedPassword) throws BaseException {
        boolean isMatch = PasswordUtil.isPasswordMatch(rawPassword, encodedPassword);
        if(!isMatch) {
            throw new BaseException(CommonCode.N3002);
        }
    }

    public static void setUserSession(HttpSession session, UserModel userModel) {
        session.setAttribute("userId", userModel.getId());
        session.setAttribute("userEmail", userModel.getEmail());
        session.setAttribute("userName", userModel.getName());
    }

    private LoginResponse setLoginResponse(UserModel model) {
        LoginResponse response = new LoginResponse();
        response.setId(model.getId());
        response.setEmail(model.getEmail());
        response.setName(model.getName());

        return response;
    }
}
