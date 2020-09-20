package com.commercial.after_login.drawer.your_profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.commercial.R;
import com.commercial.before_login.preference;

import static com.commercial.AppController.admin_user;
import static com.commercial.AppController.customer_user;
import static com.commercial.AppController.retailer_user;
import static com.commercial.AppController.vendor_user;


public class Your_Profile_Fragment extends Fragment {

    TextView unique_id, name, address, mobile_no, gst_no, email_id, profile, cart_no, shop_name, emp_typo;
    LinearLayout linearLayout_admin;
    LinearLayout linearLayout_retailer;
    LinearLayout linearLayout_vendor;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_explore_your_profile, container, false);

        profile = view.findViewById(R.id.ep_profile);
        unique_id = view.findViewById(R.id.ep_id);
        name = view.findViewById(R.id.ep_name);
        address = view.findViewById(R.id.ep_address);
        mobile_no = view.findViewById(R.id.ep_mobile_no);
        gst_no = view.findViewById(R.id.ep_gst_no);
        email_id = view.findViewById(R.id.ep_email_id);
        cart_no = view.findViewById(R.id.ep_cart_no);
        shop_name = view.findViewById(R.id.ep_shop_name);
        emp_typo = view.findViewById(R.id.ep_type);
        linearLayout_admin = view.findViewById(R.id.empo_type);
        linearLayout_retailer = view.findViewById(R.id.ep_shoppe_name);
        linearLayout_vendor = view.findViewById(R.id.ep_vendor_cart);


        profile.setText(preference.type);

        if (preference.type.equals("Admin")) {
            Log.e("data", preference.type);
            unique_id.setText(admin_user.getEmp_id());
            name.setText(admin_user.getEmp_name());
            address.setText(admin_user.getEmp_address());
            mobile_no.setText(String.valueOf(admin_user.getEmp_mobile_no()));
            gst_no.setText(admin_user.getEmp_gst_no());
            email_id.setText(admin_user.getEmp_email_id());
            emp_typo.setText(admin_user.getEmp_type());
            linearLayout_admin.setVisibility(View.VISIBLE);
        } else if (preference.type.equals("Retailer")) {
            unique_id.setText(retailer_user.getR_consumer_id());
            name.setText(retailer_user.getR_owner_name());
            mobile_no.setText(String.valueOf(retailer_user.getR_mobile_no()));
            gst_no.setText(retailer_user.getR_gst_no());
            address.setText(retailer_user.getR_address());
            email_id.setText(retailer_user.getR_email_id());
            shop_name.setText(retailer_user.getR_shop_name());
            linearLayout_retailer.setVisibility(View.VISIBLE);
        } else if (preference.type.equals("Customer")) {
            unique_id.setText(customer_user.getCustomer_id());
            name.setText(customer_user.getCustomer_name());
            mobile_no.setText(String.valueOf(customer_user.getCustomer_mobile_no()));
            gst_no.setText(customer_user.getCustomer_gst_no());
            address.setText(customer_user.getCustomer_address());
            email_id.setText(customer_user.getCustomer_email_id());
        } else if (preference.type.equals("Vendor")) {
            unique_id.setText(vendor_user.getVendor_id());
            name.setText(vendor_user.getVendor_name());
            address.setText(vendor_user.getVendor_address());
            mobile_no.setText(String.valueOf(vendor_user.getVendor_mobile_no()));
            email_id.setText(vendor_user.getVendor_email_id());
            gst_no.setText(vendor_user.getVendor_gst_no());
            cart_no.setText(vendor_user.getCart_no());
            linearLayout_vendor.setVisibility(View.VISIBLE);
        }


        return view;
    }

}

