package com.commercial.before_login;

import android.app.ProgressDialog;
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
import com.commercial.after_login.retailer.for_retailer;
import com.commercial.after_login.vendor.for_vendor;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import static com.commercial.AppController.retailer_database_user_email;
import static com.commercial.AppController.retailer_user;
import static com.commercial.AppController.vendor_database_user_email;

public class login_retailer extends Fragment {

    TextView txvUsername,txvPassword;
    Button login_retailer;
    TextView create_new_retailer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_retailer, container, false);

        login_retailer = view.findViewById(R.id.login_retailer);
        create_new_retailer = view.findViewById(R.id.create_new_retailer);
        txvPassword = view.findViewById(R.id.lr_et_pass);
        txvUsername = view.findViewById(R.id.lr_et_uid);

        login_retailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog dialog=new ProgressDialog(getActivity());
                dialog.setCancelable(false);
                dialog.setMessage("Please Wait");
                dialog.show();
                retailer_database_user_email.child(AppController.formatEmail(txvUsername.getText().toString())).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Log.e("value",dataSnapshot.toString());
                        retailer_user=dataSnapshot.getValue(register_retailer_user.class);
                        dialog.dismiss();
                        if(retailer_user==null){
                            Toast.makeText(getContext(), "Invalid Email ID", Toast.LENGTH_SHORT).show();
                        }else {
                            if(!retailer_user.status){
                                Toast.makeText(getActivity(), "You are not Authorised", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if(retailer_user.r_password.equals(txvPassword.getText().toString())){
                                startActivity(new Intent(getActivity(), explore.class).putExtra("type","retailer"));
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.e("value","canceled");
                        dialog.dismiss();
                    }
                });
            }
        });

        create_new_retailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment2 = new Fragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction =        fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.preference_layout, new register_retailer());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        
        return view;
    }
}
