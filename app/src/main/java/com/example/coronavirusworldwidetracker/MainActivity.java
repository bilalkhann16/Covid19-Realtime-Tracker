package com.example.coronavirusworldwidetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    RequestQueue mQueue;
    TextView totalconfirmed,totalactive,totalrecovered,totalcrtitical,totaldeaths;
    String totalconfirmed1,totalactive1,totalrecovered1,totalcritical1,totaldeaths1;
    String url="https://corona.lmao.ninja/all";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalconfirmed=findViewById(R.id.totalconfirmed);
        totalactive=findViewById(R.id.totalactive);
        totalrecovered=findViewById(R.id.totalrecovered);
        totalcrtitical=findViewById(R.id.totalcritical);
        totaldeaths=findViewById(R.id.totaldeaths);

        mQueue = Volley.newRequestQueue(this);
        sendjsonrequest();
    }
    public void sendjsonrequest(){
        JsonObjectRequest JsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    totalconfirmed1=response.getString("cases");
                    totalactive1=response.getString("active");
                    totalcritical1=response.getString("critical");
                    totaldeaths1=response.getString("deaths");
                    totalrecovered1=response.getString("recovered");

                    totalconfirmed.setText(totalconfirmed1);
                    totalactive.setText(totalactive1);
                    totalrecovered.setText(totalrecovered1);
                    totalcrtitical.setText(totalcritical1);
                    totaldeaths.setText(totaldeaths1);


                    Toast toast1=Toast. makeText(getApplicationContext(),"Data Fetch Success",Toast. LENGTH_SHORT);
                    toast1.show();

                } catch (JSONException e) {
                    Toast toast1=Toast. makeText(getApplicationContext(),"JsonError.dev",Toast. LENGTH_SHORT);
                    toast1.show();
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast toast1=Toast. makeText(getApplicationContext(),"Unable to fetch the data,Restart the application",Toast. LENGTH_LONG);
                toast1.show();
                Toast toast11=Toast. makeText(getApplicationContext(),"Slow Internet Connection detected,Restart the application",Toast. LENGTH_LONG);
                toast11.show();
            }
        });
        mQueue.add(JsonObjectRequest);
    }
}
