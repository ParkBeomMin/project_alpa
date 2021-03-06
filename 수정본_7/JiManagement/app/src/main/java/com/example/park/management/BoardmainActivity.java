package com.example.park.management;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.StringRequest;


public class BoardmainActivity extends AppCompatActivity {


    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    //Intent intent = getIntent();
    //private final String frag = intent.getStringExtra("Frag");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boardmain);


        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


    }



    public void imageClick(View v){
        Intent intent = new Intent(BoardmainActivity.this, MainActivity.class);
        BoardmainActivity.this.startActivity(intent);
    }



    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */


        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();

            Bundle args = new Bundle();

            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            Bundle args = getActivity().getIntent().getExtras();
            String frag = args.getString("Frag");

            if(getArguments().getInt(ARG_SECTION_NUMBER) == 1){
                View rootView = inflater.inflate(R.layout.fragment_school_page, container, false);

                WebView web = (WebView) rootView.findViewById(R.id.school_web);
                web.loadUrl("http://www.hanyang.ac.kr/web/www/main-notices");
                web.getSettings().setJavaScriptEnabled(true);
                web.setWebViewClient(new WebViewClient());
                return rootView;
            }
            else if (getArguments().getInt(ARG_SECTION_NUMBER) == 2){
                View rootView = inflater.inflate(R.layout.fragment_major_page, container, false);

                WebView web1 = (WebView) rootView.findViewById(R.id.major_web);
                web1.loadUrl("http://cse.hanyang.ac.kr/boards/notices/");
                web1.getSettings().setJavaScriptEnabled(true);
                web1.setWebViewClient(new WebViewClient());
                return rootView;
            }
            else if (getArguments().getInt(ARG_SECTION_NUMBER) == 3){
                View rootView = inflater.inflate(R.layout.fragment_academy_page, container, false);

                WebView web2 = (WebView) rootView.findViewById(R.id.academy_web);
                web2.loadUrl("http://jisung0920.cafe24.com");
                web2.getSettings().setJavaScriptEnabled(true);
                web2.setWebViewClient(new WebViewClient());


                return rootView;
            }
            else {
                View rootView = inflater.inflate(R.layout.fragment_main, container, false);
                TextView textView = (TextView) rootView.findViewById(R.id.section_label);
                textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
                return rootView;
            }
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            /*if(frag.equals("univ"))
                return "학교";
            else if(frag.equals("depart"))
                return "학과";
            else if(frag.equals("alpa"))
                return "학회";
            */
            switch (position) {
                case 0:
                    return "학교";
                case 1:
                    return "학과";
                case 2:
                    return "학회";
            }
            return null;
        }
    }
}
