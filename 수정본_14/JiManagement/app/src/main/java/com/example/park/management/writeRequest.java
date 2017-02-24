package com.example.park.management;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jisung on 2017. 2. 17..
 */

public class writeRequest extends StringRequest {
    final static private String URL = "http://jisung0920.cafe24.com/writeRegister.php";

    private Map<String, String> parameters;

    public writeRequest(String  userID,  String Title, String Content, String Date,int i, Response.Listener<String> listener) {
        super(Request.Method.POST, URL, listener, null);

        parameters = new HashMap<>();
        parameters.put("userID", userID);
        parameters.put("Title",Title);
        parameters.put("Content",Content);
        parameters.put("Date", Date);
        Log.d("fdsaf", "res: " + i);
        if(i==0){
            parameters.put("boardNum","free");
        }
        else if(i==1){
            parameters.put("boardNum","anony");

        }
        else
            parameters.put("boardNum","tip");

    }

    public Map<String, String> getParams() {
        return parameters;
    }
}
