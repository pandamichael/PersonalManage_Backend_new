package com.project.personal.model;

import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
public class UserModel {

    private Long id;

    private String email;

    private String name;

    private String gender;

    private String phone;

    private String password;

    private Date birthday;

    private String createUser;

    private Timestamp createDate;

    private String updateUser;

    private Timestamp updateDate;
}
