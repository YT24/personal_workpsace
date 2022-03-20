package com.example.yangt.mybatis.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class PrMenu implements Serializable {

    private String pr_id;

    private String pr_menuName;

    private String pr_menuPath;

    private String pr_parentMenuId;

    private String pr_path;

    private Integer pr_level;

    private List<PrMenu> childrenList;


}
