package com.example.admin.whatsapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Contact extends Fragment {
    ProgressDialog mProgressDialog;
    //ProgressBar progressBar;
    private List<JobListItem1> jobListItem1;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contact_frag, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ArrayList<ChatBean> list = new ArrayList<>();

        jobListItem1 = new ArrayList<>();

        String url = "https://api.androidhive.info/contacts/";

        new ResponseAsync().execute(url);

        return view;

    }
    private class ResponseAsync extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(getActivity());
            mProgressDialog.setTitle("get response Tutorial");
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }
        @Override
        protected String doInBackground(String... urls) {

            String respURL = urls[0];

            try {

                URL url = new URL(respURL);
                InputStream is = url.openConnection().getInputStream();

                StringBuffer buffer = new StringBuffer();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }

                Log.e("Why data ", buffer.toString());
                return buffer.toString();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(String result) {

            Log.e("Response data", result);
            try {
                JSONObject object = new JSONObject(result);
                JSONArray array = object.getJSONArray("contacts");
                for (int i =0; i<array.length(); i++){
                    JobListItem1 item = new JobListItem1();
                    JSONObject list_obj = array.getJSONObject(i);
                    item.setId(list_obj.getString("id"));
                    item.setName(list_obj.getString("name"));
                    item.setEmail(list_obj.getString("email"));
                    item.setAddress(list_obj.getString("address"));
                    item.setGender(list_obj.getString("gender"));
                    //item.setPhone(list_obj.getString("phone"));
                    JSONObject phoneObj = list_obj.getJSONObject("phone");

                    item.setMobile(phoneObj.getString("mobile"));
                    item.setOffice(phoneObj.getString("office"));
                    item.setHome(phoneObj.getString("home"));
                    jobListItem1.add(item);
                }
                recyclerView.setAdapter(new JobListAdapter1(getActivity(), jobListItem1));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mProgressDialog.dismiss();
        }
    }



    /*public void onAttach(Context context) {
        super.onAttach(context);
        Log.i("onAttach", "called");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("onCreate", "called");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contact_frag, container, false);


        Log.i("onCreateView", "called");
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("onActivityCreated", "called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("onPause", "called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("onResume", "called");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("onStart", "called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("onStop", "called");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("onAttach", "called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("onDestroy", "called");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("onDestroyView", "called");
    }*/
}