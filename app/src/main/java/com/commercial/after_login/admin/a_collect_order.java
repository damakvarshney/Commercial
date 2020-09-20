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
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.commercial.R;
import com.commercial.after_login.customer.c_order_user;
import com.commercial.after_login.retailer.r_order_user;
import com.commercial.after_login.vendor.v_order_user;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.commercial.AppController.reference;

public class a_collect_order extends Fragment {
    TextView textview_fd1, textview_td1;
    Button btn_fd1, btn_td1;
    Calendar calendar;
    Calendar fromDate1, toDate1;
    Spinner spinner1;
    FragmentManager fragmentManager;

    a_collect_order_aa1 acceessAdapter1;

    List<a_collect_order_user1> retailers = new ArrayList<>();
    List<a_collect_order_user1> customers = new ArrayList<>();
    List<a_collect_order_user1> vendors = new ArrayList<>();
    a_collect_order_user1 user;

    List<r_order_user> all_retailers = new ArrayList<>();
    List<c_order_user> all_customers = new ArrayList<>();
    List<v_order_user> all_vendors = new ArrayList<>();
    RecyclerView recyclerView1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.a_collect_order, container, false);

        fragmentManager = getChildFragmentManager();

        reference.child("Retailer").child("Order").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e("data", dataSnapshot.toString());
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    r_order_user retailer_user = child.getValue(r_order_user.class);
                    user = new a_collect_order_user1(retailer_user.getOrder_id(), retailer_user.getDate(), retailer_user.getRetailer_name(), retailer_user.getOrder_status());
                    retailers.add(user);
                    all_retailers.add(retailer_user);
                }
                int position = spinner1.getSelectedItemPosition();
                if (position == 1) {
                    Toast.makeText(getActivity(), "Retailer", Toast.LENGTH_SHORT).show();
                    if (fromDate1 != null && toDate1 != null) {
                        setRetailer();
                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        reference.child("Customer").child("Order").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e("customers", dataSnapshot.toString());
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    c_order_user customer_user = child.getValue(c_order_user.class);

                    user = new a_collect_order_user1(customer_user.getOrder_id(), customer_user.getDate(), customer_user.getCustomer_name(), customer_user.getOrder_status());
                    customers.add(user);
                    all_customers.add(customer_user);
                }
                int position = spinner1.getSelectedItemPosition();
                if (position == 2) {
                    Toast.makeText(getActivity(), "Customers", Toast.LENGTH_SHORT).show();
                    if (fromDate1 != null && toDate1 != null) {
                        setCustomers();
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        reference.child("Vendor").child("Order").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e("vendors", dataSnapshot.toString());
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    v_order_user vendor_user = child.getValue(v_order_user.class);

                    user = new a_collect_order_user1(vendor_user.getOrder_id(), vendor_user.getDate(), vendor_user.getVendor_name(), vendor_user.getOrder_status());
                    vendors.add(user);
                    all_vendors.add(vendor_user);
                }
                int position = spinner1.getSelectedItemPosition();
                if (position == 3) {
                    Toast.makeText(getActivity(), "Vendors", Toast.LENGTH_SHORT).show();
                    if (fromDate1 != null && toDate1 != null) {
                        setVendors();
                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "Can't Load Data", Toast.LENGTH_SHORT).show();
            }
        });


        textview_fd1 = view.findViewById(R.id.co_tv_fd1);
        textview_td1 = view.findViewById(R.id.co_tv_td1);
        btn_fd1 = view.findViewById(R.id.co_btn_fd1);
        btn_td1 = view.findViewById(R.id.co_btn_td1);
        spinner1 = view.findViewById(R.id.co_spinner_ft1);
        calendar = Calendar.getInstance();
        recyclerView1 = view.findViewById(R.id.recycler_cor1);

        recyclerView1.setAdapter(acceessAdapter1);
        acceessAdapter1 = new a_collect_order_aa1(getActivity(), new ArrayList<a_collect_order_user1>(), all_retailers, all_customers, all_vendors, null, fragmentManager);

        recyclerView1.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        btn_fd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        fromDate1 = Calendar.getInstance();
                        fromDate1.set(year, month + 1, dayOfMonth, 0, 0, 0);
                        textview_fd1.setText(dayOfMonth + "/" + (month + 1) + "/" + year);

                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
                dialog.show();
            }
        });

        btn_td1.setOnClickListener(new View.OnClickListener() {
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

                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
                dialog.show();
            }
        });


        final List<String> states = new ArrayList<>();
        states.add("None");
        states.add("Retailer");
        states.add("Customer");
        states.add("Vendor");

        SpinnerAdapter adapter1 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, states);
        spinner1.setAdapter(adapter1);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (fromDate1 == null || toDate1 == null) {
                    Toast.makeText(getActivity(), "Select Date First", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (position == 0) {
                    Toast.makeText(getActivity(), "None Selected", Toast.LENGTH_SHORT).show();
                    acceessAdapter1 = new a_collect_order_aa1(getActivity(), new ArrayList<a_collect_order_user1>(), null, null, null, null, fragmentManager);
                    recyclerView1.setAdapter(acceessAdapter1);
                } else if (position == 1) {
                    Toast.makeText(getActivity(), "Retailer", Toast.LENGTH_SHORT).show();
                    setRetailer();
                } else if (position == 2) {
                    Toast.makeText(getActivity(), "Customer", Toast.LENGTH_SHORT).show();
                    setCustomers();
                } else if (position == 3) {
                    Toast.makeText(getActivity(), "Vendor", Toast.LENGTH_SHORT).show();
                    setVendors();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getActivity(), "Enter the Feed", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void setRetailer() {
        List<a_collect_order_user1> users = new ArrayList<>();
        for (r_order_user vendor : all_retailers) {
            Calendar calendar = Calendar.getInstance();
            Log.e("date", vendor.getDate());
            calendar.setTimeInMillis(vendor.getOrder_id());
            if (calendar.compareTo(fromDate1) >= 0 && calendar.compareTo(toDate1) <= 0) {
                user = new a_collect_order_user1(vendor.getOrder_id(), vendor.getDate(), vendor.getRetailer_name(), vendor.getOrder_status());
                users.add(user);
            }
        }
        acceessAdapter1 = new a_collect_order_aa1(getActivity(), users, all_retailers, all_customers, all_vendors, "retailer", fragmentManager);
        recyclerView1.setAdapter(acceessAdapter1);
    }

    private void setVendors() {
        List<a_collect_order_user1> users = new ArrayList<>();
        for (v_order_user vendor : all_vendors) {
            Calendar calendar = Calendar.getInstance();
            Log.e("date", vendor.getDate());
            calendar.setTimeInMillis(vendor.getOrder_id());
            if (calendar.compareTo(fromDate1) >= 0 && calendar.compareTo(toDate1) <= 0) {
                user = new a_collect_order_user1(vendor.getOrder_id(), vendor.getDate(), vendor.getVendor_name(), vendor.getOrder_status());
                users.add(user);
            }
        }
        acceessAdapter1 = new a_collect_order_aa1(getActivity(), users, all_retailers, all_customers, all_vendors, "vendor", fragmentManager);
        recyclerView1.setAdapter(acceessAdapter1);
    }

    private void setCustomers() {
        List<a_collect_order_user1> users = new ArrayList<>();
        for (c_order_user vendor : all_customers) {
            Calendar calendar = Calendar.getInstance();
            Log.e("date", vendor.getDate());
            calendar.setTimeInMillis(vendor.getOrder_id());
            if (calendar.compareTo(fromDate1) >= 0 && calendar.compareTo(toDate1) <= 0) {
                user = new a_collect_order_user1(vendor.getOrder_id(), vendor.getDate(), vendor.getCustomer_name(), vendor.getOrder_status());
                users.add(user);
            }
        }
        acceessAdapter1 = new a_collect_order_aa1(getActivity(), users, all_retailers, all_customers, all_vendors, "customer", fragmentManager);
        recyclerView1.setAdapter(acceessAdapter1);
    }
}
