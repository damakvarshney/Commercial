package com.commercial.after_login.vendor;

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
import com.commercial.after_login.vendor.v_offer_now_aa;
import com.commercial.after_login.vendor.v_offer_now_user;
import com.commercial.before_login.preference;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.commercial.AppController.admin_database;


public class v_offer_now extends Fragment {

    v_offer_now_user user;
    List<v_offer_now_user> vendors = new ArrayList<>();
    RecyclerView recyclerView;
    v_offer_now_aa acceessAdapter;
    List<a_update_offer_user> all_vendor = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.offer_avia_v, container, false);

        recyclerView = view.findViewById(R.id.recycler_oav);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
        Log.e("data","fetching");
        admin_database.child("New Offer").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e("data",dataSnapshot.toString());
                List<a_update_offer_user> users=new ArrayList<>();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    a_update_offer_user offer_user =child.getValue(a_update_offer_user.class);
                    user = new v_offer_now_user(offer_user.getOffer_name(),offer_user.getFrom_date(),offer_user.getTill_date());
                    vendors.add(user);
                    all_vendor.add(offer_user);
                }
                acceessAdapter =new v_offer_now_aa(getContext(),vendors,all_vendor, preference.type);
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
