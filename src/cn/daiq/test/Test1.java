package cn.daiq.test;

import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import cn.daiq.ui.percent.RadialPercentage;

import com.example.android.apis.R;

public class Test1 extends Activity {
    
    private class BoolObj {
        boolean value = false;

        public BoolObj(boolean b) {
            value = b;
        }

        private void set(boolean b) {
            value = b;
        }

        private boolean value() {
            return value;
        }
    }

    HashMap<Integer, BoolObj> map = new HashMap<Integer, BoolObj>();
    BoolObj b1 = new BoolObj(false);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        //setContentView(new RadialPercentage(this));
        setContentView(R.layout.daiq_test_white);
        
        map.put(1, b1);
        BoolObj b2 = map.get(1);
        Log.d("daiq", "1b1=" + b1.value() + ";b2=" + b2.value());
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
        RadialPercentage view = (RadialPercentage) findViewById(R.id.radialPercentage);
        view.setFractions(50, 100);

        b1.set(true);
        BoolObj b2 = map.get(1);
        Log.d("daiq", "2b1=" + b1.value() + ";b2=" + b2.value());
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
