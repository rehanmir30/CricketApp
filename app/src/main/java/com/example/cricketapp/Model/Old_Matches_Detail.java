package com.example.cricketapp.Model;

public class Old_Matches_Detail {
    String id,description,title;

    public Old_Matches_Detail(String id, String description, String title) {
        this.id = id;
        this.description = description;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
