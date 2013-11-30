package cn.daiq.src;

import android.app.Activity;
import android.os.Bundle;

public class StackTrace extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

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

        java.util.Map<Thread, StackTraceElement[]> ts = Thread.getAllStackTraces();
        for (StackTraceElement[] stes : ts.values()) {
            android.util.Log.e("daiq", "============================");
            for (StackTraceElement s : stes) {
                android.util.Log.e("daiq2", s.toString());
            }
        }
        android.util.Log.e("daiq", "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        Thread.dumpStack();
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

}
