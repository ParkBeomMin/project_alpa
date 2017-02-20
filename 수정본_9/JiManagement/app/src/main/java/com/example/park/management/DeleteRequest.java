package com.example.park.management;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jisung on 2017. 2. 20..
 */

public class DeleteRequest extends StringRequest {
    final static private String URL = "http://jisung0920.cafe24.com/Delete.php";

    private Map<String, String> parameters;

    public DeleteRequest(String  userID, String board, String title, String date, Response.Listener<String> listener) {
        super(Request.Method.POST, URL, listener, null);

        parameters = new HashMap<>();
        parameters.put("userID", userID);
        parameters.put("board",board);
        parameters.put("title",title);
        parameters.put("date",date);

        Log.d("fdsaf", "res: " + board);
        Log.d("fdsaf", "res: " + userID);
        Log.d("fdsaf", "res: " + title);
        Log.d("fdsaf", "res: " + date);

    }

    public Map<String, String> getParams() {
        return parameters;
    }
}
