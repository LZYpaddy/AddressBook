package com.example.hello;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.Helper.FriendDBHelper;


public class MainActivity extends AppCompatActivity {
    ListView listView;
    FriendDBHelper friendDBHelper;
    Button add;
    Button upd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_list);
        add = findViewById(R.id.btnAdd);
        listView = findViewById(R.id.friend_listview);
        this.setTitle("浏览通讯录列表");
        friendDBHelper = new FriendDBHelper(this);
        use_cursor();//查询数据，获取游标

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);//提示对话框
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final long temp = id;
                        Intent it = new Intent(MainActivity.this, UpdateActivity.class);
                        it.putExtra("id",temp);

                        startActivity(it);
                        use_cursor();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, AddFriendActivity.class);
                startActivity(it);
            }
        });

        friendDBHelper.close();
    }

    private void use_cursor() {
        Cursor cursor = friendDBHelper.query();
        String[] from = {"_id", "friendName", "friendTel"};
        int[] to = {R.id.text0, R.id.text1, R.id.text2};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getApplicationContext(), R.layout.activity_row, cursor, from, to);
        listView.setAdapter(adapter);
    }


}
