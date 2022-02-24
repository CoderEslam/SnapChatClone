package com.doubleclick.snapchatclone.recyclerViewStory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.doubleclick.snapchatclone.DisplayImageActivity;
import com.doubleclick.snapchatclone.R;


public class StoryViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {

//    public TextView mEmail;
    public LinearLayout mLayout;

    public TextView username;
    public ImageView profileImage;
    public CardView cardView;
    public StoryViewHolders(View itemView){
        super(itemView);
        itemView.setOnClickListener(this);
        username = itemView.findViewById(R.id.textViewUsernameStory);
        mLayout = itemView.findViewById(R.id.layout);
        profileImage = itemView.findViewById(R.id.imageViewStoryProfile);
        cardView = itemView.findViewById(R.id.cardViewStory);

    }


    @Override
    public void onClick(View view) {
//        Intent intent = new Intent(view.getContext(), DisplayImageActivity.class);
//        Bundle b = new Bundle();
//        b.putString("userId", username.getTag().toString());
//        b.putString("chatOrStory", mLayout.getTag().toString());
//        intent.putExtras(b);
//        view.getContext().startActivity(intent);
        Intent intent = new Intent(view.getContext(), DisplayImageActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("userId", username.getTag().toString());
//        bundle.putString("profileImageUrl", cardView.getTag().toString());
        bundle.putString("profileImageUrl",profileImage.getTag().toString());
        bundle.putString("username", username.getText().toString());
        bundle.putString("chatOrStory", mLayout.getTag().toString());
        intent.putExtras(bundle);
        view.getContext().startActivity(intent);
    }
//    public TextView username;
//    public LinearLayout layout;
//    public ImageView profileImage;
//    public CardView cardView;
//
//    public StoryViewHolders(View itemView) {
//        super(itemView);
//        itemView.setOnClickListener(this);
//        username = itemView.findViewById(R.id.textViewUsernameStory);
//        layout = itemView.findViewById(R.id.layoutStory);
//        profileImage = itemView.findViewById(R.id.imageViewStoryProfile);
//        cardView = itemView.findViewById(R.id.cardViewStory);
//    }
//
//    @Override
//    public void onClick(View view) {
//        Intent intent = new Intent(view.getContext(), DisplayImageActivity.class);
//        Bundle b = new Bundle();
//        b.putString("uid", username.getTag().toString());
//        b.putString("profileImageUrl", cardView.getTag().toString());
//        b.putString("username", username.getText().toString());
//        b.putString("chatOrStory", layout.getTag().toString());
//        intent.putExtras(b);
//        view.getContext().startActivity(intent);
//    }
}
