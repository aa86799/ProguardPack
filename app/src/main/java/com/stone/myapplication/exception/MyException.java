package com.stone.myapplication.exception;

/**
 * author : stone
 * time   : 15/4/11 13 06
 * email  : aa86799@163.com
 */
public class MyException extends Exception {

    private String tag;
    public void getEvery() {

    }

    public MyException(String detailMessage, String tag) {
        super(detailMessage);
        this.tag = tag;
        System.out.println("是一地在要");
    }
}
