package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Official> person = new ArrayList<Official>();
    Recycler_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.rv_items);

        adapter = new Recycler_Adapter(person);

        rvContacts.setAdapter(adapter);

        rvContacts.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        getFromWebAPI();
    }

    protected void getFromWebAPI() {

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://www.googleapis.com/civicinfo/v2/representatives?key=API_KEY&address=90001";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // JSON
                        JSONObject jObj = null;
                        try {
                            jObj = new JSONObject(response);
                            JSONArray jsonArry1 = jObj.getJSONArray("offices");
                            JSONArray jsonArry2 = jObj.getJSONArray("officials");
                            for (int i = 0; i < jsonArry1.length(); i++) {
                                Official c = new Official();
                                JSONObject obj1 = jsonArry1.getJSONObject(i);
                                JSONObject obj2 = jsonArry2.getJSONObject(i);
                                c.people_name = obj1.getString("name");
                                c.position_name = obj2.getString("name");
                                person.add(c);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //error
                person = null;
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }
}