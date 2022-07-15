package com.example.newsapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.newsapp.Api.ApiInterface;
import com.example.newsapp.Api.RetrofitBuilder;
import com.example.newsapp.Model.News;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SportsFragment extends Fragment {
    Adapter adapter;
    SwipeRefreshLayout swipe1;
    LinearLayout linear;
    RecyclerView recyclerView;
    RelativeLayout error;
    SwipeRefreshLayout sp;
    Button retry;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_sports, container, false);

        recyclerView = v.findViewById(R.id.recyclerview);
        adapter=new Adapter(getActivity());
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        sp=v.findViewById(R.id.swipe);
        retry=v.findViewById(R.id.retry);

        linear=v.findViewById(R.id.linear);
        error=v.findViewById(R.id.error);
        LoadItems("sports");
        sp.setOnRefreshListener(() -> LoadItems("sports"));

        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadItems("sports");
            }
        });
        return v;
    }
    public String getDate() {
        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MMM-yyyy");
        String date=simpleDateFormat.format(calendar.getTime());
        return date ;
    }
    public static   String getCountry(){
        Locale locale = Locale.getDefault();
        String country = String.valueOf(locale.getCountry());
        return country.toLowerCase();
    }
    public static String getLanguage(){
        Locale locale = Locale.getDefault();
        String language = String.valueOf(locale.getLanguage());
        return language;
    }
    public void LoadItems(String k) {
        final String myApi="647fc6731bb64da68f0db64cea57b275";
        sp.setRefreshing(true);
        ApiInterface apiInterface= RetrofitBuilder.getRetrofitBuilder().create(ApiInterface.class);
        Call<News> call;
        if(k.isEmpty())
            call=apiInterface.getData(getCountry(),myApi);
        else
        {
            call=apiInterface.getSearch(k, getLanguage(),"publishedAt",myApi);
        }
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {


                if(response.isSuccessful())
                {
                    adapter.setArticles(response.body().getArticles());
                    //recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                     sp.setRefreshing(false);
                    error.setVisibility(View.GONE);
                    linear.setVisibility(View.VISIBLE);
                }
                else {
                     sp.setRefreshing(false);
                    Toast.makeText(getActivity(), "Nothing to show", Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onFailure(Call<News> call, Throwable t) {
                error.setVisibility(View.VISIBLE);
                linear.setVisibility(View.GONE);
            }
        });
    }
}