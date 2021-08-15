package com.example.cricketapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openLiveScore(View v) {
        Intent i = new Intent(getApplicationContext(), LiveScore.class);
        startActivity(i);
    }

    public void openNewMatches(View v) {
        Intent i = new Intent(getApplicationContext(), NewMatches.class);
        startActivity(i);
    }

    public void openOldMatches(View v) {
        Intent i = new Intent(getApplicationContext(), OldMatches.class);
        startActivity(i);
    }

    public void openPlayerInfo(View v) {
        Intent i = new Intent(getApplicationContext(), PlayerInfo.class);
        startActivity(i);
    }
}