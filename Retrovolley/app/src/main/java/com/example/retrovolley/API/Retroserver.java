package com.example.retrovolley.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retroserver {

    //membuat base url
    private static final String baseURL = "http://192.168.1.8/retrovolley/";
    //membuat variable retrofit
    private static Retrofit retro;
    //membuat function dengan tipe retrofit
    public static Retrofit konekRetrofit() {
        if (retro == null) {
            retro = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    //mengkonvert data menjadi json agar dapat dikenali android
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retro;
    }
}
