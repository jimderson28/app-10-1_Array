package com.example.ex05_recyclerview01a;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        Button backButton = findViewById(R.id.backButton);
        Button shop = findViewById(R.id.shop);

        descriptionTextView.setText(description);
        countryTextView.setText(country);
        if (imageResId != -1) {
            imageView.setImageResource(imageResId);
        }

        // 設置返回按鈕的點擊事件
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 顯示提示信息
                Toast.makeText(SecondActivity.this, "已加入購物車", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
