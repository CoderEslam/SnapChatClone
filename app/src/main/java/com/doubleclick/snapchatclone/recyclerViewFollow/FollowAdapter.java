package com.doubleclick.snapchatclone.recyclerViewFollow;

import android.content.Context;
import android.graphics.Color;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.doubleclick.snapchatclone.R;
import com.doubleclick.snapchatclone.UserInformation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class FollowAdapter extends RecyclerView.Adapter<FollowViewHolders> {

    private List<FollowObject> usersList;
    private Context context;

    public FollowAdapter(List<FollowObject> usersList, Context context){
        this.usersList = usersList;
        this.context = context;
    }
    @Override
    public FollowViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recylerview_followers_item, null);
        FollowViewHolders rcv = new FollowViewHolders(layoutView);
        return rcv;
    }

//    @Override
//    public void onBindViewHolder(final FollowViewHolders holder, int position) {
////        holder.mEmail.setText(usersList.get(position).getEmail());
//
//        holder.mEmail.setText(usersList.get(position).getUsername());
//
//        if(UserInformation.listFollowing.contains(usersList.get(holder.getLayoutPosition()).getUid())){
//            holder.mFollow.setText("following");
//        }else{
//            holder.mFollow.setText("follow");
//        }
//
//        holder.mFollow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
//                if(!UserInformation.listFollowing.contains(usersList.get(holder.getLayoutPosition()).getUid())){
//                    holder.mFollow.setText("following");
//                    FirebaseDatabase.getInstance().getReference().child("users").child(userId).child("following").child(usersList.get(holder.getLayoutPosition()).getUid()).setValue(true);
//                }else{
//                    holder.mFollow.setText("follow");
//                    FirebaseDatabase.getInstance().getReference().child("users").child(userId).child("following").child(usersList.get(holder.getLayoutPosition()).getUid()).removeValue();
//                }
//            }
//        });
//    }

    @Override
    public int getItemCount() {
        return this.usersList.size();
    }

//    private List<FollowObject> usersList;
//    private Context context;
//
//    public FollowAdapter(List<FollowObject> usersList, Context context) {
//        this.usersList = usersList;
//        this.context = context;
//    }
//
//    @NonNull
//    @Override
//    public FollowViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int i) {
//        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_followers_item, null);
//        FollowViewHolders rcv = new FollowViewHolders(layoutView);
//        return rcv;
//    }
//
    @Override
    public void onBindViewHolder(@NonNull final FollowViewHolders rcViewHolders, int i) {
        rcViewHolders.mEmail.setText(usersList.get(i).getUsername());

        String imageUrl = usersList.get(i).getProfileImageUrl();
        Toast.makeText(context.getApplicationContext(), "Follow Adapter At 91 = Image  = "+imageUrl,Toast.LENGTH_LONG).show();
        if (imageUrl!= null) {
            if (imageUrl.equals("default")) {
                rcViewHolders.profileImage.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.profile));
            } else {
                Glide.with(context).load(imageUrl).into(rcViewHolders.profileImage);
            }
        }

        if (UserInformation.listFollowing.contains(usersList.get(rcViewHolders.getLayoutPosition()).getUid())) {
            rcViewHolders.mFollow.setText("Following");
            rcViewHolders.mFollow.setBackground(ContextCompat.getDrawable(context, R.drawable.btn_round));
            rcViewHolders.mFollow.setTextColor(Color.parseColor("#ffffff"));
        } else {
            rcViewHolders.mFollow.setText("Follow");
            rcViewHolders.mFollow.setBackground(ContextCompat.getDrawable(context, R.drawable.button_follow));
            rcViewHolders.mFollow.setTextColor(ContextCompat.getColor(context, R.color.blueLight));
        }

        rcViewHolders.mFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                if (!UserInformation.listFollowing.contains(usersList.get(rcViewHolders.getLayoutPosition()).getUid())) {
                    rcViewHolders.mFollow.setText("Following");
                    rcViewHolders.mFollow.setBackground(ContextCompat.getDrawable(context, R.drawable.btn_round));
                    rcViewHolders.mFollow.setTextColor(Color.parseColor("#ffffff"));
                    FirebaseDatabase.getInstance().getReference().child("users").child(uid).child("following")
                            .child(usersList.get(rcViewHolders.getLayoutPosition()).getUid()).setValue(true);
                } else {
                    rcViewHolders.mFollow.setText("Follow");
                    rcViewHolders.mFollow.setBackground(ContextCompat.getDrawable(context, R.drawable.button_follow));
                    rcViewHolders.mFollow.setTextColor(ContextCompat.getColor(context, R.color.blueLight));
                    FirebaseDatabase.getInstance().getReference().child("users").child(uid).child("following")
                            .child(usersList.get(rcViewHolders.getLayoutPosition()).getUid()).removeValue();
                }
            }
        });
    }
//
//    @Override
//    public int getItemCount() {
//        return this.usersList.size();
//    }
}
