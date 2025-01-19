package com.mau.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//import javax.inject.Inject;

@Controller
public class HelloController {

    private static final Logger logger = LogManager.getLogger(HelloController.class);

    private GreetingService greetingService;

    @ResponseBody
    @RequestMapping("/")
    public String helloWorld() {
        logger.info("Handling request to /");
        String message = "Hello, World! from Spring controller!!";
        logger.debug("Returning message: {}", message);
        return message;
    }

    @ResponseBody
    @RequestMapping(value = "/custom", params = {"name"})
    public String helloName(@RequestParam("name") String name) {
        logger.info("Handling request to /custom with name: {}", name);
        try {
            String greeting = this.greetingService.getGreeting(name);
            logger.debug("Greeting generated: {}", greeting);
            return greeting;
        } catch (Exception e) {
            logger.error("Error generating greeting for name: {}", name, e);
            return "Error generating greeting."; // Or handle the error differently
        }
    }

//    @Inject
//    public void setGreetingService(GreetingService greetingService) {
//        logger.info("Injecting GreetingService");
//        this.greetingService = greetingService;
//    }
}