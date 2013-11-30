package cn.daiq.src;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.BlurMaskFilter.Blur;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.example.android.apis.R;

public class BlurFrameLayout extends FrameLayout {
    
    private static final int OFFSET = 10;
    private static final int RADIUS = 20;
    private static final int BITMAP_WIDTH = 100;
    private static final int BITMAP_HEIGHT = 100;
    private static final int CENTER = BITMAP_HEIGHT / 2;
    
    //LayoutInflater 需要重载这个构造函数，不然会出现错误
    public BlurFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub

        BlurMaskFilter filter = new BlurMaskFilter(RADIUS, Blur.INNER);
        Paint paint = new Paint();
        paint.setMaskFilter(filter);
        paint.setColor(Color.WHITE);
        paint.setAlpha(160);
        //this.getBackground().setAlpha(100);
        canvas.drawRect(CENTER - OFFSET, CENTER - OFFSET, CENTER + OFFSET, CENTER + OFFSET, paint);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.daiq_wallpaper_00);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        

        final Bitmap result = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(),
                Bitmap.Config.ARGB_8888);
        /*final Canvas dest = new Canvas(result);

        dest.drawColor(0, PorterDuff.Mode.CLEAR);

        Paint mBlurPaint = new Paint();
        mBlurPaint.setMaskFilter(new BlurMaskFilter(RADIUS, BlurMaskFilter.Blur.NORMAL));
        int[] xy = new int[2];
        Bitmap src = BitmapFactory.decodeResource(getResources(), R.drawable.daiq_wallpaper_00);
        Bitmap mask = src.extractAlpha(mBlurPaint, xy);

        Paint mGlowColorPressedPaint = new Paint();
        mGlowColorPressedPaint.setColor(0xffffc300);
        dest.drawBitmap(mask, xy[0], xy[1], mGlowColorPressedPaint);

        mask.recycle();

        Paint mPaint = new Paint();
        dest.drawBitmap(src, 0, 0, mPaint);
        dest.setBitmap(null);

        super.onDraw(dest);*/
        super.onDraw(canvas);
    }

}
