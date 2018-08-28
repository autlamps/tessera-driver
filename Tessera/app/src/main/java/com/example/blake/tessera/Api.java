package com.example.blake.tessera;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

    String BASE_URL = "https://dev-api.tessera-dev.haydenwoodhead.com/api/";

    @POST("v1/users/authtokens/")
    Call<APIToken> loginUser(@Body LoginData ld);



}
