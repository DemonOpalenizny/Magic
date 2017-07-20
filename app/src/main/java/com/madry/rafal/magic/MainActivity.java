package com.madry.rafal.magic;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    MyDBHandler myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //sets the activity to fullscreen without action bar
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);


        myDB = new MyDBHandler(this, null, null, 1);

        //life counter
        int hp = myDB.getLife();
        final TextView lf = (TextView) findViewById(R.id.textView);
        lf.setRotation(180);
        lf.setText(Integer.toString(hp));
        Button plus = (Button) findViewById(R.id.button);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myDB.addLife(Integer.parseInt(lf.getText().toString()));
                int hp = myDB.getLife();
                lf.setText(Integer.toString(hp));



            }
        });
        Button minus = (Button) findViewById(R.id.button2);
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDB.takeLife(Integer.parseInt(lf.getText().toString()));
                int hp = myDB.getLife();
                lf.setText(Integer.toString(hp));
            }
        });
        //life counter ends

        //gridview for tokens along with it's adapter

        GridView gv = (GridView) findViewById(R.id.gridView);
        MyCursorAdapter mca = new MyCursorAdapter(this, myDB.getCursor(), 0);
        gv.setAdapter(mca);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int tap = myDB.getTap(id);

                if (tap == 0) {
                    myDB.tap(id, 1);
                    recreate();
                } else {
                    myDB.tap(id, 0);
                    recreate();
                }

            }
        });

        //on an item long click an alert dialog appears (called by the method getOptions(id))
        //the menu that shows up lets us edit a single token's statistics
        gv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                getOptions(id);

                return true;
            }
        });
        //button for a new token, puts us through to another activity
        Button addToken = (Button) findViewById(R.id.button4);
        addToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Creator.class);
                startActivity(i);
            }
        });



    }

    //creates alert dialog that lets us edit tokens
    public void getOptions (long id) {

        final long id2 = id;
        myDB = new MyDBHandler(MainActivity.this, null, null, 0);
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.long_dialog, null);


        final TextView toughness = mView.findViewById(R.id.toughness);
        toughness.setText(Integer.toString(myDB.getToughness(id)));
        final TextView power = mView.findViewById(R.id.power);
        power.setText(Integer.toString(myDB.getPower(id)));
        Button addT = mView.findViewById(R.id.addT);
        Button takeT = mView.findViewById(R.id.takeT);
        Button addP = mView.findViewById(R.id.addP);
        Button takeP = mView.findViewById(R.id.takep);
        Button copy = mView.findViewById(R.id.copy);
        Button destroy = mView.findViewById(R.id.destroy);
        Button exit = mView.findViewById(R.id.exit);
        mBuilder.setView(mView);
        AlertDialog dialog = mBuilder.create();

        dialog.show();




        addP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDB.addPower(id2, Integer.parseInt(power.getText().toString()) );
                power.setText(Integer.toString(myDB.getPower(id2)));
            }
        });
        takeP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDB.takePower(id2, Integer.parseInt(power.getText().toString()) );
                power.setText(Integer.toString(myDB.getPower(id2)));
            }
        });

        addT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDB.addToughness(id2, Integer.parseInt(toughness.getText().toString()));
                toughness.setText(Integer.toString(myDB.getToughness(id2)));
            }
        });
        takeT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDB.takeToughness(id2, Integer.parseInt(toughness.getText().toString()));
                toughness.setText(Integer.toString(myDB.getToughness(id2)));
            }
        });



        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDB.copyToken(id2);
                recreate();
            }
        });


        destroy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDB.deleteToken(id2);
                recreate();
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });





    }



}

