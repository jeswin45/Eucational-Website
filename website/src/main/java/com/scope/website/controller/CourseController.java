package com.scope.website.controller;

import com.scope.website.model.CoursesModel;
import com.scope.website.model.SignupModel;
import com.scope.website.repository.CourseRepository;
import com.scope.website.repository.SignupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CourseController {

    private final SignupRepository signupRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public CourseController(SignupRepository signupRepository, CourseRepository courseRepository) {
        this.signupRepository = signupRepository;
        this.courseRepository = courseRepository;
    }

    // Handler method to display the HTML page with available courses
    @GetMapping("/courses")
    public String displayCourses(Model model) {
        // Fetch available courses from the database
        List<CoursesModel> courses = courseRepository.findAll();

        // Add the list of courses to the model
        model.addAttribute("courses", courses);

        return "courses"; 
    }
}



