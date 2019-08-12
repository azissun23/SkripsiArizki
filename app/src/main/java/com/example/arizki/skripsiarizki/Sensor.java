package com.example.arizki.skripsiarizki;

import org.json.JSONException;
import org.json.JSONObject;

public class Sensor {
    private String NTU = "NTU";
    private String status = "status";
    private String Kg = "Kg";

    public String getNTU() {return NTU;}
    public void setNTU(String NTU) {this.NTU = NTU;}

    public String getstatus() {return status;}
    public void setStatus(String status) {this.status = status;}

    public String getKg() {return Kg;}
    public void setKg(String kg) {this.Kg = kg;}

public Sensor (JSONObject obj) {
        try {
            String NTU = obj.getString("NTU");
            String Status = obj.getString("Status");
            String Kg = obj.getString("Kg");

            this.NTU = NTU;
            this.Kg = Kg;
            this.status = Status;
        } catch (JSONException e){
            e.printStackTrace();
        }

}
}
