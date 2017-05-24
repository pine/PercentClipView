package moe.pine.percent_clip_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * ClipHelper
 * Created by pine on May 24, 2017.
 */

class ClipHelper {
    static final class ClipParams {
        float left;
        float top;
        float right;
        float bottom;

        ClipParams(@NonNull Context context, @Nullable AttributeSet attrs) {
            if (attrs != null) {
                final TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.PercentClipView);
                this.left = array.getFloat(R.styleable.PercentClipView_clipLeft, 0f);
                this.top = array.getFloat(R.styleable.PercentClipView_clipTop, 0f);
                this.right = array.getFloat(R.styleable.PercentClipView_clipRight, 0f);
                this.bottom = array.getFloat(R.styleable.PercentClipView_clipBottom, 0f);
                array.recycle();
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ClipParams that = (ClipParams) o;

            if (Float.compare(that.left, left) != 0) return false;
            if (Float.compare(that.top, top) != 0) return false;
            if (Float.compare(that.right, right) != 0) return false;
            return Float.compare(that.bottom, bottom) == 0;

        }

        @Override
        public int hashCode() {
            int result = (left != +0.0f ? Float.floatToIntBits(left) : 0);
            result = 31 * result + (top != +0.0f ? Float.floatToIntBits(top) : 0);
            result = 31 * result + (right != +0.0f ? Float.floatToIntBits(right) : 0);
            result = 31 * result + (bottom != +0.0f ? Float.floatToIntBits(bottom) : 0);
            return result;
        }
    }

    @NonNull
    private final ClipParams params;

    @NonNull
    private final Paint paint;

    @NonNull
    private final Paint maskPaint;

    @Nullable
    private Bitmap offscreenBitmap;

    @Nullable
    private Canvas offscreenCanvas;

    @Nullable
    private Bitmap maskBitmap;

    @Nullable
    private ClipParams maskParams;

    ClipHelper(@NonNull View view, @Nullable AttributeSet attrs) {
        this.params = new ClipParams(view.getContext(), attrs);
        this.paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.maskPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
        this.maskPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        view.setWillNotDraw(false);
    }

    void prepare(@NonNull Canvas canvas) {
        this.offscreenBitmap = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
        this.offscreenCanvas = new Canvas(this.offscreenBitmap);


        int width = canvas.getWidth();
        int height = canvas.getHeight();
        /*
        boolean isEmpty = this.maskBitmap == null || this.maskParams == null;
        boolean isSizeChanged = !isEmpty && (this.maskBitmap.getWidth() != width || this.maskBitmap.getHeight() != height);
        boolean isParamsChanged = !isEmpty && !this.maskParams.equals(this.params);
*/
        //if (isEmpty || isSizeChanged || isParamsChanged) {
            this.maskBitmap = this.createMask(width, height);
            this.maskParams = this.params;
        //}
    }

    void draw(Canvas canvas) {
        if (this.offscreenCanvas == null || this.offscreenBitmap == null || this.maskBitmap == null) {
            throw new IllegalStateException();
        }

        this.offscreenCanvas.drawBitmap(this.maskBitmap, 0f, 0f, this.maskPaint);
        canvas.drawBitmap(this.offscreenBitmap, 0f, 0f, this.paint);
    }

    private Bitmap createMask(int width, int height) {
        Bitmap mask = Bitmap.createBitmap(width, height, Bitmap.Config.ALPHA_8);
        Canvas canvas = new Canvas(mask);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        canvas.drawRect(0, 0, width, height, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        canvas.drawRect(0, 0, width, height, paint);

        return mask;
    }

    @Nullable
    Canvas getOffscreenCanvas() {
        return offscreenCanvas;
    }
}
