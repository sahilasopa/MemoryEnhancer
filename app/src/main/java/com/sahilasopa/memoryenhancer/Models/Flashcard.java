package com.sahilasopa.memoryenhancer.Models;

public class Flashcard {
    private String title;
    private String body;
    private String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Flashcard(String title, String body, String uid) {
        this.title = title;
        this.body = body;
        this.uid = uid;
    }

    public Flashcard() {
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
}
