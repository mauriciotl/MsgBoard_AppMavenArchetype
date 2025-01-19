package com.mau.test;

import com.mau.app.dao.LocalDbDAO;
import com.mau.app.model.Message;
import com.mau.app.service.GenerateMessageId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
public class TestMessageController {

    //Spring beans
    private final LocalDbDAO localStorage;
    private final GenerateMessageId getMsgId;

    @Autowired
    public TestMessageController(LocalDbDAO localDbDAO, GenerateMessageId generateMessageId){
        this.localStorage = localDbDAO;
        this.getMsgId = generateMessageId;
    }

    @ResponseBody
    @RequestMapping(value = "/test/message", method = RequestMethod.GET)
    public Message createTestMessage(){
        Message message = new Message();
        message.setId(getMsgId.getId());
        message.setDate(new Date());
        message.setUser("Mau");
        message.setMsgContent("This is the Mau message content. !! baby!!");
        this.saveMsg(message);
        return message;
    }

    @RequestMapping(value = "/test/messageOnJsp", method = RequestMethod.GET)
    public String createViewMessage(Model model){
        Message message = new Message();
        message.setId(getMsgId.getId());
        message.setDate(new Date());
        message.setUser("Mau JSP");
        message.setMsgContent("This is the Mau message content, for JSP method!! baby!!");
        this.saveMsg(message);

        model.addAttribute("message", message);

        return "test/msgTest/viewMessage";
    }

    @RequestMapping(value = "/test/displayMessages", method = RequestMethod.GET)
    public String displayAllMessages(Model model) {

        List<Message> messages = this.localStorage.getAllMessages();

        // Add the messages list to the model with a name
        model.addAttribute("messages", messages);

        // Return the view name
        return "test/msgTest/displayMessages";
    }



    private void saveMsg(Message msg){

        if(this.localStorage.addMessage(msg)){
            System.out.println("Ok, Message successfully added: " + msg.toString());
        }else{
            System.out.println("Error, message not added.");
        }
    }


}
