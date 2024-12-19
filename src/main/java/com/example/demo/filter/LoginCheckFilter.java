package com.example.demo.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCheckFilter implements Filter {

    // 인증이 필요없는 요청 경로
    private static final String[] whitelist = {"/api/v1/login", "/api/v1/users"}; // 회원가입, 로그인 제외

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        if(isLoginCheckPath(requestURI)) {
            HttpSession session = httpRequest.getSession(false);
            if(session == null || session.getAttribute("userId") == null) {
                // 로그인하지 않은 사용자
                ((jakarta.servlet.http.HttpServletResponse)response).sendError(401, "Unauthorized");
                return;
            }
        }

        chain.doFilter(request, response);
    }

    private boolean isLoginCheckPath(String requestURI) {
        for(String path : whitelist) {
            if(requestURI.startsWith(path)) {
                return false;
            }
        }
        return true;
    }
}
