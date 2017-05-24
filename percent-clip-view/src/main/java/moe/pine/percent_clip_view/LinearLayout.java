package moe.pine.percent_clip_view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;

/**
 * LinearLayout
 * Created by pine on May 24, 2017.
 */

public class LinearLayout extends android.widget.LinearLayout {
    private final PercentClipHelper helper;

    public LinearLayout(Context context) {
        super(context);
        this.helper = new PercentClipHelper(this, null);
    }

    public LinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.helper = new PercentClipHelper(this, attrs);
    }

    public LinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.helper = new PercentClipHelper(this, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @SuppressWarnings("unused")
    public LinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.helper = new PercentClipHelper(this, attrs);
    }

    @Override
    public void draw(Canvas canvas) {
        this.helper.prepare(canvas);
        super.draw(this.helper.getOffscreenCanvas());
        this.helper.draw(canvas);
    }

    public void setClipLeft(float clipLeft) {
        this.helper.setClipLeft(clipLeft);
    }

    public void setClipTop(float clipTop) {
        this.helper.setClipTop(clipTop);
    }

    public void setClipRight(float clipRight) {
        this.helper.setClipRight(clipRight);
    }

    public void setClipBottom(float clipBottom) {
        this.helper.setClipBottom(clipBottom);
    }
}
