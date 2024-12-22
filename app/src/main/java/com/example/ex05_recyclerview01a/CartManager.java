package com.example.ex05_recyclerview01a;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static final List<SceneInfo> cartItems = new ArrayList<>();

    // 添加到購物車
    public static void addToCart(SceneInfo item) {
        cartItems.add(item);
    }

    // 獲取購物車的商品
    public static List<SceneInfo> getCartItems() {
        return cartItems;
    }

    // 從購物車刪除商品
    public static void removeFromCart(SceneInfo item) {
        if (cartItems.contains(item)) {
            cartItems.remove(item);
        } else {
            throw new IllegalArgumentException("Item not found in cart");
        }
    }
}
