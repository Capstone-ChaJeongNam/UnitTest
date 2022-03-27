package com.unittest.firebasedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.unittest.firebasedemo.adapter.MessageAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "Firebase";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // region Firebase
        /**
         * Firebase Realtime Database 인스턴스 저장(연결)
         * message 라는 하위 경로 존재하면 그대로 사용
         * 아니면 새로 생성
         */
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        /**
         * Message Model Class 생성
         * 클래스 안에 속성 값들 입력하고, 모델 객체로 DB에 저장해도
         * 그 안의 속성 값들을 저장할 수 있음
         */
        Message message = new Message();
        message.setName("name");
        message.setContent("content");

        /**
         * Firebase Lib 사용하여 Key값 생성, 속성으로 저장
         */
        String key = myRef.push().getKey();
        message.setKey(key);

        /**
         * 선언한 모델 객체를 message 경로 하위에 저장
         * 각 reference에서 child라는 함수로 계속 하위 경로를 지정 할 수 있음.
         * ref.child(_).child(_).child(_).....
         *
         * 아래 예시에선 하나의 하위 경로만 선언.
         *
         * 저장한 key값의 하위 경로에 message 객체 저장
         */
        myRef.child(key).setValue(message);
//        myRef.setValue(message);

        /**
         * DB에 저장된 값들을 저장할 리스트 선언
         */
        List<Message> messages = new ArrayList<>();
        // Read from the database

        /**
         * DB message 경로에 저장된 값들을 읽어옴.
         */
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                /**
                 * message 경로 하위에 있는 값들을 가져오기 위해
                 * dataSnapshot이라는 파라미터를 참조하는 getChildren() 함수 사용.
                 */
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {

                    /**
                     * message 경로 하위 값들은 Message 클래스의 객체로 저장했으므로
                     * Message.class 의 형태로 가져올 수 있음.
                     *
                     * 가져온 값들을 위에서 선언한 리스트에 저장.
                     * Log를 사용하여 Message 클래스 객체의 속성 값들 출력
                     */
                    Message value = dsp.getValue(Message.class);
                    messages.add(value);

                    Log.d(TAG, "name is: " + value.getName());
                    Log.d(TAG, "content is: " + value.getContent());
                    Log.d(TAG, "key is: " + value.getKey());//add result into array list
                }
                RecyclerView messageRecyclerView = findViewById(R.id.messageRecyclerView);
                messageRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                MessageAdapter messageAdapter = new MessageAdapter(messages);
                messageRecyclerView.setAdapter(messageAdapter);
//                Message value = dataSnapshot.getValue(Message.class);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        //endregion


    }

}