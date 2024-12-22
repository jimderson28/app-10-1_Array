package com.example.ex05_recyclerview01a;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // 處理窗口內邊距，避免顯示系統欄
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 設置適配器，並處理按鈕的點擊事件
        ScenesAdapter adapter = new ScenesAdapter(SceneInfo.getSceneInfoFromJson(MainActivity.this), position -> {
            // 獲取當前選中的商品資料
            SceneInfo selectedScene = SceneInfo.getSceneInfoFromJson(MainActivity.this).get(position);

            // 創建 Intent 傳遞資料到 SecondActivity
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("description", selectedScene.getDescription());
            intent.putExtra("country", selectedScene.getCountry());
            intent.putExtra("imageResId", selectedScene.getImgId());
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);
        findViewById(R.id.shop2).setOnClickListener(v -> {
            // 跳轉到 activity_shopping 頁面
            Intent intent = new Intent(MainActivity.this, ShoppingActivity.class);
            startActivity(intent);
        });
    }
    }

