package com.example.inventarioapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class MainActivity extends AppCompatActivity {
    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String KEY_FIRST_RUN = "firstRun";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        Drawer drawerManager = new Drawer();
        drawerManager.setupDrawer(this, drawerLayout);

        TopBar topBar = new TopBar();
        topBar.setupTopBar(this);
        topBar.setNavigationIcon(
                ContextCompat.getDrawable(this, R.drawable.baseline_menu_24),
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
                        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                            drawerLayout.closeDrawer(GravityCompat.START);
                        } else {
                            drawerLayout.openDrawer(GravityCompat.START);
                        }
                    }
                }
        );
    }
    public void logout() {

        Intent intent = new Intent(this, Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
       // Cierra la actividad actual para evitar que el usuario vuelva atrás
    }
    public void doThis(MenuItem item){
        Intent intent = new Intent(this, Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(KEY_FIRST_RUN, true);
        editor.apply();
    }

}