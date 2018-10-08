package com.example.admin.whatsapp;

import android.app.ProgressDialog;
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
import android.widget.ImageView;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Chats extends Fragment {
    ProgressDialog mProgressDialog;
    ImageView imageView;
    //ProgressBar progressBar;
    private List<JobListItem> jobListItems;
    private RecyclerView recyclerView;


    /*public String loadJSONFromAsset(){
        String json = null;
        try{
            InputStream is = getActivity().getAssets().open("StudentList.json");
            int size = is.available();
            byte buffer[] = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer,"UTF-8");

        }catch (IOException ex){
            ex.printStackTrace();
            return null;
        }
        return json;
    }*/

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chat_frag, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        //imageView = view.findViewById(R.id.imageView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ArrayList<ChatBean> list = new ArrayList<>();

        jobListItems = new ArrayList<>();

        String url = "https://raw.githubusercontent.com/vineetha19/vineetha/master/file";

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
                JSONArray array = object.getJSONArray("worldpopulation");
                for (int i =0; i<array.length(); i++){
                    JobListItem item = new JobListItem();
                    JSONObject list_obj = array.getJSONObject(i);
                    item.setRank(list_obj.getString("rank"));
                    item.setCountry(list_obj.getString("country"));
                    item.setPopulation(list_obj.getString("population"));
                    item.setFlag(list_obj.getString("flag"));
                    /*item.setPosName(list_obj.getString("pos_name"));
                    item.setExperience(list_obj.getString("experience"));
                    item.setLocation(list_obj.getString("location"));
                    item.setSalRange(list_obj.getString("sal_range"));
                    item.setKeySkills(list_obj.getString("key_skills"));*/
                    jobListItems.add(item);
                }
                recyclerView.setAdapter(new JobListAdapter(getActivity(), jobListItems));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mProgressDialog.dismiss();
        }
    }
}

        /*String json = "{"+
                "\"job_list_item\":["+
                "{"+
                "\"stu_name\":\"vineetha\","+
                "\"stu_id\":\"123\","+
                "\"company\":\"marolix\","+
                "\"mobile\":\"6303189126\""+
                "},{"+
                "\"stu_name\":\"swetha\","+
                "\"stu_id\":\"456\","+
                "\"company\":\"tcs\","+
                "\"mobile\":\"1234567890\""+
                "},{"+
                "\"stu_name\":\"niroosha\","+
                "\"stu_id\":\"789\","+
                "\"company\":\"ibm\","+
                "\"mobile\":\"1234567890\""+
                "}]}";*/
        /*try {
            String json = loadJSONFromAsset();
            JSONObject object = new JSONObject(json);
            Log.i("*********",object.toString());
            JSONArray array = object.getJSONArray("job_list_item");
            for(int i=0;i<array.length();i++){
                StudentList item = new StudentList();
                JSONObject job_list_item = array.getJSONObject(i);
                item.setStuName(job_list_item.getString("stu_name"));
                item.setStuId(job_list_item.getString("stu_id"));
                item.setCompany(job_list_item.getString("company"));
                item.setMobile(job_list_item.getString("mobile"));
                studentList.add(item);

            }
            recyclerView.setAdapter(new StudentListAdapter(getActivity(), studentList));


        } catch (JSONException e) {
            e.printStackTrace();
        }


        /*list.add(new ChatBean("Vineetha", "Hi", "Today is thursday"));
        list.add(new ChatBean("vini", "Hello", "its 3:25 pm"));
        list.add(new ChatBean("chandu", "hey", "meeting"));

        list.add(new ChatBean("Vineetha", "Hi", "Today is thursday"));
        list.add(new ChatBean("vini", "Hello", "its 3:25 pm"));
        list.add(new ChatBean("chandu", "hey", "meeting"));

        list.add(new ChatBean("Vineetha", "Hi", "Today is thursday"));
        list.add(new ChatBean("vini", "Hello", "its 3:25 pm"));
        list.add(new ChatBean("chandu", "hey", "meeting"));



        recyclerView.setAdapter(new ChatAdapter(getActivity(), list));
        Log.i("onCreateView", "called");
        return view;*/
