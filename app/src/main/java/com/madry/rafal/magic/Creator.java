package com.madry.rafal.magic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Creator extends AppCompatActivity {


    MyDBHandler myDB;
    Symbol symbol = new Symbol();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creator);


        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);


        myDB = new MyDBHandler(this, null, null, 1);



        Button minusP = (Button) findViewById(R.id.minuspower);
        Button plusP = (Button) findViewById(R.id.pluspower);
        Button minusT = (Button) findViewById(R.id.takeT1);
        Button plusT = (Button) findViewById(R.id.addTT);
        Button minusQ = (Button) findViewById(R.id.takeQ);
        Button plusQ = (Button) findViewById(R.id.addQ);
        final TextView cp = (TextView) findViewById(R.id.creator_p);
        cp.setText("1");
        final TextView ct = (TextView) findViewById(R.id.creator_t);
        cp.setText("1");
        final TextView cq = (TextView) findViewById(R.id.creatorQ);
        cq.setText("1");
        final ImageView csymbol = (ImageView) findViewById(R.id.csymbol);
        csymbol.setImageResource(R.drawable.cl);



        minusP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cp.setText(Integer.toString(Integer.parseInt(cp.getText().toString())-1));
            }
        });

        plusP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cp.setText(Integer.toString(Integer.parseInt(cp.getText().toString())+1));
            }
        });

        minusT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ct.setText(Integer.toString(Integer.parseInt(ct.getText().toString())-1));
            }
        });

        plusT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ct.setText(Integer.toString(Integer.parseInt(ct.getText().toString())+1));
            }
        });

        minusQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cq.setText(Integer.toString(Integer.parseInt(cq.getText().toString())-1));
            }
        });

        plusQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cq.setText(Integer.toString(Integer.parseInt(cq.getText().toString())+1));
            }
        });




        Button exit = (Button) findViewById(R.id.cexit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Creator.this, MainActivity.class);
                startActivity(i);
            }
        });



        Button create = (Button) findViewById(R.id.create);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int pw = Integer.parseInt(cp.getText().toString());
                int ts = Integer.parseInt(ct.getText().toString());
                int qty = Integer.parseInt(cq.getText().toString());
                String clr1= symbol.getPathString();


                myDB.addToken(qty, pw, ts, clr1);

                Intent i = new Intent(Creator.this, MainActivity.class);
                startActivity(i);
            }
        });



    }

    //this method handles clicks on images in the creator showing them on the main card
    //and send it to another class 'Symbol' that returns the drawable path as int value
    //which is also used in the adapter
    public void onSymbolClick (View v){
        ImageView csymbol = (ImageView) findViewById(R.id.csymbol);
        switch(v.getId()){
            case R.id.cl:
                csymbol.setImageResource(R.drawable.cl);
                this.symbol.setPath("R.drawable.cl");
                break;
            case R.id.f:
                csymbol.setImageResource(R.drawable.f);
                this.symbol.setPath("R.drawable.f");
                break;
            case R.id.fi:
                csymbol.setImageResource(R.drawable.fi);
                this.symbol.setPath("R.drawable.fi");
                break;
            case R.id.fm:
                csymbol.setImageResource(R.drawable.fm);
                symbol.setPath("R.drawable.fm");
                break;
            case R.id.fp:
                csymbol.setImageResource(R.drawable.fp);
                symbol.setPath("R.drawable.fp");
                break;
            case R.id.fs:
                csymbol.setImageResource(R.drawable.fs);
                symbol.setPath("R.drawable.fs");
                break;
            case R.id.i:
                csymbol.setImageResource(R.drawable.i);
                symbol.setPath("R.drawable.i");
                break;
            case R.id.im:
                csymbol.setImageResource(R.drawable.im);
                symbol.setPath("R.drawable.im");
                break;
            case R.id.ip:
                csymbol.setImageResource(R.drawable.ip);
                symbol.setPath("R.drawable.ip");
                break;
            case R.id.is:
                csymbol.setImageResource(R.drawable.is);
                symbol.setPath("R.drawable.is");
                break;
            case R.id.m:
                csymbol.setImageResource(R.drawable.m);
                symbol.setPath("R.drawable.m");
                break;
            case R.id.mp:
                csymbol.setImageResource(R.drawable.mp);
                symbol.setPath("R.drawable.mp");
                break;
            case R.id.ms:
                csymbol.setImageResource(R.drawable.ms);
                symbol.setPath("R.drawable.ms");
                break;
            case R.id.p:
                csymbol.setImageResource(R.drawable.p);
                symbol.setPath("R.drawable.p");
                break;
            case R.id.ps:
                csymbol.setImageResource(R.drawable.ps);
                symbol.setPath("R.drawable.ps");
                break;
            case R.id.s:
                csymbol.setImageResource(R.drawable.s);
                symbol.setPath("R.drawable.s");
                break;


        }



    }


}
