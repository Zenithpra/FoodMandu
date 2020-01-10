package com.zenith.foodmandu.url;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Url {
    //  public static final String base_url = "http://192.168.1.11:3000/";  //private network real device
    public static final String base_url = "http://10.0.2.2:3000/"; //localhost if run in emulator(virtual machine)
    // public static final String base_url = "http://172.100.100.5:3000/"; //for cloud
    public static String token = "Bearer ";
    public static String imagePath = base_url + "uploads/" ;

    public static Retrofit getInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
