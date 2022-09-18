package com.unittest.retrofit2java;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface JsonApi {
    @GET("patient")
    Call<List<PatientData>> getPatient();

    @GET("user")
    Call<User> getUser();

    @POST("user/signUp")
    Call<String> postUser(@Body User user);

    @GET("category/list")
    Call<String> getCategoryList();

}