package com.commercial.after_login.admin;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.commercial.R;
import com.commercial.before_login.register_admin;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.commercial.AppController.admin_database;
import static com.commercial.AppController.admin_database_new_gallery;

public class a_update_offer extends Fragment {

    MenuItem new_offer;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.a_update_offer, container, false);

        final RecyclerView recyclerView = view.findViewById(R.id.recycler_uor);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
        Log.e("data","fetching");
        admin_database.child("New Offer").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e("data",dataSnapshot.toString());
                List<a_update_offer_user> users=new ArrayList<>();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    a_update_offer_user user=child.getValue(a_update_offer_user.class);
                    users.add(user);
                }
                a_update_offer_aa adapter=new a_update_offer_aa(getActivity(),users);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("data",databaseError.toString());
            }
        });


        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuin) {
        new_offer = menu.add("New Album");
        new_offer.setIcon(R.drawable.perm_media_24px);
        new_offer.setShowAsAction(2);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (true) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.update_offer_layout, new a_update_offer_new());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        return super.onOptionsItemSelected(item);
    }
}
