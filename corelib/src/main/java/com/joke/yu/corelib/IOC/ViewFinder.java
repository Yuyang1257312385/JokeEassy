package com.joke.yu.corelib.IOC;

import android.app.Activity;
import android.view.View;

/**
 * @author luyanjun
 * @email lu.yj@sand.com.cn
 * @description
 */
public class ViewFinder {

    private Activity mAct;
    private View mView;

    public ViewFinder(Activity mAct) {
        this.mAct = mAct;
    }

    public ViewFinder(View mView) {
        this.mView = mView;
    }

    public View findViewById(int id){
        View view = mAct!=null ? mAct.findViewById(id) : mView.findViewById(id);
        return view;
    }

}
