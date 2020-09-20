package com.commercial.after_login.admin;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.commercial.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.commercial.AppController.admin_database_stock;

public class a_maintain_stock extends Fragment {

    MenuItem new_item;
    TextView total_quantity, total_amount;
    private int total_amt;
    private int total_qty;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.a_maintain_stock, container, false);

        setHasOptionsMenu(true);

        total_amount = view.findViewById(R.id.ms_et_agrand);
        total_quantity = view.findViewById(R.id.ms_et_qgrand);
        final RecyclerView recyclerView = view.findViewById(R.id.recycler_msr);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Log.e("data", "fetching");
        admin_database_stock.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e("data", dataSnapshot.toString());
                total_amt = 0;
                total_qty = 0;
                List<a_maintain_stock_user> users = new ArrayList<>();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    a_maintain_stock_user user = child.getValue(a_maintain_stock_user.class);
                    users.add(user);
                    total_amt += user.amount;
                    total_amount.setText(total_amt + "");

                    total_qty += user.quantity;
                    total_quantity.setText(total_qty + "");
                }
                a_maintain_stock_aa adapter = new a_maintain_stock_aa(getActivity(), users);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("data", databaseError.toString());
            }
        });

        registerForContextMenu(recyclerView);
        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuin) {
        new_item = menu.add("New Album");
        new_item.setIcon(R.drawable.perm_media_24px);
        new_item.setShowAsAction(1);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (true) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.maintain_stock_layout, new a_maintain_stock_new());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }

        return super.onOptionsItemSelected(item);
    }
}
