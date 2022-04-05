package com.example.myapplication;

import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Firebase {


    private MainAdapter mainAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private static final String TAG = "MainActivity";
    private List<MainData> datas = new ArrayList<>();

    public List<MainData> getDatas() {
        return datas;
    }

    public void fire() {


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("ECC").child("데이지플레이어");

        myRef.addValueEventListener(new ValueEventListener() {

            MainData mainData;

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

//                        getMainData();
//                파이어베이스의 데이터 받아오는 곳

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    mainData = snapshot.getValue(MainData.class);
                    datas.add(mainData); //어레이리스트에 데이터 넣은 상태}

                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


    }

}
