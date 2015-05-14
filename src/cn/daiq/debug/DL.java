
package cn.daiq.debug;

import android.util.Log;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DL {

    public static void printCurTime() {
        printCurTime(1);
    }

    public static void printCurTime(int depth) {
        long curTime = System.currentTimeMillis();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ");
        Date date = new Date(curTime);
        String milliTimeStr = formatter.format(date);
        Log.d("daiq", "--------" + milliTimeStr + "# " + getStackFuncStr(++depth));
        int i = View.STATUS_BAR_TRANSIENT;
    }

    public static void printMilliTime(long milliTime) {
        printMilliTime(1, milliTime);
    }

    public static void printMilliTime(int depth, long milliTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ");
        Date date = new Date(milliTime);
        String milliTimeStr = formatter.format(date);
        Log.d("daiq", "--------" + getStackFuncStr(++depth) + "# " + milliTimeStr);
    }

    public static void log(String msg) {
        Log.d("daiq", "--------" + getStackFuncStr(1) + "# " + msg);
    }

    public static String getStackFuncStr(int depth) {
        Object[] stackFunc = getStackFunc(++depth);
        String stackFuncStr = stackFunc[0] + ":" + stackFunc[1] + "[" + stackFunc[2] + "]";
        return stackFuncStr;
    }

    public static Object[] getStackFunc(int depth) {
        ++depth;
        Throwable ex = new Throwable();
        StackTraceElement[] stackElements = ex.getStackTrace();

        return new Object[] {
                stackElements[depth].getClassName(),
                stackElements[depth].getMethodName(),
                stackElements[depth].getLineNumber()
        };
    }

    public static void printStackTrace() {
        Throwable ex = new Throwable();
        ex.printStackTrace();
    }
}
