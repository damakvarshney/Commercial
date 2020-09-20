package com.commercial.after_login.admin;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.commercial.R;
import com.commercial.before_login.register_retailer_user;
import com.commercial.before_login.register_vendor_user;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.commercial.AppController.retailer_database_user_email;
import static com.commercial.AppController.vendor_database_user_email;

public class a_give_access extends Fragment {

    Spinner spinner1;
    TextView fd_textview, td_textview;
    Calendar calendar;
    Button fd_btn, td_btn;
    a_give_access_User user;
    List<a_give_access_User> vendors = new ArrayList<>();
    List<a_give_access_User> retailers = new ArrayList<>();
    RecyclerView recyclerView;

    a_give_access_aa acceessAdapter;

    Calendar fromDate, toDate;

    List<register_vendor_user> all_vendors = new ArrayList<>();
    List<register_retailer_user> all_retailers = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.a_give_access, container, false);

        spinner1 = view.findViewById(R.id.ga_spinner_ct);
        fd_textview = view.findViewById(R.id.ga_tv_fd);
        td_textview = view.findViewById(R.id.ga_tv_td);
        fd_btn = view.findViewById(R.id.ga_btn_fd);
        td_btn = view.findViewById(R.id.ga_btn_td);
        calendar = Calendar.getInstance();
        recyclerView = view.findViewById(R.id.recycler_ga);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.setAdapter(acceessAdapter);

        acceessAdapter = new a_give_access_aa(getActivity(), new ArrayList<a_give_access_User>(), all_vendors, all_retailers, null);

        fd_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        fromDate = Calendar.getInstance();
                        fromDate.set(year, month, dayOfMonth, 0, 0, 0);

                        fd_textview.setText(dayOfMonth + "/" + (month+1) + "/" + year);

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

                        toDate = Calendar.getInstance();
                        toDate.set(Calendar.YEAR, year);
                        toDate.set(Calendar.MONTH, month);
                        toDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);


                        td_textview.setText(dayOfMonth + "/" + (month+1) + "/" + year);

                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
                dialog.show();
            }
        });

        final List<String> states = new ArrayList<>();
        states.add("None");
        states.add("Retailer");
        states.add("Vendor");

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, states);
        spinner1.setAdapter(adapter);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (fromDate == null || toDate == null) {
                    Toast.makeText(getActivity(), "Select Date First", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (position == 0) {
                    Toast.makeText(getActivity(), "None Selected", Toast.LENGTH_SHORT).show();
                    acceessAdapter = new a_give_access_aa(getActivity(), new ArrayList<a_give_access_User>(), null, null, null);
                    recyclerView.setAdapter(acceessAdapter);
                } else if (position == 1) {
                    Toast.makeText(getActivity(), "Retailer", Toast.LENGTH_SHORT).show();
                    setRetailers();
                } else if (position == 2) {
                    Toast.makeText(getActivity(), "Vendor", Toast.LENGTH_SHORT).show();
                    setVendors();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getActivity(), "Enter the Feed", Toast.LENGTH_SHORT).show();
            }
        });

        retailer_database_user_email.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    register_retailer_user retailer_user = child.getValue(register_retailer_user.class);
                    user = new a_give_access_User(retailer_user.getR_owner_name(), retailer_user.getR_consumer_id());
                    retailers.add(user);
                    all_retailers.add(retailer_user);
                }
                int position = spinner1.getSelectedItemPosition();
                if (position == 1) {
                    Toast.makeText(getActivity(), "Retailer", Toast.LENGTH_SHORT).show();
                    if (fromDate != null && toDate != null)
                        setRetailers();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        vendor_database_user_email.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e("vendors", dataSnapshot.toString());
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    register_vendor_user retailer_user = child.getValue(register_vendor_user.class);

                    user = new a_give_access_User(retailer_user.getVendor_name(), retailer_user.getVendor_id());
                    vendors.add(user);
                    all_vendors.add(retailer_user);
                }
                int position = spinner1.getSelectedItemPosition();
                if (position == 2) {
                    Toast.makeText(getActivity(), "Vendors", Toast.LENGTH_SHORT).show();
                    if (fromDate != null && toDate != null)
                        setVendors();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }

    private void setRetailers() {
        List<a_give_access_User> users = new ArrayList<>();
        for (register_retailer_user vendor : all_retailers) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(Long.parseLong(vendor.getR_consumer_id()));
            if (calendar.compareTo(fromDate) >= 0 && calendar.compareTo(toDate) <= 0) {
                user = new a_give_access_User(vendor.getR_owner_name(), vendor.getR_consumer_id());
                users.add(user);
            }
        }
        acceessAdapter = new a_give_access_aa(getActivity(), users, all_vendors, all_retailers, "vendor");
        recyclerView.setAdapter(acceessAdapter);
    }

    private void setVendors() {
        List<a_give_access_User> users = new ArrayList<>();
        for (register_vendor_user vendor : all_vendors) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(Long.parseLong(vendor.getVendor_id()));
            if (calendar.compareTo(fromDate) >= 0 && calendar.compareTo(toDate) <= 0) {
                user = new a_give_access_User(vendor.getVendor_name(), vendor.getVendor_id());
                users.add(user);
            }
        }
        acceessAdapter = new a_give_access_aa(getActivity(), users, all_vendors, all_retailers, "vendor");
        recyclerView.setAdapter(acceessAdapter);
    }
}
