package com.example.mvvmrecyclerviewtemp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mvvmrecyclerviewtemp.R;
import com.example.mvvmrecyclerviewtemp.models.NicePlace;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private Context context;
    private List<NicePlace> list = new ArrayList<>();

    public RecyclerViewAdapter(Context context, List<NicePlace> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.bind(list.get(position), position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            textView = itemView.findViewById(R.id.text_view);
        }

        void bind(NicePlace nicePlace, int position) {
            Glide.with(context).load(nicePlace.getImageUrl()).into(imageView);
            textView.setText(nicePlace.getTitle());
        }
    }

    public void setList(List<NicePlace> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}
