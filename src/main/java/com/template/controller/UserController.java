package com.template.controller;

import com.github.pagehelper.PageInfo;
import com.template.model.User;
import com.template.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
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
@Slf4j
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

//    @ResponseBody
//    @RequestMapping(value = "/add", produces = {"application/json;charset=UTF-8"})
//    public int addUser(@RequestBody User user) {
//        return userService.addUser(user);
//    }

    /**
     * 用户列表1
     *
     * @param pageNum
     * @param pageSize
     * @param model
     * @return
     */
    @RequestMapping(value = "/all/{pageNum}/{pageSize}", produces = {"application/json;charset=UTF-8"})
    public String findAllUser(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize, Model model,
                              @RequestParam(value = "id", required = false) String id, @RequestParam(value = "username", required = false) String username,
                              @RequestParam(value = "gender", required = false) String gender, @RequestParam(value = "beginDate", required = false) String beginDate,
                              @RequestParam(value = "endDate", required = false) String endDate) {
        PageInfo<User> pageInfo = userService.findAllUser(pageNum, pageSize, id, username, gender, beginDate, endDate);
        //获得当前页
        model.addAttribute("pageNum", pageInfo.getPageNum());
        //获得一页显示的条数
        model.addAttribute("pageSize", pageInfo.getPageSize());
        //是否是第一页
        model.addAttribute("isFirstPage", pageInfo.isIsFirstPage());
        if (pageInfo.getPages() == 0) {
            model.addAttribute("totalPages", 1);
        } else {
            //获得总页数
            model.addAttribute("totalPages", pageInfo.getPages());
        }
        //是否是最后一页
        model.addAttribute("isLastPage", pageInfo.isIsLastPage());
        model.addAttribute("users", pageInfo.getList());
        return "user/list";
    }

    /**
     * 用户列表2
     *
     * @param pageNum
     * @param pageSize
     * @param model
     * @return
     */
    @RequestMapping(value = "/all2/{pageNum}/{pageSize}", produces = {"application/json;charset=UTF-8"})
    public String findAllUser2(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize, Model model) {
        PageInfo<User> pageInfo = userService.findAllUser(pageNum, pageSize);
        //获得当前页
        model.addAttribute("pageNum", pageInfo.getPageNum());
        //获得一页显示的条数
        model.addAttribute("pageSize", pageInfo.getPageSize());
        //是否是第一页
        model.addAttribute("isFirstPage", pageInfo.isIsFirstPage());
        //获得总页数
        model.addAttribute("totalPages", pageInfo.getPages());
        //是否是最后一页
        model.addAttribute("isLastPage", pageInfo.isIsLastPage());
        model.addAttribute("users", pageInfo.getList());
        return "user/list";
    }

    /**
     * 验证登录
     *
     * @param username
     * @param password
     * @param map
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password,
                        Map<String, Object> map, HttpSession session, Model model) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
//        if (userService.login(user)) {
        if (true) {
            session.setAttribute("username", username);
            PageInfo<User> pageInfo = userService.findAllUser(1, 10, "", "", "", "", "");
            //获得当前页
            model.addAttribute("pageNum", pageInfo.getPageNum());
            //获得一页显示的条数
            model.addAttribute("pageSize", pageInfo.getPageSize());
            //是否是第一页
            model.addAttribute("isFirstPage", pageInfo.isIsFirstPage());
            //获得总页数
            model.addAttribute("totalPages", pageInfo.getPages());
            //是否是最后一页
            model.addAttribute("isLastPage", pageInfo.isIsLastPage());
            model.addAttribute("users", pageInfo.getList());
            return "user/list";
        } else {
            map.put("msg", "用户名密码错误");
            return "index";
        }
    }

    /**
     * 删除用户
     *
     * @param pageNum
     * @param pageSize
     * @param userid
     * @param model
     * @return
     */
    @RequestMapping(value = "/delete/{pageNum}/{pageSize}/{userid}", produces = {"application/json;charset=UTF-8"})
    public String deleteUser(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize, @PathVariable("userid") long userid, Model model) {
        boolean back = userService.deleteUserById(userid);
        PageInfo<User> pageInfo = userService.findAllUser(pageNum, pageSize);
        //获得当前页
        model.addAttribute("pageNum", pageInfo.getPageNum());
        //获得一页显示的条数
        model.addAttribute("pageSize", pageInfo.getPageSize());
        //是否是第一页
        model.addAttribute("isFirstPage", pageInfo.isIsFirstPage());
        //获得总页数
        model.addAttribute("totalPages", pageInfo.getPages());
        //是否是最后一页
        model.addAttribute("isLastPage", pageInfo.isIsLastPage());
        model.addAttribute("users", pageInfo.getList());
        return "user/list";
    }

    @ResponseBody
    @RequestMapping(value = "/add", produces = {"application/json;charset=UTF-8"})
    public List<String> addUser(@RequestParam("username") String username, @RequestParam("password") String password,
                                @RequestParam("gender") String gender) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setGender(Integer.parseInt(gender));
        user.setCreatetime(new Date());
        int back = userService.addUser(user);
        String msg = "";
        if (back > 0) {
            msg = "新增用户成功！";
        }
        List<String> list = new ArrayList<String>();
        list.add(msg);
        return list;
    }

    @RequestMapping(value = "/addUserPage", produces = {"application/json;charset=UTF-8"})
    public String addUserPage() {
        return "user/add";
    }

    /**
     * 判断用户名是否重复
     *
     * @param username
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/userExis", produces = {"application/json;charset=UTF-8"})
    public List<String> userExis(@RequestParam("username") String username) {
        boolean back = userService.selectUserExit(username);
        String msg = "";
        if (back) {
            msg = "用户名" + username + "已存在！";
        }
        List<String> list = new ArrayList<String>();
        list.add(msg);
        return list;
    }

    /**
     * 展示修改页面，反显用户数据
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/showUpdatePage/{userid}", produces = {"application/json;charset=UTF-8"})
    public User showUpdatePage(@PathVariable("userid") Integer id) {
        User user = userService.getUserById(id);
        return user;
    }

    /**
     * 更新用户
     *
     * @param username
     * @param password
     * @param gender
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update", produces = {"application/json;charset=UTF-8"})
    public List<String> updateUser(@RequestParam("id") String id, @RequestParam("username") String username, @RequestParam("password") String password,
                                   @RequestParam("gender") String gender) {
        User user = new User();
        user.setId(Long.parseLong(id));
        user.setUsername(username);
        user.setPassword(password);
        user.setGender(Integer.parseInt(gender));
        user.setCreatetime(new Date());
        int back = userService.updateUser(user);
        String msg = "";
        if (back > 0) {
            msg = "修改用户成功！";
        }
        List<String> list = new ArrayList<String>();
        list.add(msg);
        return list;
    }
}
