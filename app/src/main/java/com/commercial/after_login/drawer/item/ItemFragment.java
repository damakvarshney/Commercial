package com.commercial.after_login.drawer.item;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.commercial.R;
import com.commercial.after_login.admin.a_update_item_aa;
import com.commercial.after_login.admin.a_update_item_user;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.commercial.AppController.admin_database;
import static com.commercial.AppController.admin_database_new_gallery;

public class ItemFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.content_explore_item, container, false);

        final RecyclerView recyclerView = root.findViewById(R.id.cei);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Log.e("data","fetching");
        admin_database.child("New Item").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e("data",dataSnapshot.toString());
                List<a_update_item_user> users=new ArrayList<>();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    a_update_item_user user=child.getValue(a_update_item_user.class);
                    users.add(user);
                }
                a_update_item_aa adapter=new a_update_item_aa(getActivity(),users);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("data",databaseError.toString());
            }
        });



        return root;
    }
}
