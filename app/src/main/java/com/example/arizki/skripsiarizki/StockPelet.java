package com.example.arizki.skripsiarizki;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.arizki.skripsiarizki.Pojo.StockPakan;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.MySSLSocketFactory;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class StockPelet extends AppCompatActivity {

    @BindView(R.id.pakanset)
    RecyclerView pakanset;
    StockAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);
        ButterKnife.bind(this);
        adapter = new StockAdapter(this);
        String url = "http://hidroponik.96.lt/gettabel.php";
        DemoAsync demoASync = new DemoAsync();
        demoASync.execute(url);
    }
    public class DemoAsync extends AsyncTask<String, Void, ArrayList<StockPakan>> {


        @Override
        protected ArrayList<StockPakan> doInBackground(String... strings) {
            String uri = strings[0];
            final ArrayList<StockPakan> stocks = new ArrayList<>();
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
                            JSONObject stockObj = jsonArray.getJSONObject(i);
                            StockPakan stockPakan = new StockPakan(stockObj);

                            stocks.add(stockPakan);
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
            return stocks;
        }

        @Override
        protected void onPostExecute (ArrayList<StockPakan> stock){
            super.onPostExecute(stock);
            pakanset.setLayoutManager(new LinearLayoutManager(StockPelet.this));
            adapter.setListStok(stock);
            pakanset.setAdapter(adapter);
        }
    }
}
