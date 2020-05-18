package com.example.tabexperiment;



public class YourData {
    private String dailyconfirmed;
    private String totalconfirmed;
    private String totalrecovered;
    private String totaldeceased;
    private String date;

    public YourData() {
    }

    public YourData(String dailyconfirmed, String totalconfirmed, String totalrecovered, String totaldeceased, String date) {
        this.dailyconfirmed = dailyconfirmed;
        this.totalconfirmed = totalconfirmed;
        this.totalrecovered = totalrecovered;
        this.totaldeceased = totaldeceased;
        this.date = date;
    }

    public String getDailyconfirmed() {
        return dailyconfirmed;
    }

    public String getTotalconfirmed() {
        return totalconfirmed;
    }

    public String getDate() {
        return date;
    }

    public String getTotalrecovered() {
        return totalrecovered;
    }

    public String getTotaldeceased() {
        return totaldeceased;
    }
}

