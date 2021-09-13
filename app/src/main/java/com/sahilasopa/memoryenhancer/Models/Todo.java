package com.sahilasopa.memoryenhancer.Models;

public class Todo {
    private String title;
    private String body;
    private String uid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;

    public Todo() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Todo(String title, String body, String uid, String id) {
        this.title = title;
        this.body = body;
        this.uid = uid;
        this.id = id;
    }
}
