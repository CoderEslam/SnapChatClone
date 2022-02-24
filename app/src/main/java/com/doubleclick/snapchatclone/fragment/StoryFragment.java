package com.doubleclick.snapchatclone.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.doubleclick.snapchatclone.R;
import com.doubleclick.snapchatclone.UserInformation;
import com.doubleclick.snapchatclone.recyclerViewStory.StoryAdapter;
import com.doubleclick.snapchatclone.recyclerViewStory.StoryObject;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.TestOnly;

import java.util.ArrayList;

public class StoryFragment extends
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//        Fragment {
//
//    private RecyclerView mRecyclerView;
//    private RecyclerView.Adapter mAdapter;
//    private RecyclerView.LayoutManager mLayoutManager;
//
//    public static StoryFragment newInstance() {
//        StoryFragment fragment = new StoryFragment();
//        return fragment;
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_story, container, false);
//
//        mRecyclerView = view.findViewById(R.id.recyclerView);
//        mRecyclerView.setNestedScrollingEnabled(false);
//        mRecyclerView.setHasFixedSize(true);
//        mLayoutManager = new LinearLayoutManager(getContext());
//        mRecyclerView.setLayoutManager(mLayoutManager);
//        mAdapter = new StoryAdapter(getDataSet(), getContext());
//        mRecyclerView.setAdapter(mAdapter);
//
//
//        final SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.swipeStory);
//
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                clear();
//                listenForData();
//                swipeRefreshLayout.setRefreshing(false);
//            }
//        });
//
////        Button mResfresh = view.findViewById(R.id.resfresh);
//        clear();
//        listenForData();
////        mResfresh.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                clear();
////                listenForData();
////            }
////        });
//        return view;
//    }
//
//    @Override
//    public void setUserVisibleHint ( boolean isVisibleToUser){
//        super.setUserVisibleHint(isVisibleToUser);
//        if (isVisibleToUser && isResumed()) {
//            clear();
//            listenForData();
//        }
//    }
//
//    private void clear() {
//        int size = this.results.size();
//        this.results.clear();
//        mAdapter.notifyItemRangeChanged(0, size);
//    }
//
//
//    private ArrayList<StoryObject> results = new ArrayList<>();
//
//    private ArrayList<StoryObject> getDataSet() {
//        listenForData();
//        return results;
//    }
//
//    private void listenForData() {
//        for (int i = 0; i < UserInformation.listFollowing.size(); i++) {
//            DatabaseReference followingStoryDb = FirebaseDatabase.getInstance().getReference().child("users").child(UserInformation.listFollowing.get(i));
//            followingStoryDb.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    String email = dataSnapshot.child("email").getValue().toString();
//                    String uid = dataSnapshot.getRef().getKey();
//                    long timestampBeg = 0;
//                    long timestampEnd = 0;
//                    for (DataSnapshot storySnapshot : dataSnapshot.child("story").getChildren()) {
//                        if (storySnapshot.child("timestampBeg").getValue() != null) {
//                            timestampBeg = Long.parseLong(storySnapshot.child("timestampBeg").getValue().toString());
//                        }
//                        if (storySnapshot.child("timestampEnd").getValue() != null) {
//                            timestampEnd = Long.parseLong(storySnapshot.child("timestampEnd").getValue().toString());
//                        }
//                        long timestampCurrent = System.currentTimeMillis();
////                        Toast.makeText(getContext(),"StoryFragment At 121 = "+timestampCurrent,Toast.LENGTH_LONG).show();
//                        if (timestampCurrent >= timestampBeg && timestampCurrent <= timestampEnd) {
//                            StoryObject obj = new StoryObject(email, uid, "story");
//                            if (!results.contains(obj)) {
//                                results.add(obj);
//                                mAdapter.notifyDataSetChanged();
//                            }
//                        }
//
//                    }
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//                }
//            });
//        }
//    }
//}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<StoryObject> results = new ArrayList<>();

    public static StoryFragment newInstance() {
        StoryFragment fragment = new StoryFragment();
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
        View view = inflater.inflate(R.layout.fragment_story, container, false);

        mRecyclerView = view.findViewById(R.id.recyclerViewStory);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setHasFixedSize(false);

        layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);

        adapter = new StoryAdapter(getDataset(), getContext());

        mRecyclerView.setAdapter(adapter);

        final SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.swipeStory);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                clear();
                listenForData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        clear();
        listenForData();

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
        for (int i = 0; i < UserInformation.listFollowing.size(); i++) {
            DatabaseReference followingStoryDB = FirebaseDatabase.getInstance().getReference().child("users").child(UserInformation.listFollowing.get(i));
            followingStoryDB.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String username = dataSnapshot.child("name").getValue().toString();
                    String profileImageUrl = dataSnapshot.child("profileImageUrl").getValue().toString();
                    String uid = dataSnapshot.getRef().getKey();

                    long timestampBeg = 0, timestampEnd = 0;

                    for (DataSnapshot storySnapshot : dataSnapshot.child("story").getChildren()) {
                        if (storySnapshot.child("timestampBeg").getValue() != null) {
                            timestampBeg = Long.parseLong(storySnapshot.child("timestampBeg").getValue().toString());
                        }
                        if (storySnapshot.child("timestampEnd").getValue() != null) {
                            timestampEnd = Long.parseLong(storySnapshot.child("timestampEnd").getValue().toString());
                        }

                        long currentTime = System.currentTimeMillis();

                        if (currentTime >= timestampBeg && currentTime <= timestampEnd) {
                            StoryObject object = new StoryObject(username, uid, profileImageUrl,"story");
                            if (!results.contains(object)) {
                                results.add(object);
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }
}
