package com.template.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 * 首页
 * </p>
 *
 * @author liyapeng
 * @Date 2018/5/3
 */
@Controller
public class IndexController {
    @RequestMapping(value = {"/index", "/", "index.html"})
    public String index() {
        return "index";
    }
}
