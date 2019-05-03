package com.esraa.hp.realmproject;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateActivity extends AppCompatActivity {
    EditText edit1;
    EditText edit2;
    Button button;
    int id;
    RealmService realmService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        edit1=findViewById(R.id.editname);
        edit2=findViewById(R.id.editphone);
        button=findViewById(R.id.btn);
        realmService=new RealmService(this);

        id=getIntent().getIntExtra("id",0);
        Contact contact=realmService.getContact(id);
        edit1.setText(contact.getName());
        edit2.setText(contact.getPhone());


       button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=edit1.getText().toString();
                String phone= edit2.getText().toString();
                Contact newContact=new Contact();
                newContact.setName(name);
                newContact.setPhone(phone);
                newContact.setId(id);
                realmService.updateContact(newContact);
                finish();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                showAlert();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showAlert() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Confirmation")
                .setMessage("Are you sure")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        realmService.deleteContact(id);
                        finish();

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog=builder.create();
        dialog.show();
    }
}
