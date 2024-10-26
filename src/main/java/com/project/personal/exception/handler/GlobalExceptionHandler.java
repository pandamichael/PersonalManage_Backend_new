package com.project.personal.exception.handler;

import com.project.personal.dto.response.ApiResponse;
import com.project.personal.enums.CommonCode;
import com.project.personal.exception.BaseException;
import com.project.personal.exception.DataNotFindException;
import com.project.personal.exception.helper.ValidationExceptionHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ApiResponse<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ApiResponse<Object> response = new ApiResponse<>();

        return ValidationExceptionHelper.methodArgumentNotValidExceptionHandle(response, e);
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ApiResponse<Object>> handleBaseException(BaseException e) {
        log.error(e.getMessage(), e);
        ApiResponse<Object> apiResponse = new ApiResponse<>();

        apiResponse.setCode(e.getCommonCode().getCode());
        apiResponse.setMessage(formatMessage(e.getCommonCode().getMessage(), e.getArgs()));
        apiResponse.setResult(new HashMap<String, String>());

        HttpStatus status;

        if (e instanceof DataNotFindException) {
            status = HttpStatus.NOT_FOUND;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(apiResponse, status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleException(Exception e) {
        log.error(e.getMessage(), e);
        ApiResponse<Object> apiResponse = new ApiResponse<>();

        apiResponse.setCode(CommonCode.ERROR.getCode());
        apiResponse.setMessage(CommonCode.ERROR.getMessage());
        apiResponse.setResult(new HashMap<String, String>());

        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String formatMessage(String message, Object[] args) {
        if (message != null && args != null) {
            message = String.format(message, args);
        }
        return message;
    }
}
