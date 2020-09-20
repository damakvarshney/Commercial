package com.commercial.after_login.customer;

import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.commercial.R;
import com.commercial.after_login.admin.a_maintain_stock_user;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.commercial.AppController.admin_database;
import static com.commercial.AppController.admin_database_stock;
import static com.commercial.AppController.customer_user;
import static com.commercial.AppController.formatter;
import static com.commercial.AppController.gson;
import static com.commercial.AppController.reference;

public class c_order_pay extends Fragment {

    TextView tv_date,tv_order_id,tv_g_total,tv_mode;
    RadioButton cod,paytm,g_pay,upi;
    Button proceed,send_order;
    Calendar calendar;
    List<c_order_item_user> orders;
    int total_of_amt =0;
    int t_quantity=0;
    long order_id;
    String pmode;
    c_order_user user;
    String order_status = "Not Delivered";
    String pay_status = "Not Paid";

    List<a_maintain_stock_user> allStock;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.c_order_pay, container, false);

        tv_date = view.findViewById(R.id.cop_date);

        Bundle bundle=getArguments();

        if(bundle==null)
            return null;

        Type type = new TypeToken<List<c_order_item_user>>() {
        }.getType();

        String json=bundle.getString("orderlist");
        orders=gson.fromJson(json,type);

        total_of_amt=bundle.getInt("total_amount");
        order_id = bundle.getLong("order_id");
        t_quantity = bundle.getInt("total_quantity");
        order_status = bundle.getString("orderstatus");
        pay_status = bundle.getString("paystatus");


        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());
        calendar = Calendar.getInstance();
        tv_date.setText(currentDateandTime);


        tv_order_id = view.findViewById(R.id.cop_o_id);
        tv_g_total = view.findViewById(R.id.cop_g_total);
        tv_mode = view.findViewById(R.id.cop_mode);
        cod = view.findViewById(R.id.cop_cod);
        paytm = view.findViewById(R.id.cop_paytm);
        g_pay = view.findViewById(R.id.cop_g_pay);
        upi = view.findViewById(R.id.cop_upi);
        proceed = view.findViewById(R.id.cop_proceed);
        send_order = view.findViewById(R.id.cop_submit);
        RadioGroup rg=view.findViewById(R.id.rbc_group);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.cop_cod){
                    pmode="Cash On Delivery";
                    tv_mode.setText("Cash On Delivery");
                }else if(checkedId==R.id.cop_paytm){
                    pmode="Paytm";
                    tv_mode.setText("Paytm");
                }else  if (checkedId==R.id.cop_g_pay){
                    pmode="GPay";
                    tv_mode.setText("GPay");
                }else {
                    pmode="BHIM UPI";
                    tv_mode.setText("BHIM UPI");
                }
            }
        });

        tv_order_id.setText(String.valueOf(order_id));
        tv_g_total.setText(String.valueOf(total_of_amt));

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Proceeding for Payment", Toast.LENGTH_SHORT).show();
                pay_status = "Paid";

            }
        });

        admin_database_stock.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e("data", dataSnapshot.toString());
                allStock = new ArrayList<>();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    a_maintain_stock_user user = child.getValue(a_maintain_stock_user.class);
                    allStock.add(user);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("data", databaseError.toString());
            }
        });

        send_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pay_status.equals("Paid")){
                    c_order_user order=new c_order_user(
                            orders,
                            total_of_amt,
                            t_quantity,order_status,pay_status,
                            formatter.format(new Date()),
                            order_id,
                            customer_user.getCustomer_name(),
                            pmode,
                            new Date().getTime(),
                            ""
                    );
                    reference.child("Customer").child("Order").child(String.valueOf(order_id)).setValue(order)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(getActivity(), "Order Submitted", Toast.LENGTH_SHORT).show();
                                        updateStock(orders);
                                    }else {
                                        Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }else{
                    Toast.makeText(getContext(), "Not Paid Yet", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private void updateStock(List<c_order_item_user> orders) {

        for (c_order_item_user order : orders) {
            for (a_maintain_stock_user a_maintain_stock_user : allStock) {
                if(a_maintain_stock_user.getItem().equals(order.item)){
                    a_maintain_stock_user.setQuantity(a_maintain_stock_user.getQuantity() - order.quantity);
                    admin_database.child("Stock").child(order.item).setValue(a_maintain_stock_user);
                }
            }
        }
        Toast.makeText(getActivity(), "Stock Updated", Toast.LENGTH_SHORT).show();
    }
}
