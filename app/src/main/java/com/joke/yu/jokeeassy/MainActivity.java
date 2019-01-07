package com.joke.yu.jokeeassy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.joke.yu.corelib.IOC.CheckNet;
import com.joke.yu.corelib.IOC.OnClick;
import com.joke.yu.corelib.IOC.ViewById;
import com.joke.yu.corelib.IOC.ViewUtils;
import com.joke.yu.jokeeassy.sample.IocSampleAct;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewUtils.inject(this);
    }



    @OnClick({R.id.btn_ioc_sample})
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.btn_ioc_sample:
                actionStart(this,IocSampleAct.class);
                break;
        }
    }

}
