package yingyue_activity.example.com.processpower.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * 广播接收者，监听屏幕开启和熄灭
 */
public class KeepLiveReceiver extends BroadcastReceiver {
    Intent _intent;

    public KeepLiveReceiver(Intent new_intent) {
        this._intent = new_intent;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_SCREEN_ON.equals(intent.getAction())) {
            //屏幕开启，关闭KeepLiveActivity
            KeepLiveManager.getInstance().finishKeepLiveActivity();
        } else if (Intent.ACTION_SCREEN_OFF.equals(intent.getAction())) {
            //屏幕熄灭，开启KeepLiveActivity
            KeepLiveManager.getInstance().startKeepLiveActivity(context, _intent);
        }
    }
}
