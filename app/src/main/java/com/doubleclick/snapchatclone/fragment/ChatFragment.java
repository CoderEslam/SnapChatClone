package com.doubleclick.snapchatclone.fragment;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.doubleclick.snapchatclone.R;
import com.doubleclick.snapchatclone.recyclerViewStory.StoryAdapter;
import com.doubleclick.snapchatclone.recyclerViewStory.StoryObject;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChatFragment extends
        ///////////////////////////////////////////////////////////////////////////////////////////////////////
//        Fragment{
//
//
//    private RecyclerView mRecyclerView;
//    private RecyclerView.Adapter mAdapter;
//    private RecyclerView.LayoutManager mLayoutManager;
//
//    String uid;
//
//    public static ChatFragment newInstance(){
//        ChatFragment fragment = new ChatFragment();
//        return fragment;
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_chat , container, false);
//
//        uid = FirebaseAuth.getInstance().getUid();
//
//        mRecyclerView = view.findViewById(R.id.recyclerView);
//        mRecyclerView.setNestedScrollingEnabled(false);
//        mRecyclerView.setHasFixedSize(true);
//        mLayoutManager = new LinearLayoutManager(getContext());
//        mRecyclerView.setLayoutManager(mLayoutManager);
//        mAdapter = new StoryAdapter(getDataSet(), getContext());
//        mRecyclerView.setAdapter(mAdapter);
//
//        Button mRefresh = view.findViewById(R.id.refresh);
//        mRefresh.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                clear();
//                listenForData();
//            }
//        });
//        return view;
//    }
//
//    private void clear() {
//        int size = this.results.size();
//        this.results.clear();
//        mAdapter.notifyItemRangeChanged(0, size);
//    }
//
//    private ArrayList<StoryObject> results = new ArrayList<>();
//    private ArrayList<StoryObject> getDataSet() {
//        listenForData();
//        return results;
//    }
//
//    private void listenForData(){
//        DatabaseReference receivedDb = FirebaseDatabase.getInstance().getReference().child("users").child(uid).child("received");
//        receivedDb.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if(dataSnapshot.exists()){
//                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
//                        getUserInfo(snapshot.getKey());
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }
//
//    private void getUserInfo(String chatUid) {
//        DatabaseReference chatUserDb = FirebaseDatabase.getInstance().getReference().child("users").child(chatUid);
//        chatUserDb.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if(dataSnapshot.exists()){
//                    String email = dataSnapshot.child("email").getValue().toString();
//                    String uid = dataSnapshot.getRef().getKey();
//
//                    StoryObject obj = new StoryObject(email, uid, "chat");
//                    if(!results.contains(obj)){
//                        results.add(obj);
//                        mAdapter.notifyDataSetChanged();
//                    }
//
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }
//}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<StoryObject> results = new ArrayList<>();

    public static ChatFragment newInstance() {
        ChatFragment fragment = new ChatFragment();
        return fragment;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isResumed()) {
            clear();
            listenForData();
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat , container, false);

        mRecyclerView = view.findViewById(R.id.recyclerViewStory);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setHasFixedSize(false);

        layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);

        adapter = new StoryAdapter(getDataset(), getContext());

        mRecyclerView.setAdapter(adapter);

        final SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.swipeChat);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                clear();
                listenForData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        return view;
    }

    private void clear() {
        int size = this.results.size();
        this.results.clear();
        adapter.notifyItemRangeChanged(0, size);
    }

    private ArrayList<StoryObject> getDataset() {
        listenForData();
        return results;
    }

    private void listenForData() {
        DatabaseReference receiveDB = FirebaseDatabase.getInstance().getReference().child("users")
                .child(FirebaseAuth.getInstance().getUid()).child("received");

        receiveDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snap : dataSnapshot.getChildren()) {
                        getUserInfo(snap.getKey());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void getUserInfo(String key) {
        DatabaseReference userDB = FirebaseDatabase.getInstance().getReference().child("users").child(key);
        userDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String username = dataSnapshot.child("name").getValue().toString();
                    String profileImageUrl = dataSnapshot.child("profileImageUrl").getValue().toString();
                    String uid = dataSnapshot.getRef().getKey();

                    StoryObject obj = new StoryObject(username, uid, profileImageUrl,"chat");
                    if (!results.contains(obj)) {
                        results.add(obj);
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
