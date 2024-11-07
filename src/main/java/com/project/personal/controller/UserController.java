package com.project.personal.controller;

import com.project.personal.dto.request.user.UpdateUserRequest;
import com.project.personal.dto.request.PageRequest;
import com.project.personal.dto.request.user.CreateUserRequest;
import com.project.personal.dto.response.ApiPageResponse;
import com.project.personal.dto.response.ApiResponse;
import com.project.personal.dto.response.user.UserResponse;
import com.project.personal.exception.BaseException;
import com.project.personal.exception.RequestDataFormatException;
import com.project.personal.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@Validated
@RequestMapping("/api/user")
@RestController
@Tag(name = "user", description = "使用者模組")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @GetMapping
    @Operation(summary = "根據分頁取得使用者列表")
    public ApiResponse<UserResponse> getAllByPagination(PageRequest request) {

        // 呼叫UserService分頁查詢
        ApiPageResponse<UserResponse> response = userService.getAllByPagination(request);

        return handlePageResponse(response.getData(), response.getCount());
    }

    @PostMapping
    @Operation(summary = "新增使用者")
    public ApiResponse<Object> create(@RequestBody @Valid CreateUserRequest request) throws NoSuchAlgorithmException, RequestDataFormatException {
        userService.create(request, userId);

        return handleResponse(null);
    }

    @PutMapping("/{id}")
    @Operation(summary = "修改使用者")
    public ApiResponse<Object> update(@PathVariable Long id, @RequestBody @Valid UpdateUserRequest request) throws BaseException {
        userService.update(id, request, userId);

        return handleResponse(null);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "刪除使用者")
    public ApiResponse<Object> delete(@PathVariable Long id) throws BaseException {
        userService.delete(id);

        return handleResponse(null);
    }
}
