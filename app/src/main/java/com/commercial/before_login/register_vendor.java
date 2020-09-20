package com.commercial.before_login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

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
import com.google.android.gms.tasks.Task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.commercial.AppController.reference;

public class register_vendor extends Fragment {

    TextView vendor_id,vendor_date;
    EditText vendor_cart_no, vendor_name, vendor_address, vendor_mobile_no, vendor_gst_no, vendor_email_id, vendor_password, vendor_c_password;
    Button register_vendor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_vendor, container, false);

        vendor_date = view.findViewById(R.id.v_date);
        vendor_cart_no = view.findViewById(R.id.v_cart_no);
        vendor_id = view.findViewById(R.id.v_consumer_id);
        vendor_name = view.findViewById(R.id.v_name);
        vendor_address = view.findViewById(R.id.v_address);
        vendor_mobile_no = view.findViewById(R.id.v_mobile_no);
        vendor_gst_no = view.findViewById(R.id.v_gst_no);
        vendor_email_id = view.findViewById(R.id.v_email_id);
        vendor_password = view.findViewById(R.id.v_password);
        vendor_c_password = view.findViewById(R.id.v_c_password);

        Long uniqueId = System.currentTimeMillis() ;
        String c_id = "" + uniqueId;
        vendor_id.setText(c_id);

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);
        vendor_date.setText(formattedDate);

        register_vendor = view.findViewById(R.id.register_vendor);
        register_vendor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
            }
        });


        return view;
    }

    private void check() {
        String v_cart_no = vendor_cart_no.getText().toString();
        String v_id = vendor_id.getText().toString();
        String v_name = vendor_name.getText().toString();
        String v_address = vendor_address.getText().toString();
        String v_gst_no = vendor_gst_no.getText().toString();
        String v_email_id = vendor_email_id.getText().toString();
        String v_password = vendor_password.getText().toString();
        long v_mobile_no = Long.parseLong(String.valueOf(vendor_mobile_no.getText()));
        String v_date = vendor_date.getText().toString();
        if(v_password.equals(vendor_c_password.getText().toString())){
            register_vendor_user user = new register_vendor_user(v_cart_no, v_id, v_name, v_address, v_gst_no, v_email_id, v_password, v_mobile_no,v_date);

            reference.child("Vendor").child("Registered_user").child(AppController.formatEmail(user.vendor_email_id)).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else {
            Toast.makeText(getContext(), "Password Doesn't Match", Toast.LENGTH_SHORT).show();
        }

    }
}
