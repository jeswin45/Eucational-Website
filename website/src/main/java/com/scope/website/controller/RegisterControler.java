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
public class RegisterControler {
	
	private RegisterRepository registerRepository;
	private SignupRepository signupRepository;
    @Autowired
    public RegisterControler(RegisterRepository registerRepository,SignupRepository signupRepository) {
        this.registerRepository = registerRepository;
        this.signupRepository = signupRepository;
    }


	@GetMapping("/register")
	public String showRegisterPage() {
		return"register";
	}
	
	@PostMapping("/register")
	public String saveRegistration(@ModelAttribute("RegisterModel") RegisterModel registerModel, Model model) {
	    RegisterModel existingUser = registerRepository.findByEmail(registerModel.getEmail());
	    if (existingUser != null) {
	        
	        model.addAttribute("message", "User with this email already exists. Please log in.");
	        return "login";
	    }else {
	    registerRepository.save(registerModel);
	    return "regsuccess";
	}

	}
}
