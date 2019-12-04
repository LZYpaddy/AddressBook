package com.example.hello;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.entity.Friend;

import java.util.List;

public class FriendAdapter extends ArrayAdapter {
    private int resourceId;
    private Context context;
    private List<Friend> texts;
    public FriendAdapter(Context context, int resource, List<Friend> objects) {
        super(context, resource,objects);
        this.context=context;
        resourceId = resource;
        this.texts=objects;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView ==null){
            convertView=LayoutInflater.from(context).inflate(R.layout.activity_row,null);
            ItemViewCache itemViewCache =new ItemViewCache();
            itemViewCache.mTextView1=(TextView) convertView.findViewById(R.id.text1);
            itemViewCache.mTextView2=(TextView) convertView.findViewById(R.id.text2);
//            itemViewCache.Bupd=(Button) convertView.findViewById(R.id.btnUpD);
            convertView.setTag(itemViewCache);
            itemViewCache.Bupd.setTag(position);//获取索引
            itemViewCache.mTextView1.setText(texts.get(position).getName());
            itemViewCache.mTextView2.setText(texts.get(position).getTel());

        }
        Friend f = (Friend) getItem(position);//获取当前项的实例
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        ((TextView) view.findViewById(R.id.text1)).setText(f.getName());
       ((TextView) view.findViewById(R.id.text2)).setText(f.getTel());
        return view;
    }
    private class ItemViewCache{
        public TextView mTextView1;
        public TextView mTextView2;
        public Button Bupd;

    }
}
