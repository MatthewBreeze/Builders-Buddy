package com.example.builders_buddy.TradsCard;

public class CardUplaod {

    private String cName;
    private String cImageUrl;

    public CardUplaod(){

    }
    public CardUplaod(String name, String ImageUrl)
    {
        setcName(name);
        setcImageUrl(ImageUrl);
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcImageUrl() {
        return cImageUrl;
    }

    public void setcImageUrl(String cImageUrl) {
        this.cImageUrl = cImageUrl;
    }
}
