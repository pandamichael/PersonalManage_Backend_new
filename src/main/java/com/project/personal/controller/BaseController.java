package com.project.personal.controller;

import com.project.personal.dto.response.ApiPageResponse;
import com.project.personal.dto.response.ApiResponse;
import com.project.personal.enums.CommonCode;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Data
public class BaseController {

    protected String userId;

    protected ApiResponse handleResponse(Object data) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(CommonCode.SUCCESS.getCode());
        apiResponse.setMessage(CommonCode.SUCCESS.getMessage());
        apiResponse.setResult(Objects.requireNonNullElseGet(data, HashMap::new));

        return apiResponse;
    }

    public ApiResponse handlePageResponse(List data, int totalCount) {
        ApiPageResponse pageResponse = new ApiPageResponse();
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(CommonCode.SUCCESS.getCode());
        apiResponse.setMessage(CommonCode.SUCCESS.getMessage());
        pageResponse.setData(data);
        pageResponse.setCount(totalCount);

        apiResponse.setResult(pageResponse);

        return apiResponse;
    }

}
