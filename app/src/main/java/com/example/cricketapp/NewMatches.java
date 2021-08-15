package com.example.cricketapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cricketapp.Model.Match_details;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NewMatches extends AppCompatActivity {

    private ArrayList<Match_details> Match_list;
    private RecyclerView mRecyclerView;
    private NewMatchesAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_matches);

        mRecyclerView = findViewById(R.id.recyclerView);
        Match_list = new ArrayList<>();

        RequestQueue queue = Volley.newRequestQueue(this);

        String new_match_url = "https://cricapi.com/api/matches?apikey=uSLh1jfPC5dAZaUfu1NUUyTixvs1";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, new_match_url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String m_id = "No id";
                String m_date = "No Date";
                String m_team1 = "No team";
                String m_team2 = "No team";
                String m_toss_winner = "Not decided yet";
                String m_squad = "Not available";
                String m_winner_team = "Winner not decided yet";
                try {
                    JSONArray array = response.getJSONArray("matches");

                    for (int i = 0; i < array.length(); i++) {
                        // Toast.makeText(getApplicationContext(), "Try added", Toast.LENGTH_SHORT).show();

                        JSONObject object = array.getJSONObject(i);

                        if (object.has("unique_id")) {
                            m_id = object.getString("unique_id");
                        }
                        if (object.has("date")) {
                            m_date = object.getString("date");
                        }
                        if (object.has("team-1")) {
                            m_team1 = object.getString("team-1");
                        }
                        if (object.has("team-2")) {
                            m_team2 = object.getString("team-2");
                        }
                        if (object.has("toss_winner_team")) {
                            m_toss_winner = object.getString("toss_winner_team");
                        }
                        if (object.has("winner_team")) {
                            m_winner_team = object.getString("winner_team");
                        }

                        m_squad = object.getString("squad");

                        Match_details match_details = new Match_details(m_id, m_date, m_team1, m_team2, m_toss_winner, m_winner_team, m_squad);
                        Match_list.add(match_details);

                    }

                   // Toast.makeText(getApplicationContext(), "Matches added", Toast.LENGTH_SHORT).show();
                    mRecyclerView.setHasFixedSize(true);
                    mLayoutManager = new LinearLayoutManager(NewMatches.this, LinearLayoutManager.VERTICAL, false);
                    mAdapter = new NewMatchesAdapter(Match_list);
                    mRecyclerView.setLayoutManager(mLayoutManager);
                    mRecyclerView.setAdapter(mAdapter);

                    mAdapter.setOnItemClickListener(new NewMatchesAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            Intent i = new Intent(getApplicationContext(), OpenMatch.class);
                            i.putExtra("m_team1", Match_list.get(position).getM_team1());
                            i.putExtra("m_team2", Match_list.get(position).getM_team2());
                            i.putExtra("m_date", Match_list.get(position).getM_date());
                            i.putExtra("m_winner", Match_list.get(position).getM_winner_team());
                            i.putExtra("m_toss_winner", Match_list.get(position).getM_toss_winner());
                            startActivity(i);
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