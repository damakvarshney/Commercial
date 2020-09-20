package com.commercial.after_login.retailer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.commercial.R;

import java.util.ArrayList;
import java.util.List;

public class for_retailer extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.for_retailer, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.retailer_menu_recycler);

        List<for_retailer_user> users = new ArrayList<>();
        for_retailer_user user = new for_retailer_user(R.drawable.order_now, "Order Now");
        for_retailer_user user1 = new for_retailer_user(R.drawable.order_status, "Order Status");
        for_retailer_user user2 = new for_retailer_user(R.drawable.offer_now, "Offer Now");
        for_retailer_user user3 = new for_retailer_user(R.drawable.pay_status, "Pay Status");
        for_retailer_user user4 = new for_retailer_user(R.drawable.feedback, "Feedback");

        users.add(user);
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);

        for_retailer_aa aa = new for_retailer_aa(getActivity(), users, getActivity().getSupportFragmentManager());

        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(aa);

        return view;
    }
}
