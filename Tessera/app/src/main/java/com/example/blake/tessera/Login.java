package com.example.blake.tessera;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {

    private Button LoginButton;
    private EditText username;
    private EditText password;
    public static final String BASE_URL = "https://dev-api.tessera-dev.haydenwoodhead.com/api/";
    public static final String TOKEN_KEY = "token_key";
    private static final String defaultToken = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(Login.this);
        SharedPreferences.Editor editor = sharedPref.edit();

        if(sharedPref.getString(TOKEN_KEY, defaultToken) != null)
        {
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
            finish();
        }


        LoginButton = (Button) findViewById(R.id.login);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                username = (EditText) findViewById(R.id.username);
                password = (EditText) findViewById(R.id.password);

                String usernameString = username.getText().toString();
                String passwordString = password.getText().toString();


                Gson gson = new GsonBuilder()
                        .setDateFormat("yyyy-MM-dd'T'HH:mm:ssz")
                        .create();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                Api apiService = retrofit.create(Api.class);

                LoginData user = new LoginData(usernameString, passwordString);
                Call<APIToken> call = apiService.loginUser(user);
                call.enqueue(new Callback<APIToken>() {
                    @Override
                    public void onResponse(Call<APIToken> call, Response<APIToken> response) {

                        if(response.isSuccessful())
                        {

                            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(Login.this);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putString(TOKEN_KEY, response.body().getToken());
                            editor.commit();

                            Toast.makeText(Login.this, response.body().getToken(), Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(Login.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else
                        {
                            Toast.makeText(Login.this, "Invalid Credentials, Please try again.", Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void onFailure(Call<APIToken> call, Throwable t) {

                        Toast.makeText(Login.this,t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}
