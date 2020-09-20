package com.commercial.before_login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.commercial.AppController;
import com.commercial.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.commercial.AppController.reference;

public class register_customer extends Fragment {

    TextView customer_id,customer_date;
    EditText  customer_name, customer_address, customer_mobile_no, customer_gst_no, customer_email_id, customer_password, customer_c_password;
    Button register_customer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_customer, container, false);

        customer_date = view.findViewById(R.id.c_date);
        customer_id = view.findViewById(R.id.c_id);
        customer_name = view.findViewById(R.id.c_name);
        customer_address = view.findViewById(R.id.c_address);
        customer_mobile_no = view.findViewById(R.id.c_mobile_no);
        customer_gst_no = view.findViewById(R.id.c_gst_no);
        customer_email_id = view.findViewById(R.id.c_email_id);
        customer_password = view.findViewById(R.id.c_password);
        customer_c_password = view.findViewById(R.id.c_c_password);

        long uniqueIds = System.currentTimeMillis();
        String ids = "" + uniqueIds;
        customer_id.setText(ids+"");

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);
        customer_date.setText(formattedDate);

        register_customer = view.findViewById(R.id.register_customer);
        register_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
            }
        });


        return view;
    }

    private void check() {
        String c_id = customer_id.getText().toString();
        String c_name = customer_name.getText().toString();
        String c_address = customer_address.getText().toString();
        long c_mobile_no = Long.parseLong(String.valueOf(customer_mobile_no.getText())); 
        String c_gst_no = customer_gst_no.getText().toString();
        String c_email_id = customer_email_id.getText().toString();
        String c_password = customer_password.getText().toString();
        String c_date = customer_date.getText().toString();
        if(c_password.equals(customer_c_password.getText().toString())){
            register_customer_user user = new register_customer_user(c_id, c_name, c_address, c_gst_no, c_email_id, c_password, c_mobile_no,c_date);

            reference.child("Customer").child("Registered_user").child(AppController.formatEmail(user.customer_email_id)).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful())
                        Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e("retailer failed", e.toString());
                }
            });

        }
        else {
            Toast.makeText(getContext(), "Password Doesn't Match", Toast.LENGTH_SHORT).show();
        }


           

    }
}
