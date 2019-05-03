package com.esraa.hp.realmproject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {
EditText editName,editPhone;
Button add;
String name,phone;
RealmService realmService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        editName=findViewById(R.id.editname);
        editPhone=findViewById(R.id.editphone);
        add=findViewById(R.id.btn);
        realmService=new RealmService(this);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=editName.getText().toString();
                phone=editPhone.getText().toString();
                realmService.addContact(name,phone);
                finish();


            }
        });

    }


}
