package com.example.park.management;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Park on 2017-01-11.
 */

public class RegisterRequest2 extends StringRequest {

    final static private String URL = "http://jisung0920.cafe24.com/Confirm.php";
    private Map<String, String> parameters;

    public RegisterRequest2(String  confirm, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("confirm", confirm);
    }

    public Map<String, String> getParams() {
        return parameters;
    }


}

