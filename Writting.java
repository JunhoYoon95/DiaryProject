package com.example.bmrreal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Activity;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Writting extends AppCompatActivity {

    // 현재시간을 msec 으로 구한다.
    long now = System.currentTimeMillis();
    // 현재시간을 date 변수에 저장한다.
    Date date = new Date(now);
    // 시간을 나타냇 포맷을 정한다 ( yyyy/MM/dd 같은 형태로 변형 가능 )
    SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    // nowDate 변수에 값을 저장한다.
    String formatDate = sdfNow.format(date);
    EditText editText4;
    Button modifybutton;
    Button deletebutton;
    Button publishbutton;
    TextView dateNow;
    TextView articletitle;
    EditText editting;


    String participants[] = new String[100];
    // 일단은 100명정도라고 보자고ㅎㅎ
    StringBuffer sb;
    int count  = 0;
    int version = 1;
    DBHelper helper;
    SQLiteDatabase database;


    String sql;
    Cursor cursor;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.writting);
        setUp();
        titleList();



    }
    private void setUp()
    {
        dateNow = (TextView) findViewById(R.id.dateNow);
        dateNow.setText(formatDate);    // TextView 에 현재 시간 문자열 할당
        dateNow = (EditText)findViewById(R.id.dateNow);
        articletitle = (TextView)findViewById(R.id.articletitle);
        editting = (EditText)findViewById(R.id.editting);
        modifybutton = (Button)findViewById(R.id.modifybutton);
        deletebutton = (Button)findViewById(R.id.deletebutton);
        publishbutton = (Button)findViewById(R.id.publishbutton);

        helper = new DBHelper(Writting.this, DBHelper.TABLE_NAME, null, version);
        // 읽고 쓸 수 있는 형태의 데이터베이스 오픈
        database = helper.getWritableDatabase();
        sb = new StringBuffer();

    }

    View.OnClickListener myListener = new View.OnClickListener(){


        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.publishbutton){
                insert(v);
                Toast.makeText(v.getContext(), "게시", Toast.LENGTH_SHORT).show();
            }
            else if(v.getId() == R.id.deletebutton){
                delete(v);
                Toast.makeText(v.getContext(), "삭제", Toast.LENGTH_SHORT).show();
            }
            else if(v.getId() == R.id.modifybutton)
            {
                Toast.makeText(v.getContext(), "수정", Toast.LENGTH_SHORT).show();
            }

        }
    };
    private void titleList()
    {
        sql = "select name from " + helper.TABLE_NAME;
        cursor = database.rawQuery(sql, null);
        if (cursor != null)
        {
            count = cursor.getCount();
            for (int i = 0; i < count; i++)
            {
                cursor.moveToNext();
                String participant = cursor.getString(0);
                participants[i] = participant;
                sb.append(participants[i] + " ");
            }
            editting.setText("" + sb);
            cursor.close();
        }
    }

    public void insert(View v) {
        // 이런 insert 기능을 다시 거기 Writting.java 파일 안에다가 넣으면 모든 것이 끝이 날 거 같지.

        String name = editting.getText().toString();
        // tableName 테이블의 (name, info) Values를 insert(삽입) 한다는 의미이다.
        database.execSQL("INSERT INTO tableName VALUES ('');");

        // 여기서 tableName 테이블의 이름으로 이는 저번 테이블을 CREATE(생성) 할 때 정한 테이블의 이름이다.
        // 그러니 테이블을 생성할 때 의미의 맞게 테이블의 이름을 준 후 사용하면 된다.
        Toast.makeText(getApplicationContext(), "추가 성공", Toast.LENGTH_LONG).show();

        //""(큰 따옴표)는 안드로이드(자바)에서 사용되는 것이고,
        // ''(작은따옴표)는 SQL문에서 사용되는 것이라 생각하면 편하다.
        editting.setText("");
    }


    public void delete(View v) {
        String name = editting.getText().toString();
        //단, SQL문에서 DELETE를 사용하여 삭제해준다. 그리고 WHERE은 if문 같은 것으로
        //
        //
        //
        //조건은 name ='name'; 으로 editText에서 name을 적은 후 삭제 버튼을 클릭하면
        // 이미 기존 테이블에 저장되어 있는 name 데이터와 같은 것이 있으면 삭제되도록 한다.
        database.execSQL("DELETE FROM tableName WHERE name = '" + name + "';");
    }


}
