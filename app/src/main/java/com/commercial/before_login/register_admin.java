package com.commercial.before_login;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.commercial.AppController;
import com.commercial.R;
import com.commercial.after_login.admin.for_admin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.commercial.AppController.reference;

public class register_admin extends Fragment {
    TextView emp_id,emp_date;
    EditText emp_type, emp_name, emp_address, emp_mobile_no, emp_gst_no, emp_email_id, emp_password, emp_c_password;
    Button register_admin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_admin, container, false);

        emp_date = view.findViewById(R.id.a_date);
        emp_type = view.findViewById(R.id.e_type);
        emp_id = view.findViewById(R.id.e_id);
        emp_name = view.findViewById(R.id.e_name);
        emp_address = view.findViewById(R.id.e_address);
        emp_mobile_no = view.findViewById(R.id.e_mobile_no);
        emp_gst_no = view.findViewById(R.id.e_gst_no);
        emp_email_id = view.findViewById(R.id.e_email_id);


        emp_password = view.findViewById(R.id.e_password);
        emp_c_password = view.findViewById(R.id.e_c_password);

        Long uniqueId = System.currentTimeMillis();
        String id = "" + uniqueId;
        emp_id.setText(id);

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);
        emp_date.setText(formattedDate);

        register_admin = view.findViewById(R.id.register_admin);
        register_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
            }
        });

        return view;
    }

    private void check() {
        String e_type = emp_type.getText().toString();
        String e_id = emp_id.getText().toString();
        String e_name = emp_name.getText().toString();
        String e_address = emp_address.getText().toString();
        long e_mobile_no = Long.parseLong(String.valueOf(emp_mobile_no.getText()));
        String e_gst_no = emp_gst_no.getText().toString();
        String e_email_id = emp_email_id.getText().toString();
        String e_password = emp_password.getText().toString();
        String e_date = emp_date.getText().toString();
        if(e_password.equals(emp_c_password.getText().toString())){

            register_admin_user user = new register_admin_user(e_type, e_id, e_name, e_address, e_gst_no, e_password, e_mobile_no, e_email_id,e_date);
            reference.child("Admin").child("Registered_user").child(AppController.formatEmail(user.emp_email_id)).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
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
