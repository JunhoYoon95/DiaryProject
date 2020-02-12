package com.example.bmrreal;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper
{

    // 데이터베이스
    private static final String DATABASE_NAME      = "test.db";
    private static final int DATABASE_VERSION = 1;
    // 테이블
    // 테이블명 : bangbang
    public static final String TABLE_NAME       = "bangbang";
    // 칼럼으로 id
    public static final String COLUMN_ID        = "id";
    // 칼럼으로 date1(날짜)
    public static final String COLUMN_DATE      = "date1";
    // 칼럼으로 title(제목)
    public static final String COLUMN_TITLE      = "title";
    // 칼럼으로 image(작성자의 사진... 아 이건 테이블에 넣는게 아닌거 같은데.. 아니야 맞아)
    public static final String COLUMN_IMAGE     = "image";
    // 칼럼으로 text(본문내용)
    public static final String COLUMN_TEXT      = "text";


    // 테이블들에 내한 타입을 선언해주면서 DATABASE를 정의
    private static final String DATABASE_CREATE_TEAM = "create table "
            + TABLE_NAME + "(" + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_DATE + " date, "
            + COLUMN_TITLE + " integer, "
            + COLUMN_IMAGE + " blob, "
            + COLUMN_TEXT + " text);";


//    기존 테이블에 레코드를 추가하고싶은 경우
//    private static final String DATABASE_ALTER_TEAM_1 = "ALTER TABLE "
//            + TABLE_TEAM + " ADD COLUMN " + COLUMN_COACH + " string;";
//
//    private static final String DATABASE_ALTER_TEAM_2 = "ALTER TABLE "
//            + TABLE_TEAM + " ADD COLUMN " + COLUMN_STADIUM + " string;";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        // 앱을 삭제후 앱을 재설치하면 기존 DB파일은 앱 삭제시 지워지지 않기 때문에
        // 테이블이 이미 있다고 생성 에러남
        // 앱을 재설치시 데이터베이스를 삭제해줘야함.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL(DATABASE_CREATE_TEAM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

        //기존 테이블에 레코드 추가시 사용
//        if (oldVersion < 2) {
//            db.execSQL(DATABASE_ALTER_TEAM_1);
//        }
//        if (oldVersion < 3) {
//            db.execSQL(DATABASE_ALTER_TEAM_2);
//        }

    }

    public void insertTitle(SQLiteDatabase db, String title)
    {
        db.beginTransaction();
        try
        {
            String sql = "insert into " + TABLE_NAME + "(title)" + " values('" + title + "')";
            db.execSQL(sql);
            db.setTransactionSuccessful();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            db.endTransaction();
        }
    }
}
