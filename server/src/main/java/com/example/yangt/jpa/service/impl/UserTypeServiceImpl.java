package com.example.yangt.jpa.service.impl;

import com.example.yangt.jpa.dao.UserTypeDao;
import com.example.yangt.jpa.pojo.UserType;
import com.example.yangt.jpa.service.UserTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserTypeServiceImpl implements UserTypeService {

    @Autowired
    private UserTypeDao userTypeDao;

    @Override
    public void addUserType(UserType userType) {

        this.userTypeDao.save(userType);

    }

    @Override
    public List<UserType> findUserType(UserType userType) {
        Example<UserType> example = Example.of(userType);
        return userTypeDao.findAll(example);
    }

    @Override
    public UserType findUserType(Integer id) {
        Optional<UserType> optionalUserType = userTypeDao.findById(id);
        if(optionalUserType.isPresent()){
            return optionalUserType.get();
        }
        return null;
    }

    @Override
    public void updateUserType(UserType UserType) {

    }

    @Override
    public void deleteUserType(Integer id) {

    }
}
