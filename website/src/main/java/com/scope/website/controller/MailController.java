package com.scope.website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.scope.website.repository.MailRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;



@Controller
public class MailController {

    private  JavaMailSender mailSender;
    private  MailRepository mailRepository;

    @Autowired
    public MailController(JavaMailSender mailSender, MailRepository mailRepository) {
        this.mailSender = mailSender;
        this.mailRepository = mailRepository;
    }

    @PostMapping("/contact")
    public String sendEmail(@RequestParam String name, String email, String course, String phonenumber, Model m) throws MessagingException {
        m.addAttribute("name", name);
        String from = "jeswinnijoe@gmail.com";
        String to = "jeswinnijoe@gmail.com";
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setSubject("Request for Callback");
        helper.setFrom(from);
        helper.setTo(to);
        String htmlContent = "<p>Name: " + name + "</p>" +
                "<p>Email: " + email + "</p>" +
                "<p>Course: " + course + "</p>" +
                "<p>Phone Number: " + phonenumber + "</p>";
        helper.setText(htmlContent, true);
        mailSender.send(message);
        m.addAttribute("message", "We will contact you shortly");
        return "regsuccess";
    }
}
