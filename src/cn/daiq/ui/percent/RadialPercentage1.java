
package cn.daiq.ui.percent;

import android.R;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class RadialPercentage1 extends View {

    //分数：fractions

    //分子：numerator

    //分母：denominator

    private int roundColor;

    private int roundNumeratorColor;

    private int textColor;

    private float textSize;

    private float roundWidth;
    private float oneWidth;

    private boolean textIsDisplayable;

    private float numerator = 92;
    private float denominator = 100;

    private float startAngle = 0;

    public RadialPercentage1(Context context) {
        this(context, null);
    }

    public RadialPercentage1(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RadialPercentage1(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        /*TypedArray mTypedArray = context.obtainStyledAttributes(attrs,
                R.styleable.RoundnumeratorBar);*/

        /*roundColor = mTypedArray.getColor(R.styleable.RoundnumeratorBar_roundColor, Color.RED);
        roundnumeratorColor = mTypedArray.getColor(R.styleable.RoundnumeratorBar_roundnumeratorColor,
                Color.GREEN);
        textColor = mTypedArray.getColor(R.styleable.RoundnumeratorBar_textColor, Color.GREEN);
        textSize = mTypedArray.getDimension(R.styleable.RoundnumeratorBar_textSize, 15);
        roundWidth = mTypedArray.getDimension(R.styleable.RoundnumeratorBar_roundWidth, 5);
        denominator = mTypedArray.getInteger(R.styleable.RoundnumeratorBar_max, 100);
        textIsDisplayable = mTypedArray.getBoolean(R.styleable.RoundnumeratorBar_textIsDisplayable,
                true);
        style = mTypedArray.getInt(R.styleable.RoundnumeratorBar_style, 0);

        mTypedArray.recycle();*/

        numerator = 92;
        denominator = 100;
        textIsDisplayable = true;

        roundColor = 0xffd3d3d3;
        roundNumeratorColor = 0xff00b248;
        textColor = 0xff444444; //0xff323232
        textSize = 100;
        roundWidth = 100;
        oneWidth = 1;
        startAngle = 0;
        setBackgroundColor(Color.WHITE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        final float percentWidth = 1;

        int centre = getWidth() / 2;
        int radius = (int) (centre - roundWidth / 2);
        float percentAngle = 360 / denominator;
        int percentNum = (int) (100 * numerator / denominator);

        //draw percent text
        if (textIsDisplayable) {
            paint.setStrokeWidth(0);
            paint.setColor(textColor);
            paint.setTextSize(textSize);
            paint.setTypeface(Typeface.DEFAULT_BOLD);
            String percentText = percentNum + "%";
            float textWidth = paint.measureText(percentText);
            canvas.drawText(percentText, centre - textWidth / 2, centre + textSize / 2, paint);
        }

        //draw outsize ring
        //paint.setStyle(Paint.Style.STROKE);
        //paint.setStrokeWidth(roundWidth);
        //paint.setAntiAlias(true);
        //canvas.drawCircle(centre, centre, radius, paint);

        //draw percent ring
        paint.setStrokeWidth(roundWidth);
        paint.setColor(roundNumeratorColor);
        paint.setStyle(Paint.Style.STROKE);
        RectF oval = new RectF(centre - radius, centre - radius,
                centre + radius, centre + radius);
        //canvas.drawArc(oval, startAngle - 90, 360 * numerator / denominator, false, paint);
        for (int percent = 0; percent < denominator; percent++) {
            if (percent < numerator) {
                paint.setColor(roundNumeratorColor);
            } else {
                paint.setColor(roundColor);
            }
            float start = startAngle - 90 + (percent * percentAngle - percentWidth / 2);
            canvas.drawArc(oval, start, percentWidth, false, paint);
            Log.d("daiq", "start="+start);
            Log.d("daiq", "percentAngle=" + percentAngle);
        }

    }

    public synchronized int getMax() {
        return (int) denominator;
    }

    /** 
     * 设置进度的最大值 
     * @param denominator 
     */
    public synchronized void setMax(int denominator) {
        if (denominator < 0) {
            throw new IllegalArgumentException("denominator not less than 0");
        }
        this.denominator = denominator;
    }

    /** 
     * 获取进度.需要同步 
     * @return 
     */
    public synchronized int getnumerator() {
        return (int) numerator;
    }

    /** 
     * 设置进度，此为线程安全控件，由于考虑多线的问题，需要同步 
     * 刷新界面调用postInvalidate()能在非UI线程刷新 
     * @param numerator 
     */
    public synchronized void setnumerator(int numerator) {
        if (numerator < 0) {
            throw new IllegalArgumentException("numerator not less than 0");
        }
        if (numerator > denominator) {
            numerator = (int) denominator;
        }
        if (numerator <= denominator) {
            this.numerator = numerator;
            postInvalidate();
        }

    }

    public synchronized void setFractions(int numerator, int denominator) {
        if (numerator < 0) {
            throw new IllegalArgumentException("numerator not less than 0");
        }
        if (denominator < 0) {
            throw new IllegalArgumentException("denominator not less than 0");
        }
        if (numerator > denominator) {
            numerator = (int) denominator;
        }
        if (numerator <= denominator) {
            this.numerator = numerator;
            this.denominator = denominator;
            postInvalidate();
        }
    }

    public int getCricleColor() {
        return roundColor;
    }

    public void setCricleColor(int cricleColor) {
        this.roundColor = cricleColor;
    }

    public int getCriclenumeratorColor() {
        return roundNumeratorColor;
    }

    public void setCriclenumeratorColor(int cricleNumeratorColor) {
        this.roundNumeratorColor = cricleNumeratorColor;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public float getTextSize() {
        return textSize;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public float getRoundWidth() {
        return roundWidth;
    }

    public void setRoundWidth(float roundWidth) {
        this.roundWidth = roundWidth;
    }

}
