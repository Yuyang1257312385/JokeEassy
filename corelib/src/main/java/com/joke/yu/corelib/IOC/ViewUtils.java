package com.joke.yu.corelib.IOC;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author luyanjun
 * @email lu.yj@sand.com.cn
 * @description
 */
public class ViewUtils {

    //Activity中注入
    public static void inject(Activity activity){
        inject(new ViewFinder(activity),activity);
    }

    //View中注入
    public static void inject(View view){
        inject(new ViewFinder(view),view);
    }

    //Fragment中注入
    public static void inject(View view,Object object){
        inject(new ViewFinder(view),object);
    }

    private static void inject(ViewFinder viewFinder,Object object){
        //字段注入
        injectField(viewFinder,object);
        //方法注入
        injectMethod(viewFinder,object);
    }


    /**
     * 属性注入
     * @param viewFinder
     * @param object
     */
    private static void injectField(ViewFinder viewFinder, Object object) {
        //1. 获取到注解
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            //2. 获取到注解的值
            ViewById viewById = field.getAnnotation(ViewById.class);
            if(viewById != null){
                int viewId = viewById.value();
                //3. 根据值获取View
                View view = viewFinder.findViewById(viewId);
                //4. 反射注入属性
                if(view != null){
                    try {
                        field.setAccessible(true);
                        field.set(object,view);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }


    }

    /**
     * 方法注入
     * @param viewFinder
     * @param object
     */
    private static void injectMethod(ViewFinder viewFinder, Object object) {
        //1. 获取到注解
        Class<?> clazz = object.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            OnClick onClick = method.getAnnotation(OnClick.class);
            if(onClick != null){
                //2. 获取到注解的值
                int[] ids = onClick.value();
                for (int id : ids) {

                    //3. 根据值获取view
                    View view = viewFinder.findViewById(id);
                    if(view != null){
                        //4. 设置onclickListener
                        view.setOnClickListener(new DeclearOnClickListener(method,object));
                    }
                }
            }
        }

    }

    public static class DeclearOnClickListener implements View.OnClickListener{

        private Method mMethod;
        private Object mObject;

        public DeclearOnClickListener(Method mMethod, Object mObject) {
            this.mMethod = mMethod;
            this.mObject = mObject;
        }

        @Override
        public void onClick(View v) {

            //=====检查网络====
            CheckNet checkNet = mMethod.getAnnotation(CheckNet.class);
            if(checkNet != null){
                if(!isNetworkAvailable(v.getContext())){
                    String tip = checkNet.value();
                    Toast.makeText(v.getContext(),tip,Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            //5. 动态执行
            mMethod.setAccessible(true);
            try {
                mMethod.invoke(mObject,v);
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    mMethod.invoke(mObject,null);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public static boolean isNetworkAvailable(Context context) {

        ConnectivityManager manager = (ConnectivityManager) context
                .getApplicationContext().getSystemService(
                        Context.CONNECTIVITY_SERVICE);

        if (manager == null) {
            return false;
        }

        NetworkInfo networkinfo = manager.getActiveNetworkInfo();

        if (networkinfo == null || !networkinfo.isAvailable()) {
            return false;
        }

        return true;
    }


}
