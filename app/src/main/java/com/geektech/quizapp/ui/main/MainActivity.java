package com.geektech.quizapp.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.geektech.quizapp.R;
import com.geektech.quizapp.adapters.MainPagerAdapter;
import com.geektech.quizapp.data.remote.IQuizApiClient;
import com.geektech.quizapp.models.Question;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private BottomNavigationView bottomNavigationView;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.main_view_pager);
        bottomNavigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.tool_bar);

        viewPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(2);
        toolbar.setTitle("Quiz");


        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                default:
                    viewPager.setCurrentItem(0, false);
                    toolbar.setTitle("Quiz");
                    break;
                case R.id.history:
                    viewPager.setCurrentItem(1, false);
                    toolbar.setTitle("History");
                    break;
                case R.id.settings:
                    viewPager.setCurrentItem(2, false);
                    toolbar.setTitle("Settings");
                    break;
            }
            return true;
        });

    }
}