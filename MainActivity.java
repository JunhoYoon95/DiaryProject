package com.example.bmrreal;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{


    EditText editText2;
    // editText : search 용
    // editText2 : 제목
    // editText3 : 내용
    ListView listView;

    DBHelper dbHelper;
    // 데이터를 위한 DB 클래스 가져오고
    SQLiteDatabase db = null;
    Cursor cursor;

    ArrayAdapter adapter;
    // Array 받아줄 ArrayAdapter 들을 나중에
    // 나중에 이거를 제목 내용 순으로 받아올건데 이거 나중에 기능 뺄거면 빼버리자고. 하나로 통합을 하던지 해서.
    // 이 데이터들을 정렬을 할 시에는 sortingNTM 부분에 spinner 로 만들어 놓았음.


    RecyclerView mRecyclerView = null;
    RecyclerImageTextAdapter mAdapter = null ;
    ArrayList<RecyclerItem> mList = new ArrayList<RecyclerItem>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mRecyclerView = findViewById(R.id.recycler1) ;

        // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
        mAdapter = new RecyclerImageTextAdapter(mList) ;
        mRecyclerView.setAdapter(mAdapter) ;

        // 리사이클러뷰에 LinearLayoutManager 지정. (vertical)
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this)) ;

        Spinner spinner = findViewById(R.id.spinner);
        Spinner spinner2 = findViewById(R.id.spinner2);

        //Button button = (Button)findViewById(R.id.button);
        //ImageButton button2 = (ImageButton)findViewById(R.id.button2);
        ImageButton startwr = (ImageButton)findViewById(R.id.startwr);

        // 아이템 추가.
        addItem(getDrawable(R.drawable.catt),
                "Box", "Account Box Black 36dp") ;
        // 두 번째 아이템 추가.
        addItem(getDrawable(R.drawable.dino),
                "Circle", "Account Circle Black 36dp") ;
        // 세 번째 아이템 추가.
        addItem(getDrawable(R.drawable.catt),
                "Ind", "Assignment Ind Black 36dp") ;

        // 여기서 오류가 나고 있다. 내가 지금 Recycler 을 위한 adapter 을 또 만들어서 그것이랑 충돌이 일어나고 있는지에 대해서 알아봐야 겠다.


        startwr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Writting.class);
                startActivity(intent);
            }
        });



        editText2 = findViewById(R.id.editText2);

        String[] datas = getResources().getStringArray(R.array.sortntm);
        String[] datas2 = getResources().getStringArray(R.array.sortvcd);

        ArrayAdapter<String> ar1 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, datas);
        ArrayAdapter<String> ar2 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, datas2);
        ar1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ar2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(ar1);
        spinner2.setAdapter(ar2);

    }

    public void addItem(Drawable icon, String title, String desc) {
        RecyclerItem item = new RecyclerItem();

        item.setIcon(icon);
        item.setTitle(title);
        item.setDesc(desc);

        mList.add(item);
    }


    public void listUpdate(View v) {
        cursor = db.rawQuery("SELECT * FROM tableName", null);
        startManagingCursor(cursor);    //엑티비티의 생명주기와 커서의 생명주기를 같게 한다.

        adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1);

        while (cursor.moveToNext()) {
            adapter.add(cursor.getString(0));
        }


        cursor.moveToFirst();
        cursor.moveToPrevious();
        cursor.moveToPosition(2);


        listView.setAdapter(adapter);
    }






}
