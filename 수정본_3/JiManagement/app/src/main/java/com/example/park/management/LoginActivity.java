package com.example.park.management;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private  long lastTimeBackPressed;
    String autoId, autoPw;
    boolean autoCh;

    boolean autochecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText idText = (EditText) findViewById(R.id.idText);
        final EditText passwordText = (EditText) findViewById(R.id.passwordText);

        final Button loginButton = (Button) findViewById(R.id.loginbutton);
        final Button registerButton = (Button) findViewById(R.id.registerbutton);

        final CheckBox autocheckBox = (CheckBox) findViewById(R.id.autocheckBox);

        SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);

        autoId = auto.getString("userId", null);
        autoPw = auto.getString("userPw", null);
        autoCh = auto.getBoolean("autoLogin", false);
//        SharedPreferences auto = getSharedPreferences("auto", LoginActivity.MODE_PRIVATE);

//        autoId = auto.getString("userId", null);
//        autoPw = auto.getString("userPW", null);
//        autoCh = auto.getBoolean("autoLogin", false);



        if(autoId != null && autoPw != null && autoCh == true) {
            String userID = autoId;
            String userPassword = autoPw;
            Response.Listener<String> responesListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        boolean success = jsonResponse.getBoolean("success");
                        if (success) {
                            String userID = jsonResponse.getString("userID");
                            String userPassword = jsonResponse.getString("userPassword");
                            Toast.makeText(LoginActivity.this, "자동로그인되었습니다.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("userID", userID);
                            intent.putExtra("userPassword", userPassword);
                            LoginActivity.this.startActivity(intent);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    Toast.makeText(LoginActivity.this, "자동로그인되었습니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    LoginActivity.this.startActivity(intent);
                    finish();
                }

            };

            LoginRequest loginRequest = new LoginRequest(userID, userPassword, responesListener);
            RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
            queue.add(loginRequest);
        }
        else  {

            loginButton.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {
                    String userID = idText.getText().toString();
                    String userPassword = passwordText.getText().toString();
                    Response.Listener<String> responesListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");
                                if (success) {
                                    String userID = jsonResponse.getString("userID");
                                    String userPassword = jsonResponse.getString("userPassword");

//                                    SharedPreferences auto = getSharedPreferences("auto", LoginActivity.MODE_PRIVATE);
//                                    SharedPreferences.Editor autoLogin = auto.edit();
                                    SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
                                    SharedPreferences.Editor autoLogin = auto.edit();

                                    autoLogin.putString("userId", idText.getText().toString());
                                        autoLogin.putString("userPw", passwordText.getText().toString());
                                    if(autochecked) {
                                        autoLogin.putBoolean("autoLogin", true);
                                    }
                                    else {
                                        autoLogin.putBoolean("autoLogin", false);
                                    }
                                        autoLogin.commit();


                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    intent.putExtra("userID", userID);
                                    intent.putExtra("userPassword", userPassword);
                                    LoginActivity.this.startActivity(intent);


                                } else {
//                                    SharedPreferences auto = getSharedPreferences("auto", LoginActivity.MODE_PRIVATE);
//                                    SharedPreferences.Editor autoLogin = auto.edit();
                                    SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
                                    SharedPreferences.Editor autoLogin = auto.edit();

                                    autoLogin.clear();
                                    autoLogin.commit();

                                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                    builder.setMessage("로그인에 실패")
                                            .setNegativeButton("다시시시도", null)
                                            .create()
                                            .show();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    LoginRequest loginRequest = new LoginRequest(userID, userPassword, responesListener);
                    RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                    queue.add(loginRequest);
                }
            });

        }
            registerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                    LoginActivity.this.startActivity(registerIntent);
                }
            });

        //////////////////////////////////////////
//        pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
//        editor = pref.edit();
//        editor.putString("id", String.valueOf(idText));
//        editor.putString("pw", String.valueOf(passwordText));
//        editor.commit();
//        ///////////////////////////////////////////
//        ////////////////////////////////////////////////////////////////////////////////
        autocheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
            SharedPreferences.Editor autoLogin = auto.edit();

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked) {
                    autoLogin.putBoolean("autoLogin", true);
                    autochecked = true;
                } else {
                    // if unChecked, removeAll
                    autoLogin.putBoolean("autoLogin", false);
                    autochecked = false;
                    autoLogin.clear();
                    autoLogin.commit();
                }
            }
        });
//
//
//        if(loginChecked) {
//            // if autoLogin Checked, save values
//            editor.putString("id", String.valueOf(idText));
//            editor.putString("pw", String.valueOf(passwordText));
//            editor.putBoolean("autoLogin", true);
//            editor.commit();
//        }
//        // goto mainActivity
//
//     else {
//        Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
//        // goto LoginActivity
//    }



////////////////////////////////////////////////////////////////////////////



    }

    public void onBackPressed() {
        if(System.currentTimeMillis() - lastTimeBackPressed < 1500) {
            finishAffinity();
            return;
        }

        Toast.makeText(this, "뒤로 버튼을 한번 더 누르면 종료됩니다", Toast.LENGTH_SHORT).show();
        lastTimeBackPressed = System.currentTimeMillis();
    }
}


