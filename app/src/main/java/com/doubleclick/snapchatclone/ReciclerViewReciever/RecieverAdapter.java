package com.doubleclick.snapchatclone.ReciclerViewReciever;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.doubleclick.snapchatclone.R;

import java.util.List;


public class RecieverAdapter extends RecyclerView.Adapter<RecieverViewHolders> {

    private List<RecieverObject> usersList;
    private Context context;

    public RecieverAdapter(List<RecieverObject> usersList, Context context){
        this.usersList = usersList;
        this.context = context;
    }
    @Override
    public RecieverViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_receiver_item, null);
        RecieverViewHolders rcv = new RecieverViewHolders(layoutView);
        return rcv;
    }

//    @Override
//    public void onBindViewHolder(final RecieverViewHolders holder, int position) {
//        holder.mEmail.setText(usersList.get(position).getUsername());
//        holder.mReceive.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                boolean receiveState = !usersList.get(holder.getLayoutPosition()).getReceive();
//                usersList.get(holder.getLayoutPosition()).setReceive(receiveState);
//            }
//        });
//    }

    @Override
    public int getItemCount() {
        return this.usersList.size();
    }

//    private List<RecieverObject> usersList;
//    private Context context;
//
//    public RecieverAdapter(List<RecieverObject> usersList, Context context) {
//        this.usersList = usersList;
//        this.context = context;
//    }
//
//        @NonNull
//        @Override
//    public RecieverViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int i) {
//        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_reciever_item, null);
//       RecieverViewHolders rcv = new RecieverViewHolders(layoutView);
//        return rcv;
//    }
//
    @Override
    public void onBindViewHolder(@NonNull final RecieverViewHolders rcViewHolders, int i) {
        rcViewHolders.mEmail.setText(usersList.get(i).getUsername());

        String imageUrl = usersList.get(i).getProfileImageUrl();

        if (imageUrl.equals("default")) {
            rcViewHolders.profileImage.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.profile));
        } else {
            Glide.with(context).load(imageUrl).into(rcViewHolders.profileImage);
        }

        rcViewHolders.mReceive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean receiveState = !usersList.get(rcViewHolders.getLayoutPosition()).getReceive();
                usersList.get(rcViewHolders.getLayoutPosition()).setReceive(receiveState);
            }
        });
    }
//
//    @Override
//    public int getItemCount() {
//        return this.usersList.size();
//    }
}
