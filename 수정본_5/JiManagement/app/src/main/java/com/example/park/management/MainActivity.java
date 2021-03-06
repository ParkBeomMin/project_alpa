package com.example.park.management;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.TextViewCompat;
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

import com.example.park.management.R;

public class MainActivity extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener {
    private  long lastTimeBackPressed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        tipButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UserBoardActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        TextView annouceButton= (Button)findViewById(R.id.announcementButton);

        annouceButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BoardmainActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        TextView boardButton= (Button)findViewById(R.id.boardButton);

        boardButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UserBoardActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });



    }

//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent Intent = new Intent(MainActivity.this, SettingActivity.class);
            MainActivity.this.startActivity(Intent);
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

        if (id == R.id.nav_announcement_univ_layout) {
//            manager.beginTransaction().replace(R.id.content_main, new AnnoucementLayout()).commit();
            Intent intent = new Intent(MainActivity.this, BoardmainActivity.class);
            startActivity(intent);
        }
        if(id == R.id.nav_announcement_depart_layout){
            Intent intent = new Intent(MainActivity.this, BoardmainActivity.class);
            startActivity(intent);
            //과 프레그먼트
        }
        if(id == R.id.nav_announcement_alpa_layout){

        }
        if (id == R.id.nav_board_free_layout) {
            Intent intent = new Intent(MainActivity.this, UserBoardActivity.class);
            startActivity(intent);
        }
        if (id == R.id.nav_board_anony_layout){
            Intent intent = new Intent(MainActivity.this, UserBoardActivity.class);
            intent.putExtra("Frag","anony");
            startActivity(intent);
        }
        if (id == R.id.nav_board_tip_layout){

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
}
