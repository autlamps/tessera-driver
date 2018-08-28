package com.example.blake.tessera;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ToolbarWidgetWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class MainActivity extends AppCompatActivity {

    public static final String TOKEN_KEY = "token_key";
    private static final String defaultToken = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       android.support.v7.widget.Toolbar hamburger = (android.support.v7.widget.Toolbar) findViewById(R.id.hamburger);
       hamburger.setBackground(getResources().getDrawable(R.color.colorAccent));

       setSupportActionBar(hamburger);



        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Home");
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName("Topup");
        PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(3).withName("Settings");
        PrimaryDrawerItem item4 = new PrimaryDrawerItem().withIdentifier(4).withName("Logout");


        final Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(hamburger)
                .addDrawerItems(
                        item1,
                        new DividerDrawerItem(),
                        item2,
                        new DividerDrawerItem(),
                        item3,
                        new DividerDrawerItem(),
                        item4
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override


                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {


                        if(drawerItem.getIdentifier() == 1)
                        {

                        }
                        else if(drawerItem.getIdentifier() == 2)
                        {
                            Intent intent = new Intent(MainActivity.this, Topup.class);
                            startActivity(intent);
                            finish();
                        }
                        else if(drawerItem.getIdentifier() == 3)
                        {
                            Intent intent = new Intent(MainActivity.this, settings.class);
                            startActivity(intent);
                            finish();
                        }
                        else if(drawerItem.getIdentifier() == 4)
                        {
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
}
