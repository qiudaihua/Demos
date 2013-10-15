package cn.daiq.test;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TableLayout.LayoutParams;
import android.widget.TextView;

public class ConfigureTest extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        
        //setContentView(new ConfigureTestView(this));
        
        StringBuffer configureSB = new StringBuffer();
        Configuration configure = getResources().getConfiguration();
        configureSB.append("\n").append("fontScale=").append(configure.fontScale);
        configureSB.append("\n").append("hardKeyboardHidden=").append(configure.hardKeyboardHidden);
        configureSB.append("\n").append("keyboard=").append(configure.keyboard);
        configureSB.append("\n").append("mcc=").append(configure.mcc);
        configureSB.append("\n").append("mnc=").append(configure.mnc);
        configureSB.append("\n").append("navigation=").append(configure.navigation);
        configureSB.append("\n").append("navigationHidden=").append(configure.navigationHidden);
        configureSB.append("\n").append("orientation=").append(configure.orientation);
        configureSB.append("\n").append("screenHeightDp=").append(configure.screenHeightDp);
        configureSB.append("\n").append("screenLayout=").append(configure.screenLayout);
        configureSB.append("\n").append("screenWidthDp=").append(configure.screenWidthDp);
        configureSB.append("\n").append("touchscreen=").append(configure.touchscreen);
        configureSB.append("\n").append("uiMode=").append(configure.uiMode);
        TextView tx = new TextView(this);
        tx.setText(configureSB.toString());
        //setContentView(tx);
        
        TextView tx2 = new TextView(this);
        tx2.setText(configure.toString());
        setContentView(tx2);
        
        //this.addContentView(tx2, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }
    
    private static class ConfigureTestView extends ViewGroup{
        
        Context mContext;

        public ConfigureTestView(Context context) {
            super(context);
            // TODO Auto-generated constructor stub
            
            mContext = context;
        }
        
        @Override
        protected void onDraw(Canvas canvas) {
            // TODO Auto-generated method stub
            super.onDraw(canvas);
        }
        
        @Override
        protected void dispatchDraw(Canvas canvas) {
            // TODO Auto-generated method stub

            StringBuffer configureSB = new StringBuffer();

            Paint paint = new Paint();
            paint.setColor(Color.WHITE);
            paint.setTextSize(40);
            paint.setTextAlign(Paint.Align.LEFT);

            Configuration configure = getResources().getConfiguration();
            float y = 30;
            float d = 40;

            configureSB.append("\n").append("fontScale=").append(configure.fontScale);
            y += d;
            canvas.drawText(configureSB.toString(), 10, y, paint);
            configureSB.setLength(0);

            configureSB.append("\n").append("hardKeyboardHidden=").append(configure.hardKeyboardHidden);
            y += d;
            canvas.drawText(configureSB.toString(), 10, y, paint);
            configureSB.setLength(0);
            configureSB.append("\n").append("keyboard=").append(configure.keyboard);
            y += d;
            canvas.drawText(configureSB.toString(), 10, y, paint);
            configureSB.setLength(0);
            configureSB.append("\n").append("mcc=").append(configure.mcc);
            y += d;
            canvas.drawText(configureSB.toString(), 10, y, paint);
            configureSB.setLength(0);
            configureSB.append("\n").append("mnc=").append(configure.mnc);
            y += d;
            canvas.drawText(configureSB.toString(), 10, y, paint);
            configureSB.setLength(0);
            configureSB.append("\n").append("navigation=").append(configure.navigation);
            y += d;
            canvas.drawText(configureSB.toString(), 10, y, paint);
            configureSB.setLength(0);
            configureSB.append("\n").append("navigationHidden=").append(configure.navigationHidden);
            y += d;
            canvas.drawText(configureSB.toString(), 10, y, paint);
            configureSB.setLength(0);
            configureSB.append("\n").append("orientation=").append(configure.orientation);
            y += d;
            canvas.drawText(configureSB.toString(), 10, y, paint);
            configureSB.setLength(0);
            configureSB.append("\n").append("screenHeightDp=").append(configure.screenHeightDp);
            y += d;
            canvas.drawText(configureSB.toString(), 10, y, paint);
            configureSB.setLength(0);
            configureSB.append("\n").append("screenLayout=").append(configure.screenLayout);
            y += d;
            canvas.drawText(configureSB.toString(), 10, y, paint);
            configureSB.setLength(0);
            configureSB.append("\n").append("screenWidthDp=").append(configure.screenWidthDp);
            y += d;
            canvas.drawText(configureSB.toString(), 10, y, paint);
            configureSB.setLength(0);
            configureSB.append("\n").append("touchscreen=").append(configure.touchscreen);
            y += d;
            canvas.drawText(configureSB.toString(), 10, y, paint);
            configureSB.setLength(0);
            configureSB.append("\n").append("uiMode=").append(configure.uiMode);
            y += d;
            canvas.drawText(configureSB.toString(), 10, y, paint);
            configureSB.setLength(0);


            super.dispatchDraw(canvas);
        }

        @Override
        protected void onLayout(boolean changed, int l, int t, int r, int b) {
            // TODO Auto-generated method stub
        }
    }

}
