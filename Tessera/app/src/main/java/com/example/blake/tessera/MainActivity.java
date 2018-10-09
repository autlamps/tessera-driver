package com.example.blake.tessera;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.blake.tessera.Api.BASE_URL;
//import com.google.zxing.Result;

public class MainActivity extends AppCompatActivity {
    public static final String TOKEN_KEY = "token_key";
    private static final String defaultToken = null;
    private ZXingScannerView scannerView;
    private String driverToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        this.driverToken = sharedPref.getString(TOKEN_KEY, "something else");

        android.support.v7.widget.Toolbar hamburger = (android.support.v7.widget.Toolbar) findViewById(R.id.hamburger);
       hamburger.setBackground(getResources().getDrawable(R.color.colorAccent));

       setSupportActionBar(hamburger);

        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Home");
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName("Topup");
        PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(3).withName("Settings");
        PrimaryDrawerItem item4 = new PrimaryDrawerItem().withIdentifier(4).withName("Logout");

        final Drawer result = new DrawerBuilder().withActivity(this).withToolbar(hamburger).addDrawerItems(item1, new DividerDrawerItem(), item2, new DividerDrawerItem(), item3, new DividerDrawerItem(), item4).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
            if(drawerItem.getIdentifier() == 1) {
                //Do Nothing
            }
            else if(drawerItem.getIdentifier() == 2) {
                Intent intent = new Intent(MainActivity.this, Topup.class);
                startActivity(intent);
                finish();
            }
            else if(drawerItem.getIdentifier() == 4) {
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString(TOKEN_KEY, null);
                editor.commit();

                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
                finish();
            }
            return true;
            }
        }
        ).build();
    }

    public void scanCode(View view){
        scannerView = new ZXingScannerView(this);
        scannerView.setResultHandler(new ZXingScannerResultHandler());

        setContentView(scannerView);
        scannerView.startCamera();

    }

    @Override
    public void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

    class ZXingScannerResultHandler implements ZXingScannerView.ResultHandler {
        @Override
        public void handleResult(com.google.zxing.Result result) {
            String resultCode = result.getText();

            setContentView(R.layout.activity_main);
            scannerView.stopCamera();

            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssz").create();

            Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

            Api apiService = retrofit.create(Api.class);

            TagOnData tgData = new TagOnData(1, resultCode);

            Call<TagOnReturn> call = apiService.TagOn(driverToken, tgData);
            call.enqueue(new Callback<TagOnReturn>() {
                @Override
                public void onResponse(Call<TagOnReturn> call, Response<TagOnReturn> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "User current bal: " + response.body().getUserCurrentBal(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<TagOnReturn> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Failed to talk to server", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

}
