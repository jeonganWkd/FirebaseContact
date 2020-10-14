package com.test.firebasecontact.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.test.firebasecontact.R;
import com.test.firebasecontact.Update;
import com.test.firebasecontact.contact.Contact;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context context;
    ArrayList<Contact> contactArrayList;

    public RecyclerViewAdapter(Context context, ArrayList<Contact> contactArrayList){
        this.context = context;
        this.contactArrayList = contactArrayList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_row,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {

        Contact contact = contactArrayList.get(position);
        String name = contact.getName();
        String phone = contact.getPhone();

        holder.txtName.setText(name);
        holder.txtPhone.setText(phone);

    }

    @Override
    public int getItemCount() {
        return contactArrayList.size();

    }

    public  class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtName;
        TextView txtPhone;
        ImageView imgDelete;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtName);
            txtPhone = itemView.findViewById(R.id.txtPhone);
            imgDelete = itemView.findViewById(R.id.imgDelete);
            cardView = itemView.findViewById(R.id.cardView);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, Update.class);

                    int index = getAdapterPosition();
                    Contact contact = contactArrayList.get(index);
                    String id = contact.getId();
                    String name = contact.getName();
                    String phone = contact.getPhone();

                    i.putExtra("id",id);
                    i.putExtra("name", name);
                    i.putExtra("phone", phone);

                    context.startActivity(i);

                }
            });



        }
    }
}









