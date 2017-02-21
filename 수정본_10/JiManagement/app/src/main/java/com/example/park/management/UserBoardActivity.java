package com.example.park.management;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class UserBoardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_board);


        final Button freeButton = (Button) findViewById(R.id.freeButton);
        final Button annoButton = (Button) findViewById(R.id.annoButton);
        final Button tipButton = (Button) findViewById(R.id.tipButton);
        final Button writeButton  = (Button) findViewById(R.id.writeButton);
        Intent intent = getIntent();
        final String userID = intent.getStringExtra("userID");
        final String Frag = intent.getStringExtra("Frag");




        freeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                freeButton.setBackgroundColor(getResources().getColor(R.color.likeColor));
                annoButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                tipButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                writeButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Bundle args = new Bundle();
                args.putString("userID",userID);
                Fragment frag = new FreeBoardFragment();
                frag.setArguments(args);
                fragmentTransaction.replace(R.id.fragment,frag);

                fragmentTransaction.commit();

            }
        });
        annoButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                freeButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                annoButton.setBackgroundColor(getResources().getColor(R.color.likeColor));
                tipButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                writeButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                Bundle args = new Bundle();
                args.putString("userID",userID);
                Fragment frag = new AnnoBoardFragment();
                frag.setArguments(args);
                fragmentTransaction.replace(R.id.fragment,frag);
                fragmentTransaction.commit();
            }
        });
        tipButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                freeButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                annoButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                tipButton.setBackgroundColor(getResources().getColor(R.color.likeColor));
                writeButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Bundle args = new Bundle();
                args.putString("userID",userID);
                Fragment frag = new TipBoardFragment();
                frag.setArguments(args);
                fragmentTransaction.replace(R.id.fragment,frag);
                fragmentTransaction.commit();
            }
        });
        writeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                freeButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                annoButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                tipButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                writeButton.setBackgroundColor(getResources().getColor(R.color.likeColor));
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Bundle args = new Bundle();
                args.putString("userID",userID);
                Fragment frag = new WriteFragment();
                frag.setArguments(args);
                fragmentTransaction.replace(R.id.fragment,frag);
                fragmentTransaction.commit();
            }
        });

        if(Frag.equals("free")) {
            freeButton.performClick();
        }
        else if(Frag.equals("anony"))
            annoButton.performClick();
        //if(frag == "tip")
        else
            tipButton.performClick();
    }
    public void imageClick(View v){
        Intent intent = new Intent(UserBoardActivity.this, MainActivity.class);
        UserBoardActivity.this.startActivity(intent);
    }

}
