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
import com.commercial.after_login.admin.for_admin;
import com.commercial.after_login.explore;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import static com.commercial.AppController.admin_database_user_email;
import static com.commercial.AppController.admin_user;

public class login_admin extends Fragment {


    Button  login_admin;
    TextView create_new_admin, txvUsername, txvPassword;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_admin, container, false);

        login_admin = view.findViewById(R.id.login_admin);
        create_new_admin = view.findViewById(R.id.create_new_admin);
        txvPassword = view.findViewById(R.id.la_et_pass);
        txvUsername = view.findViewById(R.id.la_et_uid);

        login_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                admin_database_user_email.child(AppController.formatEmail(txvUsername.getText().toString())).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Log.e("value", dataSnapshot.toString());
                        admin_user = dataSnapshot.getValue(register_admin_user.class);
                        if (admin_user == null) {
                            Toast.makeText(getContext(), "Invalid Email ID", Toast.LENGTH_SHORT).show();
                        } else {
                            if (admin_user.emp_password.equals(txvPassword.getText().toString())) {
                                startActivity(new Intent(getActivity(), explore.class).putExtra("type", "admin"));
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

        create_new_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.preference_layout, new register_admin());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


        return view;
    }

}
