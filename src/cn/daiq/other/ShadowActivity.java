
package cn.daiq.other;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.widget.TextView;

public class ShadowActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(new drawCanvas(this));
        TextView tx = new TextSubView(this);
        tx.setText("3333333");
        tx.setBackgroundColor(0xFF0000FF);
        setContentView(tx);
    }

    class drawCanvas extends View {

        public drawCanvas(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
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
    
    public class TextSubView extends TextView {

        private TextPaint mPaint;

        public TextSubView(Context context) {
            super(context);

            mPaint = new TextPaint(getPaint());
            mPaint.setStyle(TextPaint.Style.STROKE);
            mPaint.setShadowLayer(2.0F, 2.0F, 2.0F, Color.RED);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            canvas.save();
            canvas.clipRect(0, 0, 55, getBottom());
            canvas.drawText(getText().toString(), 0, getBaseline(), mPaint);
            canvas.restore();
        }
    } 
}
