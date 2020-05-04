package com.example.builders_buddy.RecView;

public class TaxRecView {
    private String Title;
    private String total;
    private String DownloadUrl;
    private String id;

    public TaxRecView(String  title, String total  , String downloadUrl, String id){
            this.setTitle(title);
            this.setTotal(total);
            this.setDownloadUrl(downloadUrl);
            this.setId(id);
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }


    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getDownloadUrl() {
        return DownloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        DownloadUrl = downloadUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

