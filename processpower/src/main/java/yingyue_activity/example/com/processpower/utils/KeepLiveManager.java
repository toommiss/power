package yingyue_activity.example.com.processpower.utils;


import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.lang.ref.WeakReference;

public class KeepLiveManager {

    private static final KeepLiveManager ourInstance = new KeepLiveManager();

    public static KeepLiveManager getInstance() {
        return ourInstance;
    }

    private WeakReference<KeepLiveActivity> reference;
    private KeepLiveReceiver keepLiveReceiver;

    private KeepLiveManager() {
    }

    /**
     * 注册屏幕开启熄灭广播 将进程级别设置为0
     *
     * @param context
     */
    public KeepLiveManager registerKeepLiveReceiver(Context context) {
        Intent intent = new Intent(context.getApplicationContext(), KeepLiveActivity.class);
        keepLiveReceiver = new KeepLiveReceiver(intent);
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_SCREEN_ON);
        context.registerReceiver(keepLiveReceiver, filter);
        return this;
    }

    //开启通知栏提示 将进程级别设置成3
    public void startNotificationServer(Context context) {
        context.startService(new Intent(context.getApplicationContext(), ForegroundService.class));
    }

    /**
     * 反注册屏幕开启熄灭广播
     *
     * @param context
     */
    public void unregisterKeepLiveReceiver(Context context) {
        if (keepLiveReceiver != null) {
            context.unregisterReceiver(keepLiveReceiver);
        }
    }


    public void setKeepLiveActivity(KeepLiveActivity activity) {
        reference = new WeakReference<>(activity);
    }

    /**
     * 开启KeepLiveActivity
     *
     * @param context
     */
    public void startKeepLiveActivity(Context context, Intent new_intent) {
        Intent intent = new_intent;
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 关闭KeepLiveActivity
     */
    public void finishKeepLiveActivity() {
        if (reference != null && reference.get() != null) {
            reference.get().finish();
        }
    }

}
