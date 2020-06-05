package com.winston.practice.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet ;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * spring boot  整合原生servlet
 * 1 实现HttpServlet接口
 * 2 使用WebServlet 注解 指定URL pattern
 * 3 在配置类上面添加 @ServletComponentScan(basePackages = "com.winston.practice.web.servlet") 来扫描servlet
 */
@WebServlet(urlPatterns = "/servlet/my-servlet")
public class MyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("OK");
    }
}
