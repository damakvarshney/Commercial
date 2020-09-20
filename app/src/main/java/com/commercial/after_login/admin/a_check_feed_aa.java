package com.commercial.after_login.admin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.commercial.R;
import com.commercial.after_login.customer.c_feedback_user;
import com.commercial.after_login.retailer.r_feedback_user;
import com.commercial.after_login.vendor.v_feedback_user;
import com.commercial.before_login.bottom.contact_us.Contact_Us_user;

import java.util.List;

public class a_check_feed_aa extends RecyclerView.Adapter<a_check_feed_aa.myholder> {

    Context context;
    List<a_check_feed_user> users;
    List<r_feedback_user> all_retailers;
    List<v_feedback_user> all_vendors;
    List<c_feedback_user> all_customers;
    List<Contact_Us_user> all_miscs;
    String type;

    public a_check_feed_aa(Context context, List<a_check_feed_user> user, List<r_feedback_user> all_retailers, List<v_feedback_user> all_vendors, List<c_feedback_user> all_customers, List<Contact_Us_user> all_miscs, String type) {
        this.context = context;
        this.users = user;
        this.all_retailers = all_retailers;
        this.all_vendors = all_vendors;
        this.all_customers = all_customers;
        this.all_miscs = all_miscs;
        this.type = type;
    }


    @NonNull
    @Override
    public myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.a_check_feed_adap, null);
        return new myholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myholder holder, final int position) {

        a_check_feed_user user = users.get(position);

        Log.e("Position", position + "");

        holder.date.setText(user.date);
        holder.name.setText(user.name);
        holder.subject.setText(user.subject);
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(position);
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

    private void delete_item(final int position) {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(context);
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
    }

    private void showDialog(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        View view = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.a_check_feedback_inside, null);

        //Button btn_accept=view.findViewById(R.id.);
        a_check_feed_user user = users.get(position);

        TextView date, subject, feedback, name;
        name = view.findViewById(R.id.cfi_name);
        date = view.findViewById(R.id.cfi_date);
        subject = view.findViewById(R.id.cfi_subject);
        feedback = view.findViewById(R.id.cfi_feedback);

        if (type.equals("vendor")) {
            for (final v_feedback_user all_vendor : all_vendors) {
                date.setText(all_vendor.getDate());
                subject.setText(all_vendor.getSubject());
                feedback.setText(all_vendor.getSubject());
                name.setText(all_vendor.getName());
            }
        } else if (type.equals("retailer")) {
            for (final r_feedback_user all_vendor : all_retailers) {
                date.setText(all_vendor.getDate());
                subject.setText(all_vendor.getSubject());
                feedback.setText(all_vendor.getSubject());
                name.setText(all_vendor.getName());
            }
        } else if (type.equals("customer")) {
            for (final c_feedback_user all_vendor : all_customers) {
                date.setText(all_vendor.getDate());
                subject.setText(all_vendor.getSubject());
                feedback.setText(all_vendor.getSubject());
                name.setText(all_vendor.getName());
            }
        } else {
            for (final Contact_Us_user all_vendor : all_miscs) {
                date.setText(String.valueOf(all_vendor.getDate()));
                subject.setText(all_vendor.getSubject());
                feedback.setText(all_vendor.getSubject());
                name.setText(all_vendor.getName());
            }
        }

        builder.setView(view);
        builder.create().show();
    }

    @Override
    public int getItemCount() {

        return users.size();
    }


    public class myholder extends RecyclerView.ViewHolder {

        TextView date, name, subject;
        View root;


        public myholder(@NonNull View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.root);
            name = itemView.findViewById(R.id.cf1);
            date = itemView.findViewById(R.id.cf2);
            subject = itemView.findViewById(R.id.cf3);
        }
    }
}
