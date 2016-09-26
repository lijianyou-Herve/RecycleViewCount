package com.example.herve.recycleviewcount.view.refresh;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.TypedValue;

import com.example.herve.recycleviewcount.R;

/**
 * The creator is Leone && E-mail: butleone@163.com
 *
 * @author Leone
 * @date 5/20/16
 * @description Edit it! Change it! Beat it! Whatever, just do it!
 */
public class DefaultRefreshEdge implements RefreshEdge {

    private final static int PICE = 6;

    private int mCurrentState;
    private final int mHeight;
    private final boolean mIsHeaderEdge;
    private final Paint mTextPaint;
    private Paint mBackgroundPaint;
    private float mPointRadius = 0;
    private float mCircleRadius = 0;
    private int mTime = 0;

    private final String mStatePullText;
    private final String mPullReleaseText;
    private final String mRefreshingText;
    private final String mRefreshSuccessText;
    private final String mRefreshFailText;

    private final String mStateLoadText;
    private final String mLoadReleaseText;
    private final String mLoadingText;
    private final String mLoadSuccessText;
    private final String mLoadFailText;

    /**
     * DefaultRefreshEdge
     * @param context context
     * @param isHeaderEdge isHeaderEdge
     */
    public DefaultRefreshEdge(Context context, boolean isHeaderEdge) {
        mHeight = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 66, context.getResources().getDisplayMetrics());
        mIsHeaderEdge = isHeaderEdge;

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setStyle(Paint.Style.FILL);
        int fontSize = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 14, context.getResources().getDisplayMetrics());
        mTextPaint.setTextSize(fontSize);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setColor(0xFF03A9F4);

        mBackgroundPaint = new Paint();
        mBackgroundPaint.setAntiAlias(true);
        mBackgroundPaint.setStyle(Paint.Style.FILL);
        mBackgroundPaint.setColor(0xFFDDDDDD);

        mPointRadius = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 2.5f, context.getResources().getDisplayMetrics());
        mCircleRadius = mPointRadius * 3.5f;

        mStatePullText = context.getString(R.string.state_pull_rest_text);
        mPullReleaseText = context.getString(R.string.state_pull_release_text);
        mRefreshingText = context.getString(R.string.state_pull_refreshing_text);
        mRefreshSuccessText = context.getString(R.string.pull_refresh_success_text);
        mRefreshFailText = context.getString(R.string.pull_refresh_fail_text);
        mStateLoadText = context.getString(R.string.state_load_rest_text);
        mLoadReleaseText = context.getString(R.string.state_load_release_text);
        mLoadingText = context.getString(R.string.state_loading_text);
        mLoadSuccessText = context.getString(R.string.state_load_success_text);
        mLoadFailText = context.getString(R.string.state_load_fail_text);
    }

    @Override
    public boolean draw(Canvas canvas, int left, int top, int right, int bottom) {
        boolean more = false;
        final int width = right - left;
        final int height = mHeight;
        final int offset = bottom - top;
        int center = Math.max(bottom - top, 0) / 2;
        if (!mIsHeaderEdge) {
            center += top;
        }

        String showText = "";
        canvas.save();
        canvas.drawRect(left, top, right, bottom, mBackgroundPaint);

        switch (mCurrentState) {
            case STATE_REST:
                break;
            case STATE_PULL:
                showText = mIsHeaderEdge ? mStatePullText : mStateLoadText;
                break;
            case STATE_RELEASE:
                showText = mIsHeaderEdge ? mPullReleaseText : mLoadReleaseText;
                break;
            case STATE_LOADING: {
                more = true;
                for (int i = 0; i < PICE; i++) {
                    int angleParam = mTime * 5;
                    float angle = -(i * (360 / PICE) - angleParam) * (float) Math.PI / 180;
                    float radius = mCircleRadius;
                    float circleX = (float) (width / 2 + radius * Math.cos(angle));
                    float circleY;
                    if (offset < height) {
                        circleY = (float) (offset - height / 2 + radius * Math.sin(angle));
                    } else {
                        circleY = (float) (offset / 2 + radius * Math.sin(angle));
                    }
                    canvas.drawCircle(circleX, circleY + top, mPointRadius, mTextPaint);
                }
                mTime ++;
            }   break;
            case STATE_SUCCESS:
                showText = mIsHeaderEdge ? mRefreshSuccessText : mLoadSuccessText;
                break;
            case STATE_FAIL:
                showText = mIsHeaderEdge ? mRefreshFailText : mLoadFailText;
                break;
        }
        if (!TextUtils.isEmpty(showText)) {
            canvas.drawText(showText, width / 2, center, mTextPaint);
        }
        canvas.restore();
        return more;
    }

    @Override
    public int getHeight() {
        return mHeight;
    }

    @Override
    public void onStateChanged(int state) {
        mCurrentState = state;
    }

    @Override
    public int getState() {
        return mCurrentState;
    }
}