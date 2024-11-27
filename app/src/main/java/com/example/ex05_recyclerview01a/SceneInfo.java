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
            InputStream is = context.getAssets().open("sceneInfo_12131131.json");
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


   /* public static String loadJSONFromAssets(Context context, String filename) {
        String jsonStr = null;
        try {
            InputStream is = context.getAssets().open(filename); // 修正文件名處理
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonStr = new String(buffer, StandardCharsets.UTF_8); // 返回 JSON 字符串
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return jsonStr;
    }
}*/


           /* for (Iterator<String> it = jsonObject.keys(); it.hasNext(); ) {
                String region = it.next();
                JSONArray scenes = jsonObject.getJSONArray(region);

                for (int i = 0; i < scenes.length(); i++) {
                    JSONObject scene = scenes.getJSONObject(i);

                    String description = scene.getString("description");
                    int imgId = context.getResources().getIdentifier(
                            scene.getString("imgId").replace("R.drawable.", ""),
                            "drawable",
                            context.getPackageName()
                    );
                    String country = region + " " + scene.getString("country");

                    sceneList.add(new SceneInfo(description, imgId, country));
                }
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return sceneList;
    }
}*/
    /*public static ArrayList<SceneInfo> getMyListDataArrayList2() {
        ArrayList<SceneInfo> myList = new ArrayList<SceneInfo>();
        String[] description = {
                "珠穆朗瑪峰",
                "雷神之井瀑布",d
                "藍冰洞",
                "羅賴馬山",
                "奇蹟石",
                "撒哈拉大沙漠",
                "優勝美地國家公園",
                "大堡礁",
                "魔法河",
                "塞倫蓋蒂平原"

        };
        int[] imgId = {
                R.drawable.view1,
                R.drawable.view2,
                R.drawable.view3,
                R.drawable.view4,
                R.drawable.view5,
                R.drawable.view6,
                R.drawable.view7,
                R.drawable.view8,
                R.drawable.view9,
                R.drawable.view10,
        };
        String[] country = {
                "尼泊爾",
                "美國",
                "冰島",
                "委內瑞拉",
                "挪威",
                "北非",
                "美國加州",
                "澳洲",
                "菲律賓",
                "東非坦尚尼亞"
        };
        for (int i = 0; i < description.length; i++) {
            SceneInfo mySceneInfo = new SceneInfo(description[i], imgId[i], country[i]);
            myList.add(mySceneInfo);
        }
        return myList;
    }
    public static ArrayList<SceneInfo> getMyListDataArrayList() {
        ArrayList<SceneInfo> myList = new ArrayList<SceneInfo>();
        myList.add(new SceneInfo("珠穆朗瑪峰", R.drawable.view1,"尼泊爾"));
        myList.add(new SceneInfo("雷神之井瀑布", R.drawable.view2,"美國"));
        myList.add(new SceneInfo("藍冰洞", R.drawable.view3,"冰島"));
        myList.add(new SceneInfo("羅賴馬山", R.drawable.view4,"委內瑞拉"));
        myList.add(new SceneInfo("奇蹟石", R.drawable.view5,"挪威"));
        myList.add(new SceneInfo("撒哈拉大沙漠", R.drawable.view6,"北非"));
        myList.add(new SceneInfo("優勝美地國家公園", R.drawable.view7,"美國加州"));
        myList.add(new SceneInfo("大堡礁", R.drawable.view8,"澳洲"));
        myList.add(new SceneInfo("魔法河", R.drawable.view9,"菲律賓"));
        myList.add(new SceneInfo("塞倫蓋蒂平原", R.drawable.view10,"東非坦尚尼亞"));
        return  myList;
    }
}*/