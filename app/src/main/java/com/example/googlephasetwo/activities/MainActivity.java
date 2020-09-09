package com.example.googlephasetwo.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.googlephasetwo.R;
import com.example.googlephasetwo.adapter.FragmentAdapter;

public class MainActivity extends AppCompatActivity {
    public ViewPager viewpager;
    FragmentAdapter adapter;

    View view, view1;
    RelativeLayout layout, layout2;
    TextView learningLeadersText, skillLeadersText, submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.layout);
        layout2 = findViewById(R.id.layout2);

        learningLeadersText = findViewById(R.id.learning_leaders);
        skillLeadersText = findViewById(R.id.skill_leaders);
        viewpager = findViewById(R.id.viewPager);
        view = findViewById(R.id.view);
        view1 = findViewById(R.id.view1);

        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SubmissionActivity.class);
                startActivity(intent);
            }
        });


        adapter = new FragmentAdapter(getSupportFragmentManager(), MainActivity.this);

        viewpager.setAdapter(adapter);

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Fragment fragment = ((FragmentAdapter)viewpager.getAdapter()).getFragment(position);

                if (position == 0) {
                    learningLeadersText.setTextColor(getResources().getColor(R.color.white));
                    skillLeadersText.setTextColor(getResources().getColor(R.color.grey));
                    view.setVisibility(View.VISIBLE);
                    view1.setVisibility(View.GONE);
                    viewpager.getAdapter().notifyDataSetChanged();
                } else if (position == 1) {
                    learningLeadersText.setTextColor(getResources().getColor(R.color.grey));
                    skillLeadersText.setTextColor(getResources().getColor(R.color.white));
                    view.setVisibility(View.GONE);
                    view1.setVisibility(View.VISIBLE);
                    viewpager.getAdapter().notifyDataSetChanged();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                learningLeadersText.setTextColor(getResources().getColor(R.color.white));
                skillLeadersText.setTextColor(getResources().getColor(R.color.grey));
                view.setVisibility(View.VISIBLE);
                view1.setVisibility(View.GONE);
                viewpager.setCurrentItem(0, true);
            }
        });

        layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                learningLeadersText.setTextColor(getResources().getColor(R.color.grey));
                skillLeadersText.setTextColor(getResources().getColor(R.color.white));
                view.setVisibility(View.GONE);
                view1.setVisibility(View.VISIBLE);
                viewpager.setCurrentItem(1, true);
            }
        });
    }

}