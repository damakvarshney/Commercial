package com.commercial.after_login.retailer;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.commercial.R;
import com.commercial.after_login.admin.a_check_feed_aa;
import com.commercial.after_login.admin.a_check_feed_user;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.commercial.AppController.reference;

public class r_pay_status extends Fragment {

    TextView t_amt,textview_fd1,textview_td1;
    Button fd_btn,td_btn;
    RecyclerView recyclerView;
    Calendar fromDate1,toDate1,calendar;
    List<r_pay_status_user> retailers = new ArrayList<>();
    List<r_order_user> all_retailers = new ArrayList<>();
    r_pay_status_aa acceessAdapter;
    r_pay_status_user user;
    private int total_amt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.payment_status_r, container, false);

        recyclerView = view.findViewById(R.id.recycler_payr);

        fd_btn = view.findViewById(R.id.payr_btn_fd);
        td_btn = view.findViewById(R.id.payr_btn_td);
        textview_fd1 = view.findViewById(R.id.payr_tv_fd);
        textview_td1 = view.findViewById(R.id.payr_tv_td);
        t_amt = view.findViewById(R.id.payr_grand);
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
                Log.e("retailers", dataSnapshot.toString());
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    r_order_user retailer_user = child.getValue(r_order_user.class);

                    user = new r_pay_status_user(retailer_user.getOrder_id(), retailer_user.getDate(),retailer_user.getPay_status(), retailer_user.getTotal_of_amt());
                    retailers.add(user);
                    all_retailers.add(retailer_user);
                }

                Toast.makeText(getActivity(), "Retailers", Toast.LENGTH_SHORT).show();
                if (fromDate1 != null && toDate1 != null){
                    Log.e("TAG", "found" );
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
        Toast.makeText(getContext(), "context", Toast.LENGTH_SHORT).show();
        List<r_pay_status_user> users = new ArrayList<>();
        for (r_order_user vendor : all_retailers) {
            calendar = Calendar.getInstance();
            calendar.setTimeInMillis(vendor.getOrder_id());
            Log.e("retailer",String.valueOf(vendor.getOrder_id()) );
            if (calendar.compareTo(fromDate1) >= 0 && calendar.compareTo(toDate1) <= 0) {
                // Toast.makeText(getContext(),vendor.getDate(), Toast.LENGTH_SHORT).show();
                Log.e("retailer","found");
                user = new r_pay_status_user(vendor.getOrder_id(), vendor.getDate(), vendor.getPay_status(),vendor.getTotal_of_amt());
                users.add(user);
            }
        }
        acceessAdapter = new r_pay_status_aa(getContext(), users, all_retailers);
        recyclerView.setAdapter(acceessAdapter);
        total_amt += user.amount;
        t_amt.setText(total_amt + "");
    }
}
