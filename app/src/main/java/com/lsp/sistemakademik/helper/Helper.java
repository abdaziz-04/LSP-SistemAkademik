package com.lsp.sistemakademik.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class Helper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "kampus";

    public Helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_TABLE = "CREATE TABLE mahasiswa (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, jenisKelamin TEXT, tglLahir TEXT, alamat TEXT)";
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS mahasiswa");
        onCreate(sqLiteDatabase);
    }

    public ArrayList<HashMap<String, String>> getAll() {
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        String QUERY = "SELECT * FROM mahasiswa";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(QUERY, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("id", cursor.getString(0));
                map.put("name", cursor.getString(1));
                map.put("jenisKelamin", cursor.getString(2));
                map.put("tglLahir", cursor.getString(3));
                map.put("alamat", cursor.getString(4));

                list.add(map);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public void insert (String name, String jenisKelamin, String tglLahir, String alamat) {
        SQLiteDatabase db = this.getWritableDatabase();
        String QUERY = "INSERT INTO mahasiswa (name, email) VALUES ('"+name+"','"+jenisKelamin+"', '"+tglLahir+"', '"+alamat+"')";
        db.execSQL(QUERY);
    }

    public void update (int id, String name, String jenisKelamin, String tglLahir, String alamat) {
        SQLiteDatabase db = this.getWritableDatabase();
        String QUERY = "UPDATE mahasiswa SET name = '"+name+"','"+jenisKelamin+"', '"+tglLahir+"', '"+alamat+"' WHERE id = " + id;
        db.execSQL(QUERY);
    }

    public void DELETE (int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String QUERY = "DELETE FROM mahasiswa WHERE id = " + id;
        db.execSQL(QUERY);
    }
}
