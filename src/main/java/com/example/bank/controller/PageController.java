package com.example.bank.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/")
    public String home(HttpServletRequest request, Model model, @CookieValue("isLogin") boolean isLogin) {
        boolean isLoginSession = request.getSession().getAttribute("isLogin") == null;

        if (!isLogin && isLoginSession) {
            return "login";
        }
        return "home";
    }
}
