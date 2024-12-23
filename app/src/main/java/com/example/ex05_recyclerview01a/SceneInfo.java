package com.example.ex05_recyclerview01a;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;

public class SceneInfo {
    private int id;
    private String description; // 商品描述
    private int imgId; // 圖片資源 ID
    private String country; // 所屬國家


    // Getter 和 Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    // 構造方法
    public SceneInfo(int id,String description, int imgId, String country) {
        this.id = id;
        this.description = description;
        this.imgId = imgId;
        this.country = country;
    }

    // 靜態方法：從 JSON 文件中解析數據並返回 ArrayList
    public static ArrayList<SceneInfo> getSceneInfoFromJson(Context context) {
        ArrayList<SceneInfo> sceneList = new ArrayList<>();
        try {
            // 從 assets 資料夾中讀取 JSON 文件
            InputStream is = context.getAssets().open("12131131.json");
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();

            // 將讀取的數據轉換為 JSON 字符串
            String json = new String(buffer, StandardCharsets.UTF_8);

            // 將 JSON 字符串解析為 JSONObject
            JSONObject jsonObject = new JSONObject(json);

            // 遍歷 JSON 中的所有鍵（表示場景區域）
            for (Iterator<String> it = jsonObject.keys(); it.hasNext(); ) {
                // 獲取區域名稱對應的場景陣列
                String key = it.next();
                JSONArray scenes = jsonObject.getJSONArray(key);

                // 遍歷每個場景數據
                for (int i = 0; i < scenes.length(); i++) {
                    JSONObject scene = scenes.getJSONObject(i);

                    // 提取場景數據
                    String description = scene.getString("description");
                    int imgId = context.getResources().getIdentifier(
                            scene.getString("imgId").replace("R.drawable.", ""),
                            "drawable",
                            context.getPackageName()
                    );
                    String country = scene.getString("country");

                    // 創建 SceneInfo 對象並添加到列表
                    sceneList.add(new SceneInfo(0,description, imgId, country));
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace(); // 處理 IO 異常
        } catch (JSONException e) {
            throw new RuntimeException(e); // 處理 JSON 解析異常
        }

        return sceneList; // 返回場景列表
    }
}
