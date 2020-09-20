package com.commercial.after_login;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.commercial.R;
import com.commercial.before_login.about_app;
import com.commercial.before_login.help;
import com.commercial.before_login.login_admin;
import com.google.android.material.navigation.NavigationView;
import com.commercial.before_login.preference;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class explore extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Explore");

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.navigation_foradmin,R.id.navigation_forcustomer,R.id.navigation_forvendor,R.id.navigation_forretailer,
                R.id.navigation_Map, R.id.navigation_item, R.id.navigation_gallery,R.id.navigation_your_profile)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_bottom);
        NavGraph graph=navController.getNavInflater().inflate(R.navigation.mobile_navigation_drawer);
        String type=getIntent().getStringExtra("type");
        if(type.equals("customer"))
            graph.setStartDestination(R.id.navigation_forcustomer);
        else if(type.equals("admin"))
            graph.setStartDestination(R.id.navigation_foradmin);
        else if(type.equals("retailer"))
            graph.setStartDestination(R.id.navigation_forretailer);
        else if(type.equals("vendor"))
            graph.setStartDestination(R.id.navigation_forvendor);

        // else if
        navController.setGraph(graph);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.explore, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.log_out){
            startActivity(new Intent(this,preference.class));
            finish();
        }else if (id==R.id.help){
            getSupportFragmentManager().beginTransaction().add(R.id.explorer_layout, new help()).addToBackStack(null).commit();
        }else if (id == R.id.about_app){
            getSupportFragmentManager().beginTransaction().add(R.id.explorer_layout, new about_app()).addToBackStack(null).commit();
        }
        return super.onOptionsItemSelected(item);
    }

    private void startActivityForResult(Intent intent) {
        startActivity(new Intent(this,preference.class));
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_bottom);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
