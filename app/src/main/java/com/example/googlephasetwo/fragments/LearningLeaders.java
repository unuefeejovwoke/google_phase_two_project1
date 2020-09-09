package com.example.googlephasetwo.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.googlephasetwo.R;
import com.example.googlephasetwo.models.LeadersAdapter;
import com.example.googlephasetwo.models.LeadersObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class LearningLeaders extends Fragment {
    private LearningLeaders.OnFragmentInteractionListener mListener;
    private RecyclerView leadersLearnersRecyclerView;
    private LinearLayoutManager linearLayoutManager;

    private ArrayList<LeadersObject> learningLeadersList;


    public LearningLeaders() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static LearningLeaders newInstance(String param1, String param2) {
        return new LearningLeaders();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.learning_leaders, container, false);


        leadersLearnersRecyclerView = view.findViewById(R.id.learning_leaders_recyclerView);
        leadersLearnersRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        leadersLearnersRecyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getActivity());


        leadersLearnersRecyclerView.setLayoutManager(linearLayoutManager);

        fetchJsonData();

        return view;
    }

    private void fetchJsonData() {
        String url = "https://gadsapi.herokuapp.com/api/hours";
        learningLeadersList = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(requireContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject data = jsonArray.getJSONObject(i);
                            learningLeadersList.add(new LeadersObject(data.getString("name"),
                                    data.getString("hours")+" learning hours, ",
                                    data.getString("country"), data.getString("badgeUrl")));
                        }

                        showRecyclerView();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });

        queue.add(stringRequest);

    }

    private void showRecyclerView(){
        LeadersAdapter globalAdapter = new LeadersAdapter(learningLeadersList, getContext());
        leadersLearnersRecyclerView.setAdapter(globalAdapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}