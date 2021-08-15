package com.example.cricketapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
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

public class LiveScore extends AppCompatActivity {
    TextView live_status, team1, team2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_score);
        live_status = findViewById(R.id.live_check);
        team1 = findViewById(R.id.team1);
        team2 = findViewById(R.id.team2);
        RequestQueue queue = Volley.newRequestQueue(this);

        String live_url = "https://cricapi.com/api/cricketScore?apikey=uSLh1jfPC5dAZaUfu1NUUyTixvs1&unique_id=1034809";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, live_url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String match_started = response.getString("matchStarted");
                    String Team1 = response.getString("team-1");
                    String Team2 = response.getString("team-2");

                    if (match_started.equals("true")) {
                        live_status.setText("Live :)");
                    } else {
                        live_status.setText("Not Live :(");
                    }

                    team1.setText(Team1);
                    team2.setText(Team2);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);
    }

    public void goBack(View v) {
        finish();
    }
    public void thanks(View v) {
        Toast.makeText(getApplicationContext(),"Thanks For Your Review",Toast.LENGTH_SHORT).show();
        finish();
    }
}