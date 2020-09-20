package com.commercial.after_login.admin;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

import static com.commercial.AppController.admin_database;
import static com.commercial.AppController.mStorageRef;

public class a_update_gallery_new extends Fragment {

    EditText album_name;
    ImageView image;
    Button save, browse;
    private PickResult result;
    private Context context;
    TextView tv_preview, tv_img_gallery;
    a_update_gallery_user user;
    ProgressDialog progressDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.a_update_gallery_new, container, false);

        album_name = view.findViewById(R.id.ugn1);
        save = view.findViewById(R.id.ugn4);

        context = getActivity();

        tv_preview = view.findViewById(R.id.tv_preview_gallery);
        image = view.findViewById(R.id.ugn2);
        browse = view.findViewById(R.id.ugn3);
        save = view.findViewById(R.id.ugn4);
        tv_img_gallery = view.findViewById(R.id.tv_img_gallery);

        album_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tv_preview.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

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
                        tv_img_gallery.setText(result.getPath());
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
        if (result != null && !album_name.getText().toString().equals("")) {
            uploadDp();
        }
    }

    private void uploadDp() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Uploading Photo");
        progressDialog.setCancelable(false);
        progressDialog.show();
        user = new a_update_gallery_user(tv_preview.getText().toString(), "");
        if (result != null) {
            mStorageRef.child("Admin").child("Gallery Images").child(String.valueOf(user.id)).putFile(result.getUri()).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    mStorageRef.child("Admin").child("Gallery Images").child(String.valueOf(user.id)).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            user.image = uri.toString();
                            saveData();
                            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
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

    private void saveData() {
        admin_database.child("New Gallery").child(String.valueOf(user.album_name)).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
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
