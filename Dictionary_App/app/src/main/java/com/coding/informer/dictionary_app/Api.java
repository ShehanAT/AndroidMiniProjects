package com.coding.informer.dictionary_app;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String BASE_URL = "https://dog.ceo/api/breeds/image/random";
    @GET("dogs")
    Call<List<Results>> getDogs();
}
