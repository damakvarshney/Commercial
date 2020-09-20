package com.commercial.before_login.bottom.contact_us;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.commercial.AppController;
import com.commercial.R;
import com.commercial.before_login.register_admin_user;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import static com.commercial.AppController.reference;


public class Contact_Us_Fragment extends Fragment {

    TextView date;
    EditText names,address,mobile_no,email_id,description,subject;
    Button submit;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.skip_this_contact_us, container, false);

        subject = root.findViewById(R.id.skip_subject);
        date = root.findViewById(R.id.skip_date);
        names = root.findViewById(R.id.skip_name);
        address = root.findViewById(R.id.skip_address);
        mobile_no = root.findViewById(R.id.skip_mobile_no);
        email_id = root.findViewById(R.id.skip_email_id);
        description = root.findViewById(R.id.skip_desc);
        submit = root.findViewById(R.id.skip_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
            }
        });

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-mm-yyyy");
        String formattedDate = df.format(c);
        date.setText(formattedDate);

        return root;
    }

    private void check() {
        String s_name = names.getText().toString();
        String s_address = address.getText().toString();
        String s_email_id = email_id.getText().toString();
        String s_desc = description.getText().toString();
        String s_date = date.getText().toString();
        String s_subject = subject.getText().toString();
        long s_mobile_no = Long.parseLong(String.valueOf(mobile_no.getText()));
        Contact_Us_user user = new Contact_Us_user(s_name, s_address, s_email_id,s_desc, s_mobile_no,s_date,s_subject);

        reference.child("Miscellaneous").child("Feedback").child(AppController.formatEmail(user.email_id)).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
