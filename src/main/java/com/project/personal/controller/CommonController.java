package com.project.personal.controller;

import com.project.personal.dto.request.common.LoginRequest;
import com.project.personal.dto.request.common.LoginResponse;
import com.project.personal.dto.response.ApiResponse;
import com.project.personal.exception.BaseException;
import com.project.personal.service.CommonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Validated
@RestController
@RequestMapping("/api/common")
@Tag(name = "common", description = "共用模組")
public class CommonController extends BaseController {

    @Autowired
    private CommonService commonService;

    @PostMapping("/login")
    @Operation(summary = "使用者登入")
    public ApiResponse<LoginResponse> login(@RequestBody @Valid LoginRequest request) throws BaseException {
        LoginResponse loginResponse = commonService.login(request);

        return handleResponse(loginResponse);
    }
}
