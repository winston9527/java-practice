package com.winston.practice.web.servlet;

import java.io.IOException;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 异步 servlet
 * 1 首先写法就不一致   我们需要通过httpRequest.startAsync 来开起异步，并且得到返回的 异步上下文
 * 2 然后将需要执行的逻辑 放到asyncContext异步上下文的 start中 执行，执行我们的代码后  需要显示的告知异步上下文执行结束
 * 3 在@WebServlet 通过asyncSupport=true来开起异步的支持
 */
@WebServlet(asyncSupported = true, urlPatterns = "/servlet/async-servlet")
public class AsyncServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        AsyncContext asyncContext = req.startAsync();
        asyncContext.start(() -> {
            try {
                resp.getWriter().write("OK----");
            } catch (IOException e) {
                e.printStackTrace();
            }
            //触发事件完成
            asyncContext.complete();
        });

    }
}
