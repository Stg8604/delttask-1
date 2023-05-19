package com.example.deltaproject1;

public class Db {
    public static Db getInstance() {
        if(null!=instance) {
            return instance;
        }else{
            instance=new Db();
            return instance;
        }
    }

    private static Db instance;
    public Db(){

    }
}
