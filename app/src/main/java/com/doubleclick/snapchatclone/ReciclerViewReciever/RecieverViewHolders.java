package com.doubleclick.snapchatclone.ReciclerViewReciever;

import android.view.View;

import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.doubleclick.snapchatclone.R;


public class RecieverViewHolders extends RecyclerView.ViewHolder {

    public TextView mEmail;
    public CheckBox mReceive;
    public ImageView profileImage;
    public RecieverViewHolders(View itemView){
        super(itemView);
        mEmail = itemView.findViewById(R.id.textViewUsernameFollowItem);
        mReceive = itemView.findViewById(R.id.recieverId);
        profileImage = itemView.findViewById(R.id.imageViewProfileFollowItem);

    }
//    public TextView username;
//    public CheckBox receiveCheck;
//    public ImageView profileImage;
//
//    public RecieverViewHolders(View itemView) {
//        super(itemView);
//
//        username = itemView.findViewById(R.id.textViewUsernameFollowItem);
//        receiveCheck = itemView.findViewById(R.id.recieverId);
//        profileImage = itemView.findViewById(R.id.imageViewProfileFollowItem);
//    }

}
