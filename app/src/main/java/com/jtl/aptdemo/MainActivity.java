package com.jtl.aptdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.jtl.apt_annotation.ARouter;

@ARouter(path = "MainActivity")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}