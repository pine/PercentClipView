package moe.pine.percent_clip_view;

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
 * PercentClipHelper
 * Created by pine on May 24, 2017.
 */

final class PercentClipHelper {
    @NonNull
    private final View view;

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

    PercentClipHelper(@NonNull View view, @Nullable AttributeSet attrs) {
        this.view = view;
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

        boolean isEmpty = this.maskBitmap == null || this.maskParams == null;
        boolean isSizeChanged = !isEmpty && (this.maskBitmap.getWidth() != width || this.maskBitmap.getHeight() != height);
        boolean isParamsChanged = !isEmpty && !this.maskParams.equals(this.params);

        // Cache mask bitmap
        if (isEmpty || isSizeChanged || isParamsChanged) {
            this.maskBitmap = this.createMask(width, height);
            this.maskParams = this.params.clone();
        }
    }

    void draw(Canvas canvas) {
        if (this.offscreenCanvas == null || this.offscreenBitmap == null || this.maskBitmap == null) {
            throw new IllegalStateException("not initialized");
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

        if (this.params.left + this.params.right >= 1) return mask;
        if (this.params.top + this.params.bottom >= 1) return mask;

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        canvas.drawRect(
                width * this.params.left, height * this.params.top,
                width * (1f - this.params.right), height * (1f - this.params.bottom),
                paint);
        return mask;
    }

    @Nullable
    Canvas getOffscreenCanvas() {
        return offscreenCanvas;
    }

    void setClipLeft(float clipLeft) {
        this.params.left = clipLeft;
        this.view.invalidate();
    }

    float getClipLeft() {
        return this.params.left;
    }

    void setClipTop(float clipTop) {
        this.params.top = clipTop;
        this.view.invalidate();
    }

    float getClipTop() {
        return this.params.top;
    }

    void setClipRight(float clipRight) {
        this.params.right = clipRight;
        this.view.invalidate();
    }

    float getClipRight() {
        return this.params.right;
    }

    void setClipBottom(float clipBottom) {
        this.params.bottom = clipBottom;
        this.view.invalidate();
    }

    float getClipBottom() {
        return this.params.bottom;
    }
}
