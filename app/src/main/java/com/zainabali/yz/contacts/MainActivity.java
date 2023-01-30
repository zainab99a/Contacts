package com.zainabali.yz.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
ListView contactList;
Button add;
    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db =new Database(this);
        add=findViewById(R.id.btn);

        //add new contact
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,AddNewContact.class);

                startActivity(i);
            }
        });


        contactList=findViewById(R.id.list);
        //عند الضغط على الاتيم يذهب الى updateContact class
        contactList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              //هذه تابعه لل updateClass
                Contact selected_contact= (Contact) parent.getItemAtPosition(position);

                Intent intent=new Intent(MainActivity.this,UpdateContact.class);
                //هذه تابعه لل updateClass
                intent.putExtra("id",selected_contact.getId());
                startActivity(intent);
            }
        });
            }//end of onCreate

    @Override // هذه الداله نضع فيها هذا الكود النه عند تنفيذه في onCreate لا يخزن المعلومات عند المين اكتفتي لهذا نستخدم هذه الداله
    protected void onResume() {
        super.onResume();
        ArrayList<Contact>contacts=db.getAllContact();
        Adapter contactAdapter=new Adapter(this,R.layout.items,contacts);
        contactList.setAdapter(contactAdapter);
    }
}

