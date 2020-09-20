package com.commercial.after_login.vendor;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.commercial.R;
import com.commercial.after_login.admin.a_give_access_User;
import com.commercial.after_login.admin.a_give_access_aa;
import com.commercial.before_login.preference;
import com.commercial.before_login.register_vendor_user;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.commercial.AppController.reference;
import static com.commercial.AppController.vendor_database_user_email;
import static com.commercial.AppController.vendor_user;

public class v_feedback extends Fragment {

    TextView date_textview, order_id, subject, feedback;
    Calendar calendar;
    Button submit, date;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.feedback_v, container, false);

        date = view.findViewById(R.id.fv1);
        date_textview = view.findViewById(R.id.fv2);
        order_id = view.findViewById(R.id.fv3);
        subject = view.findViewById(R.id.fv4);
        feedback = view.findViewById(R.id.fv5);
        submit = view.findViewById(R.id.fv6);
        calendar = Calendar.getInstance();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        date_textview.setText(dayOfMonth + "/" + (month + 1) + "/" + year);

                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
                dialog.show();
            }
        });


        return view;
    }


    private void check() {
        String date_v = date_textview.getText().toString();
        String subject_v = subject.getText().toString();
        String feedback_v = feedback.getText().toString();
        long order_id_v = Long.parseLong(String.valueOf(order_id.getText()));
//        System.out.println(vendor_user.getVendor_name());
        v_feedback_user user = new v_feedback_user(vendor_user.getVendor_name(), date_v, subject_v, feedback_v);
        reference.child("Vendor").child("Feedback").child(subject_v).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
