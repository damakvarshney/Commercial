package com.commercial.after_login.admin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.commercial.AppController;
import com.commercial.R;
import com.commercial.before_login.register_retailer_user;
import com.commercial.before_login.register_vendor_user;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.List;

import static com.commercial.AppController.reference;

public class a_give_access_aa extends RecyclerView.Adapter<a_give_access_aa.myholder> {
    Context context;
    List<a_give_access_User> users;
    List<register_retailer_user> all_retailers;
    List<register_vendor_user> all_vendors;
    String type;

    public a_give_access_aa(Context context, List<a_give_access_User> users, List<register_vendor_user> all_vendors, List<register_retailer_user> all_retailers, String type) {
        this.context = context;
        this.users = users;
        this.all_retailers = all_retailers;
        this.all_vendors = all_vendors;
        this.type = type;
    }

    @NonNull
    @Override
    public myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.a_give_access_adap, null);
        return new myholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull a_give_access_aa.myholder holder, final int position) {
        a_give_access_User user = users.get(position);

        holder.id.setText(user.consumer_id + "");
        holder.name.setText(user.consumer_name);
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

    private void showDialog(final int position) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        View view = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.a_give_access_inside, null);

        //Button btn_accept=view.findViewById(R.id.);
        a_give_access_User user = users.get(position);

        TextView unique_id, name, cart_shop_name, address, mobile_no, gst_no, email_id, date;
        final EditText percentage;
        Button add, reject;
        date = view.findViewById(R.id.gai_date);
        unique_id = view.findViewById(R.id.gai_id);
        name = view.findViewById(R.id.gai_name);
        cart_shop_name = view.findViewById(R.id.gai_cart_shop);
        address = view.findViewById(R.id.gai_address);
        mobile_no = view.findViewById(R.id.gai_mobile_no);
        gst_no = view.findViewById(R.id.gai_gst_no);
        email_id = view.findViewById(R.id.gai_email_id);
        percentage = view.findViewById(R.id.gai_per);
        add = view.findViewById(R.id.gai_add);
        reject = view.findViewById(R.id.gai_reject);

        if (type.equals("vendor")) {
            for (final register_vendor_user all_vendor : all_vendors) {
                if (all_vendor.getVendor_id().equals(user.consumer_id)) {
                    unique_id.setText(all_vendor.getVendor_id());
                    name.setText(all_vendor.getVendor_name());
                    cart_shop_name.setText(all_vendor.getCart_no());
                    address.setText(all_vendor.getVendor_address());
                    mobile_no.setText(String.valueOf(all_vendor.getVendor_mobile_no()));
                    email_id.setText(all_vendor.getVendor_email_id());
                    gst_no.setText(all_vendor.getVendor_gst_no());
                    date.setText(all_vendor.getDate());


                    add.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            all_vendor.setDiscount(Integer.parseInt(String.valueOf(percentage.getText())));
                            all_vendor.setStatus(true);
                            reference.child("Vendor").child("Registered_user").child(AppController.formatEmail(all_vendor.getVendor_email_id())).setValue(all_vendor).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();

                                }
                            });

                        }
                    });

                    reject.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            all_vendor.setStatus(false);
                            reference.child("Vendor").child("Registered_user").child(AppController.formatEmail(all_vendor.getVendor_email_id())).setValue(all_vendor).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
            }
        } else {
            for (final register_retailer_user all_vendor : all_retailers) {
                if (all_vendor.getR_consumer_id().equals(user.consumer_id)) {
                    unique_id.setText(all_vendor.getR_consumer_id());
                    name.setText(all_vendor.getR_owner_name());
                    cart_shop_name.setText(all_vendor.getR_shop_name());
                    mobile_no.setText(String.valueOf(all_vendor.getR_mobile_no()));
                    gst_no.setText(all_vendor.getR_gst_no());
                    address.setText(all_vendor.getR_address());
                    email_id.setText(all_vendor.getR_email_id());
                    date.setText(all_vendor.getDate());


                    add.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            all_vendor.setStatus(true);
                            all_vendor.setDiscount(Integer.parseInt(String.valueOf(percentage.getText())));
                            reference.child("Retailer").child("Registered_user").child(AppController.formatEmail(all_vendor.getR_email_id())).setValue(all_vendor).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });

                    reject.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            all_vendor.setStatus(false);
                            reference.child("Vendor").child("Registered_user").child(AppController.formatEmail(all_vendor.getR_email_id())).setValue(all_vendor).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    });
                }
            }
        }

        builder.setView(view);

        builder.create().show();
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class myholder extends RecyclerView.ViewHolder {
        TextView id, name;
        View root;

        public myholder(@NonNull View itemView) {
            super(itemView);

            root = itemView.findViewById(R.id.root);

            id = itemView.findViewById(R.id.gaa1);
            name = itemView.findViewById(R.id.gaa2);
        }
    }
}
