package com.commercial.after_login.retailer;

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

public class r_order_status extends Fragment {

    TextView order_id, order_status, t_qty, t_amt, textview_fd1, textview_td1;
    Button fd_btn, td_btn, display;
    CheckBox order_consi, order_inpro, order_deli;
    RecyclerView recyclerView;
    Calendar fromDate1, toDate1, calendar;
    List<r_order_status_user> retailers = new ArrayList<>();
    List<r_order_user> all_retailers = new ArrayList<>();
    r_order_status_aa acceessAdapter;
    r_order_status_user user;
    r_order_user status;
    String r_user;
    private int total_amt;
    private int total_qty;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.order_status_r, container, false);

        order_id = view.findViewById(R.id.osr_oid);
        order_consi = view.findViewById(R.id.osr_cb1);
        order_inpro = view.findViewById(R.id.osr_cb2);
        order_deli = view.findViewById(R.id.osr_cb3);
        order_status = view.findViewById(R.id.osr_ost);
        fd_btn = view.findViewById(R.id.osr_btn_fd);
        td_btn = view.findViewById(R.id.osr_btn_td);
        textview_fd1 = view.findViewById(R.id.osr_tv_fd);
        textview_td1 = view.findViewById(R.id.osr_tv_td);
        recyclerView = view.findViewById(R.id.recycler_osr);
        t_amt = view.findViewById(R.id.osr_tv_agrand);
        t_qty = view.findViewById(R.id.osr_tv_qgrand);
        display = view.findViewById(R.id.osr_display);
        calendar = Calendar.getInstance();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        acceessAdapter = new r_order_status_aa(getContext(), new ArrayList<r_order_status_user>(), null);
        recyclerView.setAdapter(acceessAdapter);


        fd_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        fromDate1 = Calendar.getInstance();
                        fromDate1.set(year, month + 1, dayOfMonth, 0, 0, 0);
                        Log.e("fromdate1", fromDate1.toString());
                        textview_fd1.setText(dayOfMonth + "/" + (month + 1) + "/" + year);

                        if (fromDate1 != null && toDate1 != null) {
                            Log.e("TAG", "found");
                            setretailers();

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
                        Log.e("todate1", fromDate1.toString());
                        textview_td1.setText(dayOfMonth + "/" + (month + 1) + "/" + year);

                        if (fromDate1 != null && toDate1 != null) {
                            Log.e("TAG", "found");
                            setretailers();

                        }

                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
                dialog.show();
            }
        });

        reference.child("Retailer").child("Order").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    r_order_user retailer_user = child.getValue(r_order_user.class);
                    Log.e("retailers", retailer_user.toString());
                    order_id.setText(String.valueOf(retailer_user.getOrder_id()));
                    r_user = retailer_user.getOrder_status();
                    Log.e("r_user", r_user);
                    if (r_user != null) {
                        if (r_user.equals("Inprocess")) {
                            order_consi.setChecked(true);
                            order_inpro.setChecked(true);
                        } else if (r_user.equals("On Delivery")) {
                            order_consi.setChecked(true);
                            order_inpro.setChecked(true);
                            order_deli.setChecked(true);
                        } else if (r_user.equals("Check")) {
                            order_consi.setChecked(true);
                            order_inpro.setChecked(true);
                            order_deli.setChecked(true);
                            order_status.setText("Sign Required");
                        } else if (r_user.equals("Order Delivered")) {
                            order_consi.setChecked(true);
                            order_inpro.setChecked(true);
                            order_deli.setChecked(true);
                            order_status.setText("Order Delivered");
                        }
                    }
                    user = new r_order_status_user(retailer_user.getOrder_id(), retailer_user.getDate(), retailer_user.getT_quantity(), retailer_user.getTotal_of_amt());
                    retailers.add(user);
                    all_retailers.add(retailer_user);

                }
                if (fromDate1 != null && toDate1 != null) {
                    Log.e("TAG", "found");
                    setretailers();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("retailers", databaseError.toString());
            }
        });


        return view;
    }

    private void setretailers() {
        List<r_order_status_user> users = new ArrayList<>();
        for (r_order_user vendor : all_retailers) {
            Log.e("TAG", "we are here");
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(vendor.getOrder_id());
            if (calendar.compareTo(fromDate1) >= 0 && calendar.compareTo(toDate1) <= 0) {
                user = new r_order_status_user(vendor.getOrder_id(), vendor.getDate(), vendor.getT_quantity(), vendor.getTotal_of_amt());
                users.add(user);
                Log.e("data", users.toString());
            }
            acceessAdapter = new r_order_status_aa(getActivity(), users, all_retailers);
            recyclerView.setAdapter(acceessAdapter);
            total_amt += user.amount;
            t_amt.setText(total_amt + "");

            total_qty += user.quantity;
            t_qty.setText(total_qty + "");



        }

    }
}
