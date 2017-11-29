package org.yasriady.livestream.BaseClasses;

import android.database.Cursor;

import org.yasriady.livestream.MyDB;

import java.util.List;

/**
 * Created by dedy on 11/17/17.
 */

public class PrefBase {

    private MyDB m_db;

    public PrefBase(MyDB db) {
        m_db = db;
    }

    public String get(final String key, final String defValue) {
        String str = getConfig(key, defValue);
        if (str.equals(defValue)) {
            set(key, defValue);
        }
        return str;
    }

    private String getConfig(final String key, final String defValue) {
        // jika belum ada didalam tabel config, maka create secara otomatis

        String result = defValue;
        String where = String.format(" key='%s' ", key);
        String sql = String.format(" SELECT * FROM config WHERE %s  ", where);

        Cursor cursor = m_db.getCursorBySQL(sql);
        cursor.moveToFirst();

        if (!(cursor.moveToFirst()) || cursor.getCount() == 0) {
            //cursor is empty
        } else {
            result = cursor.getString(cursor.getColumnIndex("value"));
        }

        return result;
    }

    public double get(final String key, final double defValue) {

        String value = String.valueOf(defValue);
        String str = get(key, value);
        double result = 0;

        try {
            result = Double.parseDouble(str);
        } catch (NumberFormatException ex) {
            // Ddy: Please do exception handling here
        }

        return result;
    }

    public void set(final String key, String value) {
        if (!prefExists(key)) {
            prefInsert(key, value);
        } else {
            prefUpdate(key, value);
        }
    }

    public void set(final String key, final double value) {
        String str = String.valueOf(value);
        set(key, str);
    }

    private boolean prefExists(final String key) {

        //String where = String.format("key='%s'", key);
        //String sql = String.format(" SELECT COUNT(*) FROM config WHERE %s ", where);

        final String MYVAL = "MYVAL";
        String myVal = getConfig(key, MYVAL);
        if (myVal.equals(MYVAL)) // not exists
            return false;
        else
            return true;
    }

    private void prefUpdate(String key, String value) {
        String sql = String.format(" UPDATE config SET value='%s' WHERE key='%s'  ", value, key);
        m_db.execSQL(sql);
    }

    private void prefInsert(String key, String value) {

        String columns = "key, value";
        String values = String.format(" '%s', '%s' ", key, value);
        String sql = String.format(" INSERT INTO config( %s ) VALUES( %s ) ", columns, values);
        m_db.execSQL(sql);


//        ContentValues values = new ContentValues();
//        values.put(key, value);
//        m_db.insert("config", null, values);

    }

    public List<String> makeStringListFromTable(final String tableName, final String column) {
        return m_db.makeStringListFromTable(tableName, column);
    }


}
