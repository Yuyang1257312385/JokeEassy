package com.joke.yu.jokeeassy;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

/**
 * @author luyanjun
 * @email lu.yj@sand.com.cn
 * @description
 */
public class BaseActivity extends AppCompatActivity {

    public void actionStart(Context context,Class clazz){
        Intent intent = new Intent(context,clazz);
        context.startActivity(intent);
    }

}
