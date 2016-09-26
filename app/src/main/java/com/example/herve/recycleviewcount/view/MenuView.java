package com.example.herve.recycleviewcount.view;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.herve.recycleviewcount.R;


/**
 * Created           :Herve on 2016/9/13.
 *
 * @ Author          :Herve
 * @ e-mail          :lijianyou.herve@gmail.com
 * @ LastEdit        :2016/9/13
 * @ projectName     :RecycleViewCount
 * @ version
 */
public class MenuView extends LinearLayout {


    private RecyclerView menuRecycleView;
    private RelativeLayout menuSwitchButton;
    private View menuSwitchView;
    private SwipeRefreshLayout swipeRefreshLayout;

    private Context mContext;


    /*动画*/
    LayoutAnimationController layoutAnimationUP;
    LayoutAnimationController layoutAnimationDown;
    /*属性变量*/
    private Animation animation;
    private AnimationSet set;
    private LayoutAnimationController controllerUP;
    private LayoutAnimationController controllerDown;
    private int position = 0;


    private RecyclerView.LayoutManager layoutManager;

    private int menuState = 2;
    private final int MENU_STATE_DOING = 0;
    private final int MENU_STATE_OPED = 1;
    private final int MENU_STATE_CLOSED = 2;

    private RecyclerView.Adapter adapter;
    private String TAG = getClass().getSimpleName();

    public MenuView(Context context) {
        super(context);
        init(context);
    }


    public MenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    public MenuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {

        mContext = context;


        View rootView = LayoutInflater.from(mContext).inflate(R.layout.menu_view_layout, this, false);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout);
        menuRecycleView = (RecyclerView) rootView.findViewById(R.id.menu_recycle_view);
        menuSwitchButton = (RelativeLayout) rootView.findViewById(R.id.menu_switch_button);


        layoutAnimationUP = getAnimationControllerUP();
        layoutAnimationDown = getAnimationControllerDown();

        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.BLACK, Color.MAGENTA);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i(TAG, "run: 下拉监听");

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.i(TAG, "run: 下拉消失");
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },3000);

            }
        });

//        menuRecycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//
//            boolean refreshAble = true;
//
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                Log.i(TAG, "onScrolled: 可以下拉??=" + refreshAble);
//
//                swipeRefreshLayout.setEnabled(refreshAble);
//
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                int firstPosition = -1;
//                int lastVisibleItemPosition = -1;
//                if (layoutManager == null) {
//                    return;
//                }
//                if (layoutManager instanceof GridLayoutManager) {
//                    firstPosition = ((GridLayoutManager) layoutManager).findFirstVisibleItemPosition();
//                    lastVisibleItemPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
//                }
//                if (firstPosition == 0 && dy < 0) {
//                    if (!swipeRefreshLayout.isRefreshing()) {
//                        refreshAble = true;
//                        Log.i(TAG, "onScrolled: 可以下拉" + dy);
//                    }
//
//                } else {
//                    refreshAble = false;
//
//                    Log.i(TAG, "onScrolled: 不能下拉" + dy);
//
//                }
//                super.onScrolled(recyclerView, dx, dy);
//            }
//        });
        addView(rootView);


    }


    //向上的动画
    protected LayoutAnimationController getAnimationControllerUP() {
        int duration = 200;
        set = new AnimationSet(true);

        controllerUP = new LayoutAnimationController(set, 0.5f);

        animation = new TranslateAnimation(0, 0, 2500, -30);
        animation.setFillAfter(false);
        controllerUP.setOrder(LayoutAnimationController.ORDER_NORMAL);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                position++;
                if (position == adapter.getItemCount()) {
                    position = adapter.getItemCount() - 1;
                    menuState = MENU_STATE_OPED;
                    menuSwitchView.setClickable(true);

                }

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animation.setDuration(duration);
        set.addAnimation(animation);

        return controllerUP;
    }

    //向下的动画
    protected LayoutAnimationController getAnimationControllerDown() {
        int duration = 200;
        set = new AnimationSet(true);

        controllerDown = new LayoutAnimationController(set, 0.5f);
        animation = new TranslateAnimation(0, 0, 0, 2500);
        animation.setFillAfter(false);
        controllerDown.setOrder(LayoutAnimationController.ORDER_REVERSE);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {


                menuRecycleView.getChildAt(position--).layout(0, 0, 0, 0);
                if (position == -1) {
                    position = 0;
                    menuState = MENU_STATE_CLOSED;
                    menuSwitchView.setClickable(true);
//                    rl_blur_layout.setVisibility(GONE);
                }

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animation.setDuration(duration);
        set.addAnimation(animation);

        return controllerDown;
    }


    /**
     * Sets layout manager.
     *
     * @param layoutManager the layout
     */
    public void setLayoutManager(final RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
        menuRecycleView.setLayoutManager(layoutManager);

    }


    /**
     * Sets adapter.
     *
     * @param adapter the adapter
     */
    public void setAdapter(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
        menuRecycleView.setAdapter(adapter);


    }


    /**
     * Sets menu switch button.
     *
     * @param view the view
     * @param verb the verb  RelativeLayout.CENTER_IN_PARENT 居中  RelativeLayout.ALIGN_PARENT_TOP 顶部 .....
     */
    public void setMenuSwitchButton(View view, int verb) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(verb);
        menuSwitchButton.addView(view, layoutParams);
        menuSwitchView = view;
        menuSwitchView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                startAnimation();

            }
        });
    }


    //展示动画
    public void startAnimation() {

        if (menuState == MENU_STATE_CLOSED) {

            openMenu();

        } else if (menuState == MENU_STATE_OPED) {

            closeMenu();

        }

    }

    //打开
    public void openMenu() {

        if (menuState == MENU_STATE_CLOSED) {

            menuState = MENU_STATE_DOING;
//            menuSwitchView.setClickable(false);

//            rl_blur_layout.setVisibility(VISIBLE);
//            tv_start_to_make.setText(mContext.getString(R.string.close));

//            ll_make_new_film_btn.startAnimation(rotateAnimation_rota_to_45);

            adapter.notifyDataSetChanged();
            menuRecycleView.setLayoutAnimation(layoutAnimationUP);
            menuRecycleView.startLayoutAnimation();

        } else {
            //不处理
        }
    }

    //关闭
    public void closeMenu() {
        if (menuState == MENU_STATE_OPED) {

            menuState = MENU_STATE_DOING;
//            menuSwitchView.setClickable(false);

//            tv_start_to_make.setText(mContext.getString(R.string.start_to_make));

//            ll_make_new_film_btn.startAnimation(rotateAnimation_rota_to_0);

            menuRecycleView.setLayoutAnimation(layoutAnimationDown);
            menuRecycleView.startLayoutAnimation();

        } else {
            //不处理

        }

    }

}
