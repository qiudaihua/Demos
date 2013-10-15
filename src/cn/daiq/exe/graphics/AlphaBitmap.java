package cn.daiq.exe.graphics;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.view.View;

import com.example.android.apis.R;

import java.io.InputStream;

public class AlphaBitmap extends Activity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        
        setContentView(new SampleView(this));
    }
    
    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }
    
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }
    
    private static class SampleView extends View {

        private Bitmap mBitmap1;
        private Bitmap mBitmap2;
        private Bitmap mBitmap3;
        private Bitmap mBitmap4;
        private Bitmap mBitmap5;

        public SampleView(Context context) {
            super(context);
            setFocusable(true);
            
            InputStream is = context.getResources().openRawResource(R.drawable.app_sample_code);
            mBitmap1 = BitmapFactory.decodeStream(is);
            mBitmap2 = mBitmap1.extractAlpha();
            
            Paint p = new Paint();
            p.setColor(Color.BLUE);
            mBitmap3 = mBitmap1.extractAlpha(p, new int[]{0,0,3,3});
            
            mBitmap4 = Bitmap.createBitmap(mBitmap1);
            //mBitmap4.eraseColor(Color.RED);
            
            mBitmap5 = Bitmap.createBitmap(200, 200, Bitmap.Config.ALPHA_8);
            float w = mBitmap5.getWidth();
            float h = mBitmap5.getHeight();
            
            Canvas c = new Canvas(mBitmap5);
            p = new Paint();
            p.setAntiAlias(false);
            p.setAlpha(0x80);
            c.drawCircle(w/2, h/2, w/2, p);
            
            p.setAlpha(0x30);
            p.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
            p.setTextSize(60);
            p.setTextAlign(Paint.Align.LEFT);
            Paint.FontMetrics fm = p.getFontMetrics();
            c.drawText("Alpha", 0, h/2-fm.ascent/2, p);
            
        }
        
        @Override
        protected void onDraw(Canvas canvas) {
            // TODO Auto-generated method stub
            canvas.drawColor(Color.WHITE);
            Paint p = new Paint();
            float y = 20;
            
            p.setColor(Color.RED);
            canvas.drawBitmap(mBitmap1, 10, y, p);
            
            y += mBitmap1.getHeight();
            canvas.drawBitmap(mBitmap2, 10, y, p);
            
            y += mBitmap2.getHeight();
            canvas.drawBitmap(mBitmap3, 10, y, p);
            
            y+= mBitmap3.getHeight();
            canvas.drawBitmap(mBitmap4, 10, y, p);
            
            Shader shader = new LinearGradient(0, 0, 100, 100,
                    new int[]{Color.RED, Color.GREEN, Color.BLUE},
                    null/*new float[]{0, 0, 50,70,150,150,180,100}*/,
                    Shader.TileMode.MIRROR);
            
            y += mBitmap4.getHeight();
            p.setShader(shader);
            canvas.drawBitmap(mBitmap5, 10, y, p);
            
            //给图片的边缘加上光晕
            //ImageView v = (ImageView) findViewById(R.id.image);// 一定要给ImageView加上几个像素的Padding，要不然效果出来不了
            Paint p2 = new Paint();
            p2.setColor(Color.RED);// 红色的光晕
            Bitmap mBitmap6 = BitmapFactory.decodeResource(getResources(), R.drawable.pentagon);//bd.getBitmap();
            Bitmap bitmap = Bitmap.createBitmap(mBitmap6.getWidth(), mBitmap6.getHeight(),
                    Config.ARGB_8888);
            Canvas canvas2 = new Canvas(bitmap);
            canvas2.drawBitmap(mBitmap6.extractAlpha(), 0, 0, p2);

            StateListDrawable sld = new StateListDrawable();
            sld.addState(new int[] {
                android.R.attr.state_pressed
            }, new BitmapDrawable(bitmap));

            y += mBitmap5.getHeight();
            canvas.drawBitmap(mBitmap6, 10, y, p);
            
            super.onDraw(canvas);
        }
        
    }
    
}
