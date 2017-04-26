package com.component.independent.thingspeakwrite;

import android.os.SystemClock;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    String base_url_dr ="https://api.thingspeak.com/update?api_key=ZPEK9N1MJOS6PU00&";
    String base_url_br = "https://api.thingspeak.com/update?api_key=H2W6DCHAD4ZC1M4Q&";
    String json_url_dr = "http://api.thingspeak.com/channels/258518/feeds/last.json?api_key=RILPSQ2KY0Y1R0HF";
    String json_url_br = "http://api.thingspeak.com/channels/ 258519/feeds/last.json?api_key=YOGKFQXT36RDCV1T";
    String current_dr_light,current_dr_fan;
    String current_br_light,current_br_fan;

    TextView res_dr,res_br;

    Button dr_on_light,dr_off_light,dr_on_fan,dr_off_fan;
    Button br_on_light,br_off_light,br_on_fan,br_off_fan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        res_dr =(TextView)findViewById(R.id.textView) ;
        res_br =(TextView)findViewById(R.id.textView2) ;

        dr_on_light = (Button)findViewById(R.id.dr_on_light);
        dr_off_light = (Button)findViewById(R.id.dr_off_light);
        dr_on_fan = (Button)findViewById(R.id.dr_on_fan);
        dr_off_fan = (Button)findViewById(R.id.dr_off_fan);
        br_on_light = (Button)findViewById(R.id.br_on_light);
        br_off_light = (Button)findViewById(R.id.br_off_light);
        br_on_fan = (Button)findViewById(R.id.br_on_fan);
        br_off_fan = (Button)findViewById(R.id.br_off_fan);

        getFeeds();



        dr_on_light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(current_dr_light.equals("1")){
                    Toast.makeText(MainActivity.this, "This Appliance is already on", Toast.LENGTH_SHORT).show();
                }
                else {

                    StringRequest stringRequest = new StringRequest(Request.Method.GET, base_url_dr + "field1=1&field2=" + current_dr_fan,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    res_dr.setText(response);
                                    if (!response.equals("0")) {
                                        current_dr_light = "1";
                                        Toast.makeText(MainActivity.this, current_dr_light, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MainActivity.this, "Something Went Wrong", Toast.LENGTH_LONG).show();
                        }
                    });
                    MySingleton.getInstance(MainActivity.this).addToQueue(stringRequest);
                }
            }
        });

        dr_off_light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (current_dr_light.equals("0")) {
                    Toast.makeText(MainActivity.this, "This appliance is already off", Toast.LENGTH_SHORT).show();
                } else {

                    StringRequest stringRequest2 = new StringRequest(Request.Method.GET, base_url_dr + "field1=0&field2=" + current_dr_fan, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            res_dr.setText(response);
                            if (!response.equals("0")){
                                current_dr_light="0";
                                Toast.makeText(MainActivity.this, current_dr_light, Toast.LENGTH_SHORT).show();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MainActivity.this, "Something Went Wrong", Toast.LENGTH_LONG).show();
                        }
                    });
                    MySingleton.getInstance(MainActivity.this).addToQueue(stringRequest2);
                }
            }
        });

        dr_on_fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(current_dr_fan.equals("1")){
                    Toast.makeText(MainActivity.this, "This Appliance is already on", Toast.LENGTH_SHORT).show();
                }
                else {

                    StringRequest stringRequest = new StringRequest(Request.Method.GET, base_url_dr + "field1=" + current_dr_light +"&field2=1",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    res_dr.setText(response);
                                    if (!response.equals("0")) {
                                        current_dr_fan = "1";
                                        Toast.makeText(MainActivity.this, current_dr_fan, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MainActivity.this, "Something Went Wrong", Toast.LENGTH_LONG).show();
                        }
                    });
                    MySingleton.getInstance(MainActivity.this).addToQueue(stringRequest);
                }
            }
        });

        dr_off_fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (current_dr_fan.equals("0")) {
                    Toast.makeText(MainActivity.this, "This appliance is already off", Toast.LENGTH_SHORT).show();
                } else {

                    StringRequest stringRequest2 = new StringRequest(Request.Method.GET, base_url_dr + "field1=" + current_dr_light +"&field2=0", new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            res_dr.setText(response);
                            if (!response.equals("0")){
                                current_dr_fan="0";
                                Toast.makeText(MainActivity.this, current_dr_fan, Toast.LENGTH_SHORT).show();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MainActivity.this, "Something Went Wrong", Toast.LENGTH_LONG).show();
                        }
                    });
                    MySingleton.getInstance(MainActivity.this).addToQueue(stringRequest2);
                }
            }
        });

        br_on_light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(current_br_light.equals("1")){
                    Toast.makeText(MainActivity.this, "This Appliance is already on", Toast.LENGTH_SHORT).show();
                }
                else {

                    StringRequest stringRequest = new StringRequest(Request.Method.GET, base_url_br + "field1=1&field2=" + current_br_fan,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    res_br.setText(response);
                                    if (!response.equals("0")) {
                                        current_br_light = "1";
                                        Toast.makeText(MainActivity.this, current_br_light, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MainActivity.this, "Something Went Wrong", Toast.LENGTH_LONG).show();
                        }
                    });
                    MySingleton.getInstance(MainActivity.this).addToQueue(stringRequest);
                }
            }
        });

        br_off_light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (current_br_light.equals("0")) {
                    Toast.makeText(MainActivity.this, "This appliance is already off", Toast.LENGTH_SHORT).show();
                } else {

                    StringRequest stringRequest2 = new StringRequest(Request.Method.GET, base_url_br + "field1=0&field2=" + current_br_fan, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            res_br.setText(response);
                            if (!response.equals("0")){
                                current_br_light="0";
                                Toast.makeText(MainActivity.this, current_br_light, Toast.LENGTH_SHORT).show();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MainActivity.this, "Something Went Wrong", Toast.LENGTH_LONG).show();
                        }
                    });
                    MySingleton.getInstance(MainActivity.this).addToQueue(stringRequest2);
                }
            }
        });

        br_on_fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(current_br_fan.equals("1")){
                    Toast.makeText(MainActivity.this, "This Appliance is already on", Toast.LENGTH_SHORT).show();
                }
                else {

                    StringRequest stringRequest = new StringRequest(Request.Method.GET, base_url_br + "field1=" + current_br_light +"&field2=1",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    res_br.setText(response);
                                    if (!response.equals("0")) {
                                        current_br_fan = "1";
                                        Toast.makeText(MainActivity.this, current_br_fan, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MainActivity.this, "Something Went Wrong", Toast.LENGTH_LONG).show();
                        }
                    });
                    MySingleton.getInstance(MainActivity.this).addToQueue(stringRequest);
                }
            }
        });

        br_off_fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (current_br_fan.equals("0")) {
                    Toast.makeText(MainActivity.this, "This appliance is already off", Toast.LENGTH_SHORT).show();
                } else {

                    StringRequest stringRequest2 = new StringRequest(Request.Method.GET, base_url_br + "field1=" + current_br_light +"&field2=0", new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            res_br.setText(response);
                            if (!response.equals("0")){
                                current_br_fan="0";
                                Toast.makeText(MainActivity.this, current_br_fan, Toast.LENGTH_SHORT).show();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MainActivity.this, "Something Went Wrong", Toast.LENGTH_LONG).show();
                        }
                    });
                    MySingleton.getInstance(MainActivity.this).addToQueue(stringRequest2);
                }
            }
        });
    }
    private void getFeeds() {
        RequestQueue req = Volley.newRequestQueue(MainActivity.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET , json_url_dr,
                (String) null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                            Toast.makeText(MainActivity.this, "Got DR Feeds", Toast.LENGTH_SHORT).show();
                        dispValues_dr(response);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        JsonObjectRequest jsonObjectRequest2 = new JsonObjectRequest(Request.Method.GET , json_url_br,
                (String) null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(MainActivity.this, "Got BR Feeds", Toast.LENGTH_SHORT).show();
                        dispValues_br(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        req.add(jsonObjectRequest);
        req.add(jsonObjectRequest2);

    }

    private void dispValues_br(JSONObject response) {
        Toast.makeText(this, response.toString() , Toast.LENGTH_SHORT).show();
        try {
            current_br_light = response.getString("field1").toString();
            current_br_fan = response.getString("field2").toString();
            Toast.makeText(this, current_br_light + " " + current_br_fan, Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void dispValues_dr(JSONObject response) {
        Toast.makeText(this, response.toString() , Toast.LENGTH_SHORT).show();
        try {
            current_dr_light = response.getString("field1").toString();
            current_dr_fan = response.getString("field2").toString();
            Toast.makeText(this, current_dr_light + " " + current_dr_fan, Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
