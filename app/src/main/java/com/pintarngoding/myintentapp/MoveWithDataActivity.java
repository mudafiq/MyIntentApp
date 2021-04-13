package com.pintarngoding.myintentapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MoveWithDataActivity extends AppCompatActivity {

    private TextView tvdataintent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_with_data);

        tvdataintent = findViewById(R.id.tvdataintent);

        String nama = getIntent().getStringExtra("extra_name");
        int age = getIntent().getIntExtra("extra_age", 0);
        String text = "Nama: "+nama+", usia: "+age;
        tvdataintent.setText(text);
    }
}
