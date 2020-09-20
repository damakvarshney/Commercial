package com.commercial.after_login.customer;

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
import androidx.recyclerview.widget.RecyclerView;

import com.commercial.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.commercial.AppController.reference;

public class c_pay_status extends Fragment {

    TextView t_amt, textview_fd1, textview_td1;
    Button fd_btn, td_btn;
    RecyclerView recyclerView;
    Calendar fromDate1, toDate1, calendar;
    List<c_pay_status_user> customers = new ArrayList<>();
    List<c_order_user> all_customers = new ArrayList<>();
    c_pay_status_aa acceessAdapter;
    c_pay_status_user user;
    private int total_amt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.payment_status_c, container, false);

        recyclerView = view.findViewById(R.id.recycler_payc);

        fd_btn = view.findViewById(R.id.payc_btn_fd);
        td_btn = view.findViewById(R.id.payc_btn_td);
        textview_fd1 = view.findViewById(R.id.payc_tv_fd);
        textview_td1 = view.findViewById(R.id.payc_tv_td);
        t_amt = view.findViewById(R.id.payc_grand);
        calendar = Calendar.getInstance();
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));

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
                            Log.e("TAG", "found");
                            setcustomers();

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
                            Log.e("TAG", "found");
                            setcustomers();

                        }

                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
                dialog.show();
            }
        });

        reference.child("Customer").child("Order").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e("customers", dataSnapshot.toString());
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    c_order_user customer_user = child.getValue(c_order_user.class);

                    user = new c_pay_status_user(customer_user.getOrder_id(), customer_user.getDate(), customer_user.getPay_status(), customer_user.getTotal_of_amt());
                    customers.add(user);
                    all_customers.add(customer_user);
                }

                Toast.makeText(getActivity(), "customers", Toast.LENGTH_SHORT).show();
                if (fromDate1 != null && toDate1 != null) {
                    Log.e("TAG", "found");
                    setcustomers();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("customers", databaseError.toString());
            }
        });

        return view;

    }

    private void setcustomers() {
        Toast.makeText(getContext(), "context", Toast.LENGTH_SHORT).show();
        List<c_pay_status_user> users = new ArrayList<>();
        for (c_order_user vendor : all_customers) {
            calendar = Calendar.getInstance();
            calendar.setTimeInMillis(vendor.getOrder_id());
            Log.e("customer", String.valueOf(vendor.getOrder_id()));
            if (calendar.compareTo(fromDate1) >= 0 && calendar.compareTo(toDate1) <= 0) {
                // Toast.makeText(getContext(),vendor.getDate(), Toast.LENGTH_SHORT).show();
                Log.e("customer", "found");
                user = new c_pay_status_user(vendor.getOrder_id(), vendor.getDate(), vendor.getPay_status(), vendor.getTotal_of_amt());
                users.add(user);
            }
        }
        acceessAdapter = new c_pay_status_aa(getContext(), users, all_customers);
        recyclerView.setAdapter(acceessAdapter);
        total_amt += user.amount;
        t_amt.setText(total_amt + "");
    }
}
