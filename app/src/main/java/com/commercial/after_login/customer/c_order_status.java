package com.commercial.after_login.customer;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.commercial.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.commercial.AppController.reference;

public class c_order_status extends Fragment {

    TextView order_id, order_status, t_qty, t_amt, textview_fd1, textview_td1;
    Button fd_btn, td_btn;
    CheckBox order_consi, order_inpro, order_deli;
    RecyclerView recyclerView;
    Calendar fromDate1, toDate1, calendar;
    List<c_order_status_user> customers = new ArrayList<>();
    List<c_order_user> all_customers = new ArrayList<>();
    c_order_status_aa acceessAdapter;
    c_order_status_user user;
    c_order_user status;
    String c_user;
    private int total_amt;
    private int total_qty;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.order_status_c, container, false);

        order_id = view.findViewById(R.id.osc_tv_oid);
        order_consi = view.findViewById(R.id.osc_cb1);
        order_inpro = view.findViewById(R.id.osc_cb2);
        order_deli = view.findViewById(R.id.osc_cb3);
        order_status = view.findViewById(R.id.osc_tv_ost);
        fd_btn = view.findViewById(R.id.osc_btn_fd);
        td_btn = view.findViewById(R.id.osc_btn_td);
        textview_fd1 = view.findViewById(R.id.osc_tv_fd);
        textview_td1 = view.findViewById(R.id.osc_tv_td);
        recyclerView = view.findViewById(R.id.recycler_c_order_st);
        t_amt = view.findViewById(R.id.osc_tv_agrand);
        t_qty = view.findViewById(R.id.osc_tv_qgrand);
        calendar = Calendar.getInstance();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        reference.child("Customer").child("Order").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e("customers", dataSnapshot.toString());
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    c_order_user customer_user = child.getValue(c_order_user.class);
                    order_id.setText(String.valueOf(customer_user.getOrder_id()));
                    Log.e("customers", customer_user.toString());
                    order_id.setText(String.valueOf(customer_user.getOrder_id()));
                    c_user = customer_user.getOrder_status();
                    Log.e("c_user", c_user);
                    if (c_user != null) {
                        if (c_user.equals("Inprocess")) {
                            order_consi.setChecked(true);
                            order_inpro.setChecked(true);
                        } else if (c_user.equals("On Delivery")) {
                            order_consi.setChecked(true);
                            order_inpro.setChecked(true);
                            order_deli.setChecked(true);
                        } else if (c_user.equals("Check")) {
                            order_consi.setChecked(true);
                            order_inpro.setChecked(true);
                            order_deli.setChecked(true);
                            order_status.setText("Sign Required");
                        } else if (c_user.equals("Order Delivered")) {
                            order_consi.setChecked(true);
                            order_inpro.setChecked(true);
                            order_deli.setChecked(true);
                            order_status.setText("Order Delivered");
                        }
                    }
                    user = new c_order_status_user(customer_user.getOrder_id(), customer_user.getDate(), customer_user.getT_quantity(), customer_user.getTotal_of_amt());
                    customers.add(user);
                    all_customers.add(customer_user);
                }

                Toast.makeText(getActivity(), "Customers", Toast.LENGTH_SHORT).show();
                if (fromDate1 != null && toDate1 != null) {
                    setCustomers();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        fd_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        fromDate1 = Calendar.getInstance();
                        fromDate1.set(year, month + 1, dayOfMonth, 0, 0, 0);
                        textview_fd1.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                        if (fromDate1 != null && toDate1 != null) {
                            setCustomers();
                        }

                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
                dialog.show();
            }
        });

        td_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        toDate1 = Calendar.getInstance();
                        toDate1.set(Calendar.YEAR, year);
                        toDate1.set(Calendar.MONTH, month + 1);
                        toDate1.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        textview_td1.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                        if (fromDate1 != null && toDate1 != null) {
                            setCustomers();
                        }

                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
                dialog.show();
            }
        });


        return view;
    }

    private void setCustomers() {
        List<c_order_status_user> users = new ArrayList<>();
        for (c_order_user vendor : all_customers) {
            Calendar calendar = Calendar.getInstance();
            Log.e("calendar", vendor.getCustomer_name());
            calendar.setTimeInMillis(vendor.getOrder_id());
            if (calendar.compareTo(fromDate1) >= 0 && calendar.compareTo(toDate1) <= 0) {
                Log.e("customer", "found");
                user = new c_order_status_user(vendor.getOrder_id(), vendor.getDate(), vendor.getT_quantity(), vendor.getTotal_of_amt());
                users.add(user);
            }
        }
        acceessAdapter = new c_order_status_aa(getActivity(), users, all_customers);
        recyclerView.setAdapter(acceessAdapter);
        total_amt += user.amount;
        t_amt.setText(total_amt + "");

        total_qty += user.quantity;
        t_qty.setText(total_qty + "");
    }


}
