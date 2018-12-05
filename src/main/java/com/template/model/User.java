package com.template.model;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Long id;

    private String username;

    private String password;

    private int gender;

    private Date createtime;

    private Integer isonline;
}