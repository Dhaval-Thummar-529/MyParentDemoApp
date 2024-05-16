package com.example.myparentdemoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class BlockViewActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_block_view);
        textView = findViewById(R.id.txtBlockedAppName);
        textView.setText(getIntent().getExtras().getString("BLOCKED_APP_NAME_EXTRA"));
    }
}