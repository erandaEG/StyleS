package com.example.earzo.styles.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.earzo.styles.R;

public class activity_forgottenPasswordChange extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotten_password_change);
    }

    @Override
    public void onResume(){
        super.onResume();

        setTitle(R.string.app_name);
    }
}
