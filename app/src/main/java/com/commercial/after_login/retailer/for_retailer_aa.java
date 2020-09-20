package com.commercial.after_login.retailer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.commercial.R;

import java.util.List;

public class for_retailer_aa extends RecyclerView.Adapter<for_retailer_aa.myholder> {
    Context context;
    List<for_retailer_user> users;
    FragmentManager fm;

    public for_retailer_aa(Context context, List<for_retailer_user> users, FragmentManager fm) {
        this.context = context;
        this.users = users;
        this.fm = fm;
    }

    @NonNull
    @Override
    public myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.for_retailer_adap,null);
        return new myholder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final myholder holder, final int position) {
        for_retailer_user user = users.get(position);
        holder.image.setImageResource(user.image);
        holder.textview.setText(user.textview);

        holder.retailer_menu_adap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if (position==0){
                   FragmentTransaction fragmentTransaction = fm.beginTransaction();
                   fragmentTransaction.replace(R.id.nav_host_fragment_bottom, new r_order_item());
                   fragmentTransaction.addToBackStack(null);
                   fragmentTransaction.commit();
               }
               else if (position==1){
                   FragmentTransaction fragmentTransaction = fm.beginTransaction();
                   fragmentTransaction.replace(R.id.nav_host_fragment_bottom, new r_order_status());
                   fragmentTransaction.addToBackStack(null);
                   fragmentTransaction.commit();

               }
               else if (position==2){
                   FragmentTransaction fragmentTransaction = fm.beginTransaction();
                   fragmentTransaction.replace(R.id.nav_host_fragment_bottom, new r_offer_now());
                   fragmentTransaction.addToBackStack(null);
                   fragmentTransaction.commit();

               }
               else if (position==3){
                   FragmentTransaction fragmentTransaction = fm.beginTransaction();
                   fragmentTransaction.replace(R.id.nav_host_fragment_bottom, new r_pay_status());
                   fragmentTransaction.addToBackStack(null);
                   fragmentTransaction.commit();

               }
               else if (position==4){
                   FragmentTransaction fragmentTransaction = fm.beginTransaction();
                   fragmentTransaction.replace(R.id.nav_host_fragment_bottom, new r_feedback());
                   fragmentTransaction.addToBackStack(null);
                   fragmentTransaction.commit();

               }
            }
        });
    }
    @Override
    public int getItemCount() {
        return users.size();
    }

    public class myholder extends RecyclerView.ViewHolder{
        TextView textview;
        ImageView image;
        View retailer_menu_adap;

        public myholder(@NonNull View itemView) {
            super(itemView);

            retailer_menu_adap=itemView.findViewById(R.id.retailer_menu_adap);

            image = itemView.findViewById(R.id.fcaa1);
            textview = itemView.findViewById(R.id.fcaa2);
        }
    }
}
