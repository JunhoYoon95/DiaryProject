package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    GridView gridView;
    GridAdapter gridAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayList = new ArrayList<>();
        arrayList.add("배열0");
        arrayList.add("배열1");
        arrayList.add("배열2");
        arrayList.add("배열3");
        arrayList.add("배열4");
        arrayList.add("배열5");

        arrayAdapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,arrayList);

        TextView textView = (TextView)findViewById(R.id.textView);

        spinner = (Spinner)findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),arrayList.get(i)+"가 선택되었습니다.",
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        spinner.setAdapter(arrayAdapter);
        gridView = findViewById(R.id.gridview);

        ArrayList<Category> categories = new ArrayList<>();
        final int imglist[] = {R.drawable.dog,R.drawable.duck,R.drawable.cat,R.drawable.fish,
                R.drawable.horse,R.drawable.pigeon,R.drawable.parrot,R.drawable.rabbit};
        categories.add(new Category("test",imglist[0]));
        categories.add(new Category("test1",imglist[1]));
        categories.add(new Category("test2",imglist[2]));
        categories.add(new Category("test3",imglist[3]));

        gridAdapter = new GridAdapter(this, categories);
        gridView.setAdapter(gridAdapter);
    }
}
