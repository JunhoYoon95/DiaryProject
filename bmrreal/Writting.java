package com.example.bmrreal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Activity;
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

    TextView dateNow;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.writting);


        dateNow = (TextView) findViewById(R.id.dateNow);
        dateNow.setText(formatDate);    // TextView 에 현재 시간 문자열 할당


        EditText editText4 = (EditText)findViewById(R.id.dateNow);
        TextView textView2 = (TextView)findViewById(R.id.articletitle);
        EditText editting = (EditText)findViewById(R.id.editting);
        Button button3 = (Button)findViewById(R.id.modifybutton);
        Button button4 = (Button)findViewById(R.id.deletebutton);
        Button button5 = (Button)findViewById(R.id.publishbutton);

    }

}
