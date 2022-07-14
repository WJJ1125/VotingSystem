package com.cssl.interceptor;

import com.cssl.pojo.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getRequestURI();
        System.out.println("AuthorityInterceptor preHandle path:" + path);
        HttpSession session = request.getSession();
        User uvo = (User) session.getAttribute("userwjj");
        System.out.println("AuthorityInterceptor preHandle uvo:" + uvo);
        if (path.endsWith("login") || path.endsWith("regist") || path.endsWith("user/login/ex") || path.endsWith("user/regist") || path.endsWith("user/findUser") || path.endsWith("loginout")) {
            return true;
        } else {
            /*if(uvo == null) {
                response.sendRedirect("index.jsp");
                System.out.println("用户未登录，除了登录不能进行任何操作");
                return false;
            }else {  //已有用户登录,可进行任何操作，上传功能另验证
                if (path.endsWith("upload")) {  //如果在进行上传操作，默认false,验证是否为admin用户在返回true
                    if (uvo.getUsername().equals("admin")) {
                        return true;
                    }else {
                        System.out.println("只有admin用户可以上传操作");
                        return false;
                    }
                }
                return true;
            }*/
            if (uvo == null) {
                return false;
            } else {
                return true;
            }

        }
    }
}
