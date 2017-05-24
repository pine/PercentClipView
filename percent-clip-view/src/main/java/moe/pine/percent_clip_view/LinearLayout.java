package moe.pine.percent_clip_view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;

/**
 * LinearLayout
 * Created by pine on May 24, 2017.
 */

public class LinearLayout extends android.widget.LinearLayout {
    //private final ClipHelper helper;

    private final HorizontalClipHelper helper;

    public LinearLayout(Context context) {
        super(context);
        this.helper = new HorizontalClipHelper(this);
    }

    public LinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.helper = new HorizontalClipHelper(this);
    }

    public LinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.helper = new HorizontalClipHelper(this);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @SuppressWarnings("unused")
    public LinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.helper = new HorizontalClipHelper(this);
    }

    @Override
    public void draw(Canvas canvas) {
        Bitmap offscreenBitmap = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas offscreenCanvas = new Canvas(offscreenBitmap);

        super.draw(offscreenCanvas);

        Bitmap mask = this.helper.createMask(canvas.getWidth(), canvas.getHeight(), 1f);

        offscreenCanvas.drawBitmap(mask, 0f, 0f, helper.maskPaint);
        canvas.drawBitmap(offscreenBitmap, 0f, 0f, helper.paint);
    }
}
