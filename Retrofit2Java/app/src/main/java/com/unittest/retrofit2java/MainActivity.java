package com.unittest.retrofit2java;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.conscrypt.Conscrypt;

import java.security.Security;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Retrofit retrofit;
    JsonApi jsonApi;
    Call<List<PatientData>> call;
    Call<String> call2;
    Call<String> categoryListCall;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();


        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))    //JSON -> 자바객채변환
                .build();

        jsonApi = retrofit.create(JsonApi.class);
        call2 = jsonApi.postUser(new User("970101", "abc@gmail.com", "홍길동"));
        call2.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    TextView textView = (TextView) findViewById(R.id.textView);
                    //자바 객체로 변환된 JSON데이터 저장
                    String str = response.body();
                    textView.setText(str);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });



    categoryListCall = jsonApi.getCategoryList();

    categoryListCall.enqueue(new Callback<String>() {
        @Override
        public void onResponse(Call<String> call, Response<String> response) {
            if (response.isSuccessful()) {
//                TextView textView = (TextView) findViewById(R.id.textView);
//                //자바 객체로 변환된 JSON데이터 저장
//                String str = response.body();
//                textView.setText(str);
            }
        }

        @Override
        public void onFailure(Call<String> call, Throwable t) {

        }
    });
    }


}

