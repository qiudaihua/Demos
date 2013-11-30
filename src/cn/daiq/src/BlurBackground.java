package cn.daiq.src;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;

import com.example.android.apis.R;

import java.util.zip.Inflater;

public class BlurBackground extends Activity {

    private BlurFrameLayout mBlurFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        
        mBlurFrameLayout = (BlurFrameLayout)LayoutInflater.from(this).inflate(R.layout.daiq_blur_background, null);
        setContentView(R.layout.daiq_activity_white);
        FrameLayout.LayoutParams blurLp = new FrameLayout.LayoutParams(600, 600);
        addContentView(mBlurFrameLayout, blurLp);
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
