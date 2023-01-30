package com.zainabali.yz.contacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class Adapter extends ArrayAdapter<Contact> {

Context context;

    public Adapter(@NonNull Context context, int resource, @NonNull List<Contact> objects) {
        super(context, resource, objects);
        this.context=context;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       convertView= LayoutInflater.from(context).inflate(R.layout.items,parent,false);
        TextView showName=convertView.findViewById(R.id.nameId);
        TextView showPhone=convertView.findViewById(R.id.phoneId);
        Contact currentContact=getItem(position);
        showName.setText(currentContact.getName());
        showPhone.setText(String.valueOf(currentContact.getPhone()));
        return convertView;
    }
}
