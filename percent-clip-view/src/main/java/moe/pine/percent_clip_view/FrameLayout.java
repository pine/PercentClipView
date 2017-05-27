package moe.pine.percent_clip_view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;

/**
 * FrameLayout
 * Created by pine on May 24, 2017.
 */

public class FrameLayout extends android.widget.FrameLayout {
    private final PercentClipHelper helper;

    public FrameLayout(Context context) {
        super(context);
        this.helper = new PercentClipHelper(this, null);
    }

    public FrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.helper = new PercentClipHelper(this, attrs);
    }

    public FrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.helper = new PercentClipHelper(this, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @SuppressWarnings("unused")
    public FrameLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.helper = new PercentClipHelper(this, attrs);
    }

    @Override
    public void draw(Canvas canvas) {
        if (this.isInEditMode()) {
            super.draw(canvas);
        } else {
            this.helper.prepare(canvas);
            super.draw(this.helper.getOffscreenCanvas());
            this.helper.draw(canvas);
        }
    }

    public void setClipLeft(float clipLeft) {
        this.helper.setClipLeft(clipLeft);
    }

    public float getClipLeft() {
        return this.helper.getClipLeft();
    }

    public void setClipTop(float clipTop) {
        this.helper.setClipTop(clipTop);
    }

    public float getClipTop() {
        return this.helper.getClipTop();
    }

    public void setClipRight(float clipRight) {
        this.helper.setClipRight(clipRight);
    }

    public float getClipRight() {
        return this.helper.getClipRight();
    }

    public void setClipBottom(float clipBottom) {
        this.helper.setClipBottom(clipBottom);
    }

    public float getClipBottom() {
        return this.helper.getClipBottom();
    }
}
