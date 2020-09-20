package com.commercial.after_login.vendor;

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
import com.commercial.after_login.customer.c_order_user;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.commercial.AppController.reference;


public class v_order_status extends Fragment {

    TextView order_id, order_status, t_qty, t_amt, textview_fd1, textview_td1;
    Button fd_btn, td_btn;
    CheckBox order_consi, order_inpro, order_deli;
    RecyclerView recyclerView;
    Calendar fromDate1, toDate1, calendar;
    List<v_order_status_user> vendors = new ArrayList<>();
    List<v_order_user> all_vendors = new ArrayList<>();
    v_order_status_aa acceessAdapter;
    v_order_status_user user;
    c_order_user status;
    String v_user;
    private int total_amt;
    private int total_qty;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.order_status_v, container, false);

        recyclerView = view.findViewById(R.id.recycler_osv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        order_id = view.findViewById(R.id.osv_tv_oid);
        order_consi = view.findViewById(R.id.osv_cb1);
        order_inpro = view.findViewById(R.id.osv_cb2);
        order_deli = view.findViewById(R.id.osv_cb3);
        order_status = view.findViewById(R.id.osv_tv_ost);
        fd_btn = view.findViewById(R.id.osv_btn_fd);
        td_btn = view.findViewById(R.id.osv_btn_td);
        textview_fd1 = view.findViewById(R.id.osv_tv_fd);
        textview_td1 = view.findViewById(R.id.osv_tv_td);
        t_amt = view.findViewById(R.id.osv_tv_agrand);
        t_qty = view.findViewById(R.id.osv_tv_qgrand);
        calendar = Calendar.getInstance();


        reference.child("Vendor").child("Order").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e("Vendors", dataSnapshot.toString());
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    v_order_user vendor_user = child.getValue(v_order_user.class);
                    all_vendors.add(vendor_user);
                    Log.e("Vendors", vendor_user.toString());
                    order_id.setText(String.valueOf(vendor_user.getOrder_id()));
                    v_user = vendor_user.getOrder_status();
                    Log.e("v_user", v_user);
                    if (v_user != null) {
                        if (v_user.equals("Inprocess")) {
                            order_consi.setChecked(true);
                            order_inpro.setChecked(true);
                        } else if (v_user.equals("On Delivery")) {
                            order_consi.setChecked(true);
                            order_inpro.setChecked(true);
                            order_deli.setChecked(true);
                        } else if (v_user.equals("Check")) {
                            order_consi.setChecked(true);
                            order_inpro.setChecked(true);
                            order_deli.setChecked(true);
                            order_status.setText("Sign Required");
                        } else if (v_user.equals("Order Delivered")) {
                            order_consi.setChecked(true);
                            order_inpro.setChecked(true);
                            order_deli.setChecked(true);
                            order_status.setText("Order Delivered");
                        }
                    }
                    user = new v_order_status_user(vendor_user.getOrder_id(), vendor_user.getDate(), vendor_user.getT_quantity(), vendor_user.getTotal_of_amt());
                    vendors.add(user);

                }

                Toast.makeText(getActivity(), "vendors", Toast.LENGTH_SHORT).show();
                if (fromDate1 != null && toDate1 != null) {
                    setvendors();
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
                            setvendors();
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
                            setvendors();
                        }

                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
                dialog.show();
            }
        });


        return view;
    }

    private void setvendors() {
        List<v_order_status_user> users = new ArrayList<>();
        for (v_order_user vendor : all_vendors) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(vendor.getOrder_id());
            if (calendar.compareTo(fromDate1) >= 0 && calendar.compareTo(toDate1) <= 0) {
                user = new v_order_status_user(vendor.getOrder_id(), vendor.getDate(), vendor.getT_quantity(), vendor.getTotal_of_amt());
                users.add(user);
            }
        }
        acceessAdapter = new v_order_status_aa(getActivity(), users, all_vendors);
        recyclerView.setAdapter(acceessAdapter);
        total_amt += user.amount;
        t_amt.setText(total_amt + "");

        total_qty += user.quantity;
        t_qty.setText(total_qty + "");
    }
}
