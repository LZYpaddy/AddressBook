package com.example.hello;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.Helper.FriendDBHelper;

public class AddFriendActivity extends AppCompatActivity {
    private EditText et1, et2;
    private SQLiteDatabase mDatabase;
    private FriendDBHelper friendDBHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        friendDBHelper = new FriendDBHelper(this);
        super.onCreate(savedInstanceState);
        mDatabase = friendDBHelper.getWritableDatabase();
        setContentView(R.layout.activity_add_friend);
        Button Subtn = findViewById(R.id.submit1);
        Button Delbtn = findViewById(R.id.delete);
        et1 = findViewById(R.id.FriendName2);
        et2 = findViewById(R.id.FriendTel2);
        Subtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Log.i("LZY", "点击增加");

                ContentValues values = new ContentValues();
                values.put("friendName", et1.getText().toString());
                values.put("friendTel", et2.getText().toString());
                FriendDBHelper helper = new FriendDBHelper(getApplicationContext());
                helper.insert(values);
                Intent intent=new Intent(AddFriendActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
