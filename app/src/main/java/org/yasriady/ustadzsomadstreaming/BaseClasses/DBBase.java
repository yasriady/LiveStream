package org.yasriady.ustadzsomadstreaming.BaseClasses;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.yasriady.ustadzsomadstreaming.BaseClasses.DBHelper.DataBaseHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dedy on 11/4/17.
 */

public class DBBase {

    private Context m_context;
    protected SQLiteDatabase m_db;
    protected DataBaseHelper m_dbHelper; // Note! 171108, ketika m_dbHelper dijadikan local variable didalam baris no 26, maka crash sebab bHelper ini telah menjadi null

    public DBBase(Context context) {
        this.m_context = context;

        try {
            m_dbHelper = new DataBaseHelper(m_context);
            m_db = m_dbHelper.getSQLite();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Cursor getCursorBySQL(String sql) {
        Cursor cursor = m_db.rawQuery(sql, null);
        if (cursor.getCount() > 0)
            cursor.moveToFirst();
        return cursor;
    }

    public void execSQL(String sql) {
        m_db.execSQL(sql);
    }

    public long insert(String table, String nullColumnHack, ContentValues values) {
        long retval = m_db.insert(table, nullColumnHack, values);
        return retval;
    }

    public List<String> makeStringListFromTable(final String tableName, final String column) {
        List<String> list = new ArrayList<String>();
        //String sql = String.format(" SELECT %s FROM %s ORDER BY %s ASC", column, tableName, column);
        String sql = String.format(" SELECT %s FROM %s ", column, tableName, column);
        Cursor cursor = getCursorBySQL(sql);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            final String str = cursor.getString(cursor.getColumnIndex(column));
            list.add(str);
            cursor.moveToNext();
        }
        return list;
    }


}
