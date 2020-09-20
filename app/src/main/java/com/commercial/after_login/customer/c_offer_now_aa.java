package com.commercial.after_login.customer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.commercial.R;
import com.commercial.after_login.admin.a_update_offer_user;
import com.commercial.before_login.preference;

import java.util.List;

public class c_offer_now_aa extends RecyclerView.Adapter<c_offer_now_aa.myholder> {

    Context context;
    List<c_offer_now_user> users;
    List<a_update_offer_user> all_customers;
    String type;

    public c_offer_now_aa(Context context, List<c_offer_now_user> users, List<a_update_offer_user> all_customers, String type) {
        this.context = context;
        this.users = users;
        this.all_customers = all_customers;
        this.type = type;
    }

    @NonNull
    @Override
    public myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.offer_avia_c_adap, null);
        return new myholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myholder holder, final int position) {

        c_offer_now_user user = users.get(position);

        holder.offer_name.setText(user.offer_name);
        holder.from_date.setText(user.from_date);
        holder.till_date.setText(user.till_date);

        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater in = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = in.inflate(R.layout.c_offer_i_now, null);

                TextView c_offer_name, c_offer_id, c_offer, c_from_date, c_till_date;
                ImageView c_offer_image;

                c_offer_name = view.findViewById(R.id.c_offer_name);
                c_offer_id = view.findViewById(R.id.c_offer_id);
                c_offer = view.findViewById(R.id.c_offer);
                c_from_date = view.findViewById(R.id.c_from_date);
                c_till_date = view.findViewById(R.id.c_till_date);
                c_offer_image = view.findViewById(R.id.c_offer_image);


                if (preference.type.equals("Customer")) {
                    for (final a_update_offer_user all_customer : all_customers) {
                        c_offer_name.setText(all_customer.getOffer_name());
//                        c_offer_image.setImageURI(all_customer.getOffer_image());
                        c_offer_id.setText(all_customer.getOffer_id());
                        c_from_date.setText(all_customer.getFrom_date());
                        c_till_date.setText(all_customer.getTill_date());
                        c_offer.setText(all_customer.getOffer());
                    }
                }

                builder.setView(view);
                builder.create().show();
            }
        });

    }


    @Override
    public int getItemCount() {

        return users.size();
    }

    public class myholder extends RecyclerView.ViewHolder {

        TextView offer_name, from_date, till_date;
        View root;

        public myholder(@NonNull View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.root);
            offer_name = itemView.findViewById(R.id.oac1);
            from_date = itemView.findViewById(R.id.oac2);
            till_date = itemView.findViewById(R.id.oac3);

        }
    }
}
