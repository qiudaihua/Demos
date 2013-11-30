
package cn.daiq.other;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.example.android.apis.R;

public class BlurActivity extends Activity {
    public class Vi extends Activity {
        /** Called when the activity is first created. */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            
            MyView view = new MyView(this); 
            view.setBackgroundResource(R.drawable.daiq_wallpaper_00);
            setContentView(view);
        }

        public class MyView extends View {
            private Paint mPaint;
            private MaskFilter mBlur;

            private Bitmap mBitmap;
            private Canvas mCanvas;
            private Path mPath;
            private Paint mBitmapPaint;

            public MyView(Context c) {
                super(c);
                mPaint = new Paint();
                mPaint.setAntiAlias(true);
                mPaint.setDither(true);
                mPaint.setColor(0xFFFF0000);
                mPaint.setStyle(Paint.Style.STROKE);
                mPaint.setStrokeJoin(Paint.Join.ROUND);
                mPaint.setStrokeCap(Paint.Cap.ROUND);
                mPaint.setStrokeWidth(12);
                mBlur = new BlurMaskFilter(8, BlurMaskFilter.Blur.NORMAL);
                mPaint.setMaskFilter(mBlur);
                mBitmap = Bitmap.createBitmap(320, 480, Bitmap.Config.ARGB_8888);
                mCanvas = new Canvas(mBitmap);
                mPath = new Path();
                mBitmapPaint = new Paint(Paint.DITHER_FLAG);
            }

            @Override
            protected void onSizeChanged(int w, int h, int oldw, int oldh) {
                super.onSizeChanged(w, h, oldw, oldh);
            }

            @Override
            protected void onDraw(Canvas canvas) {
                canvas.drawColor(0xFFAAAAAA);

                canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);

                canvas.drawPath(mPath, mPaint);
            }

            private float mX, mY;
            private static final float TOUCH_TOLERANCE = 4;

            private void touch_start(float x, float y) {
                mPath.reset();
                mPath.moveTo(x, y);
                mX = x;
                mY = y;
            }

            private void touch_move(float x, float y) {
                float dx = Math.abs(x - mX);
                float dy = Math.abs(y - mY);
                if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
                    mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
                    mX = x;
                    mY = y;
                }
            }

            private void touch_up() {
                mPath.lineTo(mX, mY);
                // commit the path to our offscreen
                mCanvas.drawPath(mPath, mPaint);
                // kill this so we don't double draw
                mPath.reset();
            }

            @Override
            public boolean onTouchEvent(MotionEvent event) {
                float x = event.getX();
                float y = event.getY();

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        touch_start(x, y);
                        invalidate();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        touch_move(x, y);
                        invalidate();
                        break;
                    case MotionEvent.ACTION_UP:
                        touch_up();
                        invalidate();
                        break;
                }
                return true;
            }
        }
    }
}
