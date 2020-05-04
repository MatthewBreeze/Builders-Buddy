package com.example.builders_buddy.RecView;

public class TradCardExample {
    private String Title;
    private String location;
    private String id;

    public TradCardExample(String  title, String location , String id){
            this.setTitle(title);
            this.setLocation(location);

            this.setId(id);
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

