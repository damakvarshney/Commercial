package com.commercial.after_login.customer;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.commercial.R;
import com.commercial.after_login.retailer.r_feedback_user;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.Calendar;

import static com.commercial.AppController.customer_user;
import static com.commercial.AppController.reference;

public class c_feedback extends Fragment {

    TextView fd_textview,td_textview,date_textview,order_id,subject,feedback;
    Calendar calendar;
    Button fd_btn,td_btn,submit,date,display;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.feedback_c, container, false);

        date = view.findViewById(R.id.fc1);
        date_textview = view.findViewById(R.id.fc2);
        order_id = view.findViewById(R.id.fc3);
        subject = view.findViewById(R.id.fc4);
        feedback = view.findViewById(R.id.fc5);
        submit = view.findViewById(R.id.fc6);
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
                DatePickerDialog dialog =  new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        date_textview.setText(dayOfMonth+"/"+(month+1)+"/"+year);

                    }
                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE));
                dialog.show();
            }
        });



       
        return view;
    }

    private void check() {
        String date_c = date_textview.getText().toString();
        String subject_c = subject.getText().toString();
        String desc_c = feedback.getText().toString();

        c_feedback_user user = new c_feedback_user(date_c,subject_c,desc_c,customer_user.getCustomer_name());
        reference.child("Customer").child("Feedback").child(subject_c).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();


            }
        });
    }



}
