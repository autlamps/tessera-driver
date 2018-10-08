package com.example.blake.tessera;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {

    private List<Driver> theLoginData;

    public static final String BASE_URL = "https://dev-api.tessera-dev.haydenwoodhead.com/api/";
    public static final String TOKEN_KEY = "token_key";
    private static final String defaultToken = null;
    private static final int id = 1;
    private static final int pin = 1234;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(Login.this);
        SharedPreferences.Editor editor = sharedPref.edit();

        login = (Button) findViewById(R.id.LoginButton);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssz").create();

                Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

                Api apiService = retrofit.create(Api.class);

                LoginData user = new LoginData(id, pin);
                Call<APIToken> call = apiService.LoginDriver(user);
                call.enqueue(new Callback<APIToken>() {
                    @Override
                    public void onResponse(Call<APIToken> call, Response<APIToken> response) {
                        if (response.isSuccessful()) {
                            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(Login.this);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putString(TOKEN_KEY, response.body().getToken());
                            editor.commit();

                            Toast.makeText(Login.this, response.body().getToken(), Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(Login.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            Toast.makeText(Login.this, "Invalid Credentials, Please try again.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<APIToken> call, Throwable t) {
                        //Do Nothing
                    }
                });
            }
        });
    }
}
