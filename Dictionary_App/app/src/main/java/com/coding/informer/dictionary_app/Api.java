package com.coding.informer.dictionary_app;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String BASE_URL = "https://dog.ceo/api/breeds/image/random";
    String DICTIONARY_BASE_URL = "https://api.dictionaryapi.dev/api/v2/entries/en";
    @GET("dogs")
    Call<List<Results>> getDogs();

    @GET("Word Definition")
    Call<List<Results>> getWordDefinitions();
}
