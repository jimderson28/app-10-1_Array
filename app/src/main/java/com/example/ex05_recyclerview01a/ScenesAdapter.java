package com.example.ex05_recyclerview01a;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ScenesAdapter extends RecyclerView.Adapter<ScenesAdapter.ViewHolder> {
    private ArrayList<SceneInfo> scenesList;

    public ScenesAdapter(ArrayList<SceneInfo> scenesList) {
        this.scenesList = scenesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SceneInfo scene = scenesList.get(position);
        holder.descriptionTextView.setText(scene.getDescription());
        holder.countryTextView.setText(scene.getCountry());
        holder.imageView.setImageResource(scene.getImgId());
    }

    @Override
    public int getItemCount() {
        return scenesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView descriptionTextView;
        TextView countryTextView;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            descriptionTextView = itemView.findViewById(R.id.textView);
            countryTextView = itemView.findViewById(R.id.textView2);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
