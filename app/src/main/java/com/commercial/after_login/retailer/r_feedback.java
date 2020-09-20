package com.commercial.after_login.retailer;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.commercial.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.Calendar;

import static com.commercial.AppController.retailer_database;
import static com.commercial.AppController.retailer_user;

public class r_feedback extends Fragment {

    TextView fd_textview, td_textview, date_textview, order_id, subject, feedback;
    Calendar calendar;
    Button fd_btn, td_btn, submit, date, display;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.feedback_r, container, false);

        date = view.findViewById(R.id.fr1);
        date_textview = view.findViewById(R.id.fr2);
        order_id = view.findViewById(R.id.fr3);
        subject = view.findViewById(R.id.fr4);
        feedback = view.findViewById(R.id.fr5);
        submit = view.findViewById(R.id.fr6);
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

                        date_textview.setText(dayOfMonth + "/" + (month+1) + "/" + year);

                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
                dialog.show();
            }
        });

        return view;
    }


    private void check() {
        String date_r = date_textview.getText().toString();
        String subject_r = subject.getText().toString();
        String feedback_r = feedback.getText().toString();

        r_feedback_user user = new r_feedback_user(retailer_user.getR_owner_name(),date_r,  subject_r, feedback_r);
        retailer_database.child("Feedback").child(subject_r).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
