package com.example.kjh_stock01.ui.home.post;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kjh_stock01.R;

import java.util.ArrayList;

public class PostListAdapter extends RecyclerView.Adapter<PostViewHolder> {

    ArrayList<Post> items = new ArrayList<Post>();
    private PostItemClicked listener;

    public PostListAdapter(PostItemClicked postItemClicked) {
        this.listener = postItemClicked;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        PostViewHolder viewHolder = new PostViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {

        Post currentItem = items.get(position);

        if(currentItem.getId() == "null"){
            holder.Id.setText("Unkown Source");
        }else {
            holder.Id.setText(currentItem.getId());
        }

        if(currentItem.getUserId() == "null"){
            holder.UserId.setText("Unkown Source");
        }else {
            holder.UserId.setText(currentItem.getUserId());
        }

        if(currentItem.getTitle() == "null"){
            holder.Title.setText("Unkown Source");
        }else {
            holder.Title.setText(currentItem.getTitle());
        }

        if(currentItem.getText() == "null"){
            holder.Text.setText("Unkown Source");
        }else {
            holder.Text.setText(currentItem.getText());
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void updateNews(ArrayList<Post> updatePost) {
        items.clear();
        items.addAll(updatePost);

        notifyDataSetChanged();
    }
}

class PostViewHolder extends RecyclerView.ViewHolder {
    TextView Id, UserId, Title, Text;

    public PostViewHolder(@NonNull View view){
        super(view);
        Id = view.findViewById(R.id.Post_ID);
        UserId = view.findViewById(R.id.Post_UserId);
        Title = view.findViewById(R.id.Post_Title);
        Text = view.findViewById(R.id.Post_Text);
    }
}