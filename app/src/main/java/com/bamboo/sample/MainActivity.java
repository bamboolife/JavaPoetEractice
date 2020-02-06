package com.bamboo.sample;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bamboo.annotation.Hello;


@Hello
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HelloWorld.hello1();
    }
}
