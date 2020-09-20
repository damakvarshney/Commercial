package com.commercial.after_login.retailer;

import android.content.Context;
import android.content.DialogInterface;
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
import com.commercial.after_login.customer.c_offer_now_user;
import com.commercial.before_login.preference;

import java.util.List;

public class r_offer_now_aa extends RecyclerView.Adapter<r_offer_now_aa.myholder> {

    Context context;
    List<r_offer_now_user> users;
    List<a_update_offer_user> all_retailers;
    String type;

    public r_offer_now_aa(Context context, List<r_offer_now_user> users, List<a_update_offer_user> all_retailers, String type) {
        this.context = context;
        this.users = users;
        this.all_retailers = all_retailers;
        this.type = type;
    }

    @NonNull
    @Override
    public myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.offer_avia_r_adap,null);
        return new myholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myholder holder, final int position) {

        r_offer_now_user user = users.get(position);

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


                if (preference.type.equals("Retailer")) {
                    for (final a_update_offer_user all_retailer : all_retailers) {
                        c_offer_name.setText(all_retailer.getOffer_name());
//                        c_offer_image.setImageURI(all_retailer.getOffer_image());
                        c_offer_id.setText(all_retailer.getOffer_id());
                        c_from_date.setText(all_retailer.getFrom_date());
                        c_till_date.setText(all_retailer.getTill_date());
                        c_offer.setText(all_retailer.getOffer());
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
            offer_name = itemView.findViewById(R.id.oar1);
            from_date = itemView.findViewById(R.id.oar2);
            till_date = itemView.findViewById(R.id.oar3);


        }
    }
}
