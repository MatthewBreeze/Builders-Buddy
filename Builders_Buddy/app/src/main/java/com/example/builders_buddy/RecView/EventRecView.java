package com.example.builders_buddy.RecView;

public class EventRecView {
    private String Title;
    private String location;
    private String PhoneNumber;
    private String date;
    private String id;

    public EventRecView(String  title, String location  , String phoneNumber,String date,String id){
            this.setTitle(title);
            this.setLocation(location);
            this.setPhoneNumber(phoneNumber);
            this.setDate(date);
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

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

