package com.mau.test.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.time.Instant;
import java.util.Map;

@Controller
public class HomeController
{
    @RequestMapping("/mvc") //It's available at path: /
    public View home(Map<String, Object> model)
    {
        model.put("dashboardUrl", "dashboard");
        return new RedirectView("/mvc/{dashboardUrl}", true);
    }

    @RequestMapping(value = "/mvc/dashboard", method = RequestMethod.GET)
    public String dashboard(Map<String, Object> model)
    {
        model.put("text", "This is a model attribute.");
        model.put("date", Instant.now());

        return "test/home/dashboard"; //The JSP path location (implicit  dashboard.jsp)
    }

    @RequestMapping(value = "/test/user/home", method = RequestMethod.GET)
    @ModelAttribute("currentUser")
    public User userHome()
    {
        User user = new User();
        user.setUserId(1234987234L);
        user.setUsername("adam");
        user.setName("Adam Johnson");
        return user;
    }

    @RequestMapping(value = "/test/user/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public User getUser(@PathVariable("userId") long userId)
    {
        User user = new User();
        user.setUserId(userId);
        user.setUsername("john");
        user.setName("John Smith");
        return user;
    }
}
