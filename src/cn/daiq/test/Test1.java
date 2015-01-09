package cn.daiq.test;

import android.app.Activity;
import android.os.Bundle;

import com.example.android.apis.R;

import cn.daiq.debug.DPL;
import cn.daiq.ui.percent.RadialPercentage;

public class Test1 extends Activity {

    private DPL dpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        //setContentView(new RadialPercentage(this));
        setContentView(R.layout.daiq_test_white);
        dpl = DPL.instance(this);
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
        dpl.markCoord(0, 0);
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
