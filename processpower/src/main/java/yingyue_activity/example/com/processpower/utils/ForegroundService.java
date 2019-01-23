package yingyue_activity.example.com.processpower.utils;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import yingyue_activity.example.com.processpower.R;


/**
 * 设置为前台Service,系统API需要关联一个Notification，希望尽量不显示通知栏消息
 */
public class ForegroundService extends Service {


    @Override
    public void onCreate() {
        super.onCreate();
        Notification.Builder builder = new Notification.Builder(this);
        //        builder.setContentInfo("补充内容");
        //        builder.setContentText("正在运行...");
        builder.setContentTitle("测试应用");
        builder.setSmallIcon(R.drawable.ic_launcher);
        //        builder.setTicker("新消息");
        // 将AutoCancel设为true后，当你点击通知栏的notification后，它会自动被取消消失
        builder.setAutoCancel(true);
        // 将Ongoing设为true 那么notification将不能滑动删除
        builder.setOngoing(true);
        builder.setWhen(System.currentTimeMillis());
        //        Intent notificationIntent = new Intent(this, null);
        //        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        //        builder.setContentIntent(pendingIntent);
        Notification notification = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            notification = builder.build();
        } else {
            notification = builder.getNotification();
        }
        //把该service创建为前台service
        startForeground(1, notification);
        //        app_Shut_down();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        /**
         * 将Service 设置为 START_STICKY，利用系统机制在 Service 挂掉后自动拉活。
         * START_STICKY：
         * “粘性”。如果service进程被kill掉，保留service的状态为开始状态，但不保留递送的intent对象。随后系统会尝试重新创建service，由于服务状态为开始状态，所以创建服务后一定会调用onStartCommand(Intent,int,int)
         * 方法。如果在此期间没有任何启动命令被传递到service，那么参数Intent将为null。
         * START_NOT_STICKY：
         * “非粘性的”。使用这个返回值时，如果在执行完onStartCommand后，服务被异常kill掉，系统不会自动重启该服务。
         * START_REDELIVER_INTENT：
         * 重传Intent。使用这个返回值时，如果在执行完onStartCommand后，服务被异常kill掉，系统会自动重启该服务，并将Intent的值传入。
         * START_STICKY_COMPATIBILITY：
         * START_STICKY的兼容版本，但不保证服务被kill后一定能重启。
         * 只要 targetSdkVersion 不小于5，就默认是 START_STICKY。
         */
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("Evan", "销毁Service");
    }
}
