package com.example.ex05_recyclerview01a;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private CartDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        dbHelper = new CartDatabaseHelper(this);

        // 接收從 MainActivity 傳遞過來的數據
        String description = getIntent().getStringExtra("description");
        String country = getIntent().getStringExtra("country");
        int imageResId = getIntent().getIntExtra("imageResId", -1);

        // 設置資料到視圖

        TextView descriptionTextView = findViewById(R.id.descriptionTextView);
        TextView countryTextView = findViewById(R.id.countryTextView);
        ImageView imageView = findViewById(R.id.imageView);
        Button backButton = findViewById(R.id.backButton);
        Button shopButton = findViewById(R.id.shop);

        descriptionTextView.setText(description);
        countryTextView.setText(country);
        if (imageResId != -1) {
            imageView.setImageResource(imageResId);
        }

        // 設置返回按鈕的點擊事件
        backButton.setOnClickListener(v -> finish());

        // 設置購物車按鈕的點擊事件
        shopButton.setOnClickListener(v -> {
            // 建立 SceneInfo 物件
            SceneInfo item = new SceneInfo(0,description, imageResId, country);

            // 新增商品到購物車資料庫
            dbHelper.addToCart(item); // 使用正確的方法

            // 顯示提示訊息
            Toast.makeText(SecondActivity.this, "已加入購物車", Toast.LENGTH_SHORT).show();
        });

    }
}
