package com.commercial.after_login.retailer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.commercial.R;
import com.commercial.after_login.admin.a_update_offer_user;
import com.commercial.after_login.customer.c_offer_now_aa;
import com.commercial.after_login.customer.c_offer_now_user;
import com.commercial.before_login.preference;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.commercial.AppController.admin_database;


public class r_offer_now extends Fragment {

    r_offer_now_user user;
    List<r_offer_now_user> retailers = new ArrayList<>();
    RecyclerView recyclerView;
    r_offer_now_aa acceessAdapter;
    List<a_update_offer_user> all_retailer = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.offer_avia_r, container, false);

        recyclerView = view.findViewById(R.id.recycler_oar);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
        Log.e("data","fetching");
        admin_database.child("New Offer").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e("data",dataSnapshot.toString());
                List<a_update_offer_user> users=new ArrayList<>();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    a_update_offer_user offer_user =child.getValue(a_update_offer_user.class);
                    user = new r_offer_now_user(offer_user.getOffer_name(),offer_user.getFrom_date(),offer_user.getTill_date());
                    retailers.add(user);
                    all_retailer.add(offer_user);
                }
                acceessAdapter =new r_offer_now_aa(getContext(),retailers,all_retailer, preference.type);
                recyclerView.setAdapter(acceessAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("data",databaseError.toString());
            }
        });




        return view;
    }
}
