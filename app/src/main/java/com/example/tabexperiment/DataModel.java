package com.example.tabexperiment;

public class DataModel {
    String state;
    String active;
    String confirmed;
    String recovered;
    String deaths;

    public DataModel() {
    }

    public DataModel(String state, String active, String confirmed, String recovered, String deaths) {
        this.state = state;
        this.active = active;
        this.confirmed = confirmed;
        this.recovered = recovered;
        this.deaths = deaths;
    }

    public String getState() {
        return state;
    }

    public String getActive() {
        return active;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public String getRecovered() {
        return recovered;
    }

    public String getDeaths() {
        return deaths;
    }
}