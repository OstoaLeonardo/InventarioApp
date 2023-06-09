package com.example.inventarioapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String KEY_FIRST_RUN = "firstRun";

    InventoryFragment inventario = new InventoryFragment();
    AddInventoryFragment agregarInventario = new AddInventoryFragment();
    RemoveInventoryFragment quitarInventario = new RemoveInventoryFragment();
    AddProductFragment nuevoProducto = new AddProductFragment();
    AddUserFragment nuevoUsuario = new AddUserFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_login);
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

        BottomNavigationView navigation = findViewById(R.id.bottomNavigationView);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadFragment(inventario);
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.inventario:
                    loadFragment(inventario);
                    return true;
                case R.id.agregar:
                    loadFragment(agregarInventario);
                    return true;
                case R.id.quitar:
                    loadFragment(quitarInventario);
                    return true;
                case R.id.nuevoProducto:
                    loadFragment(nuevoProducto);
                    return true;
                case R.id.nuevoUsuario:
                    loadFragment(nuevoUsuario);
                    return true;
            }
            return false;
        }
    };

    public void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);
        transaction.commit();
    }

    public void logout() {

    }
    public void doThis(MenuItem item){
        Intent intent = new Intent(MainActivity.this, Login.class);
        startActivity(intent);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(KEY_FIRST_RUN, true);
        editor.apply();
    }
    public void doThis1(MenuItem item){
        Intent intent = new Intent(MainActivity.this, Register.class);
        startActivity(intent);

       ;
    }

}