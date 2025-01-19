package com.mau.app.model;

import java.util.Date;

public class Message {
    private int id;
    private String user;
    private String msgContent;
    private Date date;

    // Default constructor
    public Message() {
    }

    // Parameterized constructor
    public Message(int id, String user, String msgContent, Date date) {
        this.id = id;
        this.user = user;
        this.msgContent = msgContent;
        this.date = date;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", msgContent='" + msgContent + '\'' +
                ", date=" + date +
                '}';
    }
}
