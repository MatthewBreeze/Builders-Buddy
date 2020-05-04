package com.example.builders_buddy.RecView;

public class InvoiceRecView {
    private String Title1;
    private String Title2;
    private String Title3;
    private String docId;

    public InvoiceRecView(String title1, String title2, String title3,String docId ){
      this.setTitle1(title1);
      this.setTitle2(title2);
      this.setTitle3(title3);
      this.setDocId(docId);
    }


    public String getTitle1() {
        return Title1;
    }

    public void setTitle1(String title1) {
        Title1 = title1;
    }

    public String getTitle2() {
        return Title2;
    }

    public void setTitle2(String title2) {
        Title2 = title2;
    }

    public String getTitle3() {
        return Title3;
    }

    public void setTitle3(String title3) {
        Title3 = title3;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }


}

