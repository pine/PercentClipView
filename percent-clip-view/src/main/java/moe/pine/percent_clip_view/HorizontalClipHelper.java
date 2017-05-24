package moe.pine.percent_clip_view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.view.View;

/**
 * Created by pine on May 24, 2017.
 */
final class HorizontalClipHelper {
    Paint paint;
    Paint maskPaint;

    HorizontalClipHelper(View view) {
        this.paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.maskPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
        this.maskPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));

        view.setWillNotDraw(false);
    }

    Bitmap createMask(int width, int height, float ratio) {
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
}