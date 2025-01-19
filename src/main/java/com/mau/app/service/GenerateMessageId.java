package com.mau.app.service;

import com.mau.app.utils.GenerateId;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class GenerateMessageId implements GenerateId {

    // AtomicInteger to ensure thread-safe ID generation
    private final AtomicInteger messageIdCounter = new AtomicInteger(0);

    @Override
    public int getId() {
        // Increment and return the next unique ID
        return messageIdCounter.incrementAndGet(); //first one is 1 because increment then return.
    }
}
