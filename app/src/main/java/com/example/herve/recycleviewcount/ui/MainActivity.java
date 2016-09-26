package com.example.herve.recycleviewcount.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.herve.recycleviewcount.R;
import com.example.herve.recycleviewcount.adapter.HeadFootAdapter;
import com.example.herve.recycleviewcount.bean.MakeFilmMenuBean;
import com.example.herve.recycleviewcount.view.MenuView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @Bind(R.id.menu_view)
    MenuView menu_view;

    private Context mContext;
    ArrayList<MakeFilmMenuBean> menuBeen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContext = this;


        initTemplateData();

        final HeadFootAdapter customAdapter = new HeadFootAdapter(mContext);
        View headd = LayoutInflater.from(mContext).inflate(R.layout.recycle_head_view, menu_view, false);
        View headd2 = LayoutInflater.from(mContext).inflate(R.layout.recycle_head_view, menu_view, false);
        View headd3 = LayoutInflater.from(mContext).inflate(R.layout.recycle_head_view, menu_view, false);

        View foot = LayoutInflater.from(mContext).inflate(R.layout.recycle_head_view, menu_view, false);
        customAdapter.addHeaderView(headd);
        customAdapter.addHeaderView(headd2);
        customAdapter.addHeaderView(headd3);
        customAdapter.addFooterView(foot);
        customAdapter.setData(menuBeen);

        final int count = 2;
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, count);

        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0 || position == 1 || position == 2) {
                    return count;
                }

                if (position == customAdapter.getItemCount() - 1) {
                    return count;
                }

                return 1;
            }
        });


        menu_view.setLayoutManager(layoutManager);

        View emptyView = LayoutInflater.from(mContext).inflate(R.layout.menu_switch, null, false);

        menu_view.setAdapter(customAdapter);
        menu_view.setMenuSwitchButton(emptyView, RelativeLayout.CENTER_IN_PARENT);


    }

    private void initTemplateData() {
        menuBeen = new ArrayList<>();

        for (int position = 0; position < 8; position++) {
            MakeFilmMenuBean videoAlbumBean = new MakeFilmMenuBean(R.mipmap.make_collect_touch, getString(R.string.template_name) + position);
            menuBeen.add(videoAlbumBean);

        }


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
