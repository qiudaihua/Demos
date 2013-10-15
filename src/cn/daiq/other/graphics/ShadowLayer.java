
package cn.daiq.other.graphics;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import com.example.android.apis.R;

public class ShadowLayer extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(new drawCanvas(this));
        setContentView(new ImageEffect(this));
    }

    class drawCanvas extends View {

        public drawCanvas(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawColor(Color.WHITE);
            super.onDraw(canvas);

            // 建立 Paint 物件
            Paint vPaint = new Paint();
            Paint vPaint2 = new Paint();

            // --------------------------------------------
            // 设定颜色
            vPaint.setColor(0xFFFFFF00);

            // 实心矩形
            canvas.drawRect(30
                    , 50
                    , 130
                    , 150
                    , vPaint
                    );

            // 设定阴影 (柔边, X轴位移, Y轴位移, 阴影颜色)
            vPaint.setShadowLayer(5, 3, 3, 0xFFFF00FF);

            // 实心矩形 & 其阴影
            canvas.drawRect(30
                    , 200
                    , 130
                    , 300
                    , vPaint
                    );

            // --------------------------------------------
            // 设定颜色
            vPaint2.setColor(0xFFFFFF00);

            // 空心
            vPaint2.setStyle(Style.STROKE);

            // 空心矩形
            canvas.drawRect(200
                    , 50
                    , 300
                    , 150
                    , vPaint2
                    );

            // 设定阴影 (柔边, X轴位移, Y轴位移, 阴影颜色)
            vPaint2.setShadowLayer(5, 3, 3, 0xFFFF00FF);

            // 空心矩形 & 其阴影
            canvas.drawRect(200
                    , 200
                    , 300
                    , 300
                    , vPaint2
                    );
        }
    }

    class ImageEffect extends View {
        Paint paint;

        public ImageEffect(Context context) {
            super(context);
            paint = new Paint(); // 初始化画笔，为后面阴影效果使用。
            paint.setAntiAlias(true);// 去除锯齿。
            paint.setShadowLayer(5f, 5.0f, 5.0f, Color.BLACK); // 设置阴影层，这是关键。
            paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));

        }

        public void onDraw(Canvas canvas) {
            canvas.drawColor(Color.WHITE);
            super.onDraw(canvas);
            int posX = 20;
            int posY = 50;
            int PicWidth, PicHeight;

            Drawable drawable = getResources().getDrawable(R.drawable.app_sample_code);
            Drawable dbe = getResources().getDrawable(R.drawable.app_sample_code).mutate();// 如果不调用mutate方法，则原图也会被改变，因为调用的资源是同一个，所有对象是共享状态的。
            Drawable drawTest = getResources().getDrawable(R.drawable.app_sample_code);
            Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.app_sample_code);
            PicWidth = drawable.getIntrinsicWidth();
            PicHeight = drawable.getIntrinsicHeight();

            drawTest.setBounds(posX, (2 * posY) + PicHeight, posX + PicWidth, (2 * posY) + 2
                    * PicHeight);
            drawable.setBounds(posX, posY, posX + PicWidth, posY + PicHeight);
            dbe.setBounds(0, 0, PicWidth, PicHeight);

            canvas.drawColor(Color.WHITE);// 设置画布颜色
            canvas.save(Canvas.MATRIX_SAVE_FLAG);
            dbe.setColorFilter(0x7f000000, PorterDuff.Mode.SRC_IN);
            canvas.translate(posX + (int) (0.9 * PicWidth / 2), posY + PicHeight / 2);// 图像平移为了刚好在原图后形成影子效果。
            canvas.skew(-0.9F, 0.0F);// 图像倾斜效果。
            canvas.scale(1.0f, 0.5f);// 图像（其实是画布）缩放，Y方向缩小为1/2。
            dbe.draw(canvas);// 此处为画原图像影子效果图，比原图先画，则会在下层。
            drawable.clearColorFilter();
            canvas.restore();

            canvas.save(Canvas.MATRIX_SAVE_FLAG);
            drawable.draw(canvas);// 此处为画原图像，由于canvas有层次效果，因此会盖在影子之上。
            canvas.restore();

            // 默认无效果原图
            canvas.save(Canvas.MATRIX_SAVE_FLAG);
            drawTest.draw(canvas);
            canvas.restore();

            // 图片阴影效果
            Rect rect = new Rect(2 * posX + PicWidth + 3, 2 * posY + PicHeight + 3, 2 * posX + 2
                    * PicWidth - 2, 2 * posY + 2 * PicHeight - 2);
            // 由于图片的实际尺寸比显示出来的图像要大一些，因此需要适当更改下大小，以达到较好的效果

            RectF rectF = new RectF(rect);
            canvas.drawRoundRect(rectF, 10f, 10f, paint);// 在原有矩形基础上，画成圆角矩形，同时带有阴影层。
            canvas.drawBitmap(bmp, 2 * posX + PicWidth, 2 * posY + PicHeight, null);// 画上原图。
            canvas.restore();

            // add
/*            Bitmap photo = BitmapFactory.decodeResource(getResources(), R.drawable.app_sample_code);
            int width = photo.getWidth();
            int height = photo.getHeight();
            Bitmap icon = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888); // 建立一个空的BItMap
            Canvas canvas2 = new Canvas(icon);// 初始化画布 绘制的图像到icon上

            Paint photoPaint = new Paint(); // 建立画笔
            photoPaint.setDither(true); // 获取跟清晰的图像采样
            photoPaint.setFilterBitmap(true);// 过滤一些

            Rect src = new Rect(0, 0, photo.getWidth(), photo.getHeight());// 创建一个指定的新矩形的坐标
            Rect dst = new Rect(0, 0, width, height);// 创建一个指定的新矩形的坐标
            canvas2.drawBitmap(photo, src, dst, photoPaint);// 将photo 缩放或则扩大到
                                                            // dst使用的填充区photoPaint

            Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DEV_KERN_TEXT_FLAG);// 设置画笔
            textPaint.setTextSize(20.0f);// 字体大小
            textPaint.setTypeface(Typeface.DEFAULT_BOLD);// 采用默认的宽度
            textPaint.setColor(Color.RED);// 采用的颜色
            textPaint.setShadowLayer(3f, 1, 1,
                    getResources().getColor(android.R.color.background_dark));// 影音的设置
            canvas2.drawText("Text", 20, 26, textPaint);// 绘制上去 字，开始未知x,y采用那只笔绘制
            
            canvas.drawBitmap(photo, 3 * posX + PicWidth, 2 * posY, null);//水印
            canvas.restore();*/
        }
    }
}
