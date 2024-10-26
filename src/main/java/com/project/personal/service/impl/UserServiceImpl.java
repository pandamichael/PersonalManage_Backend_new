package com.project.personal.service.impl;

import com.project.personal.dto.request.PageRequest;
import com.project.personal.dto.request.user.CreateUserRequest;
import com.project.personal.dto.request.user.UpdateUserRequest;
import com.project.personal.dto.response.ApiPageResponse;
import com.project.personal.dto.response.user.UserResponse;
import com.project.personal.enums.CommonCode;
import com.project.personal.exception.BaseException;
import com.project.personal.exception.DataNotFindException;
import com.project.personal.exception.RequestDataFormatException;
import com.project.personal.mapper.UserMapper;
import com.project.personal.model.UserModel;
import com.project.personal.service.BaseService;
import com.project.personal.service.UserService;
import com.project.personal.util.PasswordUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import static com.project.personal.util.PasswordUtil.validatePasswordFormat;

@Service
public class UserServiceImpl extends BaseService implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ApiPageResponse<UserResponse> getAllByPagination(PageRequest request) {
        List<UserModel> modelList = userMapper.selectAllByPagination(request.getOffset(), request.getLimit());
        int count = userMapper.selectAllCount();

        List<UserResponse> response = convertObjectList(modelList, UserResponse.class);

        return setPageResponse(response, count);
    }

    @Override
    @Transactional
    public void create(CreateUserRequest request, String userId) throws RequestDataFormatException {
        validateEmailExist(request.getEmail());
        validatePasswordFormat(request.getPassword());

        UserModel model = convertToUserModelForCreate(request, userId);

        userMapper.insert(model);
    }

    @Override
    @Transactional
    public void update(Long id, UpdateUserRequest request, String userId) throws DataNotFindException {
        UserModel model = userMapper.selectByPrimaryKey(id);
        if(model == null) {
            throw new DataNotFindException(String.valueOf(id));
        }

        convertToUserModelForUpdate(request, model, userId);

        userMapper.updateByPrimaryKey(model);
    }

    @Override
    @Transactional
    public void delete(Long id) throws DataNotFindException {
        UserModel model = userMapper.selectByPrimaryKey(id);
        if(model == null) {
            throw new DataNotFindException(String.valueOf(id));
        }

        userMapper.deleteByPrimaryKey(id);
    }

    private void validateEmailExist(String email) throws RequestDataFormatException {
        UserModel model = userMapper.selectByEmail(email);
        if(model != null){
            throw new BaseException(CommonCode.N3004);
        }
    }

    private UserModel convertToUserModelForCreate(CreateUserRequest request, String userId) {
        UserModel model = convertSingleObject(request, UserModel.class);
        String hashPassword = PasswordUtil.hashPassword(request.getPassword());
        model.setPassword(hashPassword);
        model.setBirthday(new Date(request.getBirthday()));
        model.setCreateUser(userId);
        model.setCreateDate(new Timestamp(System.currentTimeMillis()));
        model.setUpdateDate(new Timestamp(System.currentTimeMillis()));
        model.setUpdateUser(userId);

        return model;
    }

    private void convertToUserModelForUpdate(UpdateUserRequest request, UserModel model, String userId) {
        modelMapper.map(request, model);
        model.setUpdateUser(userId);
        model.setUpdateDate(new Timestamp(System.currentTimeMillis()));
    }

}
