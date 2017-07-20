package com.madry.rafal.magic;


//this class handles references to resources
public class Symbol {

    int path;
    String pathString = "";


    public void setPath (String s){
        this.pathString = s;
        switch(s){
            case "R.drawable.cl":
                this.path = R.drawable.cl;
                break;
            case "R.drawable.f":
                this.path = R.drawable.f;
                break;
            case "R.drawable.fi":
                this.path = R.drawable.fi;
                break;
            case "R.drawable.fm":
                this.path = R.drawable.fm;
                break;
            case "R.drawable.fp":
                this.path = R.drawable.fp;
                break;
            case "R.drawable.fs":
                this.path = R.drawable.fs;
                break;
            case "R.drawable.i":
                this.path = R.drawable.i;
                break;
            case "R.drawable.im":
                this.path = R.drawable.im;
                break;
            case "R.drawable.ip":
                this.path = R.drawable.ip;
                break;
            case "R.drawable.is":
                this.path = R.drawable.is;
                break;
            case "R.drawable.m":
                this.path = R.drawable.m;
                break;
            case "R.drawable.mp":
                this.path = R.drawable.mp;
                break;
            case "R.drawable.ms":
                this.path = R.drawable.ms;
                break;
            case "R.drawable.p":
                this.path = R.drawable.p;
                break;
            case "R.drawable.ps":
                this.path = R.drawable.ps;
                break;
            case "R.drawable.s":
                this.path = R.drawable.s;
                break;
            default:
                this.path = R.drawable.is;
                this.pathString = "R.drawable.is";
                break;

        }
    }

    public int getPath(){
        return this.path;
    }

    public String getPathString (){
        return this.pathString;
    }


}
