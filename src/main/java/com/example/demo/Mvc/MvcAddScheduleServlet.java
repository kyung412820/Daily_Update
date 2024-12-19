package com.example.demo.Mvc;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "MvcAddScheduleServlet",
        urlPatterns = "/mvc/posts/add-schedule"
)
public class MvcAddScheduleServlet extends HttpServlet {

    @Override
    protected void service(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        // webapp/WEB-INF/views -> WEB-INF 내부의 파일은 외부에서 호출할 수 없다.
        String path = "/WEB-INF/views/add-schedule.jsp";
        // 이때 Controller에서 View로 이동
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        // forward : Servlet에서 다른 Servlet이나 jsp 호출
        dispatcher.forward(request, response);

    }
}