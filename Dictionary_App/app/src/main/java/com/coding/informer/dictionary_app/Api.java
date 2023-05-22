package com.coding.informer.dictionary_app;

import java.util.List;

import retrofit2.Call;

public interface Api {
    String BASE_URL = "https://dog.ceo/api/breeds/image/random";

    Call<List<Results>> getDogs();
}
