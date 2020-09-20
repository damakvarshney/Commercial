package com.commercial.after_login.vendor;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
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

public class v_pay_status extends Fragment {

    TextView t_amt, textview_fd1, textview_td1;
    Button fd_btn, td_btn;
    RecyclerView recyclerView;
    Calendar fromDate1, toDate1, calendar;
    List<v_pay_status_user> vendors = new ArrayList<>();
    List<v_order_user> all_vendors = new ArrayList<>();
    v_pay_status_aa acceessAdapter;
    v_pay_status_user user;
    private int total_amt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.payment_status_v, container, false);

        recyclerView = view.findViewById(R.id.recycler_payv);

        fd_btn = view.findViewById(R.id.payv_btn_fd);
        td_btn = view.findViewById(R.id.payv_btn_td);
        textview_fd1 = view.findViewById(R.id.payv_tv_fd);
        textview_td1 = view.findViewById(R.id.payv_tv_td);
        t_amt = view.findViewById(R.id.payv_grand);
        calendar = Calendar.getInstance();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

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

        reference.child("Vendor").child("Order").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e("vendors", dataSnapshot.toString());
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    v_order_user vendor_user = child.getValue(v_order_user.class);

                    user = new v_pay_status_user(vendor_user.getOrder_id(), vendor_user.getDate(), vendor_user.getPay_status(), vendor_user.getTotal_of_amt());
                    vendors.add(user);
                    all_vendors.add(vendor_user);
                }

                Toast.makeText(getActivity(), "vendors", Toast.LENGTH_SHORT).show();
                if (fromDate1 != null && toDate1 != null) {
                    Log.e("TAG", "found");
                    setvendors();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("vendors", databaseError.toString());
            }
        });

        return view;

    }

    private void setvendors() {
        Toast.makeText(getContext(), "context", Toast.LENGTH_SHORT).show();
        List<v_pay_status_user> users = new ArrayList<>();
        for (v_order_user vendor : all_vendors) {
            calendar = Calendar.getInstance();
            calendar.setTimeInMillis(vendor.getOrder_id());
            Log.e("vendor", String.valueOf(vendor.getOrder_id()));
            if (calendar.compareTo(fromDate1) >= 0 && calendar.compareTo(toDate1) <= 0) {
                // Toast.makeText(getContext(),vendor.getDate(), Toast.LENGTH_SHORT).show();
                Log.e("vendor", "found");
                user = new v_pay_status_user(vendor.getOrder_id(), vendor.getDate(), vendor.getPay_status(), vendor.getTotal_of_amt());
                users.add(user);
            }
            acceessAdapter = new v_pay_status_aa(getContext(), users, all_vendors);
            recyclerView.setAdapter(acceessAdapter);
            total_amt += user.amount;
            t_amt.setText(total_amt + "");
        }



    }
}
