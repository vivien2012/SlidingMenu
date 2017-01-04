package com.vivien.slidingmenu.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.vivien.slidingmenu.R;

public class MainActivity extends AppCompatActivity {

    private TextView mNormalTv;
    private TextView mFiveTv;
    private TextView mFiveAfterTv;

    private TextView mDNormalTv;
    private TextView mDFiveTv;
    private TextView mDFiveAfterTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mNormalTv = (TextView) findViewById(R.id.main_normal);
        mFiveTv = (TextView) findViewById(R.id.main_five_one);
        mFiveAfterTv = (TextView) findViewById(R.id.main_five_two);
        mDNormalTv = (TextView) findViewById(R.id.main_normal_double);
        mDFiveTv = (TextView) findViewById(R.id.main_five_one_double);
        mDFiveAfterTv = (TextView) findViewById(R.id.main_five_two_double);

        mNormalTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NormalSlidingMenuActivity.class));
            }
        });
        mFiveTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, QQFiveSlidingMenuActivity.class));
            }
        });
        mFiveAfterTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AfterQQFiveSlidingMenuActivity.class));
            }
        });
        mDNormalTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DNormalSlidingMenuActivity.class));
            }
        });
        mDFiveTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DQQFiveSlidingMenuActivity.class));
            }
        });
        mDFiveAfterTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DAfterQQFiveSlidingMenuActivity.class));
            }
        });
    }
}
