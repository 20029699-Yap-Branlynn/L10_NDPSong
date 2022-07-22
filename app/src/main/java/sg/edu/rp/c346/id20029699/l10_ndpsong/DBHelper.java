package sg.edu.rp.c346.id20029699.l10_ndpsong;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Song.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_SONG = "Song";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_SONG_TITLE = "title";
    private static final String COLUMN_SONG_SINGER = "singer";
    private static final String COLUMN_SONG_YEAR = "year";
    private static final String COLUMN_SONG_STARS = "stars";
    private int getStar;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String createSongTableSql = "CREATE TABLE " + TABLE_SONG + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_SONG_TITLE + " TEXT,"
                + COLUMN_SONG_SINGER + " TEXT,"
                + COLUMN_SONG_YEAR + " INTEGER,"
                + COLUMN_SONG_STARS + " INTEGER)";
        db.execSQL(createSongTableSql);
        Log.i("info", "created tables");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SONG);
        onCreate(db);
    }

    public long insertSong(String title, String singer, int year, int stars) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SONG_TITLE, title);
        values.put(COLUMN_SONG_SINGER, singer);
        values.put(COLUMN_SONG_YEAR, year);
        values.put(COLUMN_SONG_STARS, stars);

        long result = db.insert(TABLE_SONG, null, values);
        db.close();
        Log.d("SQL Insert", "ID:" + result); //id returned, shouldn’t be -1
        return result;

    }

    public ArrayList<Song> getAllSongs() {
        ArrayList<Song> al = new ArrayList<Song>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns= {COLUMN_ID, COLUMN_SONG_TITLE, COLUMN_SONG_SINGER, COLUMN_SONG_YEAR,COLUMN_SONG_STARS};
        Cursor cursor = db.query(TABLE_SONG, columns, null, null,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singer = cursor.getString(2);
                int year = cursor.getInt(3);
                int stars = cursor.getInt(4);

                Song note = new Song(id, title, singer, year, stars);
                al.add(note);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return al;
    }

    public ArrayList<Song> get5stars(int getStar) {
        ArrayList<Song> al = new ArrayList<Song>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns= {COLUMN_ID, COLUMN_SONG_TITLE, COLUMN_SONG_SINGER, COLUMN_SONG_YEAR, COLUMN_SONG_STARS};
        String filter = COLUMN_SONG_STARS + "= 5";
        //String[] a = {R.id.radioButton5 + ""};
        Cursor cursor = db.query(TABLE_SONG, columns, filter, null,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singer = cursor.getString(2);
                int year = cursor.getInt(3);
                int stars = cursor.getInt(4);

                Song note = new Song(id, title,singer, year, stars);
                al.add(note);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return al;
        //return getStar;
    }

    public ArrayList<Song> getYears(int yearFilter) {
        ArrayList<Song> al = new ArrayList<Song>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns= {COLUMN_ID, COLUMN_SONG_TITLE, COLUMN_SONG_SINGER, COLUMN_SONG_YEAR, COLUMN_SONG_STARS};
        String filter = COLUMN_SONG_YEAR + "= ?";
        String[] a = {String.valueOf(yearFilter)};
        Cursor cursor = db.query(TABLE_SONG, columns, filter, a,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singer = cursor.getString(2);
                int year = cursor.getInt(3);
                int stars = cursor.getInt(4);

                Song note = new Song(id, title,singer, year, stars);
                al.add(note);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return al;
        //return getStar;
    }

    public int updateSong(Song edit){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SONG_TITLE, edit.getTitle());
        values.put(COLUMN_SONG_SINGER, edit.getSinger());
        values.put(COLUMN_SONG_YEAR, edit.getYear());
        values.put(COLUMN_SONG_STARS, edit.getStars());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(edit.getId())};
        int result = db.update(TABLE_SONG, values, condition, args);
        db.close();
        return result;
    }

    public int deleteSong(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_SONG, condition, args);
        db.close();
        return result;
    }
}

