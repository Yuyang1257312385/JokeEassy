package com.joke.yu.jokeeassy.sample;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.joke.yu.corelib.IOC.CheckNet;
import com.joke.yu.corelib.IOC.OnClick;
import com.joke.yu.corelib.IOC.ViewById;
import com.joke.yu.corelib.IOC.ViewUtils;
import com.joke.yu.jokeeassy.R;

/**
 * @author luyanjun
 * @email lu.yj@sand.com.cn
 * @description
 */
public class IocSampleAct extends AppCompatActivity {

    @ViewById(R.id.tv_ioc)
    TextView mIocTv;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_ioc_sample);
        ViewUtils.inject(this);
        mIocTv.setText("IOC字段注入测试");
    }


    @OnClick({R.id.tv_ioc,R.id.iv_ioc,R.id.btn_no_error})
    private void onClick(View view){
        switch (view.getId()){
            case R.id.iv_ioc:
                Toast.makeText(this,"click imageview",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_ioc:
                Toast.makeText(this,"click textview",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_no_error:
                //看错误日志需要看warn
                int i = 2/0;
                Toast.makeText(this,"不崩溃测试" + i,Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @OnClick(R.id.btn_checknet_default)
    @CheckNet
    private void onClick(){
        Toast.makeText(this,"有网络了",Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn_checknet_custom)
    @CheckNet("自定义无网络提示")
    private void onCheckNetClick(){
        Toast.makeText(this,"有网络了",Toast.LENGTH_SHORT).show();
    }





}
