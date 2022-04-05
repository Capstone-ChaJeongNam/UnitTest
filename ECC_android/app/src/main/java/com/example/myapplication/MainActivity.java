package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private MainAdapter mainAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    Firebase firebase = new Firebase();
    ArrayList<MainData> arrayList = new ArrayList<>();
    List<MainData> datas;


    private Button button;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebase.fire();
        button = (Button) findViewById(R.id.btn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                recyclerView = (RecyclerView) findViewById(R.id.rv);
                linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(MainActivity.this,linearLayoutManager.getOrientation());
                PhOffsetItemDecoration itemDecoration=new PhOffsetItemDecoration(50);
                recyclerView.addItemDecoration(itemDecoration);
                recyclerView.addItemDecoration(dividerItemDecoration);
                recyclerView.setLayoutManager(linearLayoutManager);
                mainAdapter = new MainAdapter((ArrayList<MainData>) firebase.getDatas(), MainActivity.this);
                recyclerView.setAdapter(mainAdapter);
            }
        });
        // Read from the database


    }


}



