package com.scope.website.controller;

import com.scope.website.model.RegisterModel;
import com.scope.website.model.SignupModel;
import com.scope.website.repository.RegisterRepository;
import com.scope.website.repository.SignupRepository;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private SignupRepository signupRepository;
    private RegisterRepository registerRepository;

    @Autowired
    public LoginController(SignupRepository signupRepository, RegisterRepository registerRepository) {
        this.signupRepository = signupRepository;
        this.registerRepository = registerRepository;
    }

    @GetMapping("/login")
    public String showLoginPage(HttpServletRequest request, Model model) {
        // Check if the user has a remember-me cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("loggedInEmail")) {
                    // If the remember-me cookie exists, create a session and set the loggedInEmail attribute
                    HttpSession session = request.getSession(true);
                    session.setAttribute("loggedInEmail", cookie.getValue());
                    String email = cookie.getValue();
                    SignupModel loggedInUser = signupRepository.findByEmail(email);
                    if (loggedInUser != null) {
                        String username = loggedInUser.getUsername();
                        model.addAttribute("username", username);
                        return "dashboard";
                    }
                }
            }
        }

        // If the user does not have a remember-me cookie, show the login page
        return "login";
    }



    @PostMapping("/login")
    public String showLoginForm(@RequestParam String email, String password, @RequestParam(required = false) boolean remember, Model model, HttpServletRequest request, HttpServletResponse response) {
        // Check if the email and password match with the credentials in the signupdetails table
        SignupModel user = signupRepository.findByEmailAndPassword(email, password);

        if (user != null) {
            // Create a session and store the email in it
            HttpSession session = request.getSession(true);
            session.setAttribute("loggedInEmail", email);

            // If remember checkbox is checked, create a cookie to keep the user logged in
            if (remember) {
                Cookie cookie = new Cookie("loggedInEmail", email);
                cookie.setMaxAge(7 * 24 * 60 * 60); // Cookie lasts for 7 days
                cookie.setPath("/"); // Set cookie path
                response.addCookie(cookie);
            } else {
                // If remember checkbox is not checked, delete any existing remember-me cookie
                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("loggedInEmail")) {
                            cookie.setMaxAge(0); // Set cookie to expire immediately
                            cookie.setPath("/"); // Set cookie path
                            response.addCookie(cookie);
                            break;
                        }
                    }
                }
            }

            // Add username to the model
            String username = user.getUsername();
            String emailid = user.getEmail();
            model.addAttribute("username", username);
            model.addAttribute("emailid", emailid);

            // Redirect to the dashboard
            return "dashboard";
        }

        // If credentials are invalid, show the login form with an error message
        model.addAttribute("message", "Invalid email or password. Please Sign-up.");
        return "signup";
    }
    


}
