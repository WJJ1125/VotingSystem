package com.cssl.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cssl.pojo.Ticket;
import com.cssl.pojo.User;
import com.cssl.service.TicketService;
import com.cssl.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private TicketService ticketService;

    @PostMapping("user/login/ex")
    public String login(HttpSession session, User user, HttpServletResponse response, HttpServletRequest request) throws IOException {
        QueryWrapper<User> o = new QueryWrapper<>();
        Map<String, String> map = new HashMap<>();
        map.put("u_name", user.getU_name());
        map.put("u_password", user.getU_password());
        User user1 = userService.getOne(o.allEq(map));
        if (user1 != null) {
            session.setAttribute("userwjj", user1);
            //设置session  60秒不操作就注销
            session.setMaxInactiveInterval(300);
            return "redirect:/votelist";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping("userZX")
    public String userZX(HttpSession session){
        session.invalidate();
        return "redirect:/login";
    }

    @ResponseBody
    @RequestMapping("finds")
    public User finds(HttpSession session) {
        return (User) session.getAttribute("userwjj");
    }

    @PostMapping("user/regist")
    public String regist(User user,HttpSession session) {
        if (userService.save(user)) {
            session.setAttribute("userwjj", user);
            //设置session  60秒不操作就注销
            session.setMaxInactiveInterval(300);
            return "votelist";
        } else {
            return "regist";
        }
    }

    @ResponseBody
    @GetMapping("user/findUser")
    public String findUser(String uname) {
        QueryWrapper<User> o = new QueryWrapper<>();
        User user = userService.getOne(o.eq("u_name", uname));
        if (user != null) {
            return "1";
        } else {
            return "0";
        }
    }
}
