package com.in.pizza.demo.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.in.pizza.demo.model.Login;

import jakarta.servlet.http.HttpServletResponse;



@Controller
public class LoginController {
	String userType="";

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("loginForm", new Login());
        return "login";
    }

    @PostMapping("/login")
    public String performLogin(@ModelAttribute(name = "loginForm") Login login, Model model,HttpServletResponse response) throws IOException {
        String uname = login.getUsername();
        String pass = login.getPassword();
        
        if ("Admin".equals(uname) && "Admin@123".equals(pass) && userType.equals("manager")) {
            model.addAttribute("uname", uname);
            model.addAttribute("pass", pass);
            return "redirect:/welcomemanager";
            }
        else if(userType.equals("user")) {
        	  model.addAttribute("uname", uname);
              model.addAttribute("pass", pass);
              return "redirect:/welcome";
        }
        
        model.addAttribute("error", "Incorrect Username & Password");
        return "login";
    }

    @GetMapping("/select-usertype")
    public String showUserTypeSelectionPage(@RequestParam(value = "usertype", required = false) String userType, Model model) {
        System.out.println("Login Controller");
        this.userType=userType;
        if ("manager".equals(userType)) {
            model.addAttribute("userType", userType);
            return "redirect:/login?usertype=" + userType;
        }
        else if("user".equals(userType)) {
        	return "redirect:/login?usertype=" + userType;
        }
        else {
            return "user-type-selection";
        }
    }
    @GetMapping("/welcome")
    public String showWelcomePage() {
        return "welcome"; 
    }
    
    @GetMapping("/welcomemanager")
    public String redirectToAngular(HttpServletResponse response) throws IOException {
    	return "welcomemanager";
    }
   
}

