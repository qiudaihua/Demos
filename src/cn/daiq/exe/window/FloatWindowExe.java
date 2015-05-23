
package cn.daiq.exe.window;

import com.example.android.apis.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FloatWindowExe extends Activity
{

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daiq_exe_float_window);
        //获取启动按钮  
        Button start = (Button) findViewById(R.id.start_id);
        //获取移除按钮  
        Button remove = (Button) findViewById(R.id.remove_id);
        //绑定监听  
        start.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub  
                Intent intent = new Intent(FloatWindowExe.this, FloatService.class);
                //启动FxService  
                startService(intent);
                finish();
            }
        });

        remove.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                //uninstallApp("com.phicomm.hu");  
                Intent intent = new Intent(FloatWindowExe.this, FloatService.class);
                //终止FxService  
                stopService(intent);
            }
        });

    }
}
