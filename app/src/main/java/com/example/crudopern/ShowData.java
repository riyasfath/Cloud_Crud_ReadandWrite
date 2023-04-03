package com.example.crudopern;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ShowData extends AppCompatActivity {

    ListView lst;
    FirebaseFirestore fstore;

    List<String> values = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        lst = findViewById(R.id.lst);
        fstore = FirebaseFirestore.getInstance();

        ArrayAdapter<String> arr = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,values);
        lst.setAdapter(arr);

        fstore.collection("students").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                for (QueryDocumentSnapshot documentSnapshot : task.getResult()){

                    values.add(documentSnapshot.getString("fname")+"\n"+documentSnapshot.getString("fdep"));

                }

                arr.notifyDataSetChanged();


            }
        });





    }
}