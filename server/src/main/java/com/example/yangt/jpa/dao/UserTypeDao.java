package com.example.yangt.jpa.dao;


import com.example.yangt.jpa.pojo.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTypeDao extends JpaRepository<UserType,Integer> {
}
