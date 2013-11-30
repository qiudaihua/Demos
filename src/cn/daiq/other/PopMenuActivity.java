
package cn.daiq.other;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.example.android.apis.R;

/**
 * 入口Activity
 * 
 * @author haozi
 */
public class PopMenuActivity extends Activity {

    private MenuView mMenuView;
    private int setTitle;
    private View mContentView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContentView = LayoutInflater.from(this).inflate(R.layout.daiq_activity_black, null);
        setContentView(mContentView);

        mMenuView = new MenuView(this,
                new TitleClickEvent(),
                Color.argb(255, 139, 106, 47),
                R.style.PopupAnimation);

        mMenuView.update();
        mMenuView.setTitleSelect(0);
    }

    class TitleClickEvent implements OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                long arg3) {
            setTitle = arg2;
            mMenuView.setTitleSelect(setTitle);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d("daiq", "onCreateOptionsMenu");
        menu.add("menu");
        return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        // TODO Auto-generated method stub
        return super.onCreatePanelMenu(featureId, menu);
    }
  
 /*   public boolean onTouchEvent(MotionEvent event) {
        this.openOptionsMenu();
        return super.onTouchEvent(event);
}
    */
/*    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub
        //return super.onPrepareOptionsMenu(menu);
        if (mMenuView != null) {
            Log.d("daiq", "mMenuView != null");
            Log.d("daiq", "mMenuView.isShowing()" + (mMenuView.isShowing()));
            if (mMenuView.isShowing())
                mMenuView.dismiss();
            else {
                mMenuView.showAtLocation(mContentView,
                        Gravity.BOTTOM, 0, 0);
            }
        }
        return false;// 返回为true 则显示系统menu
    }*/

    
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        if (mMenuView != null) {
            Log.d("daiq", "mMenuView != null");
            Log.d("daiq", "mMenuView.isShowing()" + (mMenuView.isShowing()));
            if (mMenuView.isShowing())
                mMenuView.dismiss();
            else {
                mMenuView.showAtLocation(mContentView,
                        Gravity.BOTTOM, 0, 0);
            }
        }
        return super.onContextItemSelected(item);
    }
    
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        Log.d("daiq", "onMenuOpened");
        if (mMenuView != null) {
            Log.d("daiq", "mMenuView != null");
            Log.d("daiq", "mMenuView.isShowing()" + (mMenuView.isShowing()));
            if (mMenuView.isShowing())
                mMenuView.dismiss();
            else {
                mMenuView.showAtLocation(mContentView,
                        Gravity.BOTTOM, 0, 0);
            }
        }
        return false;// 返回为true 则显示系统menu
    }
    
    @Override
    public void onActionModeFinished(ActionMode mode) {
        // TODO Auto-generated method stub
        super.onActionModeFinished(mode);
    }
    
    @Override
    public void onActionModeStarted(ActionMode mode) {
        // TODO Auto-generated method stub
        if (mMenuView != null) {
            Log.d("daiq", "mMenuView != null");
            Log.d("daiq", "mMenuView.isShowing()" + (mMenuView.isShowing()));
            if (mMenuView.isShowing())
                mMenuView.dismiss();
            else {
                mMenuView.showAtLocation(mContentView,
                        Gravity.BOTTOM, 0, 0);
            }
        }
        //return false;// 返回为true 则显示系统menu
        super.onActionModeStarted(mode);
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        Log.d("daiq", "keyCode="+keyCode);
        Log.d("daiq", "event="+event.toString());
        return super.onKeyDown(keyCode, event);
    }
}
