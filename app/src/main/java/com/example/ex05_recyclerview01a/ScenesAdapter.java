package com.example.ex05_recyclerview01a;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ScenesAdapter extends RecyclerView.Adapter<ScenesAdapter.ViewHolder> {

    private ArrayList<SceneInfo> scenesList;
    private OnButtonClickListener listener;

    // 回調接口
    public interface OnButtonClickListener {
        void onButtonClick(int position);
    }

    // 修改構造函數，傳入回調接口
    public ScenesAdapter(ArrayList<SceneInfo> scenesList, OnButtonClickListener listener) {
        this.scenesList = scenesList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SceneInfo scene = scenesList.get(position);
        holder.descriptionTextView.setText(scene.getDescription());
        holder.countryTextView.setText(scene.getCountry());
        holder.imageView.setImageResource(scene.getImgId());

        // 設置按鈕點擊事件
        holder.button1.setOnClickListener(v -> {
            if (listener != null) {
                listener.onButtonClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return scenesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView descriptionTextView;
        TextView countryTextView;
        ImageView imageView;
        Button button1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            descriptionTextView = itemView.findViewById(R.id.textView);
            countryTextView = itemView.findViewById(R.id.textView2);
            imageView = itemView.findViewById(R.id.imageView);
            button1 = itemView.findViewById(R.id.Button1); // 對應 list_item.xml 中的按鈕 ID
        }
    }
}
