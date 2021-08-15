package com.example.cricketapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class OpenMatch extends AppCompatActivity {

    TextView teamone,teamtwo,winnerteam,tosswinner,Date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_match);

        teamone=findViewById(R.id.team1);
        teamtwo=findViewById(R.id.team2);
        winnerteam=findViewById(R.id.winner);
        tosswinner=findViewById(R.id.toss_winner);
        Date=findViewById(R.id.date);

        Intent i=getIntent();
        String team1=i.getStringExtra("m_team1");
        String team2=i.getStringExtra("m_team2");
        String date=i.getStringExtra("m_date");
        String winner=i.getStringExtra("m_winner");
        String toss_winner=i.getStringExtra("m_toss_winner");

        teamone.setText(team1);
        teamtwo.setText(team2);
        winnerteam.setText(winner);
        tosswinner.setText(toss_winner);
        Date.setText(date);

    }
    public void goBack(View view) {
        finish();
    }

    public void thanks(View view) {
        Toast.makeText(getApplicationContext(),"Thanks for your Feedback",Toast.LENGTH_SHORT).show();
        finish();
    }
}