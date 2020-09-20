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
import com.commercial.after_login.customer.for_customer;
import com.commercial.after_login.explore;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import static com.commercial.AppController.customer_database_user_email;
import static com.commercial.AppController.customer_user;

public class login_customer extends Fragment {

    TextView txvUsername,txvPassword;
    Button login_customer;
    TextView create_new_customer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_customer, container, false);


        login_customer = view.findViewById(R.id.login_customer);
        create_new_customer = view.findViewById(R.id.create_new_customer);
        txvPassword = view.findViewById(R.id.lc_et_pass);
        txvUsername = view.findViewById(R.id.lc_et_uid);


        login_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                customer_database_user_email.child(AppController.formatEmail(txvUsername.getText().toString())).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Log.e("value",dataSnapshot.toString());
                        customer_user=dataSnapshot.getValue(register_customer_user.class);
                        if(customer_user==null){
                            Toast.makeText(getContext(), "Invalid Email ID", Toast.LENGTH_SHORT).show();
                        }else {
                            if(customer_user.customer_password.equals(txvPassword.getText().toString())){
                                startActivity(new Intent(getActivity(), explore.class).putExtra("type", "customer"));
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.e("value","canceled");
                    }
                });

            }
        });

        create_new_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.preference_layout, new register_customer());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


        return view;
    }
}
