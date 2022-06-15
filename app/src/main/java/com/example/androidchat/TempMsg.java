package com.example.androidchat;

public class TempMsg {
    private boolean sent;
    private int id;
    private String content;
    private String created;

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getCreated() {
        return created;
    }

    public TempMsg(boolean sent, int id, String content, String created) {
        this.sent = sent;
        this.id = id;
        this.content = content;
        this.created = created;
    }
}
