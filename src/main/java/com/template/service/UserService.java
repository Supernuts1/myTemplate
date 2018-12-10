package com.template.service;

import com.github.pagehelper.PageInfo;
import com.template.model.User;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author liyapeng
 * @Date 2018/5/2
 */
public interface UserService {
    int addUser(User user);

    PageInfo<User> findAllUser(int pageNum, int pageSize, String... args);

    boolean login(User user);

    boolean deleteUserById(long id);

    boolean selectUserExit(String username);

    User getUserById(Integer id);

    int updateUser(User user);
}
