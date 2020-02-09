package com.bamboo.sample;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bamboo.annotation.Hello;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.lang.reflect.Modifier;


@Hello
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HelloWorld.hello1();
        init();
    }

    private static void init() {

    }
}
