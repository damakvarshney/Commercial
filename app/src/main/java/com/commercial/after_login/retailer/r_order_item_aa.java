package com.commercial.after_login.retailer;

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

public class r_order_item_aa extends RecyclerView.Adapter<r_order_item_aa.myholder> {

    Context context;
    List<r_order_item_user> users;

    public r_order_item_aa(Context context, List<r_order_item_user> user) {
        this.context = context;
        this.users = user;
    }

    @NonNull
    @Override
    public myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.order_item_r_adap, null);
        return new myholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myholder holder, final int position) {

        r_order_item_user user = users.get(position);

        holder.r_item.setText(user.item);
        holder.r_quantity.setText(user.quantity + "");
        holder.r_rate.setText(user.rate + "");
        holder.r_amount.setText(user.amount + "");

        int per_item_amount = (user.rate * user.quantity);

        holder.r_amount.setText(per_item_amount + "");
        holder.root.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setNegativeButton("No", null);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        users.remove(position);
                        notifyDataSetChanged();
                    }
                });
                builder.setNeutralButton("Cancel", null);
                builder.setTitle("Confirm Delete?");
                builder.setMessage("Do you really want to delete this");
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

        TextView r_item, r_quantity;
        TextView r_rate, r_amount;
        View root;

        public myholder(@NonNull View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.root);

            r_item = itemView.findViewById(R.id.oira2);
            r_quantity = itemView.findViewById(R.id.oira3);
            r_rate = itemView.findViewById(R.id.oira4);
            r_amount = itemView.findViewById(R.id.oira5);

        }
    }
}
