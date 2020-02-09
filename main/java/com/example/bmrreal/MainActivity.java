package com.example.bmrreal;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity{

    EditText editText, editText2;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Spinner spinner = findViewById(R.id.spinner);
        Spinner spinner2 = findViewById(R.id.spinner2);

        Button button = (Button)findViewById(R.id.button);
        ImageButton button2 = (ImageButton)findViewById(R.id.button2);
        ImageButton startwr = (ImageButton)findViewById(R.id.startwr);


        startwr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Writting.class);
                startActivity(intent);
            }
        });



        editText2 = findViewById(R.id.editText);
        listView = findViewById(R.id.listView);

        dbHelper = new DBHelper(this, 4);
        db = dbHelper.getWritableDatabase();    // 읽기/쓰기 모드로 데이터베이스를 오픈



        String[] datas = getResources().getStringArray(R.array.sortntm);
        String[] datas2 = getResources().getStringArray(R.array.sortvcd);

        ArrayAdapter<String> ar1 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, datas);
        ArrayAdapter<String> ar2 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, datas2);
        ar1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ar2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(ar1);
        spinner2.setAdapter(ar2);

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



    public void insert(View v) {
        // 이런 insert 기능을 다시 거기 Writting.java 파일 안에다가 넣으면 모든 것이 끝이 날 거 같지.

        String name = editText2.getText().toString();
        // tableName 테이블의 (name, info) Values를 insert(삽입) 한다는 의미이다.
        db.execSQL("INSERT INTO tableName VALUES ('" + name + "');");

        // 여기서 tableName 테이블의 이름으로 이는 저번 테이블을 CREATE(생성) 할 때 정한 테이블의 이름이다.
        // 그러니 테이블을 생성할 때 의미의 맞게 테이블의 이름을 준 후 사용하면 된다.
        Toast.makeText(getApplicationContext(), "추가 성공", Toast.LENGTH_LONG).show();

        //""(큰 따옴표)는 안드로이드(자바)에서 사용되는 것이고,
        // ''(작은따옴표)는 SQL문에서 사용되는 것이라 생각하면 편하다.
        editText2.setText("");
    }

    public void delete(View v) {
        String name = editText2.getText().toString();
        //단, SQL문에서 DELETE를 사용하여 삭제해준다. 그리고 WHERE은 if문 같은 것으로
        //
        //
        //
        //조건은 name ='name'; 으로 editText1에서 name을 적은 후 삭제 버튼을 클릭하면
        // 이미 기존 테이블에 저장되어 있는 name 데이터와 같은 것이 있으면 삭제되도록 한다.
        db.execSQL("DELETE FROM tableName WHERE name = '" + name + "';");
    }
}
