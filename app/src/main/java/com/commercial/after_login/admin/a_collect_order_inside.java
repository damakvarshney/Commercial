package com.commercial.after_login.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.commercial.R;
import com.commercial.after_login.customer.c_order_item_aa;
import com.commercial.after_login.customer.c_order_item_user;
import com.commercial.after_login.customer.c_order_user;
import com.commercial.after_login.retailer.r_order_item_aa;
import com.commercial.after_login.retailer.r_order_item_user;
import com.commercial.after_login.retailer.r_order_user;
import com.commercial.after_login.vendor.v_order_item_aa;
import com.commercial.after_login.vendor.v_order_item_user;
import com.commercial.after_login.vendor.v_order_user;

import java.util.ArrayList;
import java.util.List;

import static com.commercial.AppController.customer_database;
import static com.commercial.AppController.gson;
import static com.commercial.AppController.retailer_database;
import static com.commercial.AppController.vendor_database;
import static com.commercial.after_login.admin.a_collect_order_aa1.order_of;

public class a_collect_order_inside extends Fragment {

    TextView name, order_id, gt_amt, payment, gt_qty, date;
    RecyclerView recyclerView;
    Button inprocess, on_deliv, check;

    r_order_user r_user;
    c_order_user c_user;
    v_order_user v_user;

    List<r_order_item_user> r_orders = new ArrayList<>();
    List<c_order_item_user> c_orders = new ArrayList<>();
    List<v_order_item_user> v_orders = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.a_collect_order_inside, container, false);

        Bundle bundle = getArguments();
        String orderType = bundle.getString("orderType");
        String order = bundle.getString("order");


        if (orderType.equals("Retailer")) {
            r_user = gson.fromJson(order, r_order_user.class);
            r_orders = r_user.getOrders();
        } else if (orderType.equals("Customer")) {
            c_user = gson.fromJson(order, c_order_user.class);
            c_orders = c_user.getOrders();
        } else if (orderType.equals("Vendor")) {
            v_user = gson.fromJson(order, v_order_user.class);
            v_orders = v_user.getOrders();
        }

        date = view.findViewById(R.id.coi_date);
        name = view.findViewById(R.id.coi_name);
        order_id = view.findViewById(R.id.coi_order_id);
        recyclerView = view.findViewById(R.id.recycler_coi);
        payment = view.findViewById(R.id.coi_pay_status);
        inprocess = view.findViewById(R.id.coi_btn_inprocess);
        on_deliv = view.findViewById(R.id.coi_btn_deli);
        check = view.findViewById(R.id.coi_btn_sign);
        gt_amt = view.findViewById(R.id.coi_et_agrand);
        gt_qty = view.findViewById(R.id.coi_et_qgrand);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        switch (order_of) {
            case "Retailer":
                name.setText(r_user.getRetailer_name());
                order_id.setText((String.valueOf(r_user.getOrder_id())));
                gt_amt.setText(r_user.getGrandtotal() + "");
                payment.setText(String.valueOf(r_user.getPayment_mode()));
                gt_qty.setText(r_user.getT_quantity() + "");
                date.setText(r_user.getDate() + "");
                r_order_item_aa adapter1 = new r_order_item_aa(getContext(), r_orders);
                recyclerView.setAdapter(adapter1);
                inprocess.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "Order in process", Toast.LENGTH_SHORT).show();
                        r_user.setOrder_status("Inprocess");
                        retailer_database.child("Order").child(String.valueOf(r_user.getOrder_id())).setValue(r_user);
                    }
                });

                on_deliv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "Order on Delivery", Toast.LENGTH_SHORT).show();
                        r_user.setOrder_status("On Delivery");
                        retailer_database.child("Order").child(String.valueOf(r_user.getOrder_id())).setValue(r_user);
                    }
                });
                check.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "Sign Pls", Toast.LENGTH_SHORT).show();
                        r_user.setOrder_status("Check");
                        retailer_database.child("Order").child(String.valueOf(r_user.getOrder_id())).setValue(r_user);
                        FragmentManager fragmentManager = getFragmentManager();
                        Bundle bundle = new Bundle();
                        bundle.putString("Retailer_Name", r_user.getRetailer_name());
                        bundle.putLong("Order_Id", r_user.getOrder_id());
                        Fragment fragment = new CaptureSignatureView();
                        fragment.setArguments(bundle);
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.collect_order_inside, fragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                });
                break;
            case "Customer":
                name.setText(c_user.getCustomer_name());
                order_id.setText((String.valueOf(c_user.getOrder_id())));
                gt_amt.setText(c_user.getTotal_of_amt() + "");
                payment.setText(String.valueOf(c_user.getPayment_mode()));
                gt_qty.setText(c_user.getT_quantity() + "");
                date.setText(c_user.getDate() + "");
                c_order_item_aa adapter2 = new c_order_item_aa(getContext(), c_orders);
                recyclerView.setAdapter(adapter2);
                inprocess.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "Order in process", Toast.LENGTH_SHORT).show();
                        c_user.setOrder_status("Inprocess");
                        customer_database.child("Order").child(String.valueOf(c_user.getOrder_id())).setValue(c_user);
                    }
                });

                on_deliv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "Order on Delivery", Toast.LENGTH_SHORT).show();
                        c_user.setOrder_status("On Delivery");
                        customer_database.child("Order").child(String.valueOf(c_user.getOrder_id())).setValue(c_user);
                    }
                });
                check.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "Sign Pls", Toast.LENGTH_SHORT).show();
                        c_user.setOrder_status("Check");
                        customer_database.child("Order").child(String.valueOf(c_user.getOrder_id())).setValue(c_user);
                        FragmentManager fragmentManager = getFragmentManager();
                        Bundle bundle = new Bundle();
                        bundle.putString("Retailer_Name", c_user.getCustomer_name());
                        bundle.putLong("Order_Id", c_user.getOrder_id());
                        Fragment fragment = new CaptureSignatureView();
                        fragment.setArguments(bundle);
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.collect_order_inside, fragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                });
                break;
            case "Vendor":
                name.setText(v_user.getVendor_name());
                order_id.setText((String.valueOf(v_user.getOrder_id())));
                gt_amt.setText(v_user.getGrandtotal() + "");
                payment.setText(String.valueOf(v_user.getPayment_mode()));
                gt_qty.setText(v_user.getT_quantity() + "");
                date.setText(v_user.getDate() + "");
                v_order_item_aa adapter3 = new v_order_item_aa(getContext(), v_orders);
                recyclerView.setAdapter(adapter3);
                inprocess.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "Order in process", Toast.LENGTH_SHORT).show();
                        v_user.setOrder_status("Inprocess");
                        vendor_database.child("Order").child(String.valueOf(v_user.getOrder_id())).setValue(v_user);
                    }
                });

                on_deliv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "Order on Delivery", Toast.LENGTH_SHORT).show();
                        v_user.setOrder_status("On Delivery");
                        vendor_database.child("Order").child(String.valueOf(v_user.getOrder_id())).setValue(v_user);
                    }
                });
                check.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "Sign Pls", Toast.LENGTH_SHORT).show();
                        v_user.setOrder_status("Check");
                        vendor_database.child("Order").child(String.valueOf(v_user.getOrder_id())).setValue(v_user);
                        FragmentManager fragmentManager = getFragmentManager();
                        Bundle bundle = new Bundle();
                        bundle.putString("Retailer_Name", v_user.getVendor_name());
                        bundle.putLong("Order_Id", v_user.getOrder_id());
                        Fragment fragment = new CaptureSignatureView();
                        fragment.setArguments(bundle);
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.collect_order_inside, fragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                });
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + order_of);
        }
        return view;
    }


}



