package cn.cocowwy.showdbcore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Cocowwy
 * @create 2022-03-03-21:32
 */
@Controller
@RequestMapping
public class RouteController {
    /**
     * 首页
     * @return
     */
    @GetMapping("db")
    public String preview() {
        return "db";
    }

    /**
     * 首页
     * @return
     */
    @GetMapping("crud")
    public String crud() {
        return "crud";
    }
}
