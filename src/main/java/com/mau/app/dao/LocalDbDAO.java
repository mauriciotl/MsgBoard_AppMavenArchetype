package com.mau.app.dao;

import com.mau.app.model.Message;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class LocalDbDAO implements MessageDAO {

    private final List<Message> storeMessages = new ArrayList<>();

    @Override
    public boolean addMessage(Message message) {
        return storeMessages.add(message);
    }

    @Override
    public Message getMessageById(int id) {
        return storeMessages.stream().filter(message -> message.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Message> getAllMessages() {
        return new ArrayList<>(storeMessages); // Return a copy to avoid modification of the internal list
    }

    @Override
    public boolean updateMessage(Message message) {
        int index = storeMessages.indexOf(message);
        if (index != -1) {
            storeMessages.set(index, message);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteMessage(int id) {
        return storeMessages.removeIf(message -> message.getId() == id);
    }

    @Override
    public List<Message> messageContains(String messageContent) {
        if (messageContent == null || messageContent.isEmpty()) {
            return new ArrayList<>();
        }
        return storeMessages.stream()
                .filter(message -> message.getMsgContent().toLowerCase().contains(messageContent.toLowerCase()))
                .collect(Collectors.toList());
    }
}