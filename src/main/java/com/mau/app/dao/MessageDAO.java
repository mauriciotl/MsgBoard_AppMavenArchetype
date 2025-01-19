package com.mau.app.dao;

import com.mau.app.model.Message;
import java.util.List;

public interface MessageDAO {

    // Create a new message and return true if the operation was successful
    boolean addMessage(Message message);

    // Retrieve a message by its ID
    Message getMessageById(int id);

    // Retrieve all messages
    List<Message> getAllMessages();

    // Update an existing message and return true if the update was successful
    boolean updateMessage(Message message);

    // Delete a message by its ID and return true if the deletion was successful
    boolean deleteMessage(int id);

    //Get messages with specific content.
    public abstract List<Message> messageContains(String messageContent);

}
