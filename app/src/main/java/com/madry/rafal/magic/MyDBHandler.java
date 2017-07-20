package com.madry.rafal.magic;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

// All tokens are stored in the Tokens.db database. Here's the handler
public class MyDBHandler extends SQLiteOpenHelper {

    private static final String TAG = "DBhelper";



    private static final String DATABASE_NAME = "Tokens.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_TOKENS = "tokens";
    public static final String TABLE_LIFE = "life";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_COLOUR = "colour";
    public static final String COLUMN_POWER = "power";
    public static final String COLUMN_TS = "ts";
    public static final String COLUMN_TAP = "tap";
    public static final String COLUMN_LIFE = "Life";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }
    // tables are created
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query1 = "CREATE TABLE " +
                TABLE_TOKENS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TAP + " INTEGER," +
                COLUMN_COLOUR + " TEXT, " +
                COLUMN_POWER + " INTEGER, " +
                COLUMN_TS+ " INTEGER)";

        String query2 = "CREATE TABLE " +
                TABLE_LIFE + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_LIFE + " INTEGER)";
        db.execSQL(query1);
        db.execSQL(query2);


        //life points are set to be 20 by default, so the value is initiated when creating the db
        int hp = 20;
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_LIFE, hp);
        db.insert(TABLE_LIFE, null, cv);
        Log.d(TAG, "dodalo zycie");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TOKENS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LIFE);
        onCreate(db);
    }

    //insert a token or tokens
    public void addToken(int qty, int pw, int ts, String clr) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_POWER, pw);
        cv.put(COLUMN_TS, ts);
        cv.put(COLUMN_COLOUR, clr);
        cv.put(COLUMN_TAP, 0);

        for (int i=1; i <= qty; i++ ){
            db.insert(TABLE_TOKENS, null, cv);
        }
        db.close();

    }
    //methods below manage token stats
    public void copyToken(long id){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO " + TABLE_TOKENS + "(" + COLUMN_COLOUR + ", " + COLUMN_POWER + ", " + COLUMN_TS + ")" +
                " SELECT " + COLUMN_COLOUR + ", " + COLUMN_POWER + ", " + COLUMN_TS  +
                " FROM " + TABLE_TOKENS +
                " WHERE _id = " + id);
        Log.d(TAG, Long.toString(id));
        db.close();
    }


    public void deleteToken(long i) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_TOKENS + " WHERE " + COLUMN_ID  + " = '" + i + "'");
        db.close();
    }

    public void addPower (long id, int power){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        Log.d(TAG, "weszlo  " + id);
        int power2 = power+1;
        cv.put(COLUMN_POWER, power2);
        db.update(TABLE_TOKENS, cv, "_id=" + id, null);
        db.close();
    }
    public void addToughness (long id, int toughness){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        int ts2 = toughness+1;
        cv.put(COLUMN_TS, ts2);
        db.update(TABLE_TOKENS, cv, "_id=" + id, null);
        db.close();
    }

    public void takePower (long id, int power){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        int power2 = power-1;
        cv.put(COLUMN_POWER, power2);
        db.update(TABLE_TOKENS, cv, "_id=" + id, null);
        db.close();
    }
    public void takeToughness (long id, int toughness){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        int ts2 = toughness-1;
        cv.put(COLUMN_TS, ts2);
        db.update(TABLE_TOKENS, cv, "_id=" + id, null);
        db.close();
    }

    public int getPower (long id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_POWER + " FROM " + TABLE_TOKENS + " WHERE _id = " + id, null);
        int power;
        cursor.moveToFirst();
        power = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_POWER));
        return power;
    }

    public int getToughness(long id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_TS + " FROM " + TABLE_TOKENS + " WHERE _id = " + id, null);
        int ts;
        cursor.moveToFirst();
        ts = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_TS));
        return ts;
    }

    public Cursor getCursor(){

        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_TOKENS, null);
        return cursor;

    }

    public void tap (long id, int tap){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        int tapp = tap;
        cv.put(COLUMN_TAP, tapp);
        db.update(TABLE_TOKENS, cv, "_id=" + id, null);
        db.close();
    }

    public int getTap(long id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_TAP + " FROM " + TABLE_TOKENS + " WHERE _id = " + id, null);
        int ts;
        cursor.moveToFirst();
        ts = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_TAP));
        return ts;
    }

    //methods below manage life counter
    public void addLife (int hp){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        int hp1 = hp+1;
        cv.put(COLUMN_LIFE, hp1);
        db.update(TABLE_LIFE, cv, "_id=1", null);
        db.close();
    }
    public void takeLife (int hp){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        int hp1 = hp-1;
        cv.put(COLUMN_LIFE, hp1);
        db.update(TABLE_LIFE, cv, "_id=1", null);
        db.close();
    }
    public int getLife (){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_LIFE + " FROM " + TABLE_LIFE + " WHERE _id = 1", null);
        int hp;
        cursor.moveToFirst();
        hp = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_LIFE));
        return hp;
    }

}
