package com.commercial.before_login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.commercial.AppController;
import com.commercial.R;
import com.commercial.after_login.explore;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import static com.commercial.AppController.vendor_database_user_email;
import static com.commercial.AppController.vendor_user;

public class login_vendor extends Fragment {

    Button login_vendor;
    TextView create_new_vendor, txvUsername, txvPassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_vendor, container, false);

        txvPassword = view.findViewById(R.id.lv_et_pass);
        txvUsername = view.findViewById(R.id.lv_et_uid);
        login_vendor = view.findViewById(R.id.login_vendor);
        create_new_vendor = view.findViewById(R.id.create_new_vendor);

        login_vendor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vendor_database_user_email.child(AppController.formatEmail(txvUsername.getText().toString())).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Log.e("value", dataSnapshot.toString());
                        vendor_user = dataSnapshot.getValue(register_vendor_user.class);
                        if (vendor_user == null) {
                            Toast.makeText(getContext(), "Invalid Email ID", Toast.LENGTH_SHORT).show();
                        } else {
                            if (!vendor_user.status) {
                                Toast.makeText(getActivity(), "You are not Authorised", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if (vendor_user.vendor_password.equals(txvPassword.getText().toString())) {
                                startActivity(new Intent(getActivity(), explore.class).putExtra("type", "vendor"));
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.e("value", "canceled");
                    }
                });
            }
        });

        create_new_vendor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.preference_layout, new register_vendor());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return view;
    }
}
