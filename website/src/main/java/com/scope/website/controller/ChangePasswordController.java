package com.scope.website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.scope.website.model.SignupModel;
import com.scope.website.repository.SignupRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class ChangePasswordController {
    
    private SignupRepository signupRepository;
    
    @Autowired
    public ChangePasswordController(SignupRepository signupRepository) {
        this.signupRepository = signupRepository;
    }
    
    @GetMapping("/changepassword")
    public String showChangePassword(Model model) {
        return "changepassword";
    }
    
    @PostMapping("/changepassword")
    public String changePassword(@RequestParam String password, @RequestParam String confirmpassword, HttpSession session, Model model) {
        // Check if newPassword and confirmPassword match
        if (password.equals(confirmpassword)) {
            // Get the user's email from the session
            String userEmail = (String) session.getAttribute("loggedInEmail");
            
            if (userEmail != null) {
                // Retrieve the user from the database based on the email
                SignupModel user = signupRepository.findByEmail(userEmail);
                
                if (user != null) {
                    // Update the user's password
                    user.setPassword(password);
                    signupRepository.save(user); // Save the updated user details
                    
                    // Redirect to a success page or display a success message
                    model.addAttribute("message", "Password changed successfully");
                    return "dashboard";
                } else {
                    // Handle case where user is not found
                    model.addAttribute("error", "User not found");
                    return "errorPage";
                }
            } else {
                // Handle case where user email is not found in the session
                model.addAttribute("error", "User email not found in session");
                return "errorPage";
            }
        } else {
            // Handle case where newPassword and confirmPassword don't match
            model.addAttribute("error", "New password and confirm password do not match");
            return "changepassword";
        }
    }
}
