package com.scope.website.controller;

import com.scope.website.model.SignupModel;
import com.scope.website.repository.SignupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Random;

@Controller
public class ResetPasswordController {

    @Autowired
    private SignupRepository signupRepository;

    @Autowired
    private JavaMailSender javaMailSender;
    

    @PostMapping("/resetpassword")
    public String resetPassword(@RequestParam String email, Model model) {
        // Generate temporary password
    	model.addAttribute("email", email);
        String temporaryPassword = generateTemporaryPassword();

        // Update password in the database
        SignupModel user = signupRepository.findByEmail(email);
        if (user != null) {
            user.setPassword(temporaryPassword);
            signupRepository.save(user);
            
            
            sendTemporaryPasswordByEmail(email, temporaryPassword);
            
            
            model.addAttribute("message", "Temporary password has been sent to your email Enter it to change your password.");
            return "createnewpassword";
        } else {
            
            model.addAttribute("message", "Email not found. Please enter a valid email.");
            return "resetpasswordpage";
        }

        
    }
    
    @PostMapping("/createnewpassword")
    public String resetPassword(@RequestParam String email,
                                @RequestParam String temporarypassword,
                                @RequestParam String password,
                                Model model) {
        
        
        SignupModel user = signupRepository.findByEmail(email);

        // Check if the user exists
        if (user != null) {
            // Check if the temporary password matches
            if (user.getPassword().equals(temporarypassword)) {
                // Update password in the database
                user.setPassword(password);
                signupRepository.save(user);
                model.addAttribute("message", "Password updated successfully login to see dashboard.");
                return "login"; // Redirect to login page or any other page
            } else {
            	model.addAttribute("email", email);
                model.addAttribute("message", "Temporary password incorrect. Please re-enter the temporary password.");
                return "createnewpassword"; // Redirect back to create new password page
            }
        } else {
            // Redirect to the signup page
            return "resetpassword";
        }
    }



    



    private String generateTemporaryPassword() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder temporaryPassword = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 8; i++) { // Generate an 8-character temporary password
            temporaryPassword.append(chars.charAt(random.nextInt(chars.length())));
        }
        return temporaryPassword.toString();
    }

    private void sendTemporaryPasswordByEmail(String email, String temporarypassword) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("Temporary Password");
        mailMessage.setText("Your temporary password is: " + temporarypassword);

        try {
            javaMailSender.send(mailMessage);
        } catch (MailException e) {
            // Handle email sending exception
            e.printStackTrace();
        }
    }
}
