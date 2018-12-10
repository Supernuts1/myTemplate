package com.template.mapper;

import com.template.model.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    int addUser(User record);

    List<User> selectAllUser(Map<String, Object> params);

    boolean login(User record);

    boolean deleteUserById(long id);

    String selectUserExit(String username);

    User selectUserById(long id);

    int updateUser(User user);
}