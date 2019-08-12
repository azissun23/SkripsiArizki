package com.example.arizki.skripsiarizki.Pojo;

import org.json.JSONException;
import org.json.JSONObject;

public class Sensor {
    private String NTU = "NTU";
    private String status = "status";

    public String getNTU() {return NTU;}
    public void setNTU(String NTU) {this.NTU = NTU;}

    public String getstatus() {return status;}
    public void setStatus(String status) {this.status = status;}

    public Sensor (JSONObject obj) {
        try {
            String NTU = obj.getString("NTU");
            String Status = obj.getString("Status");

            this.NTU = NTU;
            this.status = Status;

        } catch (JSONException e) {
        e.printStackTrace();
        }
    }
}
