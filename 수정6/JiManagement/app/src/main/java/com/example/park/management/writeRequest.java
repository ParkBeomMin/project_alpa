package com.example.park.management;

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
    final static private String URLfree = "http://jisung0920.cafe24.com/freeRegit.php";
    final static private String URLanony = "http://jisung0920.cafe24.com/anonyRegit.php";
    final static private String URLtip = "http://jisung0920.cafe24.com/tipRegit.php";
    private Map<String, String> parameters;

    public writeRequest(String  userID, String freeName, String freeTitle, String freeContent, Date date, Response.Listener<String> listener) {
        super(Request.Method.POST, URLfree, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
        parameters.put("freeName", freeName);
        parameters.put("freeTitle",freeTitle);
        parameters.put("freeContent",freeContent);
        parameters.put("freeDate", String.valueOf(date));
    }
    public writeRequest(String userID,String Title, String Content, String Date,Response.Listener<String> listener){
        super(Request.Method.POST, URLanony, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
        parameters.put("anonyTitle", Title);
        /*parameters.put("anonyContent",freeTitle);
        parameters.put("freeContent",freeContent);
        parameters.put("freeDate",freeDate);*/
    }
    public writeRequest(String userID,String Name, String Title, String Content, String Date, int i, Response.Listener<String> listener){
        super(Request.Method.POST, URLfree, listener, null);
        parameters = new HashMap<>();
        //implenment
    }
// 생성자 오버라이딩
    public Map<String, String> getParams() {
        return parameters;
    }
}
