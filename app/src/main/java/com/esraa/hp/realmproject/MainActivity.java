package com.esraa.hp.realmproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    Button button;
    RealmService realmService;
    String comment="ريلم هى داتابيز مش sql و هى اسهل من room w sqlite بس عيبها الوحيد انها حجمها كبير " +
            "بتضيف للابلكيشن تقريبا 5 ميجا فلو عندى ابلكيشن صغير مفيهوش تفاصيل كتيرو مش عاوز اكبر حجمه على الفاضى" +
            " ممكن استخدم room بس لو الابلكيشن كدة كدة كبير يبقي احسن استخدم realm لانها اسهل بكتير ككود " +
            "1. هنعمل ال table اللى هوة Contact " +
            "2. هنعمل كلاس فيه ال methods اللى بتعمل ال operations و هنسميه realmservice مثلا " +
            "3. بعد كدة بمنتهى السهولة كل activity هعمل اوبجكت من RealmService و انادى على الميثود اللى جواها" +
            "https://realm.io/docs/java/latest/" +
            "لينك الdocumentation بتاعها  ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView= findViewById(R.id.list1);
        realmService=new RealmService(this);



        button= findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,AddActivity.class);
                startActivity(i);
            }
        });

       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact selected_contact= (Contact) parent.getItemAtPosition(position);
                Intent intent=new Intent(MainActivity.this,UpdateActivity.class);
                intent.putExtra("id", selected_contact.getId());
                startActivity(intent);
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        RealmResults<Contact> contacts=realmService.getAllContacts();
        ContactAdapter adapter=new ContactAdapter(MainActivity.this,contacts);
        listView.setAdapter(adapter);

    }
    }
