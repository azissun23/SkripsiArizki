package com.example.arizki.skripsiarizki;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.MySSLSocketFactory;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class Kekeruhan extends AppCompatActivity {
    @BindView(R.id.NTUset)
    RecyclerView NTUset;
    SensorAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kekeruhan);
        ButterKnife.bind(this);
        adapter = new SensorAdapter(this);
        String url = "http://hidroponik.96.lt/gettabel.php";
        DemoAsync demoASync = new DemoAsync();
        demoASync.execute(url);
    }
    public class DemoAsync extends AsyncTask<String, Void, ArrayList<Sensor>> {


        @Override
        protected ArrayList<Sensor> doInBackground(String... strings) {
            String uri = strings[0];
            final ArrayList<Sensor> sensors = new ArrayList<>();
            SyncHttpClient client = new SyncHttpClient();

            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            client.get(uri, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    try {
                        String hasil = new String(responseBody);
                        JSONObject jsonData = new JSONObject(hasil);
                        JSONArray jsonArray = jsonData.getJSONArray("Data");
                        for (int i = 0; i < jsonArray.length(); i++){
                            JSONObject sensorObj = jsonArray.getJSONObject(i);
                            Sensor sensor = new Sensor(sensorObj);

                            sensors.add(sensor);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    Log.d("Tag", "onFailure: " + statusCode);
                }
            });
            return sensors;
        }

        @Override
        protected void onPostExecute (ArrayList<Sensor> sensor){
            super.onPostExecute(sensor);
            NTUset.setLayoutManager(new LinearLayoutManager(Kekeruhan.this));
            adapter.setListSensor(sensor);
            NTUset.setAdapter(adapter);
        }
    }
}
