package com.commercial.after_login.vendor;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.commercial.R;



import java.util.List;

public class v_order_status_aa extends RecyclerView.Adapter<v_order_status_aa.myholder> {

    Context context;
    List<v_order_status_user> users;
    List<v_order_user> all_vendors;

    public v_order_status_aa(Context context, List<v_order_status_user> users, List<v_order_user> all_vendors) {
        this.context = context;
        this.users = users;
        this.all_vendors = all_vendors;
    }


    @NonNull
    @Override
    public myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.order_status_v_adap,null);
        return new myholder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull myholder holder, final int position) {

        v_order_status_user user = users.get(position);

        holder.v_order_id.setText(String.valueOf(user.order_id));
        holder.v_date.setText(user.date);
        holder.v_quantity.setText(user.quantity+"");
        holder.v_amount.setText(user.amount+"");

        holder.root.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
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
       /*LayoutInflater in=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       View v2=in.inflate(R.layout.a_login_admin,null);
       builder.setView(v2);*/
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

        TextView v_order_id,v_date,v_quantity,v_amount;
        View root;

        public myholder(@NonNull View itemView) {
            super(itemView);
            root=itemView.findViewById(R.id.root);
            v_order_id = itemView.findViewById(R.id.osv1);
            v_date = itemView.findViewById(R.id.osv2);
            v_quantity = itemView.findViewById(R.id.osv3);
            v_amount = itemView.findViewById(R.id.osv4);

        }
    }
}
