package com.example.cricketapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cricketapp.Model.Old_Matches_Detail;
import com.example.cricketapp.Model.Player;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PlayerInfo extends AppCompatActivity {

    EditText input;

    private ArrayList<Player> Match_list;
    private RecyclerView mRecyclerView;
    private PlayerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_info);
        input = findViewById(R.id.input);


        mRecyclerView = findViewById(R.id.recyclerView);
        Match_list = new ArrayList<>();

    }

    public void goBack(View view) {
        finish();
    }

    public void search(View view) {
        String name = input.getText().toString().trim();
        if (name.isEmpty()) {
            input.setError("Enter a name please");
            return;
        } else {
            String player_url = "https://cricapi.com/api/playerFinder?apikey=uSLh1jfPC5dAZaUfu1NUUyTixvs1&name=" + name;
            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, player_url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    String Name = "No name found";
                    String Full_name = "No Full name found";

                    try {
                        JSONArray array = response.getJSONArray("data");

                        for (int i = 0; i < array.length(); i++) {
                            // Toast.makeText(getApplicationContext(), "Try added", Toast.LENGTH_SHORT).show();

                            JSONObject object = array.getJSONObject(i);

                            if (object.has("name")) {
                                Name = object.getString("name");
                            }
                            if (object.has("fullName")) {
                                Full_name = object.getString("fullName");
                            }


                            Player match_details = new Player(Name, Full_name);
                            Match_list.add(match_details);

                        }

                        // Toast.makeText(getApplicationContext(), "Matches added", Toast.LENGTH_SHORT).show();
                        mRecyclerView.setHasFixedSize(true);
                        mLayoutManager = new LinearLayoutManager(PlayerInfo.this, LinearLayoutManager.VERTICAL, false);
                        mAdapter = new PlayerAdapter(Match_list);
                        mRecyclerView.setLayoutManager(mLayoutManager);
                        mRecyclerView.setAdapter(mAdapter);

                        mAdapter.setOnItemClickListener(new PlayerAdapter.OnItemClickListener() {
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
    }
}