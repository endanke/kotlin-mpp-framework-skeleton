package com.sample.kotlinandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.sample.mobile.AndroidSpecific;
import com.sample.mobile.FrameworkSample;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FrameworkSample sample1 = new FrameworkSample();
        Log.d("Sample1", String.valueOf(sample1.getTime()));

        AndroidSpecific sample2 = new AndroidSpecific();
        Log.d("Sample2", String.valueOf(sample2.multiply(2,8)));
    }
}
