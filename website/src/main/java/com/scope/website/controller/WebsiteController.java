package com.scope.website.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebsiteController {

@GetMapping("/home")
public String showHomepage() {
	return"home";
}
@GetMapping("/contact")
public String showContactPage() {
	return"contact";
}


@GetMapping("/newuser")
public String newUserPage() {
	return "newuser";
}
@GetMapping("/newotp")
public String newOtpPage() {
	return "newotp";
}
@GetMapping("/resetpassword")
public String displayResetPassword() {
	return"resetpassword";
}
@GetMapping("/resetpasswordpage")
public String displayResetPasswordPage() {
	return"resetpasswordpage";
}
@GetMapping("/createnewpassword")
public String displayCreatePasswordPage() {
	return"createnewpassword";
}
//@GetMapping("/dashboard")
//public String displayDashboard() {
//	return"dashboard";
//}
}


