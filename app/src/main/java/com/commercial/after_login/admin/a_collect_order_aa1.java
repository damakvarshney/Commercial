package com.commercial.after_login.admin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.commercial.R;
import com.commercial.after_login.customer.c_order_user;
import com.commercial.after_login.retailer.r_order_user;
import com.commercial.after_login.vendor.v_order_user;

import java.util.List;
import java.util.Objects;

import static com.commercial.AppController.gson;

public class a_collect_order_aa1 extends RecyclerView.Adapter<a_collect_order_aa1.myholder> implements View.OnCreateContextMenuListener {

    public static String order_of;
    Context context;
    List<a_collect_order_user1> users;
    List<r_order_user> all_retailers;
    List<c_order_user> all_customers;
    List<v_order_user> all_vendors;
    String type;
    FragmentManager fragmentManager;

    public a_collect_order_aa1(Context context, List<a_collect_order_user1> user, List<r_order_user> all_retailers, List<c_order_user> all_customers, List<v_order_user> all_vendors,
                               String type, FragmentManager fragmentManager) {
        this.context = context;
        this.users = user;
        this.all_retailers = all_retailers;
        this.all_customers = all_customers;
        this.all_vendors = all_vendors;
        this.type = type;
        this.fragmentManager=fragmentManager;
    }

    @NonNull
    @Override
    public myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.a_collect_order_adap1, null);
        return new myholder(v);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    @Override
    public void onBindViewHolder(@NonNull myholder holder, final int position) {

        a_collect_order_user1 user = users.get(position);

        holder.date.setText(user.date);
        holder.name.setText(user.name);
        holder.status.setText(user.status);
        holder.view_button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                String order="";
                switch (type) {
                    case "retailer":
                        order_of = "Retailer";
                        order=getRetailOrder(position);
                        break;
                    case "customer":
                        order_of = "Customer";
                        order=getCustomerOrder(position);
                        break;
                    case "vendor":
                        order_of = "Vendor";
                        order=getVendorOrder(position);
                        break;
                }
                Toast.makeText(context, "next page", Toast.LENGTH_SHORT).show();
                Bundle bundle=new Bundle();
                bundle.putString("orderType",order_of);
                bundle.putString("order",order);
                Fragment fragment=new a_collect_order_inside();
                fragment.setArguments(bundle);
                FragmentTransaction fragmentTransaction =  fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.lt_frag, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        holder.root.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                delete_item(position);
                return false;
            }
        });

    }

    private String getRetailOrder(int position) {
        String json="";
        a_collect_order_user1 user = users.get(position);
        for (r_order_user all_retailer : all_retailers) {
            if(all_retailer.getOrder_id()==user.order_id){
                json=gson.toJson(all_retailer);
            }
        }
        return json;
    }
    private String getCustomerOrder(int position) {
        String json="";
        a_collect_order_user1 user = users.get(position);
        for (c_order_user all_customer : all_customers) {
            if(all_customer.getOrder_id()==user.order_id){
                json=gson.toJson(all_customer);
            }
        }
        return json;
    }
    private String getVendorOrder(int position) {
        String json="";
        a_collect_order_user1 user = users.get(position);
        for (v_order_user all_vendor : all_vendors) {
            if(all_vendor.getOrder_id()==user.order_id){
                json=gson.toJson(all_vendor);
            }
        }
        return json;
    }

    private void delete_item(final int position) {
        androidx.appcompat.app.AlertDialog.Builder builder=new androidx.appcompat.app.AlertDialog.Builder(context);
        builder.setNegativeButton("No",null);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                users.remove(position);
                notifyDataSetChanged();
            }
        });
        builder.setNeutralButton("Cancel",null);
        builder.setTitle("Confirm Delete?");
        builder.setMessage("Do you really want to delete this");
        builder.create().show();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

    }

    public class myholder extends RecyclerView.ViewHolder {
        TextView date, name, status;
        Button view_button;
        View root;

        public myholder(@NonNull View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.root);
            date = itemView.findViewById(R.id.coa11);
            name = itemView.findViewById(R.id.coa12);
            status = itemView.findViewById(R.id.coa13);
            view_button = itemView.findViewById(R.id.coa14);
        }
    }
}





