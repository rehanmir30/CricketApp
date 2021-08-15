package com.example.cricketapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cricketapp.Model.Match_details;
import com.example.cricketapp.Model.Old_Matches_Detail;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class OldMatches extends AppCompatActivity {

    private ArrayList<Old_Matches_Detail> Match_list;
    private RecyclerView mRecyclerView;
    private OldMatchesAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_matches);

        mRecyclerView = findViewById(R.id.recyclerView);
        Match_list = new ArrayList<>();

        RequestQueue queue = Volley.newRequestQueue(this);

        String new_match_url = "https://cricapi.com/api/cricket?apikey=uSLh1jfPC5dAZaUfu1NUUyTixvs1";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, new_match_url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String m_id = "No id";
                String m_description = "No Description";
                String m_title = "No Title";
                try {
                    JSONArray array = response.getJSONArray("data");

                    for (int i = 0; i < array.length(); i++) {
                        // Toast.makeText(getApplicationContext(), "Try added", Toast.LENGTH_SHORT).show();

                        JSONObject object = array.getJSONObject(i);

                        if (object.has("unique_id")) {
                            m_id = object.getString("unique_id");
                        }
                        if (object.has("description")) {
                            m_description = object.getString("description");
                        }
                        if (object.has("title")) {
                            m_title = object.getString("title");
                        }

                        Old_Matches_Detail match_details = new Old_Matches_Detail(m_id, m_description, m_title);
                        Match_list.add(match_details);

                    }

                    // Toast.makeText(getApplicationContext(), "Matches added", Toast.LENGTH_SHORT).show();
                    mRecyclerView.setHasFixedSize(true);
                    mLayoutManager = new LinearLayoutManager(OldMatches.this, LinearLayoutManager.VERTICAL, false);
                    mAdapter = new OldMatchesAdapter(Match_list);
                    mRecyclerView.setLayoutManager(mLayoutManager);
                    mRecyclerView.setAdapter(mAdapter);

                    mAdapter.setOnItemClickListener(new OldMatchesAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), String.valueOf(error), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);
    }

    public void goBack(View view) {
        finish();
    }
}