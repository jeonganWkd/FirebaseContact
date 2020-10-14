package com.test.firebasecontact;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.test.firebasecontact.adapter.RecyclerViewAdapter;
import com.test.firebasecontact.contact.Contact;
import com.test.firebasecontact.util.Util;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnAdd;

    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    ArrayList<Contact> contactArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,AddContact.class);
                startActivity(i);
            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
        FirebaseFirestore db = FirebaseFirestore.getInstance();


        db.collection(Util.TABLE_NAME).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                //여러개니까 for루프
                contactArrayList.clear();
                for (QueryDocumentSnapshot snapshots : queryDocumentSnapshots){
                    String id = snapshots.getId();
                    String name = snapshots.getString("name");
                    String phone = snapshots.getString("phone");
                    Log.i("AAA",id);
                    Contact contact = snapshots.toObject(Contact.class);
                    contact.setId(id);
                    contactArrayList.add(contact);

                }
                Log.i("AAA","count"+contactArrayList.size());
                recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this,contactArrayList);
                recyclerView.setAdapter(recyclerViewAdapter);
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });


    }
}