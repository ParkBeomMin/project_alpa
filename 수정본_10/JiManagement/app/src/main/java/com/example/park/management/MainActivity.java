package com.example.park.management;

import android.content.Intent;

import android.os.AsyncTask;
import android.os.Bundle;

import android.text.method.ScrollingMovementMethod;

import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private TextView noticeBoard;
    private TextView day;
    private TextView freeBoard;
    private TextView tipBoard;
    private String htmlformat;
    private String htmlDate;
    private String htmltitle;
    private  long lastTimeBackPressed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        final String userID = intent.getStringExtra("userID");
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        TextView tipButton= (Button)findViewById(R.id.tipButton);

        freeBoard =(TextView)findViewById(R.id.freeboard);
        freeBoard.setMovementMethod(new ScrollingMovementMethod());
        tipBoard = (TextView)findViewById(R.id.tipBoard);
        tipBoard.setMovementMethod(new ScrollingMovementMethod());
        noticeBoard = (TextView)findViewById(R.id.announcementTextVeiw);
        noticeBoard.setMovementMethod(new ScrollingMovementMethod());
        day = (TextView)findViewById(R.id.dayTextView);
        day.setMovementMethod(new ScrollingMovementMethod());

        GetParsing par = new GetParsing();
        par.execute();
        textViewSetting txt = new textViewSetting();
        txt.execute();
        tipViewSetting tipV = new tipViewSetting();
        tipV.execute();


        tipButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UserBoardActivity.class);
                intent.putExtra("userID", userID);
                intent.putExtra("Frag","tip");
                MainActivity.this.startActivity(intent);

            }
        });
        TextView annouceButton= (Button)findViewById(R.id.announcementButton);

        annouceButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BoardmainActivity.class);
                intent.putExtra("userID", userID);
                intent.putExtra("Frag","alpa");
                MainActivity.this.startActivity(intent);
            }
        });
        TextView boardButton= (Button)findViewById(R.id.boardButton);

        boardButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UserBoardActivity.class);
                intent.putExtra("userID", userID);
                intent.putExtra("Frag","free");
                MainActivity.this.startActivity(intent);
            }
        });



    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent idintent = getIntent();
        final String userID = idintent.getStringExtra("userID");
        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, SettingActivity.class);
            //intent.putExtra("userID", userID);
            MainActivity.this.startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        android.app.FragmentManager manager = getFragmentManager();
        Intent idintent = getIntent();
        final String userID = idintent.getStringExtra("userID");

        if (id == R.id.nav_announcement_univ_layout) {
//            manager.beginTransaction().replace(R.id.content_main, new AnnoucementLayout()).commit();
            Intent intent = new Intent(MainActivity.this, BoardmainActivity.class);
            intent.putExtra("userID", userID);
            intent.putExtra("Frag","univ");
            startActivity(intent);
        }
        if(id == R.id.nav_announcement_depart_layout){
            Intent intent = new Intent(MainActivity.this, BoardmainActivity.class);
            intent.putExtra("userID", userID);
            intent.putExtra("Frag","depart");
            startActivity(intent);
            //과 프레그먼트
        }
        if(id == R.id.nav_announcement_alpa_layout){
            Intent intent = new Intent(MainActivity.this, BoardmainActivity.class);
            intent.putExtra("userID", userID);
            intent.putExtra("Frag","alpa");
            startActivity(intent);
        }
        if (id == R.id.nav_board_free_layout) {
            Intent intent = new Intent(MainActivity.this, UserBoardActivity.class);
            intent.putExtra("userID", userID);
            intent.putExtra("Frag","free");
            startActivity(intent);
        }
        if (id == R.id.nav_board_anony_layout){
            Intent intent = new Intent(MainActivity.this, UserBoardActivity.class);
            intent.putExtra("userID", userID);
            intent.putExtra("Frag","anony");
            startActivity(intent);
        }
        if (id == R.id.nav_board_tip_layout){
            Intent intent = new Intent(MainActivity.this, UserBoardActivity.class);
            intent.putExtra("userID", userID);
            intent.putExtra("Frag","tip");
            startActivity(intent);
        }
        else if (id == R.id.nav_gallary_layout) {
            manager.beginTransaction().replace(R.id.content_main, new GallaryLayout()).commit();
        } else if (id == R.id.nav_setting_layout) {
            manager.beginTransaction().replace(R.id.content_main, new SettingLayout()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void onBackPressed() {
        if(System.currentTimeMillis() - lastTimeBackPressed < 1500) {
            finishAffinity();
                return;
        }
        Toast.makeText(this, "뒤로 버튼을 한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
        lastTimeBackPressed = System.currentTimeMillis();
    }



   private class textViewSetting extends AsyncTask<Void, Void, String> {
       String target;
       @Override
        protected void onPreExecute() {
            target = "http://jisung0920.cafe24.com/FreeList.php";
        }
        @Override
        protected String doInBackground(Void... params) {
            try {
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while ((temp = bufferReader.readLine()) != null) {
                    stringBuilder.append(temp + "\n");
                }
                bufferReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            } catch (Exception e) {
                e.printStackTrace();
                }
             return null;
        }
       public void onProgressUpdate(Void... values){
           super.onProgressUpdate();
       }

        @Override
        protected void onPostExecute(String result) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int index = 0;
                JSONObject object = jsonArray.getJSONObject(index);
                freeBoard.setText(object.getString("freeTitle"));

            }
            catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    private class GetParsing extends AsyncTask<Void, Void, Void>{
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Document document = Jsoup.connect("http://jisung0920.cafe24.com/").get();
                Elements elements = document.select(".entry-content");
                Elements ele = document.select(".entry-header");
                htmlformat = elements.first().text();
                htmlDate = ele.first().text().substring(0,10);
                htmltitle=ele.first().text().substring(11);

            } catch (IOException e) {
                e.printStackTrace();

            }
            return null;
        }
        @Override
        protected  void onPostExecute(Void result){
            noticeBoard.setText(htmlformat);
            timerAlpa talpa = new timerAlpa();
            long t = talpa.Counter(htmlDate);
            if(t>0)
                day.setText(htmltitle+"\nD-"+t);
            else if(t<0)
                day.setText(htmltitle+"\nD+"+t);
            else
                day.setText("D Day");
        }
    }



    private class tipViewSetting extends AsyncTask<Void,Void,String> {
        String target;
        @Override
        protected void onPreExecute(){
            target ="http://jisung0920.cafe24.com/TipList.php";
        }
        @Override
        protected String doInBackground(Void... params) {
            try{
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while((temp=bufferReader.readLine())!=null){
                    stringBuilder.append(temp+"\n");
                }
                bufferReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
        @Override
        public void onProgressUpdate(Void... values){
            super.onProgressUpdate();
        }
        @Override
        public void onPostExecute(String result){
            try{
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count = 0;
                JSONObject object = jsonArray.getJSONObject(count);
                tipBoard.setText(object.getString("tipTitle"));


            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
