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
import com.commercial.after_login.customer.c_order_user;
import com.commercial.after_login.retailer.r_order_user;
import com.commercial.after_login.vendor.v_order_user;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static com.commercial.AppController.customer_database;
import static com.commercial.AppController.retailer_database;
import static com.commercial.AppController.vendor_database;

public class a_update_pay extends Fragment {

    TextView fd_textview, td_textview, grandtotal;
    Calendar calendar;
    Button fd_btn, td_btn;
    Spinner spinner1, spinner2;
    a_update_pay_user user;

    a_update_pay_aa acceessAdapter;
    RecyclerView recyclerView;
    Calendar fromDate, toDate;

    List<r_order_user> all_retailers = new ArrayList<>();
    List<c_order_user> all_customers = new ArrayList<>();
    List<v_order_user> all_vendors = new ArrayList<>();
    private int total_amt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for getActivity() fragment
        View view = inflater.inflate(R.layout.a_update_pay, container, false);

        fd_textview = view.findViewById(R.id.up_tv_fd);
        td_textview = view.findViewById(R.id.up_tv_td);
        fd_btn = view.findViewById(R.id.up_btn_fd);
        td_btn = view.findViewById(R.id.up_btn_td);
        calendar = Calendar.getInstance();
        spinner1 = view.findViewById(R.id.up_spinner_ft);
        spinner2 = view.findViewById(R.id.up_spinner_name);
        calendar = Calendar.getInstance();
        grandtotal = view.findViewById(R.id.payr_grand);

        recyclerView = view.findViewById(R.id.recycler_upr);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.setAdapter(acceessAdapter);
//      both date button

        fd_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        fromDate = Calendar.getInstance();
                        fromDate.set(year, month + 1, dayOfMonth, 0, 0, 0);
                        fd_textview.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                        spinner2.setAdapter(null);
                        spinner1.setSelection(0);

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
                        spinner2.setAdapter(null);
                        spinner1.setSelection(0);
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

        // spinner2 == names from spinner1

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int pos = spinner1.getSelectedItemPosition();

                if (pos == 1) {
                    List<r_order_user> filteredRetailers = getFilteredRetailers();
                    showRetailerList(filteredRetailers, position);
                } else if (pos == 2) {
                    List<c_order_user> filteredCustomers = getFilteredCustomers();
                    showCustomerList(filteredCustomers, position);
                } else if (pos == 3) {
                    List<v_order_user> filteredVendors = getFilteredVendors();
                    showVendorList(filteredVendors, position);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, states);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (fromDate == null || toDate == null) {
                    Toast.makeText(getActivity(), "Select Date First", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (position == 0) {
                    Toast.makeText(getActivity(), "None Selected", Toast.LENGTH_SHORT).show();
                    spinner2.setAdapter(null);
                } else if (position == 1) {
                    Toast.makeText(getActivity(), "Retailer", Toast.LENGTH_SHORT).show();
                    List<r_order_user> filteredRetailers = getFilteredRetailers();
                    List<String> names = new ArrayList<>();
                    for (r_order_user filteredRetailer : filteredRetailers) {
                        names.add(filteredRetailer.getRetailer_name());
                    }
                    spinner2.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line, names));
                } else if (position == 2) {
                    Toast.makeText(getActivity(), "Customer", Toast.LENGTH_SHORT).show();
                    List<c_order_user> filteredCustomers = getFilteredCustomers();
                    List<String> names = new ArrayList<>();
                    for (c_order_user filteredCustomer : filteredCustomers) {
                        names.add(filteredCustomer.getCustomer_name());
                    }
                    spinner2.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line, names));

                } else if (position == 3) {
                    Toast.makeText(getActivity(), "Vendor", Toast.LENGTH_SHORT).show();
                    List<v_order_user> filteredVendors = getFilteredVendors();
                    List<String> names = new ArrayList<>();
                    for (v_order_user filteredVendor : filteredVendors) {
                        names.add(filteredVendor.getVendor_name());
                    }
                    spinner2.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line, names));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getActivity(), "Select the Feed", Toast.LENGTH_SHORT).show();
            }
        });

        // retrieving from the retailer data
        retailer_database.child("Order").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e("retailer", dataSnapshot.toString());
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    r_order_user retailer_user = child.getValue(r_order_user.class);
                    all_retailers.add(retailer_user);
                }
                int position = spinner1.getSelectedItemPosition();
                /*if (position == 1) {
                    Toast.makeText(getActivity(), "Retailer", Toast.LENGTH_SHORT).show();
                    acceessAdapter = new a_update_pay_aa(getActivity(), retailers, all_retailers, all_vendors, all_customers, "retailer");
                    recyclerView.setAdapter(acceessAdapter);
                }*/
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        // retrieving from the vendor data
        vendor_database.child("Order").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e("vendor", dataSnapshot.toString());
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    v_order_user retailer_user = child.getValue(v_order_user.class);
                    all_vendors.add(retailer_user);
                }
                int position = spinner1.getSelectedItemPosition();
                /*if (position == 1) {
                    Toast.makeText(getActivity(), "Retailer", Toast.LENGTH_SHORT).show();
                    acceessAdapter = new a_update_pay_aa(getActivity(), retailers, all_retailers, all_vendors, all_customers, "retailer");
                    recyclerView.setAdapter(acceessAdapter);
                }*/
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        // retrieving from the customer data
        customer_database.child("Order").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e("customer", dataSnapshot.toString());
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    c_order_user retailer_user = child.getValue(c_order_user.class);
                    all_customers.add(retailer_user);
                }
                int position = spinner1.getSelectedItemPosition();
                /*if (position == 1) {
                    Toast.makeText(getActivity(), "Retailer", Toast.LENGTH_SHORT).show();
                    acceessAdapter = new a_update_pay_aa(getActivity(), retailers, all_retailers, all_vendors, all_customers, "retailer");
                    recyclerView.setAdapter(acceessAdapter);
                }*/
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }

    private void showVendorList(List<v_order_user> filteredVendors, int position) {
        String name = filteredVendors.get(position).getVendor_name();
        List<a_update_pay_user> temp_retailers = new ArrayList<>();

        for (v_order_user retailer : filteredVendors) {
            if (retailer.getVendor_name().equals(name)) {
                user = new a_update_pay_user(retailer.getOrder_id(), retailer.getDate(), retailer.getPay_status(), retailer.getTotal_of_amt());
                temp_retailers.add(user);
                total_amt += user.getAmount();
                grandtotal.setText(total_amt + "");
            }
        }

        acceessAdapter = new a_update_pay_aa(getActivity(), temp_retailers, all_retailers, all_vendors, all_customers, "vendor");
        recyclerView.setAdapter(acceessAdapter);
    }

    private List<v_order_user> getFilteredVendors() {
        List<v_order_user> users = new ArrayList<>();
        for (v_order_user retailer : all_vendors) {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            try {
                calendar.setTime(dateFormat.parse(retailer.getDate()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (calendar.compareTo(fromDate) >= 0 && calendar.compareTo(toDate) <= 0) {
                //user = new a_update_pay_user(retailer.getOrder_id(), retailer.getDate(), "", retailer.getTotal_of_amt());
                users.add(retailer);
            }
        }
        return users;
    }

    private void showCustomerList(List<c_order_user> filteredCustomers, int position) {
        String name = filteredCustomers.get(position).getCustomer_name();
        List<a_update_pay_user> temp_retailers = new ArrayList<>();

        for (c_order_user retailer : filteredCustomers) {
            if (retailer.getCustomer_name().equals(name)) {
                user = new a_update_pay_user(retailer.getOrder_id(), retailer.getDate(), retailer.getPay_status(), retailer.getTotal_of_amt());
                temp_retailers.add(user);
                total_amt += user.getAmount();
                grandtotal.setText(total_amt + "");
            }
        }

        acceessAdapter = new a_update_pay_aa(getActivity(), temp_retailers, all_retailers, all_vendors, all_customers, "customer");
        recyclerView.setAdapter(acceessAdapter);
    }

    private List<c_order_user> getFilteredCustomers() {
        List<c_order_user> users = new ArrayList<>();
        for (c_order_user retailer : all_customers) {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            try {
                calendar.setTime(dateFormat.parse(retailer.getDate()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (calendar.compareTo(fromDate) >= 0 && calendar.compareTo(toDate) <= 0) {
                //user = new a_update_pay_user(retailer.getOrder_id(), retailer.getDate(), "", retailer.getTotal_of_amt());
                users.add(retailer);
            }
        }
        return users;
    }

    private void showRetailerList(List<r_order_user> filteredRetailers, int position) {

        String name = filteredRetailers.get(position).getRetailer_name();
        List<a_update_pay_user> temp_retailers = new ArrayList<>();

        for (r_order_user retailer : filteredRetailers) {
            if (retailer.getRetailer_name().equals(name)) {
                user = new a_update_pay_user(retailer.getOrder_id(), retailer.getDate(), retailer.getPay_status(), retailer.getTotal_of_amt());
                temp_retailers.add(user);
                total_amt += user.getAmount();
                grandtotal.setText(total_amt + "");
            }
        }

        acceessAdapter = new a_update_pay_aa(getActivity(), temp_retailers, all_retailers, all_vendors, all_customers, "retailer");
        recyclerView.setAdapter(acceessAdapter);
    }


    private List<r_order_user> getFilteredRetailers() {
        List<r_order_user> users = new ArrayList<>();
        for (r_order_user retailer : all_retailers) {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            try {
                calendar.setTime(dateFormat.parse(retailer.getDate()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (calendar.compareTo(fromDate) >= 0 && calendar.compareTo(toDate) <= 0) {
                //user = new a_update_pay_user(retailer.getOrder_id(), retailer.getDate(), "", retailer.getTotal_of_amt());
                users.add(retailer);
            }
        }
        return users;
    }


}
