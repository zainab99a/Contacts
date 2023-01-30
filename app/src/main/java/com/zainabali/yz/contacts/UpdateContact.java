package com.zainabali.yz.contacts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateContact extends AppCompatActivity {
Database db;
EditText editName,editPhone;
Button btnUpdate;
int id;
    @Override//عند الضغط عاى الايتم تضهر صفحه الابديت محمله بالبيانات الموجوده في الايتم
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);
        editName=findViewById(R.id.editName);
        editPhone=findViewById(R.id.editNumber);
        btnUpdate=findViewById(R.id.btnUpdate);

        //when click on update button save the edit data
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editName.getText().toString();
                int phone = Integer.parseInt(editPhone.getText().toString());
                Contact contact=new Contact(id,name,phone);
                db.Update(contact);

                Intent intent=new Intent(UpdateContact.this,MainActivity.class);
                startActivity(intent);
            }
        });


//this for show the data in update class
         id = getIntent().getIntExtra("id",0);
        db=new Database(this);
        Contact contact=db.getContactById(id);
        editName.setText(contact.getName());
        editPhone.setText(String.valueOf(contact.getPhone()));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){//عند الضغط يضهر اريت دايلوك
            case R.id.item_id:
                showAlert();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showAlert() {
        AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);
        alertDialog.setTitle("Confirmation")
                .setTitle("Are You Sure")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //delete contact
                        db.delete(id);
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //اخفاء دايلوك
                    }
                });
        AlertDialog dialog=alertDialog.create();
        dialog.show();
    }
}