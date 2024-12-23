package com.example.ex05_recyclerview01a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class CartDatabaseHelper extends SQLiteOpenHelper {

    // 資料庫名稱與版本
    private static final String DATABASE_NAME = "cart.db";
    private static final int DATABASE_VERSION = 1;

    // 資料表名稱與欄位名稱
    private static final String TABLE_CART = "cart";
    private static final String COLUMN_ID = "id"; // 自動生成的主鍵
    private static final String COLUMN_DESCRIPTION = "description"; // 商品描述
    private static final String COLUMN_IMG_ID = "img_id"; // 商品圖片資源 ID
    private static final String COLUMN_COUNTRY = "country"; // 商品來源國

    public CartDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 建立購物車資料表
        String CREATE_CART_TABLE = "CREATE TABLE " + TABLE_CART + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_IMG_ID + " INTEGER, " +
                COLUMN_COUNTRY + " TEXT)";
        db.execSQL(CREATE_CART_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 如果資料表已存在，先刪除再重新建立
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CART);
        onCreate(db);
    }

    // 添加商品到購物車
    public void addToCart(SceneInfo scene) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DESCRIPTION, scene.getDescription());
        values.put(COLUMN_IMG_ID, scene.getImgId());
        values.put(COLUMN_COUNTRY, scene.getCountry());
        db.insert(TABLE_CART, null, values); // 插入新資料
        db.close();
    }

    // 獲取購物車的所有商品
    public ArrayList<SceneInfo> getCartItems() {
        ArrayList<SceneInfo> cartItems = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // 查詢所有商品
        Cursor cursor = db.query(TABLE_CART, null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                // 從資料庫中讀取商品欄位
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
                String description = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION));
                int imgId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_IMG_ID));
                String country = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COUNTRY));

                // 建立 SceneInfo 物件並加入列表
                SceneInfo item = new SceneInfo(0,description, imgId, country);
                item.setId(id); // 如果 SceneInfo 有 ID 欄位，則設置它
                cartItems.add(item);
            }
            cursor.close();
        }
        db.close();
        return cartItems;
    }

    // 從購物車移除商品
    public void removeFromCart(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CART, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    // 清空購物車
    public void clearCart() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CART, null, null); // 刪除所有資料
        db.close();
    }
}
