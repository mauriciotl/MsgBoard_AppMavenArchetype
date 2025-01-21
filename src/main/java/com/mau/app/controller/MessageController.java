package com.mau.app.controller;

import com.mau.app.dao.LocalDbDAO;
import com.mau.app.model.Message;
import com.mau.app.service.GenerateMessageId;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/messages")
public class MessageController {

    private static final Logger logger = LogManager.getLogger(MessageController.class);

    private final LocalDbDAO messageDao; // Make fields final
    private final GenerateMessageId idGenerator;

    // Constructor injection
    public MessageController(LocalDbDAO messageDao, GenerateMessageId idGenerator) {
        this.messageDao = messageDao;
        this.idGenerator = idGenerator;
    }

    @GetMapping
    public String viewMessages(Model model) {
        logger.info("Getting all messages"); // Log info message before fetching messages
        List<Message> messages = messageDao.getAllMessages();
        model.addAttribute("messages", messages);
        Collections.reverse(messages); // Reverse the list here!
        return "app/viewMessages";
    }

    @PostMapping("/add")
    public String addMessage(@RequestParam String user, @RequestParam String msgContent) {
        Message message = new Message(idGenerator.getId(), user, msgContent, new Date());
        logger.info("Adding message: {}", message); // Log info message with message details
        messageDao.addMessage(message);
        return "redirect:/messages";
    }

    @GetMapping("/edit/{id}")
    public String editMessage(@PathVariable int id, Model model) {
        logger.info("Getting message for edit: {}", id); // Log info message with message id
        Message message = messageDao.getMessageById(id);
        model.addAttribute("message", message);
        return "app/editMessage";
    }

    @PostMapping("/update")
    public String updateMessage(@RequestParam int id, @RequestParam String user, @RequestParam String msgContent) {
        Message message = messageDao.getMessageById(id);
        if (message != null) {
            message.setUser(user);
            message.setMsgContent(msgContent);
            message.setDate(new Date());
            messageDao.updateMessage(message);
            logger.info("Updated message: {}", message); // Log info message with updated message details
        }
        return "redirect:/messages";
    }

    @PostMapping("/delete/{id}")
    public String deleteMessage(@PathVariable int id) {
        logger.info("Deleting message: {}", id); // Log info message with message id
        messageDao.deleteMessage(id);
        return "redirect:/messages";
    }
}