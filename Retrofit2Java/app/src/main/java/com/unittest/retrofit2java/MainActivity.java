package com.unittest.retrofit2java;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Retrofit retrofit;
    JsonApi jsonApi;
    Call<List<PatientData>> call;
    Call<User> call2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://eccwithnodejs.df.r.appspot.com/")	//베이스 url등록
                .addConverterFactory(GsonConverterFactory.create())	//JSON -> 자바객채변환
                .build();

        jsonApi = retrofit.create(JsonApi.class);

        call2 = jsonApi.getUser();    //key parameter에 awd 전달

        //API 호출
        call2.enqueue(new Callback<User>() {	//비동기로 실행되어 콜백으로 앱으로 알려줌
            //API Reponse 됐을 경우 호출 단, 404, 500 error에도 호출
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                //응답이 성공적으로 됐을경우
                if(response.isSuccessful()) {
                    textView = (TextView) findViewById(R.id.text);
                    //자바 객체로 변환된 JSON데이터 저장
                    User user = response.body();
                    textView.setText(user.toString());
                }
            }
            //서버와 통신중 네트워크 예외 발생시 호출
            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

        call = jsonApi.getPatient();
        call.enqueue(new Callback<List<PatientData>>() {
            @Override
            public void onResponse(Call<List<PatientData>> call, Response<List<PatientData>> response) {
                if(response.isSuccessful()) {
                    textView = (TextView)findViewById(R.id.text);
                    textView.setText(response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<List<PatientData>> call, Throwable t) {

            }
        });
    }
    }

}