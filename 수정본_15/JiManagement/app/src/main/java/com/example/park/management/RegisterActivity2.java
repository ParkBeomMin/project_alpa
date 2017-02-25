package com.example.park.management;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        final EditText confirmText = (EditText) findViewById(R.id.confirmText);
        final Button nextButton = (Button) findViewById(R.id.nextbutton);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String userConfirm = confirmText.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            //     boolean success = jsonResponse.getBoolean("success");
                            boolean success ;
                            String confirm = jsonResponse.getString("confirm");

                            if (confirm.equals(userConfirm)) {
                                Toast.makeText(RegisterActivity2.this, "확인되었습니다.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity2.this, RegisterActivity.class);
                                RegisterActivity2.this.startActivity(intent);
                            }
//                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity2.this);
//                                    builder.setMessage("잘못된 문자입니다.")
//                                            .setNegativeButton("다시시도", null)
//                                            .create()
//                                            .show();


                        }
                        catch (JSONException e) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity2.this);
                            builder.setMessage("잘못된 문자입니다.")
                                    .setNegativeButton("다시시도", null)
                                    .create()
                                    .show();
                            e.printStackTrace();
                        }
                    }
                };

                RegisterRequest2 registerRequest2 = new RegisterRequest2(userConfirm, responseListener);
                RequestQueue queue2 = Volley.newRequestQueue(RegisterActivity2.this);
                queue2.add(registerRequest2);

            }

        });
            }

}
