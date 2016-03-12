package com.ccg.slidingview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/3/11 0011.
 */
public class FloatButton extends LinearLayout {

    private static final String TAG = "FloatButton";
    private ImageView mImageView;

    private int mIconDrawable;
    private int mImageViewColor;
    private float mImageViewWidth;
    private float mImageViewHight;


    private TextView mTextView;
    private float mTextViewSize;
    private float mTextViewHight;
    private float mTextViewWidth;
    private int mTextViewColor;
    private String mTitle;


    public FloatButton(Context context) {
        super(context);
    }

    public FloatButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attributeSet) {
        TypedArray attr = context.obtainStyledAttributes(attributeSet, R.styleable.SlidingView, 0, 0);
        mIconDrawable = attr.getResourceId(R.styleable.SlidingView_ccg_iconsrc, 0);
        mImageViewWidth = attr.getDimension(R.styleable.SlidingView_ccg_iconwidth, getDimension(R.dimen.icon_width));
        mImageViewHight = attr.getDimension(R.styleable.SlidingView_ccg_iconhight, getDimension(R.dimen.icon_hight));
        mImageViewColor = attr.getColor(R.styleable.SlidingView_ccg_iconcolor, getColor(android.R.color.black));

        mTextViewSize = attr.getDimension(R.styleable.SlidingView_ccg_tvsize, R.dimen.tv_size);
        mTextViewColor = attr.getColor(R.styleable.SlidingView_ccg_tvcolor, getColor(android.R.color.black));
        mTextViewWidth = attr.getDimension(R.styleable.SlidingView_ccg_tvwidth, LayoutParams.WRAP_CONTENT);
        mTextViewHight = attr.getDimension(R.styleable.SlidingView_ccg_tvhight, LayoutParams.WRAP_CONTENT);
        mTitle = attr.getString(R.styleable.SlidingView_ccg_tvtext);
        setOrientation(HORIZONTAL);
        setGravity(Gravity.RIGHT |Gravity.CENTER);
        mImageView = new ImageView(context);
        mTextView = new TextView(context);
        initData();

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
        );

        layoutParams.rightMargin = 10;
        addView(mTextView, layoutParams);
        addView(mImageView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    private void initData() {
        setText();
        setBitmap();
    }

    private void setText() {
        mTextView.setLayoutParams(new LayoutParams((int) mTextViewWidth, (int) mTextViewHight));
        mTextView.setTextSize(mTextViewSize);
        mTextView.setText(mTitle);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setBitmap() {
        mImageView.setLayoutParams(new LayoutParams(dip2px(mImageView.getContext(), mImageViewWidth),dip2px(mImageView.getContext(), mImageViewHight)));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mImageView.setBackground(getResources().getDrawable(mIconDrawable));
        } else {
            mImageView.setBackgroundResource(mIconDrawable);
        }
    }

    int getColor(@ColorRes int id) {
        return getResources().getColor(id);
    }

    float getDimension(@DimenRes int id) {
        return getResources().getDimension(id);
    }

    public FloatButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
