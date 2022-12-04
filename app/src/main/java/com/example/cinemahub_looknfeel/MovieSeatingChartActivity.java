package com.example.cinemahub_looknfeel;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cinemahub_looknfeel.databinding.ActivitySeatingChartV3Binding;

public class MovieSeatingChartActivity extends AppCompatActivity {

    // Add the binder:
    private ActivitySeatingChartV3Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seating_chart_v3);
        //setContentView(binding.getRoot());

        initComponent();
    }

    private void initComponent() {

    }

}