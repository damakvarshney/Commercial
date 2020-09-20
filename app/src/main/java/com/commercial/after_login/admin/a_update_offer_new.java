package com.commercial.after_login.admin;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.commercial.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.UploadTask;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

import java.util.Calendar;

import static com.commercial.AppController.mStorageRef;
import static com.commercial.AppController.reference;

public class a_update_offer_new extends Fragment {

    TextView offer_name, offer, offer_id, from_date_tv, till_date_tv, tv_img_offer;
    ImageView image;
    Button save, browse, from_date_btn, till_date_btn;
    CheckBox for_retailer, for_customer, for_vendor;
    Calendar calendar;
    private PickResult result;
    a_update_offer_user user;
    private ProgressDialog progressDialog;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.a_update_offer_new, container, false);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);

        context = getActivity();

        offer_name = view.findViewById(R.id.uon_offer_name);
        tv_img_offer = view.findViewById(R.id.tv_img_offer);
        offer = view.findViewById(R.id.uon_offer);
        offer_id = view.findViewById(R.id.uon_offer_id);
        image = view.findViewById(R.id.uon_image);
        browse = view.findViewById(R.id.uon_browse);
        save = view.findViewById(R.id.uon_save);
        for_customer = view.findViewById(R.id.uon_for_customer);
        for_retailer = view.findViewById(R.id.uon_for_retailer);
        for_vendor = view.findViewById(R.id.uon_for_vendor);
        from_date_tv = view.findViewById(R.id.uon_tv_fd);
        till_date_tv = view.findViewById(R.id.uon_tv_td);
        from_date_btn = view.findViewById(R.id.uon_btn_fd);
        till_date_btn = view.findViewById(R.id.uon_btn_td);
        calendar = Calendar.getInstance();

        Long uniqueId = System.currentTimeMillis();
        String id = "" + uniqueId;

        offer_id.setText(id);

        from_date_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        from_date_tv.setText(dayOfMonth + "/" + month + "/" + year);

                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
                dialog.show();
            }
        });

        till_date_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        till_date_tv.setText(dayOfMonth + "/" + month + "/" + year);

                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
                dialog.show();
            }
        });

        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PickImageDialog.build(new PickSetup()).setOnPickResult(new IPickResult() {
                    @Override
                    public void onPickResult(PickResult r) {
                        image.setImageBitmap(r.getBitmap());
                        result = r;
                        tv_img_offer.setText(result.getPath());
                    }
                }).show(getFragmentManager());
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
            }
        });

        return view;
    }

    private void check() {
        String from_tv_date = from_date_tv.getText().toString();
        String till_tv_date = till_date_tv.getText().toString();
        String offer_id_tv = offer_id.getText().toString();
        String offer_tv = offer.getText().toString();
        String offer_name_tv = offer_name.getText().toString();


        user = new a_update_offer_user(offer_name_tv, offer_tv, from_tv_date, till_tv_date, offer_id_tv, "", for_retailer.isChecked(), for_customer.isChecked(), for_vendor.isChecked());


        if (result != null) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setTitle("Uploading Photo");
            progressDialog.setCancelable(false);
            progressDialog.show();
            if (result != null) {
                mStorageRef.child("Admin").child("Offer Images").child(String.valueOf(user.id)).putFile(result.getUri()).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        mStorageRef.child("Admin").child("Offer Images").child(String.valueOf(user.id)).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                user.offer_image = uri.toString();
                                saveData();
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(context, "Failed Upload", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                        double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                .getTotalByteCount());
                        progressDialog.setMessage("Uploaded " + (int) progress + "%");
                    }
                });
            }
        }

    }

    private void saveData() {
        reference.child("Admin").child("New Offer").child(user.offer_name).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    progressDialog.dismiss();
                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                } else
                    progressDialog.dismiss();
                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
