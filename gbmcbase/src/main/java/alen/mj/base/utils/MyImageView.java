package alen.mj.base.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatImageView;

public class MyImageView extends AppCompatImageView {

    protected Context mContext;

    public MyImageView(Context context) {
        super(context);
        mContext = context;
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public MyImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (getDrawable() == null) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }
        int viewWidth = MeasureSpec.getSize(widthMeasureSpec);
        int viewHeight = viewWidth * getDrawable().getIntrinsicHeight() / getDrawable().getIntrinsicWidth();
        setMeasuredDimension(viewWidth, viewHeight);
    }
}