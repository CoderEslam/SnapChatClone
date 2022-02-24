package com.doubleclick.snapchatclone;



import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.doubleclick.snapchatclone.recyclerViewFollow.FollowAdapter;
import com.doubleclick.snapchatclone.recyclerViewFollow.FollowObject;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.annotations.Nullable;

import java.util.ArrayList;

public class FindUsersActivity extends AppCompatActivity {

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    private RecyclerView mRecyclerView;
//    private RecyclerView.Adapter mAdapter;
//    private RecyclerView.LayoutManager mLayoutManager;
//
//    EditText mInput;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_find_users);
//
//        mInput = findViewById(R.id.input);
//        Button mSearch = findViewById(R.id.search);
//
//        mRecyclerView = findViewById(R.id.recyclerView);
//        mRecyclerView.setNestedScrollingEnabled(false);
//        mRecyclerView.setHasFixedSize(true);
//        mLayoutManager = new LinearLayoutManager(getApplication());
//        mRecyclerView.setLayoutManager(mLayoutManager);
//        mAdapter = new FollowAdapter(getDataSet(),getApplication());
//        mRecyclerView.setAdapter(mAdapter);
//
//        mSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                clear();
//                listenForData();
//            }
//        });
//
//    }
//
//
//
//    private void listenForData() {
//        DatabaseReference usersDb = FirebaseDatabase.getInstance().getReference().child("users");
//        Query query = usersDb.orderByChild("email").startAt(mInput.getText().toString()).endAt(mInput.getText().toString() + "\uf8ff");
//        query.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                String email = "";
//                String uid = dataSnapshot.getRef().getKey();
//                if(dataSnapshot.child("email").getValue() != null){
//                    email = dataSnapshot.child("email").getValue().toString();
//                }
//                if(!email.equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())){
//                    FollowObject obj = new FollowObject(email, uid);
//                    results.add(obj);
//                    mAdapter.notifyDataSetChanged();
//                }
//
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }
//    private void clear() {
//        int size = this.results.size();
//        this.results.clear();
//        mAdapter.notifyItemRangeChanged(0, size);
//    }
//
//
//
//    private ArrayList<FollowObject> results = new ArrayList<>();
//    private ArrayList<FollowObject> getDataSet() {
//        listenForData();
//        return results;
//    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private EditText mInput;

    private ArrayList<FollowObject> results = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_users);

        mInput = findViewById(R.id.editTextFindUsers);
        Bundle bundle = getIntent().getExtras();

        mInput.setText(bundle.getString("currentUsername"));
        mInput.setSelection(mInput.getText().length());

        ImageButton mSearch = findViewById(R.id.findUsersSearch);
        ImageButton mBack = findViewById(R.id.findUsersBack);
        ImageButton mClear = findViewById(R.id.findUsersSearch);

        mRecyclerView = findViewById(R.id.recyclerViewFindUsers);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setHasFixedSize(false);

        layoutManager = new LinearLayoutManager(getApplication());
        mRecyclerView.setLayoutManager(layoutManager);

        adapter = new FollowAdapter(getDataset(), getApplication());

        mRecyclerView.setAdapter(adapter);

        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear();
                listenForData();
            }
        });

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInput.setText("");
            }
        });

        mInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                clear();
                listenForData();
            }
        });
    }

    private void listenForData() {
        DatabaseReference usersDB = FirebaseDatabase.getInstance().getReference().child("users");
        Query query = usersDB.orderByChild("email").startAt(mInput.getText().toString()).endAt(mInput.getText().toString() + "\uf8ff");

        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String username = "";
                String profileImageUrl = "";
                String uid = dataSnapshot.getRef().getKey();

                if (dataSnapshot.child("name").getValue() != null) {
                    username = dataSnapshot.child("name").getValue().toString();
                }

                if (dataSnapshot.child("profileImageUrl").getValue().toString() != null) {
                    profileImageUrl = dataSnapshot.child("profileImageUrl").getValue().toString();
                }

                if (!uid.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                    FollowObject obj = new FollowObject(username, uid, profileImageUrl);
                    results.add(obj);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void clear() {
        int size = this.results.size();
        this.results.clear();
        adapter.notifyItemRangeChanged(0, size);
    }

    private ArrayList<FollowObject> getDataset() {
        listenForData();
        return results;
    }
}
