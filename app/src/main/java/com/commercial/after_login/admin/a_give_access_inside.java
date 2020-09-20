package com.commercial.after_login.admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.commercial.R;

public class a_give_access_inside extends Fragment {

    TextView unique_id,name,cart_shop_name,address,mobile_no,gst_no,email_id;
    EditText percentage;
    Button add,reject;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.a_give_access_inside, container, false);

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

        return view;
    }
}
