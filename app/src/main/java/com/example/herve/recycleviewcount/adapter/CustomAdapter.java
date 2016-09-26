package com.example.herve.recycleviewcount.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.herve.recycleviewcount.R;
import com.example.herve.recycleviewcount.bean.MakeFilmMenuBean;
import com.example.herve.recycleviewcount.utils.ui.UI;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created           :Herve on 2016/9/13.
 *
 * @ Author          :Herve
 * @ e-mail          :lijianyou.herve@gmail.com
 * @ LastEdit        :2016/9/13
 * @ projectName     :RecycleViewCount
 * @ version
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {


    private Context mContext;
    private String TAG = getClass().getSimpleName();
    private ArrayList<MakeFilmMenuBean> data;

    public CustomAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Log.i(TAG, "onCreateViewHolder: " + viewType);
        View item = LayoutInflater.from(mContext).inflate(R.layout.menu_template_item, parent, false);


        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if (position == 0 || position == 1) {

            holder.llFilmType.setLayoutParams(UI.getLinearLayoutPararmWH(UI.getDisplayMetrics(mContext), 70,80));

        } else {

            holder.llFilmType.setLayoutParams(UI.getLinearLayoutPararmW2(UI.getDisplayMetrics(mContext), 70));
        }

        holder.ivFilmType.setLayoutParams(UI.getLinearLayoutPararmW2(UI.getDisplayMetrics(mContext), 50));


        MakeFilmMenuBean itemBean = data.get(position);
        holder.tvFilmType.setText(itemBean.getTv_film_type());
        holder.ivFilmType.setImageResource(itemBean.getIv_film_type());


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(ArrayList<MakeFilmMenuBean> data) {
        this.data = data;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.iv_film_type)
        ImageView ivFilmType;
        @Bind(R.id.tv_film_type)
        TextView tvFilmType;
        @Bind(R.id.ll_film_type)
        LinearLayout llFilmType;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

}
