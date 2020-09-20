package com.commercial.after_login.retailer;

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
import com.commercial.after_login.customer.c_order_item_user;
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
import static com.commercial.AppController.formatter;
import static com.commercial.AppController.gson;
import static com.commercial.AppController.reference;
import static com.commercial.AppController.retailer_user;

public class r_order_pay extends Fragment {

    TextView date,tv_order_id,g_total,mode,amt_paid;
    RadioButton cod,paytm,g_pay,upi;
    Button proceed,send_order;
    Calendar calendar;
    List<r_order_item_user> orders;
    int total_of_amt =0;
    int t_quantity = 0;
    int dis_percent;
    int grandtotal;
    int discount_amount;
    long order_id;
    String pmode;
    r_order_user user;
    String order_status = "Not Delivered";
    String pay_status = "Not Paid";
    List<a_maintain_stock_user> allStock;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.r_order_pay, container, false);

        date = view.findViewById(R.id.rop_date);

        Bundle bundle=getArguments();

        if(bundle==null)
            return null;

        Type type = new TypeToken<List<r_order_item_user>>() {
        }.getType();

        String json=bundle.getString("orderlist");
        orders=gson.fromJson(json,type);

        total_of_amt=bundle.getInt("total_amount");
        t_quantity = bundle.getInt("total_quantity");
        dis_percent = bundle.getInt("dis_percent");
        discount_amount = bundle.getInt("discount_amount");
        grandtotal = bundle.getInt("grand_total");
        order_id = bundle.getLong("order_id");
        order_status = bundle.getString("orderstatus");
        pay_status = bundle.getString("paystatus");



        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());
        calendar = Calendar.getInstance();
        date.setText(currentDateandTime);

        tv_order_id = view.findViewById(R.id.rop_o_id);
        g_total = view.findViewById(R.id.rop_g_total);
        mode = view.findViewById(R.id.rop_mode);
        cod = view.findViewById(R.id.rop_cod);
        paytm = view.findViewById(R.id.rop_paytm);
        g_pay = view.findViewById(R.id.rop_g_pay);
        upi = view.findViewById(R.id.rop_upi);
        proceed = view.findViewById(R.id.rop_proceed);
        send_order = view.findViewById(R.id.rop_submit);
        RadioGroup rg=view.findViewById(R.id.rb_group);
        
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.rop_cod){
                    pmode="Cash On Delivery";
                    mode.setText("Cash On Delivery");
                }else if(checkedId==R.id.rop_paytm){
                    pmode="Paytm";
                    mode.setText("Paytm");
                }else  if (checkedId==R.id.rop_g_pay){
                    pmode="GPay";
                    mode.setText("GPay");
                }else {
                    pmode="BHIM UPI";
                    mode.setText("BHIM UPI");
                }
            }
        });

        tv_order_id.setText(String.valueOf(order_id));
        g_total.setText(String.valueOf(grandtotal));
        
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Proceeding for Payment", Toast.LENGTH_SHORT).show();
                pay_status ="Paid";
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
                   r_order_user order=new r_order_user(
                           orders,
                           total_of_amt,
                           t_quantity,
                           dis_percent,grandtotal,
                           order_status,pay_status,
                           formatter.format(new Date()),
                           discount_amount,
                           order_id,
                           retailer_user.getR_owner_name(),
                           pmode,
                           new Date().getTime(),
                           ""
                   );
                   reference.child("Retailer").child("Order").child(String.valueOf(order_id)).setValue(order)
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
    private void updateStock(List<r_order_item_user> orders) {

        for (r_order_item_user order : orders) {
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
