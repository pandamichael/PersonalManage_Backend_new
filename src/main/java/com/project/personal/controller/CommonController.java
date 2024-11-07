package com.project.personal.controller;

import com.project.personal.dto.request.common.LoginRequest;
import com.project.personal.dto.request.common.LoginResponse;
import com.project.personal.dto.response.ApiResponse;
import com.project.personal.exception.BaseException;
import com.project.personal.service.CommonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.OpenAPI;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Validated

// 表明這個類別是一個 RESTful 控制器，會將回應直接轉換成 JSON 格式。
@RestController
@RequestMapping("/api/common")

// 使用 OpenAPI 的註解，用於生成 API 文件，描述這個控制器的功能。
@Tag(name = "common", description = "共用模組")
public class CommonController extends BaseController {

    // 注入 CommonService，這是一個服務層的類別，包含了登入功能的邏輯處理。
    @Autowired
    private CommonService commonService;

    @PostMapping("/login")

    // OpenAPI 的註解，用於生成 API 文件，描述登入的摘要資訊。
    @Operation(summary = "使用者登入")
    public ApiResponse<LoginResponse> login(@RequestBody @Valid LoginRequest request) throws BaseException {
        LoginResponse loginResponse = commonService.login(request);

        return handleResponse(loginResponse);
    }
}
