package com.commercial.after_login.admin;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.commercial.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.List;

import static com.commercial.AppController.admin_database;
import static com.commercial.AppController.mStorageRef;

public class a_update_offer_aa extends RecyclerView.Adapter<a_update_offer_aa.myholder>{
    Context context;
    List<a_update_offer_user> users;

    public a_update_offer_aa(Context context, List<a_update_offer_user> users) {
        this.context = context;
        this.users = users;
    }
    @NonNull
    @Override
    public myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.a_update_offer_adap,null);
        return new myholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull a_update_offer_aa.myholder holder, final int position) {
        a_update_offer_user user = users.get(position);

        holder.offer_name.setText(user.offer_name);
        Glide.with(context)
                .load(user.offer_image)
                .into(holder.offer_image);
        holder.offer.setText(user.offer);
        holder.offer_id.setText(user.offer_id);
        if(user.isFor_customer())
            holder.for_customer.setVisibility(View.VISIBLE);
        if(user.isFor_retailer())
            holder.for_retailer.setVisibility(View.VISIBLE);
        if(user.isFor_vendor())
            holder.for_vendor.setVisibility(View.VISIBLE);
        holder.from_date.setText(user.from_date);
        holder.till_date.setText(user.till_date);
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
                mStorageRef.child("Admin").child("Offer Images").child(String.valueOf(users.get(position))).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });
                admin_database.child("New offer").child(String.valueOf(users.get(position).offer_name)).removeValue();
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


    public static class myholder extends RecyclerView.ViewHolder{
        TextView offer_name,offer,for_retailer,for_customer,for_vendor,offer_id,from_date,till_date;
        ImageView offer_image;
        View root;

        public myholder(@NonNull View itemView) {
            super(itemView);

            root =itemView.findViewById(R.id.root);
            offer_name = itemView.findViewById(R.id.offer_name);
            offer = itemView.findViewById(R.id.offer);
            from_date = itemView.findViewById(R.id.from_date);
            till_date = itemView.findViewById(R.id.till_date);
            offer_image = itemView.findViewById(R.id.offer_image);
            offer_id = itemView.findViewById(R.id.offer_id);
            for_customer = itemView.findViewById(R.id.for_customer);
            for_vendor = itemView.findViewById(R.id.for_vendor);
            for_retailer = itemView.findViewById(R.id.for_retailer);
        }
    }
}
