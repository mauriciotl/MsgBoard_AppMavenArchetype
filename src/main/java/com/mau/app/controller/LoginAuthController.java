package com.mau.app.controller;

import com.mau.app.dao.UserDAO;
import com.mau.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;

@Controller
public class LoginAuthController {

    private static final Logger logger = LogManager.getLogger(LoginAuthController.class);
    private final UserDAO userDAO;

    @Autowired
    public LoginAuthController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping("/login")
    public String login(Model model, HttpSession session) {

        model.addAttribute("title", "Login Page");

        // Check if authLoginForm is already in the model (from flash attributes)
        if (!model.containsAttribute("authLoginForm")) {
            model.addAttribute("authLoginForm", new AuthLoginForm());
        }

        if (session.getAttribute("loggedInUser") != null) {
            // User already logged-in, allow access to app.
            return "redirect:/messages";
        }

        //User not logged-in. It will be sent to the authLoginForm
        logger.info("Displaying login form with authLoginForm: {}", model.getAttribute("authLoginForm"));
        return "/app/AuthLogin/authLogin";
    }

    @PostMapping("/login")
    public String authenticateUser(@ModelAttribute("authLoginForm") AuthLoginForm authLoginForm,
                                   HttpSession session,
                                   RedirectAttributes redirectAttributes) {

        logger.info("Attempting login with authLoginForm: {}", authLoginForm);

        // Verify it's a valid user and save user in session if that applies.

        User user = userDAO.getUserByNameAndPassword(
                authLoginForm.getUsername(), authLoginForm.getPassword());
        if (user != null) {
            //Allow access to app.
            session.setAttribute("loggedInUser", user);
            return "redirect:/messages";
        } else {
            logger.info("Login failed for authLoginForm: {}", authLoginForm);
            redirectAttributes.addFlashAttribute("error", true);
            redirectAttributes.addFlashAttribute("authLoginForm", authLoginForm); // Preserve the existing form data
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    public static class AuthLoginForm {
        private String username;
        private String password;

        // Getters and Setters
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @Override
        public String toString() {
            return "AuthLoginForm{" +
                    "username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }
}