package com.example.blake.tessera;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface Api {
    String BASE_URL = "https://dev-api.tessera-dev.haydenwoodhead.com/api/";

    @GET("v1/drivers/authtokens/")
    Call<List<Driver>> driverList();

    @POST("v1/drivers/authtokens/")
    Call<APIToken> LoginDriver(@Body LoginData id);

    @POST("v1/drivers/bttrips/")
    Call<TagOnReturn> TagOn(@Header("X-DRIVER-TOKEN") String dt, @Body TagOnData tg);
}