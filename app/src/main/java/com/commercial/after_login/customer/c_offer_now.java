package com.commercial.after_login.customer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.commercial.R;
import com.commercial.after_login.admin.a_give_access_User;
import com.commercial.after_login.admin.a_give_access_aa;
import com.commercial.after_login.admin.a_update_offer_aa;
import com.commercial.after_login.admin.a_update_offer_user;
import com.commercial.before_login.preference;
import com.commercial.before_login.register_retailer_user;
import com.commercial.before_login.register_vendor_user;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.commercial.AppController.admin_database;

public class c_offer_now extends Fragment {

    c_offer_now_user user;
    List<c_offer_now_user> customers = new ArrayList<>();
    RecyclerView recyclerView;
    c_offer_now_aa acceessAdapter;
    List<a_update_offer_user> all_customer = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.offer_avia_c, container, false);

        recyclerView = view.findViewById(R.id.recycler_c_offer);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
        Log.e("data","fetching");
        admin_database.child("New Offer").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e("data",dataSnapshot.toString());
                List<a_update_offer_user> users=new ArrayList<>();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    a_update_offer_user offer_user =child.getValue(a_update_offer_user.class);
                    user = new c_offer_now_user(offer_user.getOffer_name(),offer_user.getFrom_date(),offer_user.getTill_date());
                    customers.add(user);
                    all_customer.add(offer_user);
                }
                acceessAdapter =new c_offer_now_aa(getContext(),customers,all_customer, preference.type);
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
