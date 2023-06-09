package com.example.inventarioapp;

import android.content.Intent;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class Drawer {

    private ActionBarDrawerToggle drawerToggle;
    private MainActivity activity;
    private DrawerLayout drawerLayout;

    public void setupDrawer(MainActivity activity, DrawerLayout drawerLayout) {
        this.activity = activity;
        this.drawerLayout = drawerLayout;

        drawerToggle = new ActionBarDrawerToggle(
                activity,
                drawerLayout,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );

        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        if (item.getItemId() == R.id.logout) {
            // Realiza las acciones para cerrar sesión
            activity.logout();
            return true;
        }


        return true;
    }


}

