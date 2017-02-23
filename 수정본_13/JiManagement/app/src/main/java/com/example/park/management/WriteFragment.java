package com.example.park.management;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.DateFormat;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link WriteFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link WriteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WriteFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private AlertDialog dialog;

    private OnFragmentInteractionListener mListener;

    public WriteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WriteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WriteFragment newInstance(String param1, String param2) {
        WriteFragment fragment = new WriteFragment();
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
    }

    private ArrayAdapter boardAdapter;
    private Spinner boardSpinner;
    private String boardName="";
    @Override
    public void onActivityCreated(Bundle b){
        super .onActivityCreated(b);
        boardSpinner = (Spinner) getView().findViewById(R.id.boardSpinner);
        boardAdapter = ArrayAdapter.createFromResource(getActivity(),R.array.board,android.R.layout.simple_dropdown_item_1line);
        boardSpinner.setAdapter(boardAdapter);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_write,container,false);
        final String userID = getArguments().getString("userID");
        Button button = (Button) view.findViewById(R.id.pushbutton);
        final EditText Title = (EditText) view.findViewById(R.id.editTitle);
        final EditText Content = (EditText) view.findViewById(R.id.editContext);
        final Spinner spiner = (Spinner) view.findViewById(R.id.boardSpinner);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String boardSelect = spiner.getSelectedItem().toString();
                String title = Title.getText().toString();
                String content = Content.getText().toString();
                timerAlpa time = new timerAlpa();
                String date = time.doCurrentDate();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Title.setText("");
                        Content.setText("");
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        dialog = builder.setMessage("게시물이 게시되었습니다")
                                .setPositiveButton("확인", null)
                                .create();
                        dialog.show();
                        SharedPreferences auto = getActivity().getSharedPreferences("auto", Activity.MODE_PRIVATE);
                        SharedPreferences.Editor edit = auto.edit();
                        edit.putInt("Badge",auto.getInt("Badge",0)+1);
                        edit.commit();



                    }
                };
                if(title.equals("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    dialog = builder.setMessage("제목을 입력하세요")
                            .setPositiveButton("확인", null)
                            .create();
                    dialog.show();
                }
                else if(content.equals("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    dialog = builder.setMessage("내용을 입력하세요")
                            .setPositiveButton("확인", null)
                            .create();
                    dialog.show();
                }

                else if(boardSelect.equals("자유게시판")){

                    writeRequest write = new writeRequest(userID,title,content,date,0,responseListener);
                    RequestQueue queue = Volley.newRequestQueue(getActivity());
                    queue.add(write);
                }
                else if(boardSelect.equals("익명게시판")){
                    writeRequest write = new writeRequest(userID,title,content,date,1,responseListener);
                    RequestQueue queue = Volley.newRequestQueue(getActivity());
                    queue.add(write);

                  }
                else{
                    writeRequest write = new writeRequest(userID,title,content,date,2,responseListener);
                    RequestQueue queue = Volley.newRequestQueue(getActivity());
                    queue.add(write);

                  }



            }


        });
        // Inflate the layout for this fragment
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
}
