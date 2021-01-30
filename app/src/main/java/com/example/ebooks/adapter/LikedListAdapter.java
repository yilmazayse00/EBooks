package com.example.ebooks.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ebooks.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LikedListAdapter extends RecyclerView.Adapter<LikedListAdapter.PostHolder> {

    private ArrayList<String> booknameList;
    private ArrayList<String> imageList;

    public LikedListAdapter(ArrayList<String> booknameList, ArrayList<String> imageList) {
        this.booknameList = booknameList;
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.liked_row, parent ,false);

        return new PostHolder(view);
    }

    @Override
    public int getItemCount() {
        return booknameList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        holder.textView.setText(booknameList.get(position));
//        Picasso.get().load(imageList.get(position)).into(holder.imageView);
    }

    class PostHolder extends RecyclerView.ViewHolder{

        TextView textView;
        ImageView imageView;

        public PostHolder(@NonNull View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.tvBookName);
            imageView = itemView.findViewById(R.id.likeImage);
        }
    }
}
