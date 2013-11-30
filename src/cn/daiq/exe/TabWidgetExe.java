package cn.daiq.exe;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TabHost;

import com.example.android.apis.R;

public class TabWidgetExe extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.daiq_exe_tabwidget);
        
        TabHost mTabHost = (TabHost)findViewById(android.R.id.tabhost);
        if(mTabHost != null){
            mTabHost.setup();
            mTabHost.addTab(mTabHost.newTabSpec("tab1")
                    .setIndicator("1")
                    .setContent(R.id.view1));
            mTabHost.addTab(mTabHost.newTabSpec("tab2")
                    .setIndicator("2")
                    .setContent(R.id.view2));
            mTabHost.addTab(mTabHost.newTabSpec("tab3")
                    .setIndicator("3")
                    .setContent(R.id.view3));
        }
        
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

}
