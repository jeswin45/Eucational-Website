package com.scope.website.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String showDashboard() {
        // Logic to show dashboard
        return "dashboard";
    }

    @GetMapping("/logoutuser")
    public String logout(HttpServletResponse response, HttpSession session) {

        session.invalidate();
        Cookie emailCookie = new Cookie("loggedInEmail", "");
        emailCookie.setMaxAge(0);
        emailCookie.setPath("/"); 
        response.addCookie(emailCookie);
        Cookie usernameCookie = new Cookie("loggedInUsername", "");
        usernameCookie.setMaxAge(0);
        usernameCookie.setPath("/"); 
        response.addCookie(usernameCookie);
        return "login";
    }

}
