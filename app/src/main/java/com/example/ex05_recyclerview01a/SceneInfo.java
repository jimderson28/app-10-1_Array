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
    private String description;
    private int imgId;
    private String country;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public SceneInfo(String description, int imgId, String country) {
        this.description = description;
        this.imgId = imgId;
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


    public static ArrayList<SceneInfo> getSceneInfoFromJson(Context context) {
        ArrayList<SceneInfo> sceneList = new ArrayList<>();
        try {
            // 打開 JSON 文件
            InputStream is = context.getAssets().open("12131131.json");
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();

            String json = new String(buffer, StandardCharsets.UTF_8);

            // 解析 JSON 文件
            JSONObject jsonObject = new JSONObject(json);

            // 遍歷每個區域的場景數據
            for (Iterator<String> it = jsonObject.keys(); it.hasNext(); ) {
                JSONArray scenes = jsonObject.getJSONArray(it.next());
                for (int i = 0; i < scenes.length(); i++) {
                    JSONObject scene = scenes.getJSONObject(i);

                    // 提取所需信息
                    String description = scene.getString("description");
                    int imgId = context.getResources().getIdentifier(
                            scene.getString("imgId").replace("R.drawable.", ""),
                            "drawable",
                            context.getPackageName()
                    );
                    String country = scene.getString("country");

                    // 添加到列表
                    sceneList.add(new SceneInfo(description, imgId, country));
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return sceneList;
    }
}