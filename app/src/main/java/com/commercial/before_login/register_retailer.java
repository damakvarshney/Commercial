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
import static com.commercial.AppController.retailer_database_user_email;

public class register_retailer extends Fragment {

    TextView retailer_id,retailer_date;
    EditText retailer_type, retailer_shope_name,retailer_owner_name, retailer_address, retailer_mobile_no, retailer_gst_no, retailer_email_id, retailer_password, retailer_c_password;
    Button register_retailer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_retailer, container, false);

        retailer_date = view.findViewById(R.id.r_date);
        retailer_type = view.findViewById(R.id.r_type);
        retailer_id = view.findViewById(R.id.r_id);
        retailer_owner_name = view.findViewById(R.id.r_owner);
        retailer_shope_name = view.findViewById(R.id.r_shope_name);
        retailer_address = view.findViewById(R.id.r_address);
        retailer_mobile_no = view.findViewById(R.id.r_mobile_no);
        retailer_gst_no = view.findViewById(R.id.r_gst_no);
        retailer_email_id = view.findViewById(R.id.r_email_id);
        retailer_password = view.findViewById(R.id.r_password);
        retailer_c_password = view.findViewById(R.id.r_c_password);

        Long uniqueId = System.currentTimeMillis();
        String id = "" + uniqueId;
        retailer_id.setText(id);

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);
        retailer_date.setText(formattedDate);

        register_retailer = view.findViewById(R.id.register_retailer);
        register_retailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
            }
        });


        return view;
    }

    private void check() {
        String r_type = retailer_type.getText().toString();
        String r_id = retailer_id.getText().toString();
        String r_shope_name = retailer_shope_name.getText().toString();
        String r_owner_name = retailer_owner_name.getText().toString();
        String r_address = retailer_address.getText().toString();
        long r_mobile_no = Long.parseLong(String.valueOf(retailer_mobile_no.getText()));
        String r_gst_no = retailer_gst_no.getText().toString();
        String r_email_id = retailer_email_id.getText().toString();
        String r_password = retailer_password.getText().toString();
        String r_date = retailer_date.getText().toString();
        if(r_password.equals(retailer_c_password.getText().toString())){
            register_retailer_user user = new register_retailer_user (r_id,r_type, r_shope_name, r_owner_name, r_address, r_mobile_no, r_gst_no, r_email_id, r_password,r_date);

            retailer_database_user_email.child(AppController.formatEmail(user.r_email_id)).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else{
            Toast.makeText(getContext(), "Password Doesn't Match", Toast.LENGTH_SHORT).show();
        }
}

}
