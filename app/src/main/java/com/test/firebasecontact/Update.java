package com.test.firebasecontact;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.test.firebasecontact.contact.Contact;
import com.test.firebasecontact.util.Util;

public class Update extends AppCompatActivity {

    EditText editName;
    EditText editPhone;
    Button btnUpdate;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        editName = findViewById(R.id.editName);
        editPhone = findViewById(R.id.editPhone);
        btnUpdate = findViewById(R.id.btnUpdate);

        id = getIntent().getStringExtra("id");
        String name = getIntent().getStringExtra("name");
        String phone = getIntent().getStringExtra("phone");

        editName.setText(name);
        editPhone.setText(phone);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = editName.getText().toString().trim();
                String phone = editPhone.getText().toString().trim();
                Contact contact = new Contact(name, phone);
                FirebaseFirestore db = FirebaseFirestore.getInstance();

                db.collection(Util.TABLE_NAME).document(id).set(contact).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(Update.this, "수정되었습니다",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });
            }
        });

    }
}















