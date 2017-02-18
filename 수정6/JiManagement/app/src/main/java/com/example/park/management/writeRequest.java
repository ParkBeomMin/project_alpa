package com.example.park.management;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jisung on 2017. 2. 17..
 */

public class writeRequest extends StringRequest {
    final static private String URL1 = "http://jisung0920.cafe24.com/freeRegit.php";
    private Map<String, String> parameters;

    public writeRequest(String  userID, String freeName, String freeTitle, String freeContent, String freeDate, Response.Listener<String> listener) {
        super(Request.Method.POST, URL1, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
        parameters.put("freeName", freeName);
        parameters.put("freeTitle",freeTitle);
        parameters.put("freeContent",freeContent);
        parameters.put("freeDate",freeDate);

    }

    public Map<String, String> getParams() {
        return parameters;
    }
}
