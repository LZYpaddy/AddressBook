package com.example.hello;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.Helper.FriendDBHelper;
import com.entity.Friend;

public class UpdateActivity extends AppCompatActivity {
    private EditText et1, et2;

    private FriendDBHelper friendDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        friendDBHelper = new FriendDBHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Intent it=getIntent();
        final int id=(int) it.getLongExtra("id",0);
        Friend friend=friendDBHelper.FindFriendById(id);
        et1 = findViewById(R.id.FriendName2);
        et2 = findViewById(R.id.FriendTel2);
        et1.setText(friend.getName());/////读取数据库中的名字
        et2.setText(friend.getTel());/////读取数据库中的电话

        Button Updbtn = findViewById(R.id.submit2);
        Button Delbtn=findViewById(R.id.delete);
        et1 = findViewById(R.id.FriendName2);
        et2 = findViewById(R.id.FriendTel2);
        Updbtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Log.i("1", "点击修改");

                ContentValues values = new ContentValues();
                values.put("friendName", et1.getText().toString());
                values.put("friendTel", et2.getText().toString());
                friendDBHelper.Update(id,values);
                Intent intent=new Intent(UpdateActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        Delbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                friendDBHelper.Del(id);
                Intent intent=new Intent(UpdateActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });


    }
}