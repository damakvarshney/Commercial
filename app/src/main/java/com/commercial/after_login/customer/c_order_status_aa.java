package com.commercial.after_login.customer;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;


import com.commercial.R;
import com.commercial.after_login.admin.a_collect_order_user1;
import com.commercial.after_login.retailer.r_order_user;
import com.commercial.before_login.preference;

import java.util.List;

public class c_order_status_aa extends RecyclerView.Adapter<c_order_status_aa.myholder> {

    Context context;
    List<c_order_status_user> users;
    List<c_order_user> all_customers;

    public c_order_status_aa(Context context, List<c_order_status_user> users, List<c_order_user> all_customers) {
        this.context = context;
        this.users = users;
        this.all_customers = all_customers;
    }

    @NonNull
    @Override
    public myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.order_status_c_adap,null);
        return new myholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myholder holder, final int position) {

        c_order_status_user user = users.get(position);

        holder.c_order_id.setText(String.valueOf(user.order_id));
        holder.c_date.setText(user.date);
        holder.c_quantity.setText(user.quantity+"");
        holder.c_amount.setText(user.amount+"");

        holder.root.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                View view = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.a_collect_order_inside, null);

                //Button btn_accept=view.findViewById(R.id.);
                c_order_status_user user = users.get(position);

                TextView  name, order_id, gt_amt, payment /*gt_qty*/, date;
//                RecyclerView recyclerView;
                Button inprocess, on_deliv, check;
                date = view.findViewById(R.id.coi_date);
                name = view.findViewById(R.id.coi_name);
                order_id = view.findViewById(R.id.coi_order_id);
//                recyclerView = view.findViewById(R.id.recycler_coi);
                payment = view.findViewById(R.id.coi_pay_status);
                inprocess = view.findViewById(R.id.coi_btn_inprocess);
                on_deliv = view.findViewById(R.id.coi_btn_deli);
                check = view.findViewById(R.id.coi_btn_sign);
                gt_amt = view.findViewById(R.id.coi_et_agrand);
//                gt_qty = view.findViewById(R.id.coi_et_qgrand);
//        final LinearLayout mContent = (LinearLayout) view.findViewById(R.id.linearLayout);
//        CaptureSignatureView mSig = new CaptureSignatureView(context, null);
//        mContent.addView(mSig, ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);


                if (preference.type.equals("Customer")) {
                    for (final c_order_user all_vendor : all_customers) {
//                    recyclerView.setAdapter(all_vendor.getOrders());
                        name.setText(all_vendor.getCustomer_name());
                        order_id.setText((String.valueOf(all_vendor.getOrder_id())));
                        gt_amt.setText(all_vendor.getTotal_of_amt());
                        payment.setText(String.valueOf(all_vendor.getPayment_mode()));
//                        gt_qty.setText(all_vendor.getT_quantity());
                        date.setText(all_vendor.getDate());
                        inprocess.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(context, "Order in process", Toast.LENGTH_SHORT).show();

                            }
                        });

                        on_deliv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(context, "Order on Delivery", Toast.LENGTH_SHORT).show();
                            }
                        });
                        check.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(context, "CHECK", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }
                builder.create().show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {

        return users.size();
    }

    public class myholder extends RecyclerView.ViewHolder {

        TextView c_order_id,c_date,c_quantity,c_amount;
        View root;

        public myholder(@NonNull View itemView) {
            super(itemView);
            root=itemView.findViewById(R.id.root);
            c_order_id = itemView.findViewById(R.id.osc1);
            c_date = itemView.findViewById(R.id.osc2);
            c_quantity = itemView.findViewById(R.id.osc3);
            c_amount = itemView.findViewById(R.id.osc4);

        }
    }
}
