package com.example.ex05_recyclerview01a;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // 接收從 MainActivity 傳遞過來的數據
        String description = getIntent().getStringExtra("description");
        String country = getIntent().getStringExtra("country");
        int imageResId = getIntent().getIntExtra("imageResId", -1);

        // 設置資料
        TextView descriptionTextView = findViewById(R.id.descriptionTextView);
        TextView countryTextView = findViewById(R.id.countryTextView);
        ImageView imageView = findViewById(R.id.imageView);

        descriptionTextView.setText(description);
        countryTextView.setText(country);
        if (imageResId != -1) {
            imageView.setImageResource(imageResId);
        }
    }
}
