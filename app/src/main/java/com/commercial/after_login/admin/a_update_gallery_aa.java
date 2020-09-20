package com.commercial.after_login.admin;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.commercial.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.List;

import static android.content.ContentValues.TAG;
import static com.commercial.AppController.admin_database;
import static com.commercial.AppController.mStorageRef;

public class a_update_gallery_aa extends RecyclerView.Adapter<a_update_gallery_aa.myholder>{

    Context context;
    List<a_update_gallery_user> users;
    private int position;

    public a_update_gallery_aa(Context context, List<a_update_gallery_user> user) {
        this.context = context;
        this.users = user;
    }

    @NonNull
    @Override
    public myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.a_update_gallery_adap,null);
        return new myholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final myholder holder, final int position) {

        a_update_gallery_user user = users.get(position);
        holder.tv1.setText(user.album_name);
        Log.e(TAG, "onBindViewHolder: "+user.image );
        Glide.with(context)
                .load(user.image)
                .placeholder(R.drawable.blue_dot)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.image.setImageResource(R.drawable.blue_dot);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(holder.image);
        holder.root.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                delete_item(position);
                return false;
            }
        });

    }

    private void delete_item(final int position) {
        androidx.appcompat.app.AlertDialog.Builder builder=new androidx.appcompat.app.AlertDialog.Builder(context);
        builder.setNegativeButton("No",null);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mStorageRef.child("Admin").child("Gallery Images").child(String.valueOf(users.get(position))).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });
                admin_database.child("New Gallery").child(String.valueOf(users.get(position).album_name)).removeValue();
                users.remove(position);
                notifyDataSetChanged();
            }
        });
        builder.setNeutralButton("Cancel",null);
        builder.setTitle("Confirm Delete?");
        builder.setMessage("Do you really want to delete this");
        builder.create().show();
    }


    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public int getItemCount() {

        return users.size();
    }
    public class myholder extends ViewHolder {

        ImageView image;
        TextView tv1;
        View root;

        public myholder(@NonNull View itemView) {
            super(itemView);

            root = itemView.findViewById(R.id.root);
            image = itemView.findViewById(R.id.uga1);
            tv1 = itemView.findViewById(R.id.uga2);
        }
        private int position;
        
    }



}
