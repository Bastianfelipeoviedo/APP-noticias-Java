package com.example.newsapp.Activity;



import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Toast;
import com.example.newsapp.Adapter;
import com.example.newsapp.AdapterFrag;
import com.example.newsapp.Api.ApiInterface;
import com.example.newsapp.Api.RetrofitBuilder;
import com.example.newsapp.CultureFragment;
import com.example.newsapp.HomeFragment;
import com.example.newsapp.Model.News;
import com.example.newsapp.R;
import com.example.newsapp.SocialFragment;
import com.example.newsapp.SportsFragment;
import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NewsActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    Adapter adapter;
    SwipeRefreshLayout swipe;
    RelativeLayout error;
    LinearLayout linear;
    TabLayout tabLayout;
    ViewPager viewPager;
    private static final String myApi = "647fc6731bb64da68f0db64cea57b275";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        Button retry;




        retry = findViewById(R.id.retry);
        SearchView searchView = findViewById(R.id.search_bar);

        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tabLayout2);
        tabLayout.setupWithViewPager(viewPager);
        AdapterFrag adapterFrag = new AdapterFrag(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        adapterFrag.addFragment(new HomeFragment(), "Home");
        adapterFrag.addFragment(new SportsFragment(), "Sports");
        adapterFrag.addFragment(new CultureFragment(), "Culture");
        adapterFrag.addFragment(new SocialFragment(), "Social");
        viewPager.setAdapter(adapterFrag);



    }
}
