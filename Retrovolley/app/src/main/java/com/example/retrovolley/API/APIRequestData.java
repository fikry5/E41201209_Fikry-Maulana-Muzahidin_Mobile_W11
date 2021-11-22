package com.example.retrovolley.API;

import com.example.retrovolley.model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequestData {
    //Mengambil data
    @GET("retrieve.php")
    Call<ResponseModel> ardRetrieveData();

    //Post data
    @FormUrlEncoded
    @POST("create.php")
    Call<ResponseModel> ardCreateData(
            //parameter
            @Field("user_fullname") String user_fullname,
            @Field("user_email") String user_email,
            @Field("user_password") String user_password
    );
}
