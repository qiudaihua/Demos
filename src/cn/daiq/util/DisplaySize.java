package cn.daiq.util;

import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;


public class DisplaySize {

    public static int getStatusBarHeight(Activity activity) {
        Rect rect = new Rect();
        Window window = activity.getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(rect);
        int statusBarHeight = rect.top;
        return statusBarHeight;
    }

    public static int getActionBarHeight(Activity activity) {
        final TypedArray actionBarSize = ((Activity) activity).obtainStyledAttributes(
                new int[] { android.R.attr.actionBarSize });
        int actionBarHeight = actionBarSize.getDimensionPixelSize(0, 0);
        actionBarSize.recycle();
        return actionBarHeight;
    }

    public static int getTitleBarHeight(Activity activity) {
        int contentTop = activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
        int statusBarHeight =getStatusBarHeight(activity);
        int titleBarHeight = contentTop - statusBarHeight;
        return titleBarHeight;
    }

    public static int[] getScreenSize(Activity activity) {
        WindowManager windowManager = activity.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        int screenWidth = display.getWidth();
        int screenHeight = display.getHeight();
        int[] screenSize = new int[] { screenWidth, screenHeight };
        return screenSize;
    }


}
