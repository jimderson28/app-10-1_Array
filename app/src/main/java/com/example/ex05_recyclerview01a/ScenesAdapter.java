package com.example.ex05_recyclerview01a;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ex05_recyclerview01a.R;
import com.example.ex05_recyclerview01a.SceneInfo;

import java.util.List;

public class ScenesAdapter extends RecyclerView.Adapter<ScenesAdapter.ViewHolder> {

    private List<SceneInfo> scenesList;
    private OnDeleteButtonClickListener deleteListener;

    // 刪除按鈕回調接口
    public interface OnDeleteButtonClickListener {
        void onDeleteButtonClick(int position);
    }

    // 新增構造函數
    public ScenesAdapter(List<SceneInfo> scenesList, OnDeleteButtonClickListener deleteListener) {
        this.scenesList = scenesList;
        this.deleteListener = deleteListener;
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

        // 設置刪除按鈕點擊事件
        holder.deleteButton.setOnClickListener(v -> {
            if (deleteListener != null) {
                deleteListener.onDeleteButtonClick(position);
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
        Button deleteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            descriptionTextView = itemView.findViewById(R.id.textView);
            countryTextView = itemView.findViewById(R.id.textView2);
            imageView = itemView.findViewById(R.id.imageView);
            deleteButton = itemView.findViewById(R.id.Button1); // 刪除按鈕
        }
    }
}
