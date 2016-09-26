package com.example.herve.recycleviewcount.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.herve.recycleviewcount.R;
import com.example.herve.recycleviewcount.bean.MakeFilmMenuBean;
import com.example.herve.recycleviewcount.utils.ui.UI;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created           :Herve on 2016/9/17.
 *
 * @ Author          :Herve
 * @ e-mail          :lijianyou.herve@gmail.com
 * @ LastEdit        :2016/9/17
 * @ projectName     :RecycleViewCount
 * @ version
 */
public class HeadFootAdapter extends HeadFootBaseAdapter<HeadFootAdapter.ItemViewHolder, MakeFilmMenuBean> {


    public HeadFootAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    protected ItemViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(mContext).inflate(R.layout.menu_template_item, parent, false);

        return new ItemViewHolder(item);
    }

    @Override
    protected void onBindItemViewHolder(final ItemViewHolder holder, final int position) {


        holder.llFilmType.setLayoutParams(UI.getLinearLayoutPararmW2(UI.getDisplayMetrics(mContext), 70));

        holder.ivFilmType.setLayoutParams(UI.getLinearLayoutPararmW2(UI.getDisplayMetrics(mContext), 50));


        final int dataPosition = position - getHeaderViewSize();

        Log.i(TAG, "onBindViewHolder: " + dataPosition);
        MakeFilmMenuBean itemBean = data.get(dataPosition);

        holder.tvFilmType.setText(itemBean.getTv_film_type());
        holder.ivFilmType.setImageResource(itemBean.getIv_film_type());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.itemView.setClickable(false);
                /*单个数据*/
                itemChange(dataPosition, holder);

                /*所有数据刷新*/
//                dataChange(dataPosition, holder);

            }
        });

    }


    private void dataChange(int dataPosition, ItemViewHolder holder) {
        Toast.makeText(mContext, "点击了=" + dataPosition, Toast.LENGTH_SHORT).show();

        data.remove(dataPosition);

        notifyDataSetChanged();
    }

    private void itemChange(int dataPosition, ItemViewHolder holder) {
        Toast.makeText(mContext, "点击了=" + dataPosition, Toast.LENGTH_SHORT).show();

        data.remove(holder.getAdapterPosition() - getHeaderViewSize());
        notifyItemRemoved(holder.getAdapterPosition());
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.iv_film_type)
        ImageView ivFilmType;

        @Bind(R.id.tv_film_type)
        TextView tvFilmType;
        @Bind(R.id.ll_film_type)
        LinearLayout llFilmType;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
