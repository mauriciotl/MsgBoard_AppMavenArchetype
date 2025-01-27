package com.mau.app.controller;

import com.mau.app.dao.MessageDaoImpLocal;
import com.mau.app.model.Message;
import com.mau.app.service.GenerateMessageId;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/messages")
public class MessageController {

    private static final Logger logger = LogManager.getLogger(MessageController.class);

    private final MessageDaoImpLocal messageDao;
    private final GenerateMessageId idGenerator;

    public MessageController(MessageDaoImpLocal messageDao, GenerateMessageId idGenerator) {
        this.messageDao = messageDao;
        this.idGenerator = idGenerator;
    }

    @GetMapping
    public String viewMessages(Model model) {
        logger.info("Fetching all messages.");
        List<Message> messages = messageDao.getAllMessages();
        logger.debug("Successfully fetched {} messages.", messages.size());
        model.addAttribute("messages", messages);
        Collections.reverse(messages);
        return "app/viewMessages";
    }

    @GetMapping("/addByForm")
    public String addMessageByForm(Model model) {
        logger.info("Navigating to the add message form.");
        model.addAttribute("message", new Message());
        return "app/addMessageByForm";
    }

    @PostMapping("/addByForm")
    public View addMessageByForm(Message message) {
        Message newMessage = new Message(idGenerator.getId(), message.getUser(),
                message.getMsgContent(), new Date());
        messageDao.addMessage(newMessage);
        logger.info("Message added successfully: {}", newMessage);
        return new RedirectView("/messages", true, false);
    }

    @GetMapping("/editMessageByForm/{messageId}")
    public String editMessageByForm(Model model, @PathVariable("messageId") int messageId) {
        logger.info("Fetching message for editing. Message ID: {}", messageId);
        Message msgForEdition = messageDao.getMessageById(messageId);
        model.addAttribute("message", msgForEdition);
        logger.info("Successfully fetched message for editing: {}", msgForEdition);
        return "app/editMessageByForm";
    }

    @PostMapping("/editMessageByForm/{messageId}")
    public View editMessageByForm(Message message, @PathVariable("messageId") int messageId) {
        logger.info("Updating message. Message ID: {}", messageId);
        Message msg = messageDao.getMessageById(messageId);
        msg.setUser(message.getUser());
        msg.setMsgContent(message.getMsgContent());
        messageDao.updateMessage(msg);
        logger.info("Message updated successfully: {}", msg);
        return new RedirectView("/messages", true, false);
    }

    @PostMapping("/add")
    public String addMessage(@RequestParam String user, @RequestParam String msgContent) {
        Message message = new Message(idGenerator.getId(), user, msgContent, new Date());
        messageDao.addMessage(message);
        logger.info("Message added successfully: {}", message);
        return "redirect:/messages";
    }

    @GetMapping("/edit/{id}")
    public String editMessage(@PathVariable int id, Model model) {
        logger.info("Fetching message for editing. Message ID: {}", id);
        Message message = messageDao.getMessageById(id);
        if (message != null) {
            logger.info("Successfully fetched message: {}", message);
        } else {
            logger.warn("Message with ID {} not found.", id);
        }
        model.addAttribute("message", message);
        return "app/editMessage";
    }

    @PostMapping("/update")
    public String updateMessage(@RequestParam int id, @RequestParam String user, @RequestParam String msgContent) {
        logger.info("Updating message. Message ID: {}", id);
        Message message = messageDao.getMessageById(id);
        if (message != null) {
            message.setUser(user);
            message.setMsgContent(msgContent);
            message.setDate(new Date());
            messageDao.updateMessage(message);
            logger.info("Message updated successfully: {}", message);
        } else {
            logger.warn("Message with ID {} not found for update.", id);
        }
        return "redirect:/messages";
    }

    @PostMapping("/delete/{id}")
    public String deleteMessage(@PathVariable int id) {
        logger.info("Deleting message. Message ID: {}", id);
        messageDao.deleteMessage(id);
        logger.info("Message deleted successfully. Message ID: {}", id);
        return "redirect:/messages";
    }
}
