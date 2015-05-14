package cn.daiq.exe.widget;

import android.app.Activity;
import android.appwidget.AppWidgetHost;
import android.appwidget.AppWidgetHostView;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProviderInfo;
import android.content.ComponentName;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;

public class WidgetsActivity extends Activity {

    private static final int APPWIDGET_HOST_ID = 11000;
    AppWidgetHost mAppWidgetHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        mAppWidgetHost = new AppWidgetHost(this, APPWIDGET_HOST_ID);

        int appWidgetId = mAppWidgetHost.allocateAppWidgetId();
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        boolean success = appWidgetManager.bindAppWidgetIdIfAllowed(appWidgetId,
                new ComponentName("com.android.music",
                        "com.android.music.MediaAppWidgetProvider"));
        Log.d("daiq", "success=" + success);
        //need <uses-permission android:name="android.permission.BIND_APPWIDGET" />
        if (success) {
            AppWidgetProviderInfo appWidgetInfo = appWidgetManager.getAppWidgetInfo(appWidgetId);
            Log.d("daiq", "appWidgetInfo=" + appWidgetInfo);
            AppWidgetHostView mMusicPlayer =
                    (AppWidgetHostView) mAppWidgetHost.createView(
                            this, appWidgetId, appWidgetInfo);
            LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            setContentView(mMusicPlayer, lp);
        }
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        if (mAppWidgetHost != null) {
            mAppWidgetHost.startListening();
        }
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
        if (mAppWidgetHost != null) {
            mAppWidgetHost.stopListening();
        }
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

}