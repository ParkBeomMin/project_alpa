package com.example.park.management;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import static android.app.Activity.RESULT_OK;

public class popupDataActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams layoutParams= new WindowManager.LayoutParams();
        layoutParams.flags= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount= 0.7f;
        getWindow().setAttributes(layoutParams);
        setContentView(R.layout.activity_popup_data);


        //UI 객체생성
        TextView textData = (TextView)findViewById(R.id.Text);
        TextView textTitle = (TextView)findViewById(R.id.Title);
        Button deletebutton =(Button)findViewById(R.id.deleteButton);

        //데이터 가져오기
        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
        final String title = intent.getStringExtra("title");
        final String board = intent.getStringExtra("board");
        final String userID = intent.getStringExtra("userID");
        final String date = intent.getStringExtra("date");
        textTitle.setText(title);
        textData.setText(data);

        deletebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(popupDataActivity.this);
                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            Log.d("success :",success+"ok");
                            if (success) {


                                Intent intent = new Intent(popupDataActivity.this,UserBoardActivity.class);
                                intent.putExtra("Frag",board);
                                startActivity(intent);





                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                DeleteRequest deleteRequest = new DeleteRequest(userID,board,title,date,responseListener);
                RequestQueue queue = Volley.newRequestQueue(popupDataActivity.this);
                queue.add(deleteRequest);
                finish();


            }
        });
    }

    //확인 버튼 클릭
    public void mOnClose(View v){
        //데이터 전달하기
        Intent intent = new Intent();
        intent.putExtra("result", "Close Popup");
        setResult(RESULT_OK, intent);

        //액티비티(팝업) 닫기
        finish();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }

}
