package com.zainabali.yz.contacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    public static final int DB_VERSION= 1;
    public static final String DB_NAME= "contacts";
    //define column table
    public static final String TABLE_NAME="Contact";
    public static final String CLM_NAME="name";
    public static final String CLM_PHONE="phone";
    public static final String CLM_ID="id";

    public Database(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" + CLM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + CLM_NAME + "  TEXT ," + CLM_PHONE + " TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //delete table
        String delete_table="DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(delete_table);
        //rebuild table
        onCreate(db);
    }
    // we create add contact method
    public void addContact(Contact contact)
    {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(CLM_NAME,contact.getName());
        values.put(CLM_PHONE,contact.getPhone());
         db.insert(TABLE_NAME,null,values);

    }

    //داله استرجاع لعرض البيانات
    public ArrayList<Contact> getAllContact(){
        ArrayList<Contact> contacts=new ArrayList<>();
        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        if (c != null&& c.moveToFirst())
            do {
                int id=c.getInt(0);
                String name=c.getString(1);
                int phone=c.getInt(2);
                 Contact contact=new Contact(id,name,phone);
                contacts.add(contact);
            }
            while (c.moveToNext());
        c.close();
        return contacts;
    }

    //هذه الداله تاخذ البيانات المدخله وعرضها في صفحة التعديل عن طريق ال id
    public Contact getContactById(int id){
        Contact contact=null;
        SQLiteDatabase db=getReadableDatabase();
        //طريقه اخذ بيانات المستخدم عن طريق id الخاص به
        Cursor c=db.query(TABLE_NAME , new String[]{"id","name","phone",},"id=?"
        ,new String[]{String.valueOf(id)},null,null,null);

        if (c.moveToFirst()){
            int id_contact=c.getInt(0);
            String name=c.getString(1);
            int phone=c.getInt(2);
           contact=new Contact(id,name,phone);
        }
        return contact;
    }

//داله التعديل على البيانات وارحاعها الى list view
    public void Update(Contact contact){
    SQLiteDatabase db=getWritableDatabase();
    ContentValues value =new ContentValues();
    value.put(CLM_NAME,contact.getName());
    value.put(CLM_PHONE,contact.getPhone());

    String[] args ={String.valueOf(contact.getId())};
   db.update(TABLE_NAME,value,"id=?",args);
    }

    //delete data
    public void delete (int id){
        SQLiteDatabase db=getWritableDatabase();
        db.delete(TABLE_NAME,"id=?",new String[]{String.valueOf(id)});

    }
}//end of Database class
