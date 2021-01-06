package com.alaaelshazly.freeyourmind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Transparent Action Bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getSupportActionBar().hide();

    }

    public void login(View view) {
        Intent in=new Intent(this,LoginActivity.class);
        startActivity(in);
    }

    public void register(View view) {
        Intent in=new Intent(this,SignUpActivity.class);
        startActivity(in);

    }

}