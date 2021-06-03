package com.example.curso03semana03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.appbar.MaterialToolbar;

public class AboutOfActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_of);

        MaterialToolbar topAppBar = findViewById(R.id.topAppBar_aboutOf);
        setSupportActionBar(topAppBar);

        topAppBar.setNavigationOnClickListener(view -> onBackPressed());

    }
}