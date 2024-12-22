package com.example.ex05_recyclerview01a;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ShoppingActivity extends AppCompatActivity {

    private ScenesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        // 獲取購物車的商品列表
        List<SceneInfo> cartItems = CartManager.getCartItems();

        // 設置 RecyclerView 顯示購物車內容
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 設置適配器，並處理刪除按鈕的點擊事件
        adapter = new ScenesAdapter(cartItems, position -> {
            // 刪除商品
            SceneInfo itemToRemove = cartItems.get(position);
            CartManager.removeFromCart(itemToRemove);

            // 更新 RecyclerView
            adapter.notifyItemRemoved(position);
            adapter.notifyItemRangeChanged(position, cartItems.size());
        });
        recyclerView.setAdapter(adapter);
    }
}
