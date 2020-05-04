package com.example.builders_buddy.Invoice;

import com.example.builders_buddy.RecView.InvoiceItemRecView;

import java.util.List;

public class InvoiceTemplate {
    private String JobName;
    private String manHours;
    private String total;
    private List<InvoiceItemRecView> materails;
    private double houlryRate;
    private String docId;

    public InvoiceTemplate()
    {
    }

    public InvoiceTemplate(String JobName, String manHours, String total,List<InvoiceItemRecView> materails,double hourlyRate)
    {
        this.JobName = JobName;
        this.manHours = manHours;
        this.total = total;
        this.materails= materails;
        this.houlryRate = hourlyRate;

    }


    public String getJobName() {
        return JobName;
    }

    public void setJobName(String jobName) {
        JobName = jobName;
    }

    public String getManHours() {
        return manHours;
    }

    public void setManHours(String manHours) {
        this.manHours = manHours;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<InvoiceItemRecView> getMaterails() {
        return materails;
    }

    public void setMaterails(List<InvoiceItemRecView> materails) {
        this.materails = materails;
    }

    public double getHoulryRate() {
        return houlryRate;
    }

    public void setHoulryRate(double houlryRate) {
        this.houlryRate = houlryRate;
    }


}// Invoice data model
