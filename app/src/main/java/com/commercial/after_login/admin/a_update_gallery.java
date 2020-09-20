package com.commercial.after_login.admin;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.commercial.R;
import com.commercial.before_login.register_admin;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.commercial.AppController.admin_database;
import static com.commercial.AppController.admin_database_new_gallery;
import static com.commercial.AppController.admin_database_stock;

public class a_update_gallery extends Fragment {

    MenuItem menuItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.a_update_gallery, container, false);

        final RecyclerView recyclerView = view.findViewById(R.id.recycler_ugr);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
        Log.e("data","fetching");
        admin_database.child("New Gallery").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e("data",dataSnapshot.toString());
                List<a_update_gallery_user> users=new ArrayList<>();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    a_update_gallery_user user=child.getValue(a_update_gallery_user.class);
                    users.add(user);
                }
                a_update_gallery_aa adapter=new a_update_gallery_aa(getActivity(),users);
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
        menuItem = menu.add("MenuItem");
        menuItem.setIcon(R.drawable.perm_media_24px);
        menuItem.setShowAsAction(2);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        
        if(true){
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.update_gallery_layout, new a_update_gallery_new());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        
        return super.onOptionsItemSelected(item);
    }
}
