package com.madry.rafal.magic;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import static com.madry.rafal.magic.MyDBHandler.COLUMN_COLOUR;
import static com.madry.rafal.magic.MyDBHandler.COLUMN_POWER;
import static com.madry.rafal.magic.MyDBHandler.COLUMN_TAP;
import static com.madry.rafal.magic.MyDBHandler.COLUMN_TS;


//cursor adapter to populate the grid view in main activity

public class MyCursorAdapter extends CursorAdapter {



    private LayoutInflater cursorInflater;

    public MyCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        cursorInflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return cursorInflater.inflate(R.layout.token, parent, false);
    }



    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        Symbol symbol = new Symbol();
        String sciezka = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COLOUR));
        symbol.setPath(sciezka);

        int path = symbol.getPath();


        ImageView clr = view.findViewById(R.id.imageView);
        clr.setImageResource(path);

        int tap = cursor.getInt((cursor.getColumnIndexOrThrow(COLUMN_TAP)));
        if (tap == 0){
            view.setRotation(0);
        } else {
            view.setRotation(20);
        }


        TextView stats = view.findViewById(R.id.textView2);
        String p = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_POWER))+
                "/" + cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TS));
        stats.setText(p);




    }
}
