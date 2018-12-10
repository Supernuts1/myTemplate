package com.template.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.template.mapper.UserMapper;
import com.template.model.User;
import com.template.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author liyapeng
 * @Date 2018/5/2
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;//这里会报错，但是并不会影响

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    /**
     * 登陆方法
     *
     * @param user
     * @return
     */
    @Override
    public boolean login(User user) {
        return userMapper.login(user);
    }

    /**
     * 按照ID删除用户
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteUserById(long id) {
        return userMapper.deleteUserById(id);
    }

    /**
     * 查询用户是否存在
     *
     * @param username
     * @return
     */
    @Override
    public boolean selectUserExit(String username) {
        String back = userMapper.selectUserExit(username);
        if (!StringUtils.isEmpty(back)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 按照ID去查找单个用户
     *
     * @param id
     * @return
     */
    @Override
    public User getUserById(Integer id) {
        return userMapper.selectUserById(id);
    }

    /**
     * 更新单个用户信息
     * @param user
     * @return
     */
    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    /**
     * 这个方法中用到了我们开头配置依赖的分页插件pagehelper
     * 很简单，只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可；
     * pageNum 开始页数
     * pageSize 每页显示的数据条数
     */
    @Override
    public PageInfo<User> findAllUser(int pageNum, int pageSize, String... args) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(pageNum, pageSize);
        int id = 0;
        String username = null;
        int gender = 0;
        Date beginDate = null;
        Date endDate = null;
        if (!StringUtils.isEmpty(args[0])) {
            id = Integer.parseInt(args[0]);
        }
        if (!StringUtils.isEmpty(args[1])) {
            username = args[1];
        }
        if (!StringUtils.isEmpty(args[2])) {
            gender = Integer.parseInt(args[2]);
        }
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if (!StringUtils.isEmpty(args[3])) {
            try {
                beginDate = format.parse(args[3]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (!StringUtils.isEmpty(args[4])) {
            try {
                endDate = format.parse(args[4]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("username", username);
        params.put("gender", gender);
        params.put("beginDate", beginDate);
        params.put("endDate", endDate);
        List<User> users = userMapper.selectAllUser(params);
        return new PageInfo<>(users);
    }
}
