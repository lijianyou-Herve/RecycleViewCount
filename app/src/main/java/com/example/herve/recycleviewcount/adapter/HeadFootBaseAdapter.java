package com.example.herve.recycleviewcount.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;

/**
 * Created           :Herve on 2016/9/13.
 *
 * @ Author          :Herve
 * @ e-mail          :lijianyou.herve@gmail.com
 * @ LastEdit        :2016/9/13
 * @ projectName     :RecycleViewCount
 * @ version
 */
public abstract class HeadFootBaseAdapter<T extends RecyclerView.ViewHolder,V> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    protected Context mContext;
    protected String TAG = getClass().getSimpleName();
    protected ArrayList<V> data;

    private ArrayList<View> headerViews = new ArrayList<>();
    private ArrayList<View> footerViews = new ArrayList<>();


    private int VIE_TYPE_SIMPLE = -1;

    public HeadFootBaseAdapter(Context mContext) {
        this.mContext = mContext;

    }


    public void addHeaderView(View headerView) {
        this.headerViews.add(headerView);
        notifyDataSetChanged();

    }

    public void addFooterView(View footerView) {
        this.footerViews.add(footerView);

        notifyDataSetChanged();
    }

    public int getHeaderViewSize() {
        return headerViews.size();
    }

    public int getFooterViewSize() {
        return footerViews.size();
    }

    @Override
    public int getItemViewType(int position) {

        if (position < headerViews.size()) {
            return position;
        }
        if (position >= data.size() + headerViews.size()) {
            return position;
        }
        return VIE_TYPE_SIMPLE;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType >= 0 && viewType < headerViews.size()) {

            return new HeaderViewHolder(headerViews.get(viewType));

        } else if (viewType >= 0 && viewType >= data.size() + headerViews.size()) {
            return new FooterViewHolder(footerViews.get(viewType - (data.size() + headerViews.size())));

        } else {
            return onCreateItemViewHolder(parent, viewType);
        }

    }

    protected abstract T onCreateItemViewHolder(ViewGroup parent, int viewType);

    protected abstract void onBindItemViewHolder(T holder, final int position);

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof HeaderViewHolder) {

            HeaderViewHolder headFootViewHolder = (HeaderViewHolder) holder;
            headFootViewHolder.itemView.setBackgroundColor(Color.YELLOW);

        } else if (holder instanceof FooterViewHolder) {
            FooterViewHolder headFootViewHolder = (FooterViewHolder) holder;
            headFootViewHolder.itemView.setBackgroundColor(Color.GREEN);
        } else {

            onBindItemViewHolder((T) holder, position);

        }


    }

    @Override
    public int getItemCount() {
        return data.size() + headerViews.size() + footerViews.size();
    }

    public void setData(ArrayList<V> data) {
        this.data = data;
    }


    public static class FooterViewHolder extends RecyclerView.ViewHolder {


        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {


        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }


}
