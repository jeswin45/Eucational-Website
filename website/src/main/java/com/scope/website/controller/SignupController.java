package com.scope.website.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.scope.website.model.RegisterModel;
import com.scope.website.model.SignupModel;
import com.scope.website.repository.RegisterRepository;
import com.scope.website.repository.SignupRepository;

@Controller
public class SignupController {
	
	
	@GetMapping("/signup")
	public String displaySignup(Model m) {
		m.addAttribute("message","WELCOME TO SCOPE INDIA SIGNUP PAGE");
		
		return"signup";
	}

@Autowired
private RegisterRepository registerRepository;
private SignupRepository signupRepository;
    @Autowired
    public SignupController( RegisterRepository registerRepository,SignupRepository signupRepository) {
        this.registerRepository=registerRepository;
        this.signupRepository=signupRepository;
    }



    @PostMapping("/signup")
    public String newUser(@RequestParam String username, String email, String password, Model model) {
        // Check if the email already exists in the registerdetails table
        RegisterModel existingUser = registerRepository.findByEmail(email);
        if (existingUser != null) {
            // If the email exists in registerdetails, check if it exists in signup table
            SignupModel existingSignupUser = signupRepository.findByEmail(email);
            if (existingSignupUser != null) {
                // If the email exists in signup, show error message
                model.addAttribute("message", "User with this email already exists. Please log in.");
                return "login";
            } else {
                // If the email exists in registerdetails but not in signup, show error message to register
                model.addAttribute("message", "password created successfully, please login to access Dashboard");
                SignupModel newUser = new SignupModel();
                newUser.setEmail(email);
                newUser.setUsername(username);
                newUser.setPassword(password);
                signupRepository.save(newUser);
                return "login";
            }
        } else {
            // If the email doesn't exist in registerdetails, show error message to register
            model.addAttribute("message", "User with this email does not exist. Please register.");
            return "register";
        }
    }



    

    }

