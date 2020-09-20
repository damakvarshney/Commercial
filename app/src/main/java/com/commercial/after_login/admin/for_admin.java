package com.commercial.after_login.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;

import com.commercial.R;

public class for_admin extends Fragment {

    Button give_access, collect_order, maintain_stock, check_feed, update_map, update_payment, update_offer, update_gallery, update_item;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.for_admin, container, false);

        // findviewbyids
        give_access = view.findViewById(R.id.give_access);
        collect_order = view.findViewById(R.id.collect_order);
        maintain_stock = view.findViewById(R.id.maintain_stock);
        check_feed = view.findViewById(R.id.check_feed);
        update_map = view.findViewById(R.id.update_map);
        update_payment = view.findViewById(R.id.update_payment);
        update_offer = view.findViewById(R.id.update_offer);
        update_gallery = view.findViewById(R.id.update_gallery);
        update_item = view.findViewById(R.id.update_item);

        give_access.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction =  fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment_bottom, new a_give_access());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        collect_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction =        fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment_bottom, new a_collect_order());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        maintain_stock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment2 = new Fragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction =        fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment_bottom, new a_maintain_stock());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        check_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment2 = new Fragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction =        fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment_bottom, new a_check_feed());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        update_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment2 = new Fragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction =        fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment_bottom, new a_update_map());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        update_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment2 = new Fragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction =        fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment_bottom, new a_update_pay());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        update_offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment2 = new Fragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction =        fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment_bottom, new a_update_offer());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        update_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment2 = new Fragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction =        fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment_bottom, new a_update_gallery());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        update_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment2 = new Fragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction =        fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment_bottom, new a_update_item());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


        return view;
    }
}
