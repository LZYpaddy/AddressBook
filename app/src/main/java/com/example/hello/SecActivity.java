package com.example.hello;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class SecActivity extends AppCompatActivity {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    EditText user;
    EditText pass;

    public void init() {
        user = findViewById(R.id.UserName);
        pass = findViewById(R.id.PassWord);
        //        获取 SharedPreferences实例对象
        preferences = getSharedPreferences("UserAndPassword", Context.MODE_PRIVATE);
        //        用SharedPreferences实例对象的edit（）方法获取SharedPreferences.Editor编辑对象
        editor = preferences.edit();
        System.out.println("username"+preferences.getString("UserName", ""));
        user.setText(preferences.getString("UserName", null));
        pass.setText(preferences.getString("PassWord", null));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec);
        init();


        final CheckBox checkBox = findViewById(R.id.remember);
        Button denglu = findViewById(R.id.denglu);
        denglu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("1", "onClick:点击登录 ");
                if (checkBox.isChecked()) {
                    String UserName = user.getText().toString();
                    String PassWord = pass.getText().toString();
                    editor.putString("UserName", UserName);
                    editor.putString("PassWord", PassWord);
                    editor.commit();

                }
                Intent it = new Intent(SecActivity.this, MainActivity.class);
                startActivity(it);
            }
        });

    }
}
