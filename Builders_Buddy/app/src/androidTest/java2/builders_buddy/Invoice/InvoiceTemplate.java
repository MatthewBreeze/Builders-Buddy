package com.example.builders_buddy.Invoice;

import java.util.List;

public class InvoiceTemplate {
    private String JobName;
    private String manHours;
    private String total;
    private List<String> materails;

    public InvoiceTemplate(String JobName, String manHours, String total,List<String> materails)
    {
        this.JobName = JobName;
        this.manHours = manHours;
        this.total = total;
        this.materails= materails;

    }
    public InvoiceTemplate()
    {
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

    public List<String> getMaterails() {
        return materails;
    }

    public void setMaterails(List<String> materails) {
        this.materails = materails;
    }
}
