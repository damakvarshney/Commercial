package com.commercial.before_login;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.commercial.R;

import java.util.Objects;

public class preference extends AppCompatActivity {

    Button admin, retailer, customer, vendor;
    private Fragment fragmentObject;
    public static String type;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);

        admin = findViewById(R.id.admin);
        retailer = findViewById(R.id.retailer);
        customer = findViewById(R.id.customer);
        vendor = findViewById(R.id.vendor);

    }

    public void admin(View view) {
        type = "Admin";

        getSupportFragmentManager().beginTransaction().add(R.id.preference_layout, new login_admin()).addToBackStack(null).commit();
    }

    public void retailer(View view) {
        type = "Retailer";
        getSupportFragmentManager().beginTransaction().add(R.id.preference_layout, new login_retailer()).addToBackStack(null).commit();
    }

    public void customer(View view) {
        type= "Customer";
        getSupportFragmentManager().beginTransaction().add(R.id.preference_layout, new login_customer()).addToBackStack(null).commit();
    }

    public void vendor(View view) {
        type= "Vendor";
        getSupportFragmentManager().beginTransaction().add(R.id.preference_layout, new login_vendor()).addToBackStack(null).commit();
    }

    public void skip_this(View view) {
        type= "Miscellaneous";
        startActivity(new Intent(preference.this, skip_this.class));
    }
}
