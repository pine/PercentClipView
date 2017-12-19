package moe.pine.percent_clip_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * ClipParams
 * Created by pine on 2017/05/28.
 */

final class ClipParams implements Cloneable {
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

    @Override
    protected ClipParams clone() {
        try {
            return (ClipParams) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
