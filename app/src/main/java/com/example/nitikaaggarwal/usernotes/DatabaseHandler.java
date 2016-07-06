package com.example.nitikaaggarwal.usernotes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vinayaaggarwal on 05/07/16.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "notesManager";
    private static final String TABLE_NOTES = "notes";
    private static final String KEY_TITLE = "title";
    private static final String KEY_DESCRIPTION = "description";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_NOTES_TABLE = "CREATE TABLE " + TABLE_NOTES + "(" + KEY_TITLE + " STRING PRIMARY KEY ," + KEY_DESCRIPTION + " TEXT" + ")";

        db.execSQL(CREATE_NOTES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
        onCreate(db);
    }


    public void addNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, note.getTitle());
        values.put(KEY_DESCRIPTION, note.getDescription());

        db.insert(TABLE_NOTES, null, values);
        db.close();
    }

    public Note getNote(String title) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NOTES, new String[]{KEY_TITLE, KEY_DESCRIPTION}, KEY_TITLE + "=?",
                new String[]{String.valueOf(title)}, null, null, null, null);
        Note note;

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            note = new Note((cursor.getString(1)));
        } else {
            return null;
        }
        return note;

    }

    public List<Note> getAllNotess() {
        List<Note> noteList = new ArrayList<Note>();
        String selectQuery = "SELECT  * FROM " + TABLE_NOTES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Note note = new Note();
                note.setTitle(cursor.getString(0));
                note.setDiscription(cursor.getString(1));
                noteList.add(note);
            } while (cursor.moveToNext());
        }
        return noteList;
    }


    // Updating single Note
    public int updateNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, note.getTitle());
        values.put(KEY_DESCRIPTION, note.getDescription());

        // updating row
        return db.update(TABLE_NOTES, values, KEY_TITLE + " = ?",
                new String[]{String.valueOf(note.getTitle())});
    }
}