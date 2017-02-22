package com.example.park.management;

import android.os.Handler;

/**
 * Created by jisung on 2017. 2. 22..
 */

public class ServiceThread extends Thread {
    Handler handler;
    boolean isrun = true;

    public ServiceThread(Handler handler) {
        this.handler = handler;
    }
    public void stopForever(){
        synchronized (this){
            this.isrun =false;
        }
    }
    public void run(){
        while(isrun){
            handler.sendEmptyMessage(0);
            try{
                Thread.sleep(10000);//30초에 한번씩
            }catch (Exception e){}
        }
    }
}
