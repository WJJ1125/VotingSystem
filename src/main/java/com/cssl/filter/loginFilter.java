package com.cssl.filter;


import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Order(1)
public class loginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //获取httpServletRequest对象
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        //获取全局对象
        ServletContext context=request.getServletContext();
        //获取userName
        String userName=request.getParameter("u_name");
        //设置一个用户列表，用于记录用户登录
        if (context.getAttribute("userList")==null){
            //如果第一次登录这个客户端，就创建列表，加入用户
            List<String> userList = new ArrayList<String>();
            userList.add(userName);
            context.setAttribute("userList",userList);
        }else {
            //如果不是第一次登录
            List<String> userList= (List<String>) context.getAttribute("userList");
            //就判断用户列表中是否有此用户
            if (!userList.contains(userName)){
                //如果不包含该用户，就添加进去
                userList.add(userName);
            }
        }


        //获取此客户端的session
        //session列表
        HttpSession session= request.getSession();
        if (context.getAttribute("sessionMap")==null){
            Map<String,HttpSession> sessionMap=new HashMap<String,HttpSession>();
            sessionMap.put(userName,session);
            context.setAttribute("sessionMap",sessionMap);
        }else {
            Map<String,HttpSession> sessionMap= (Map<String, HttpSession>) context.getAttribute("sessionMap");
            if (!sessionMap.containsKey(userName)){
                sessionMap.put(userName,session);
            }
            //测试sessionMap
            System.out.println("======sessionMap======");
            for (Map.Entry<String,HttpSession> entry:sessionMap.entrySet()){
                System.out.println(entry.getKey()+":"+entry.getValue());
            }
            System.out.println("=======================");
        }
        //给session设置username
        session.setAttribute("userName",userName);

        //判断是否为同一个session
        Map<String,HttpSession> sessionMap = (Map<String, HttpSession>) context.getAttribute("sessionMap");
        HttpSession session1=sessionMap.get(userName);
        if (session1==session){
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            HttpServletResponse response=(HttpServletResponse) servletResponse;
            response.sendRedirect("/loginout");
            //用于销毁session
            session.invalidate();
        }
    }
}