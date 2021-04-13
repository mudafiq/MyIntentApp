package com.pintarngoding.myintentapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class MoveForResultActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int RESULT_CODE = 110;
    public static final String EXTRA_VALUE = "EXTRA_VALUE";
    private Button buttonSubmit;
    private RadioGroup radioGroupAngka;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_for_result);

        radioGroupAngka = findViewById(R.id.radioGroupAngka);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (radioGroupAngka.getCheckedRadioButtonId() != 0){
            int value = 0;
            switch (radioGroupAngka.getCheckedRadioButtonId()){
                case R.id.radioButton100:
                    value = 100;
                    break;
                case R.id.radioButton50:
                    value = 50;
                    break;
                case R.id.radioButton32:
                    value = 32;
                    break;
            }
            Intent resultIntent = new Intent();
            resultIntent.putExtra(EXTRA_VALUE, value);
            setResult(RESULT_CODE, resultIntent);
            finish();
        }
    }
}
