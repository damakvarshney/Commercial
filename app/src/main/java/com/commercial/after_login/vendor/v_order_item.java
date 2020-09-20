package com.commercial.after_login.vendor;

import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.commercial.R;
import com.commercial.after_login.retailer.r_order_pay;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.commercial.AppController.admin_database_stock;
import static com.commercial.AppController.gson;
import static com.commercial.AppController.retailer_user;
import static com.commercial.AppController.vendor_user;


public class v_order_item extends Fragment {

    TextView order_id, total_quantity, total_amount, rate, amount, date, dis_per, dis_amt,grand_total;
    AutoCompleteTextView item;
    EditText quantity;
    RecyclerView recyclerView;
    Button add, payment;
    List<v_order_item_user> users = new ArrayList<>();
    List<v_order_item_user> orders = new ArrayList<>();
    Calendar calendar;
    int total_of_amt =0;
    int dis_percent = 0;
    int grandtotal;
    int discount_amount;
    int t_quantity=0;
    String order_status = "Not Delivered";
    String pay_status = "Not Paid";


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.order_item_v, container, false);

        /// fetching data from firebase
        admin_database_stock.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e("data", dataSnapshot.toString());
                users = new ArrayList<>();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    v_order_item_user user = child.getValue(v_order_item_user.class);
                    users.add(user);
                }
                List<String> autoItem = new ArrayList<>();
                for (v_order_item_user user : users) {
                    autoItem.add(user.getItem());
                }
                ArrayAdapter adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, autoItem);
                item.setAdapter(adapter);
                item.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        rate.setText(users.get(position).rate);
                        Toast.makeText(getActivity(), "selected" + position, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("data", databaseError.toString());
            }
        });

        date = view.findViewById(R.id.oiv_c_date);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());
        calendar = Calendar.getInstance();

        date.setText(currentDateandTime);

        dis_per = view.findViewById(R.id.oiv_dis_per);
        dis_amt = view.findViewById(R.id.oiv_dis_amt);
        order_id = view.findViewById(R.id.oiv_o_id);

        Long uniqueId = System.currentTimeMillis();
        String id = "" + uniqueId;
        order_id.setText(id);

        grand_total = view.findViewById(R.id.oiv_g_total);
        item = view.findViewById(R.id.oiv_item);
        quantity = view.findViewById(R.id.oiv_quantity);
        rate = view.findViewById(R.id.oiv_rate);
        amount = view.findViewById(R.id.oiv_amount);
        add = view.findViewById(R.id.oiv_add_btn);
        total_quantity = view.findViewById(R.id.oiv_t_qt);
        total_amount = view.findViewById(R.id.oiv_t_amt);
        payment = view.findViewById(R.id.oiv_pay);
        recyclerView = view.findViewById(R.id.recycler_oiv);
        calendar = Calendar.getInstance();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toadapter();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Bundle bundle=new Bundle();
                bundle.putString("orderlist",gson.toJson(orders));
                bundle.putInt("total_amount",total_of_amt);
                bundle.putInt("grand_total",grandtotal);
                bundle.putLong("order_id", Long.parseLong(order_id.getText().toString()));
                bundle.putInt("dis_percent",vendor_user.getDiscount());
                bundle.putInt("discount_amount",discount_amount);
                bundle.putInt("total_quantity",t_quantity);
                bundle.putString("orderstatus",order_status);
                bundle.putString("paystatus",pay_status);

                v_order_pay frag=new v_order_pay();
                frag.setArguments(bundle);

                fragmentTransaction.replace(R.id.vendor_order_layout,frag);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


        quantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    int a = Integer.parseInt(rate.getText().toString());
                    int b = Integer.parseInt(s.toString());
                    amount.setText(a * b + "");
                } catch (Exception ignored) {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        item.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                for (v_order_item_user user : users) {
                    if (user.item.equals(s.toString())) {
                        rate.setText(user.rate + "");
                    }
                }
            }
        });

        dis_percent = vendor_user.getDiscount();

        dis_per.setText(String.valueOf(dis_percent));


        return view;
    }

    private void toadapter() {
        String v_item = item.getText().toString();
        int v_quantity = Integer.parseInt(String.valueOf(quantity.getText()));
        int v_rate = Integer.parseInt(String.valueOf(rate.getText()));
        int v_amount = Integer.parseInt(String.valueOf(amount.getText()));

        v_order_item_user user = new v_order_item_user(v_item, v_quantity, v_rate, v_amount);
        orders.add(user);

        t_quantity+=v_quantity;
        total_quantity.setText(String.valueOf(t_quantity));
        total_of_amt+= v_amount;
        total_amount.setText(String.valueOf(total_of_amt));

        discount_amount = (int) (((float)dis_percent/(float) 100)*total_of_amt);
        dis_amt.setText(String.valueOf(discount_amount));
        grandtotal = total_of_amt-discount_amount;
        grand_total.setText(String.valueOf(grandtotal));

        v_order_item_aa adapter = new v_order_item_aa(getContext(), orders);
        recyclerView.setAdapter(adapter);
    }
}
