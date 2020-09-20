package com.commercial.after_login.admin;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.commercial.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import static com.commercial.AppController.admin_database;

public class a_maintain_stock_new extends Fragment implements TextWatcher {

    AutoCompleteTextView ms_item_id, ms_item, ms_quantity;
    EditText ms_rate;
    TextView ms_amount;
    Button add_item;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.a_maintain_stock_new, container, false);

        ms_item_id = view.findViewById(R.id.ms1);

        Long uniqueId = System.currentTimeMillis();
        String id = "" + uniqueId;

        ms_item = view.findViewById(R.id.ms2);
        add_item = view.findViewById(R.id.ms_btn_add);
        ms_item_id.setText(id);

        ms_quantity = view.findViewById(R.id.ms3);
        ms_rate = view.findViewById(R.id.ms4);
        ms_amount = view.findViewById(R.id.ms5);


        ms_quantity.addTextChangedListener(this);
        ms_rate.addTextChangedListener(this);


        add_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
            }
        });


        return view;
    }

    private void check() {
        String item_id = ms_item_id.getText().toString();
        String item = ms_item.getText().toString();
        int quantity = Integer.parseInt(String.valueOf(ms_quantity.getText()));
        int rate = Integer.parseInt(String.valueOf(ms_rate.getText()));
        int Tamount = Integer.parseInt(String.valueOf(ms_amount.getText()));
        a_maintain_stock_user user = new a_maintain_stock_user(item_id, item, quantity, rate, Tamount);

        admin_database.child("Stock").child(item).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        try {
            int a = Integer.parseInt(String.valueOf(ms_quantity.getText()));
            int b = Integer.parseInt(String.valueOf(ms_rate.getText()));
            int c = a * b;
            String d = "" + c;
            ms_amount.setText(d);
        } catch (Exception ignored) {

        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
