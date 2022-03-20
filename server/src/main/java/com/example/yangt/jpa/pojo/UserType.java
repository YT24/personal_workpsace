package com.example.yangt.jpa.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "jpa_user_type")
public class UserType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 30)
    private String userTypeName;

    @Column(length = 200)
    private String descr;

    public UserType() {
    }

    public UserType(Integer id,  String userTypeName, String descr) {
        this.id = id;
        this.userTypeName = userTypeName;
        this.descr = descr;
    }
}
