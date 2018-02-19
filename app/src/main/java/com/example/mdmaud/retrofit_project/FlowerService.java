package com.example.mdmaud.retrofit_project;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by BITM Trainer 601 on 1/1/2018.
 */

public interface FlowerService {
    @GET("feeds/flowers.json")
    Call<List<FlowerResponse>> getAllFlowers();

  //  @GET()
  //  Call<CurrentWeatherResponse>getCurrentWeatherResponse(@Url String urlString);
}
