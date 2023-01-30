package com.zainabali.yz.contacts;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.text.LineBreaker;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddNewContact extends AppCompatActivity {
EditText editName,editPhone;
Button btnConfirm;
ImageButton imageButton;
Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contact);
        db =new Database(this);
        editName=findViewById(R.id.editName);
        editPhone=findViewById(R.id.editNumber);
        imageButton=findViewById(R.id.imageClick);
        btnConfirm=findViewById(R.id.btnConfirm);


        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String name = editName.getText().toString();
                int phone = Integer.parseInt(editPhone.getText().toString());
                Contact contact=new Contact( name,phone);
                db.addContact(contact);

// to back to main activity
                Intent intent=new Intent(AddNewContact.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }

    //داله تمكنني من فتح الصور الخاصه بالهاتف
    public void openGallery(View view){
        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent,100);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_OK && requestCode == 100){
            Uri uri=data.getData();

            try {
             InputStream   inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                imageButton.setImageBitmap(bitmap);
            } catch (Exception e) {
                Log.e("ex",e.getMessage());
        }
    }
    }
}