package com.example.lenovo.reteivingdata;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ChatUserAdapter extends ArrayAdapter<String> {

    private Activity context;
    private List<String> userList;
    private int ilayout;

    public ChatUserAdapter(Activity context, int layout, List<String> userList) {
        super(context, layout, userList);
        this.context = context;
        this.userList = userList;
        this.ilayout = layout;
    }

    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder {
        public TextView tvEmail;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        View rowView = convertView;
        if (rowView == null) {

            LayoutInflater layoutInflater = context.getLayoutInflater();
            rowView = layoutInflater.inflate(ilayout, null, true);
            viewHolder = new ViewHolder();
            viewHolder.tvEmail = (TextView) rowView.findViewById(R.id.textMessage);
            rowView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) rowView.getTag();
        }

        String item = userList.get(position);
        viewHolder.tvEmail.setText(item);
        return rowView;
    }
}