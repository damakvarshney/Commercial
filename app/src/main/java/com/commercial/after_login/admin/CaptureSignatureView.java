package com.commercial.after_login.admin;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.commercial.R;
import com.github.gcacace.signaturepad.views.SignaturePad;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import static com.commercial.AppController.admin_database;
import static com.commercial.AppController.mStorageRef;
import static com.commercial.AppController.reference;
import static com.commercial.AppController.retailer_database;
import static com.commercial.after_login.admin.a_collect_order_aa1.order_of;


public class CaptureSignatureView extends Fragment {

    SignaturePad signaturePad;
    Button saveButton, clearButton;
    String name = "name";
    long order_id;
    TextView name_sign, order_id_sign;
    Context context;
    private ProgressDialog progressDialog;
    String user = order_of;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.capture_signature_view, container, false);

        context = getActivity();

        progressDialog = new ProgressDialog(context);

        Bundle bundle = getArguments();
        name = bundle.getString("Retailer_Name");
        order_id = bundle.getLong("Order_Id");


        name_sign = view.findViewById(R.id.name_sign);
        order_id_sign = view.findViewById(R.id.order_id_sign);
        signaturePad = (SignaturePad) view.findViewById(R.id.signaturePad);
        saveButton = (Button) view.findViewById(R.id.saveButton_sign);
        clearButton = (Button) view.findViewById(R.id.clearButton_sign);

        name_sign.setText(name);
        order_id_sign.setText(String.valueOf(order_id));

        //disable both buttons at start
        saveButton.setEnabled(false);
        clearButton.setEnabled(false);

        //change screen orientation to landscape mode

        signaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {

            }

            @Override
            public void onSigned() {
                saveButton.setEnabled(true);
                clearButton.setEnabled(true);
            }

            @Override
            public void onClear() {
                saveButton.setEnabled(false);
                clearButton.setEnabled(false);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //write code for saving the signature here
                Toast.makeText(getContext(), "Signature Saved", Toast.LENGTH_SHORT).show();
                // signaturePad.getSignatureBitmap()
                File f;
                try {
                    f = new File(context.getCacheDir(), "sign" + System.currentTimeMillis());
                    f.createNewFile();

//Convert bitmap to byte array
                    Bitmap bitmap = signaturePad.getSignatureBitmap();
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
                    byte[] bitmapdata = bos.toByteArray();

//write the bytes in file
                    FileOutputStream fos = null;

                    fos = new FileOutputStream(f);
                    fos.write(bitmapdata);
                    fos.flush();
                    fos.close();
                    uploadSign(f);
                } catch (Exception e) {
                    Log.e("file",e.getLocalizedMessage());
                }
//                reference.child(order_of).child("Order").child(String.valueOf(order_id)).setValue()
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signaturePad.clear();
            }
        });

        return view;
    }

    private void uploadSign(File f) {
        Uri uri = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".fileprovider", f);
        mStorageRef.child("Sign Images").child(String.valueOf(order_id)).putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                mStorageRef.child("Sign Images").child(String.valueOf(order_id)).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        saveData(uri.toString());
                        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                        Log.e("image",""+uri.toString());
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Log.e("failed", exception.getLocalizedMessage());
            }
        });
    }

    private void saveData(String value) {
        reference.child(user).child("Order/"+(order_id) + "/sign").setValue(value).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
        reference.child(user).child("Order/"+(order_id) + "/order_status").setValue("Order Delivered").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(context, "Order Delivered", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
