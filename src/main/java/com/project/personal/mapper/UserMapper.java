package com.project.personal.mapper;

import com.project.personal.model.UserModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<UserModel> selectAllByPagination(Integer offset, Integer limit);

    int selectAllCount();

    UserModel selectByEmail(String email);

    void insert(UserModel userModel);

    UserModel selectByPrimaryKey(Long id);

    void updateByPrimaryKey(UserModel userModel);

    void deleteByPrimaryKey(Long id);

}
