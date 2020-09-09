package com.example.googlephasetwo.models;

public class PostObject {
    String name;
    String last_name;
    String email;
    String link;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public PostObject(String name, String last_name, String email, String link) {
        this.name = name;
        this.last_name = last_name;
        this.email = email;
        this.link = link;
    }
}
