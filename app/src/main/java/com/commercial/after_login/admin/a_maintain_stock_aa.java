package com.commercial.after_login.admin;

import android.content.Context;
import android.content.DialogInterface;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.commercial.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.List;

import static com.commercial.AppController.admin_database;
import static com.commercial.AppController.mStorageRef;

public class a_maintain_stock_aa extends RecyclerView.Adapter<a_maintain_stock_aa.myholder> implements View.OnCreateContextMenuListener {

    Context context;
    List<a_maintain_stock_user> users;

    public a_maintain_stock_aa(Context context, List<a_maintain_stock_user> user) {
        this.context = context;
        this.users = user;
    }

    @NonNull
    @Override
    public myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.a_maintain_stock_adap, null);
        return new myholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final myholder holder, final int position) {

        a_maintain_stock_user user = users.get(position);

        holder.item_id.setText(user.item_id);
        holder.item.setText(user.item);
        holder.quantity.setText(user.quantity + "");
        holder.rate.setText(user.rate + "");
        holder.amount.setText(user.amount + "");
        holder.root.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                delete_item(position);
                return false;
            }
        });

    }

    private void delete_item(final int position) {
        androidx.appcompat.app.AlertDialog.Builder builder=new androidx.appcompat.app.AlertDialog.Builder(context);
        builder.setNegativeButton("No",null);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                admin_database.child("Stock").child(String.valueOf(users.get(position).item)).removeValue();
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
    public int getItemCount() {

        return users.size();
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(Menu.NONE, R.id.delete,
                Menu.NONE, R.string.delete_one);
    }


    public class myholder extends RecyclerView.ViewHolder {

        TextView item_id, item, quantity, rate, amount;
        View root;

        public myholder(@NonNull View itemView) {
            super(itemView);

            root = itemView.findViewById(R.id.root);
            item_id = itemView.findViewById(R.id.msa1);
            item = itemView.findViewById(R.id.msa2);
            quantity = itemView.findViewById(R.id.msa3);
            rate = itemView.findViewById(R.id.msa4);
            amount = itemView.findViewById(R.id.msa5);
        }
    }
}
