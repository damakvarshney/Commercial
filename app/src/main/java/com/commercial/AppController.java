package com.commercial;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import androidx.annotation.NonNull;

import com.commercial.before_login.preference;
import com.commercial.before_login.register_admin_user;
import com.commercial.before_login.register_customer_user;
import com.commercial.before_login.register_retailer_user;
import com.commercial.before_login.register_vendor_user;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class AppController extends Application {

    public static register_admin_user admin_user;
    public static register_retailer_user retailer_user;
    public static register_customer_user customer_user;
    public static register_vendor_user vendor_user;


    public static FirebaseDatabase database;
    public static DatabaseReference reference;

    public static FirebaseStorage storage;
    public static StorageReference mStorageRef;

    //admin_stored_elements
    public static DatabaseReference admin_database;
    public static DatabaseReference admin_database_user_email;
    public static DatabaseReference admin_database_stock;
    public static DatabaseReference admin_database_new_gallery;

    //retailer_stored_database
    public static DatabaseReference retailer_database;
    public static DatabaseReference retailer_database_user_email;

    //customer_stored_database
    public static DatabaseReference customer_database;
    public static DatabaseReference customer_database_user_email;

    //vendor_stored_database
    public static DatabaseReference vendor_database;
    public static DatabaseReference vendor_database_user_email;

    //misc_stored_database
    public static DatabaseReference misc_database;
    public static DatabaseReference misc_database_feedback;

    public static Gson gson=new Gson();
    public static SimpleDateFormat formatter;

    @NonNull
    public static String formatEmail(String emp_email_id) {
        return emp_email_id.replace("@","").replace(".","");
    }


    @Override
    public void onCreate() {
        super.onCreate();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        storage=FirebaseStorage.getInstance();
        mStorageRef=storage.getReference();

        formatter=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());

        //login_admin
        admin_database = FirebaseDatabase.getInstance().getReference("Admin");
        admin_database_user_email = admin_database.child("Registered_user");
        //maintain stock
        admin_database_stock = admin_database.child("Stock");

        //login_customer
        customer_database = FirebaseDatabase.getInstance().getReference("Customer");
        customer_database_user_email = customer_database.child("Registered_user");

        //login_retailer
        retailer_database = FirebaseDatabase.getInstance().getReference("Retailer");
        retailer_database_user_email = retailer_database.child("Registered_user");

        //login_vendor
        vendor_database = FirebaseDatabase.getInstance().getReference("Vendor");
        vendor_database_user_email = vendor_database.child("Registered_user");

        //miscellaneous_feedback
        misc_database = FirebaseDatabase.getInstance().getReference("Misc");
        misc_database_feedback = misc_database.child("Feedback");



    }
}
