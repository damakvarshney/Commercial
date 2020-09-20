package com.commercial.after_login.vendor;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.commercial.R;
import com.commercial.after_login.vendor.for_vendor_aa;
import com.commercial.after_login.vendor.for_vendor_user;

import java.util.ArrayList;
import java.util.List;

public class for_vendor extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.for_vendor, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.vendor_menu_recycler);

        List<for_vendor_user> users= new ArrayList<>();
        for_vendor_user user = new for_vendor_user(R.drawable.order_now,"Order Now");
        for_vendor_user user1 = new for_vendor_user(R.drawable.order_status,"Order Status");
        for_vendor_user user2 = new for_vendor_user(R.drawable.offer_now,"Offer Now");
        for_vendor_user user3 = new for_vendor_user(R.drawable.pay_status,"Pay Status");
        for_vendor_user user4 = new for_vendor_user(R.drawable.feedback,"Feedback");

        users.add(user);
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);

        for_vendor_aa arrayadapter = new for_vendor_aa(getActivity(),users, getActivity().getSupportFragmentManager());
        recyclerView.setAdapter(arrayadapter);

        GridLayoutManager manager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(manager);


        return view;
    }
}
