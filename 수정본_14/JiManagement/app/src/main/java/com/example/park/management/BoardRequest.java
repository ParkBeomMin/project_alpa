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

public class BoardRequest extends StringRequest {
    final static private String URL = "http://jisung0920.cafe24.com/Url.php";

    private Map<String, String> parameters;

    public BoardRequest(String  UnivURL, String DepartURL, String AlpaURL, Response.Listener<String> listener) {
        super(Request.Method.POST, URL, listener, null);

        parameters = new HashMap<>();
        parameters.put("UnivURL", UnivURL);
        parameters.put("DepartURL",DepartURL);
        parameters.put("AlpaURL",AlpaURL);

    }

    public Map<String, String> getParams() {
        return parameters;
    }
}
