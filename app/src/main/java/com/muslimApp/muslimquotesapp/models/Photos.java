package com.muslimApp.muslimquotesapp.models;


import java.io.Serializable;

public class Photos implements Serializable {



    public String title, url , category , developers;

    public Photos() {
    }

    public Photos(String title, String url, String category, String developers) {
        this.title = title;
        this.url = url;
        this.category = category;
        this.developers = developers;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDevelopers() {
        return developers;
    }

    public void setDevelopers(String developers) {
        this.developers = developers;
    }
}
