package com.example.yangt.jpa.service;


import com.example.yangt.jpa.pojo.UserType;

import java.util.List;

public interface UserTypeService {

    void addUserType(UserType userType);

    List<UserType> findUserType(UserType userType);

    UserType findUserType(Integer id);

    public void updateUserType(UserType userType);

    public void deleteUserType(Integer id);
}
