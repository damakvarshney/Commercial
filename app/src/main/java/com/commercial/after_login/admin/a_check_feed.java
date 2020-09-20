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
import com.commercial.after_login.customer.c_feedback_user;
import com.commercial.after_login.retailer.r_feedback_user;
import com.commercial.after_login.vendor.v_feedback_user;
import com.commercial.before_login.bottom.contact_us.Contact_Us_user;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.commercial.AppController.reference;

public class a_check_feed extends Fragment {

    TextView fd_textview, td_textview;
    Button fd_btn, td_btn;
    Spinner spinner1;
    a_check_feed_user user;
    List<a_check_feed_user> vendors = new ArrayList<>();
    List<a_check_feed_user> retailers = new ArrayList<>();
    List<a_check_feed_user> customers = new ArrayList<>();
    List<a_check_feed_user> miscs = new ArrayList<>();
    a_check_feed_aa acceessAdapter;
    RecyclerView recyclerView;
    Calendar fromDate, toDate,calendar;

    List<v_feedback_user> all_vendors = new ArrayList<>();
    List<r_feedback_user> all_retailers = new ArrayList<>();
    List<c_feedback_user> all_customers = new ArrayList<>();
    List<Contact_Us_user> all_miscs = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for getActivity() fragment
        View view = inflater.inflate(R.layout.a_check_feed, container, false);

        reference.child("Retailer").child("Feedback").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e("retailer", dataSnapshot.toString());
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    r_feedback_user retailer_user = child.getValue(r_feedback_user.class);
                    user = new a_check_feed_user(retailer_user.getDate(), retailer_user.getName(), retailer_user.getSubject());
                    retailers.add(user);
                    all_retailers.add(retailer_user);
                }
                int position = spinner1.getSelectedItemPosition();
                if (position == 1) {
                    Toast.makeText(getActivity(), "Retailer", Toast.LENGTH_SHORT).show();
                    if (fromDate != null && toDate != null) {
                        setretailer();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        reference.child("Customer").child("Feedback").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e("customer", dataSnapshot.toString());
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    c_feedback_user retailer_user = child.getValue(c_feedback_user.class);

                    user = new a_check_feed_user(retailer_user.getDate(), retailer_user.getName(), retailer_user.getSubject());
                    customers.add(user);
                    all_customers.add(retailer_user);
                }
                int position = spinner1.getSelectedItemPosition();
                if (position == 2) {
                    Toast.makeText(getActivity(), "Customers", Toast.LENGTH_SHORT).show();
                    if (fromDate != null && toDate != null)
                        setcustomer();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("customer", databaseError.toString());
            }
        });

        reference.child("Vendor").child("Feedback").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e("vendors", dataSnapshot.toString());
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    v_feedback_user retailer_user = child.getValue(v_feedback_user.class);

                    user = new a_check_feed_user(retailer_user.getDate(), retailer_user.getName(), retailer_user.getSubject());
                    vendors.add(user);
                    all_vendors.add(retailer_user);
                }
                int position = spinner1.getSelectedItemPosition();
                if (position == 3) {
                    Toast.makeText(getActivity(), "Vendors", Toast.LENGTH_SHORT).show();
                    if (fromDate != null && toDate != null)
                        setVendors();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        reference.child("Miscellaneous").child("Feedback").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e("miscellaneous", dataSnapshot.toString());
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Contact_Us_user retailer_user = child.getValue(Contact_Us_user.class);

                    user = new a_check_feed_user(retailer_user.getDate(), retailer_user.getName(), retailer_user.getSubject());
                    miscs.add(user);
                    all_miscs.add(retailer_user);
                }
                int position = spinner1.getSelectedItemPosition();
                if (position == 4) {
                    Toast.makeText(getActivity(), "Miscellaneous", Toast.LENGTH_SHORT).show();
                    if (fromDate != null && toDate != null)
                        setmisc();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        fd_textview = view.findViewById(R.id.cf_tv_fd);
        td_textview = view.findViewById(R.id.cf_tv_td);
        fd_btn = view.findViewById(R.id.cf_btn_fd);
        td_btn = view.findViewById(R.id.cf_btn_td);
        calendar = Calendar.getInstance();
        spinner1 = view.findViewById(R.id.cf_spinner_ft);
        calendar = Calendar.getInstance();

        recyclerView = view.findViewById(R.id.recycler_cf);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        acceessAdapter = new a_check_feed_aa(getActivity(), new ArrayList<a_check_feed_user>(), all_retailers, all_vendors, all_customers, all_miscs, null);
        recyclerView.setAdapter(acceessAdapter);
//
////      both date button

        fd_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        fromDate = Calendar.getInstance();
                        fromDate.set(year, month + 1, dayOfMonth, 0, 0, 0);
                        fd_textview.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                        Log.e("fd_textview", fd_textview.getText().toString());


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
                        toDate.set(Calendar.MONTH, month + 1);
                        toDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        td_textview.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                        Log.e("td_textview", td_textview.getText().toString());

                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
                dialog.show();
            }
        });

        // spinner1 == feed type

        final List<String> states = new ArrayList<>();
        states.add("None");
        states.add("Retailer");
        states.add("Customer");
        states.add("Vendor");
        states.add("Miscellaneous");
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
                    acceessAdapter = new a_check_feed_aa(getActivity(), new ArrayList<a_check_feed_user>(), null, null, null, null, null);
                    recyclerView.setAdapter(acceessAdapter);
                } else if (position == 1) {
                    Toast.makeText(getActivity(), "Retailer", Toast.LENGTH_SHORT).show();
                    setretailer();
                } else if (position == 2) {
                    Toast.makeText(getActivity(), "Customer", Toast.LENGTH_SHORT).show();
                    setcustomer();
                } else if (position == 3) {
                    Toast.makeText(getActivity(), "Vendor", Toast.LENGTH_SHORT).show();
                    setVendors();
                } else if (position == 4) {
                    Toast.makeText(getActivity(), "Miscellaneous", Toast.LENGTH_SHORT).show();
                    setmisc();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getActivity(), "Enter the Feed", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }

    private void setretailer() {
        List<a_check_feed_user> users = new ArrayList<>();
        for (r_feedback_user vendor : all_retailers) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(vendor.getfeedback_id());
            Log.e("retailer", String.valueOf(vendor.getfeedback_id()));
            if (calendar.compareTo(fromDate) >= 0 && calendar.compareTo(toDate) <= 0) {
                // Toast.makeText(getContext(),vendor.getDate(), Toast.LENGTH_SHORT).show();
                Log.e("retailer", "found");
                user = new a_check_feed_user(vendor.getDate(), vendor.getName(), vendor.getSubject());
                users.add(user);
            }
        }
        acceessAdapter = new a_check_feed_aa(getActivity(), users, all_retailers, all_vendors, all_customers, all_miscs, "retailer");
        recyclerView.setAdapter(acceessAdapter);
    }

    private void setmisc() {
        List<a_check_feed_user> users = new ArrayList<>();
        for (Contact_Us_user vendor : all_miscs) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(vendor.getFeedback_id());
            if (calendar.compareTo(fromDate) >= 0 && calendar.compareTo(toDate) <= 0) {
                Log.e("misc", "found");
                user = new a_check_feed_user(vendor.getDate(), vendor.getName(), vendor.getSubject());
                users.add(user);
            }
        }
        acceessAdapter = new a_check_feed_aa(getActivity(), users, all_retailers, all_vendors, all_customers, all_miscs, "misc");
        recyclerView.setAdapter(acceessAdapter);
    }


    private void setcustomer() {
        List<a_check_feed_user> users = new ArrayList<>();
        for (c_feedback_user vendor : all_customers) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(vendor.getfeedback_id());
            if (calendar.compareTo(fromDate) >= 0 && calendar.compareTo(toDate) <= 0) {
                Log.e("customer", "found");
                user = new a_check_feed_user(vendor.getDate(), vendor.getName(), vendor.getSubject());
                users.add(user);
            }
        }
        acceessAdapter = new a_check_feed_aa(getActivity(), users, all_retailers, all_vendors, all_customers, all_miscs, "customer");
        recyclerView.setAdapter(acceessAdapter);
    }

    private void setVendors() {
        List<a_check_feed_user> users = new ArrayList<>();
        for (v_feedback_user vendor : all_vendors) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(vendor.getfeedback_id());
            if (calendar.compareTo(fromDate) >= 0 && calendar.compareTo(toDate) <= 0) {
                Log.e("vendor", "found");
                user = new a_check_feed_user(vendor.getDate(), vendor.getName(), vendor.getSubject());
                users.add(user);
            }
        }
        acceessAdapter = new a_check_feed_aa(getActivity(), users, all_retailers, all_vendors, all_customers, all_miscs, "vendor");
        recyclerView.setAdapter(acceessAdapter);
    }
}

