package yingyue_activity.example.com.processpower.utils;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;


/**
 * 设置Activity为1像素，透明主题，并且显示在屏幕左上角
 */
public class KeepLiveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activitykeeplive);
        Log.e("Evan", "开启KeepLiveActivity");
        //左上角显示
        Window window = getWindow();
        window.setGravity(Gravity.START | Gravity.TOP);
        //1个像素大小
        WindowManager.LayoutParams params = window.getAttributes();
        params.x = 0;
        params.y = 0;
        params.width = 1;
        params.height = 1;
        window.setAttributes(params);
        //设置KeepLiveActivity
        KeepLiveManager.getInstance().setKeepLiveActivity(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("Evan", "关闭KeepLiveActivity");
    }
}
