package com.service;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.Helper.FriendDBHelper;
import com.entity.Friend;

import java.util.ArrayList;
import java.util.List;

public class FriendService {
    private FriendDBHelper friendDBHelper;

    public FriendService(Context context)
    {
        friendDBHelper = new FriendDBHelper(context);
    }
    /**
     * 添加friend
     *
     * @param f
     */
    public void addFriend(Friend f)
    {
        // 对读和写操作的方法
        // 如果当我们二次调用这个数据库方法,他们调用的是同一个数据库对象,在这里的方法创建的数据调用对象是用的同一个对象
        SQLiteDatabase db = friendDBHelper.getWritableDatabase();
        db.execSQL("insert into friendinfo(friendName,friendTel) values(?,?)", new Object[]
                { f.getName(), f.getTel()});
    }

    /**
     * 修改friend
     *
     * @param f
     */
    public void modifyFriend(Friend f)
    {
        SQLiteDatabase db = friendDBHelper.getWritableDatabase();
        db.execSQL("update friendinfo set friendName=? where friendTel=?", new Object[]
                { f.getName(), f.getTel() });
    }

    /**
     * 删除Friend
     *
     */
    public void deleteFriend(Integer id)
    {
        SQLiteDatabase db = friendDBHelper.getWritableDatabase();
        db.execSQL("delete from friendinfo where _id=?", new Object[]
                { id.toString() });
    }

    public List<Friend> SelectAllFriend(){
        List<Friend> Friends =new ArrayList<Friend>();
        SQLiteDatabase db = friendDBHelper.getWritableDatabase();
        Cursor cursor =db.rawQuery("select friendName,friendTel from friendinfo",null);
        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("friendName"));
            String tel = cursor.getString(cursor.getColumnIndex("friendTel"));
            Friend f=new Friend(name,tel);
            Friends.add(f);
        }
        return Friends;
          }
}
