package com.component.independent.thingspeakwrite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

public class MainActivity extends AppCompatActivity {

    String url_on ="https://api.thingspeak.com/update?api_key=6S9LM0BJ63OYT5CP&field1=1";
    String url_off ="https://api.thingspeak.com/update?api_key=6S9LM0BJ63OYT5CP&field1=0";

    TextView res;
    Button on,off;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        res =(TextView)findViewById(R.id.textView) ;
        on = (Button)findViewById(R.id.button);
        off = (Button)findViewById(R.id.button2);

        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url_on, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        res.setText(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,"Something Went Wrong",Toast.LENGTH_LONG).show();
                    }
                });
                MySingleton.getInstance(MainActivity.this).addToQueue(stringRequest);
            }
        });

        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest stringRequest2= new StringRequest(Request.Method.GET, url_off, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        res.setText(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,"Something Went Wrong",Toast.LENGTH_LONG).show();
                    }
                });
                MySingleton.getInstance(MainActivity.this).addToQueue(stringRequest2);
            }
        });



    }
}
