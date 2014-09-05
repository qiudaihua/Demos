package cn.daiq.ui.percent;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import com.example.android.apis.R;

public class RadialPercentage extends View {

    private static final int DEFAULT_REMAINING_COLOR = 0xffd3d3d3;
    private static final int DEFAULT_OCCUPANCY_COLOR = 0xff00b248;
    private static final int DEFAULT_BACKGROUND_COLOR = Color.TRANSPARENT;
    private static final int DEFAULT_TEXT_COLOR = 0xff444444; //0xff323232
    private static final float DEFAULT_TEXT_SIZE = 50;
    private static final float DEFAULT_ROUND_WIDTH = 80;
    private static final float DEFAULT_START_ANGLE = 0 - 90;
    private static final float DEFAULT_PERCENT_WIDTH = 1;
    private static final float DEFAULT_NUMERATOR = 0;
    private static final float DEFAULT_DENOMINATOR = 100;

    private int remainingColor = DEFAULT_REMAINING_COLOR;
    private int occupancyColor = DEFAULT_OCCUPANCY_COLOR;
    private int backgroundColor = DEFAULT_BACKGROUND_COLOR;

    private boolean textIsDisplayable = true;
    private int percentTextColor = DEFAULT_TEXT_COLOR;
    private float percentTextSize = DEFAULT_TEXT_SIZE;
    private float percentTextSizeAuto = DEFAULT_TEXT_SIZE;

    private float roundWidth = DEFAULT_ROUND_WIDTH;

    private float startAngle = DEFAULT_START_ANGLE;
    private float percentWidth = DEFAULT_PERCENT_WIDTH;

    private float numerator = DEFAULT_NUMERATOR;
    private float denominator = DEFAULT_DENOMINATOR;

    public RadialPercentage(Context context) {
        this(context, null);
    }

    public RadialPercentage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RadialPercentage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.radialPercentage);
        remainingColor = typedArray.getColor(
                R.styleable.radialPercentage_remainingColor,
                DEFAULT_REMAINING_COLOR);
        occupancyColor = typedArray.getColor(
                R.styleable.radialPercentage_occupancyColor,
                Color.GREEN);

        textIsDisplayable = typedArray.getBoolean(
                R.styleable.radialPercentage_textIsDisplayable,
                true);
        percentTextColor = typedArray.getColor(
                R.styleable.radialPercentage_percentTextColor,
                DEFAULT_TEXT_COLOR);
        percentTextSize = typedArray.getDimension(
                R.styleable.radialPercentage_percentTextSize,
                DEFAULT_TEXT_SIZE);
        roundWidth = typedArray.getDimension(
                R.styleable.radialPercentage_roundWidth,
                DEFAULT_ROUND_WIDTH);
        startAngle = typedArray.getInteger(
                R.styleable.radialPercentage_startAngle,
                (int) DEFAULT_START_ANGLE);
        percentWidth = typedArray.getInteger(
                R.styleable.radialPercentage_percentWidth,
                (int) DEFAULT_PERCENT_WIDTH);
        numerator = typedArray.getInteger(
                R.styleable.radialPercentage_numerator,
                (int) DEFAULT_NUMERATOR);
        denominator = typedArray.getInteger(
                R.styleable.radialPercentage_denominator,
                (int) DEFAULT_DENOMINATOR);
        typedArray.recycle();

        //setBackgroundColor(backgroundColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        final int centre = getCentreSize();
        if (roundWidth < centre / 5 || roundWidth > centre / 2) {
            roundWidth = centre / 3;
        }
        final int radius = (int) (centre - roundWidth / 2);
        final float percentAngle = 360 / denominator;
        final int percentNum = (int) (100 * numerator / denominator);
        Paint paint = new Paint();
        paint.setAntiAlias(true);

        //draw percent text
        percentTextSizeAuto = getPercentTextSizeAuto(percentTextSize);
        if (textIsDisplayable) {
            paint.setStrokeWidth(0);
            paint.setColor(percentTextColor);
            paint.setTextSize(percentTextSizeAuto);
            paint.setTypeface(Typeface.DEFAULT_BOLD);
            String percentText = percentNum + "%";
            float textWidth = paint.measureText(percentText);
            canvas.drawText(percentText, centre - textWidth / 2, centre + percentTextSizeAuto / 2,
                    paint);
        }

        //draw percent ring
        paint.setStrokeWidth(roundWidth);
        paint.setStyle(Paint.Style.STROKE);
        RectF oval = new RectF(centre - radius, centre - radius,
                centre + radius, centre + radius);
        for (int percent = 0; percent < denominator; percent++) {
            if (percent < numerator) {
                paint.setColor(occupancyColor);
            } else {
                paint.setColor(remainingColor);
            }
            float start = startAngle + (percent * percentAngle - percentWidth / 2);
            canvas.drawArc(oval, start, percentWidth, false, paint);
        }

    }

    private int getCentreSize() {
        final int width = getWidth();
        final int height = getHeight();
        return (width < height ? width : height) / 2;
    }

    public synchronized int getDenominator() {
        return (int) denominator;
    }

    public synchronized void setDenominator(int denominator) {
        if (denominator < 0) {
            throw new IllegalArgumentException("denominator not less than 0");
        }
        this.denominator = denominator;
        postInvalidate();
    }

    public synchronized int getNumerator() {
        return (int) numerator;
    }

    public synchronized void setnumerator(int numerator) {
        if (numerator < 0) {
            throw new IllegalArgumentException("numerator not less than 0");
        }
        if (numerator > this.denominator) {
            numerator = (int) this.denominator;
        }
        if (numerator <= this.denominator) {
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

    public int getRemainingColor() {
        return remainingColor;
    }

    public void setRemainingColor(int remainingColor) {
        this.remainingColor = remainingColor;
    }

    public int getOccupancyColor() {
        return occupancyColor;
    }

    public void setOccupancyColor(int occupancyColor) {
        this.occupancyColor = occupancyColor;
    }

    public int getPercentTextColor() {
        return percentTextColor;
    }

    public void setPercentTextColor(int percentTextColor) {
        this.percentTextColor = percentTextColor;
    }

    public float getPercentTextSize() {
        return percentTextSize;
    }

    public void setPercentTextSize(float percentTextSize) {
        this.percentTextSize = percentTextSize;
    }

    private float getPercentTextSizeAuto(float percentTextSize) {
        final int centre = getCentreSize();
        float percentTextSizeAuto = (centre - roundWidth) / 2;
        if (percentTextSize > 0 && percentTextSize < percentTextSizeAuto) {
            percentTextSizeAuto = percentTextSize;
        }
        return percentTextSizeAuto;
    }

    public float getPercentTextSizeAuto() {
        return percentTextSizeAuto;
    }

    public float getRoundWidth() {
        return roundWidth;
    }

    public void setRoundWidth(float roundWidth) {
        this.roundWidth = roundWidth;
    }

}
