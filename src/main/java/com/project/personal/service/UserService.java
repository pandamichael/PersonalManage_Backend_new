package com.project.personal.service;

import com.project.personal.dto.request.PageRequest;
import com.project.personal.dto.request.user.CreateUserRequest;
import com.project.personal.dto.request.user.UpdateUserRequest;
import com.project.personal.dto.response.ApiPageResponse;
import com.project.personal.dto.response.user.UserResponse;
import com.project.personal.exception.DataNotFindException;
import com.project.personal.exception.RequestDataFormatException;

import java.security.NoSuchAlgorithmException;


public interface UserService {

    ApiPageResponse<UserResponse> getAllByPagination(PageRequest pageRequest);

    void create(CreateUserRequest createUserRequest, String userId) throws RequestDataFormatException, NoSuchAlgorithmException;

    void update(Long id, UpdateUserRequest request, String userId) throws DataNotFindException;

    void delete(Long id) throws DataNotFindException;
}
