package com.example.ex05_recyclerview01a;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShoppingActivity extends AppCompatActivity {

    private CartDatabaseHelper dbHelper;
    private ScenesAdapter adapter;
    private ArrayList<SceneInfo> cartItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        // 初始化資料庫輔助類
        dbHelper = new CartDatabaseHelper(this);

        // 獲取購物車的商品列表
        cartItems = dbHelper.getCartItems();

        // 設置 RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 初始化適配器
        adapter = new ScenesAdapter(cartItems, position -> {
            // 刪除商品
            SceneInfo itemToRemove = cartItems.get(position);
            dbHelper.removeFromCart(itemToRemove.getId()); // 根據 ID 從資料庫刪除
            cartItems.remove(position);

            // 更新 RecyclerView
            adapter.notifyItemRemoved(position);
            adapter.notifyItemRangeChanged(position, cartItems.size());
        });

        recyclerView.setAdapter(adapter);
    }
}
