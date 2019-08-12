package com.example.arizki.skripsiarizki.Pojo;

import org.json.JSONException;
import org.json.JSONObject;

public class StockPakan {

    public String getKg() {
        return Kg;
    }

    public void setKg(String kg) {
        Kg = kg;
    }

    private String Kg = "Kg";

    public StockPakan(JSONObject obj) {
        try {
            String Kg = obj.getString("Kg");

            this.Kg = Kg;

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
