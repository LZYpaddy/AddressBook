package com.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.entity.Friend;

public class FriendDBHelper extends SQLiteOpenHelper {
    private static final String TAG = "FriendDBHelper";
    private static final String DB_NAME = "Friend.db";
    private static final int DB_VERSION = 2;
    private static final String TABLE_NAME = "friendinfo";
    private static FriendDBHelper fHelper = null;
    private SQLiteDatabase fDB = null;

    public FriendDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public FriendDBHelper(Context context, int version) {
        super(context, DB_NAME, null, version);
    }

    public static FriendDBHelper getInstance(Context context, int version) {
        if (version > 0 && fHelper == null) {
            fHelper = new FriendDBHelper(context, version);

        } else if (fHelper == null) {
            fHelper = new FriendDBHelper(context);
        }
        return fHelper;
    }

    public SQLiteDatabase openReadLink() {
        if (fDB == null || fDB.isOpen() != true) {
            fDB = fHelper.getReadableDatabase();
        }
        return fDB;
    }

    public SQLiteDatabase openWriteLink() {
        if (fDB == null || fDB.isOpen() != true) {
            fDB = fHelper.getWritableDatabase();
        }
        return fDB;
    }

    public void closeLink() {
        if (fDB != null || fDB.isOpen() == true) {
            fDB.close();
            fDB = null;
        }
    }

    public String getDbName() {
        if(fHelper!=null){
            return fHelper.getDatabaseName();
        }else
        return DB_NAME;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG,"onCreate");
        this.fDB=db;
        db.execSQL("CREATE Table friendinfo (_id integer primary key autoincrement, friendName text, friendTel text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("drop table if exists friendinfo");
    }
    public void insert(ContentValues values)
    {
        SQLiteDatabase db=getWritableDatabase();
        // 对读和写操作的方法
        // 如果当我们二次调用这个数据库方法,他们调用的是同一个数据库对象,在这里的方法创建的数据调用对象是用的同一个对象

        db.insert(TABLE_NAME,null,values);


    }

    public Cursor query() {
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.query(TABLE_NAME,null,null,null,null,null,null);
        return cursor;
    }
//删除
    public int  Del(int id) {

        if (fDB==null){
            fDB=getWritableDatabase();
        }
        return  fDB.delete(TABLE_NAME,"_id=?",new String[]{String.valueOf(id)});
    }
    public int Update(int id, ContentValues values){
        if (fDB==null){
            fDB=getWritableDatabase();
        }
        return  fDB.update(TABLE_NAME,values,"_id=?",new String[]{String.valueOf(id)});
    }
    public Friend FindFriendById(int id){
        if (fDB==null){
            fDB=getWritableDatabase();
        }
        Cursor cursor=fDB.query(TABLE_NAME, null, "_id=?",new String[]{String.valueOf(id)}, null, null, null);
        Friend friend=new Friend();
        while (cursor.moveToNext()){
            friend.setName(cursor.getString(cursor.getColumnIndex("friendName")));
            friend.setTel(cursor.getString(cursor.getColumnIndex("friendTel")));
        }
        return friend;
    }
}
