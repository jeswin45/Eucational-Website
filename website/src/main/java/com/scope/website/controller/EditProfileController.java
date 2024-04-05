package com.scope.website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.scope.website.model.RegisterModel; // Assuming your model class is named RegisterModel
import com.scope.website.repository.RegisterRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class EditProfileController {

    private final RegisterRepository registerRepository;

    @Autowired
    public EditProfileController(RegisterRepository registerRepository) {
        this.registerRepository = registerRepository;
    }

    @GetMapping("/editprofile")
    public String displayEditProfile(HttpSession session, Model model) {
        // Retrieve email from session
        String loggedInUserEmail = (String) session.getAttribute("loggedInEmail");

        // Fetch user details from the database based on the email
        RegisterModel user = registerRepository.findByEmail(loggedInUserEmail);
        
        // Add the user object to the model to populate the form fields
        model.addAttribute("user", user);
        
        return "editprofile";
    }

    @PostMapping("/editprofile")
    public String updateProfile(@RequestParam String firstname, @RequestParam String lastname, @RequestParam String email,
                                @RequestParam String course, @RequestParam String dob, @RequestParam String gender,
                                @RequestParam String country, @RequestParam String state, @RequestParam String city,
                                @RequestParam String hobbies, Model model) {
        // Fetch user details from the database based on the email
        RegisterModel user = registerRepository.findByEmail(email);
        

        // Check if user is null (user not found in the database)
        if (user == null) {
            // Handle the case when the user is not found
            model.addAttribute("error", "User not found"); // Add error message to the model
            return "error"; // Redirect to an error page or handle it accordingly
        }

        // Update user details
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setCourse(course);
        user.setDob(dob);
        user.setGender(gender);
        user.setCountry(country);
        user.setState(state);
        user.setCity(city);
        user.setHobbies(hobbies);

        // Save the updated user object
        registerRepository.save(user);

        // Add the updated user object to the model
        model.addAttribute("user", user);

        // Redirect to the edit profile page
        return "editprofile";
    }

}
