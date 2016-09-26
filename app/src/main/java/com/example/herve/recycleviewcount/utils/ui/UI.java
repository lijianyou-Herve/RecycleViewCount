package com.example.herve.recycleviewcount.utils.ui;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.herve.recycleviewcount.bean.ScreenBean;

/**
 * Created           :Herve on 2016/9/13.
 *
 * @ Author          :Herve
 * @ e-mail          :lijianyou.herve@gmail.com
 * @ LastEdit        :2016/9/13
 * @ projectName     :RecycleViewCount
 * @ version
 */
public class UI {

    public final static int ORG_SCREEN_WIDTH = 480;
    public final static int ORG_SCREEN_HEIGHT = 800;

    public static ScreenBean getDisplayMetrics(Context mContext) {
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        ScreenBean srceenBean = new ScreenBean();
        srceenBean.setDmW(dm.widthPixels);
        srceenBean.setDmW(dm.heightPixels);

        return srceenBean;
    }

    public static LinearLayout.LayoutParams getLinearLayoutPararmWH(ScreenBean screenBean, int w,int h) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        params.width = screenBean.dmW * w / ORG_SCREEN_WIDTH;
        params.height = params.width * h / w;

        return params;
    }

    public static LinearLayout.LayoutParams getLinearLayoutPararmW2(ScreenBean screenBean, int w) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        params.width = screenBean.dmW * w / ORG_SCREEN_WIDTH;
        params.height = params.width;

        return params;
    }

}
