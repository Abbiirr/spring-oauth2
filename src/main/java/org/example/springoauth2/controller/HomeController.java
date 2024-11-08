package org.example.springoauth2.controller;

import lombok.RequiredArgsConstructor;
import org.example.springoauth2.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final UserService userService;

    @GetMapping("/home")
    public String home(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("message", "Welcome to Thymeleaf with Spring MVC!");
        model.addAttribute("username", user.getUsername()); // Add username to the model
        return "home";
    }


    @GetMapping("/signup")
    public String signupForm() {
        return "signup";
    }

    @PostMapping("/signup")
    public String registerUser(@RequestParam String username,
                               @RequestParam String password,
                               RedirectAttributes redirectAttributes) {
        userService.registerUser(username, password);
        redirectAttributes.addFlashAttribute("message", "User registered successfully!");
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

}
