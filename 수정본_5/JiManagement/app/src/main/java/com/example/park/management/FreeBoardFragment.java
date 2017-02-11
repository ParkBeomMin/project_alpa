package com.example.park.management;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FreeBoardFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FreeBoardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class FreeBoardFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FreeBoardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FreeBoardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FreeBoardFragment newInstance(String param1, String param2) {
        FreeBoardFragment fragment = new FreeBoardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        new BackgroundTask().execute();

    }
    private ListView freeListView;
    private FreeListAdapter adapter;
    private List<Free> freeList;



    /*
    freeListView = (ListView) findViewById(R.id.freeListView);
    freeList = new ArrayList<Free>();
    freeList.add(new Free("notice","jisung","2018-04-01"));
    freeList.add(new Free("notice","jisung","2018-04-01"));
    freeList.add(new Free("notice","jisung","2018-04-01"));
    freeList.add(new Free("notice","jisung","2018-04-01"));
    adapter = new FreeListAdapter(getApplicationContext(),freeList);
    freeListView.setAdapter(adapter);
*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_free_board,container,false);
        freeListView = (ListView) view.findViewById((R.id.freeListView));
        freeList = new ArrayList<Free>();
        adapter = new FreeListAdapter(getActivity(),freeList);

        freeListView.setAdapter(adapter);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    class BackgroundTask extends AsyncTask<Void,Void,String> {
        String target;
        @Override
        protected void onPreExecute(){
            target ="http://jisung0920.cafe24.com/FreeList.php";
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
                freeList.clear();
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count = 0;
                String freeContent,freeTitle,freeName,freeDate;
                while(count<jsonArray.length()){
                    JSONObject object = jsonArray.getJSONObject(count);
                    freeContent = object.getString("freeContent");
                    freeTitle = object.getString("freeTitle");
                    freeName = object.getString("freeName");
                    freeDate = object.getString("freeDate");
                    Free free = new Free(freeTitle,freeContent,freeName,freeDate);
                    freeList.add(free);
                    count++;
                }
                adapter.notifyDataSetChanged();
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
