package io.taptalk.TapTalk.Helper;

import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.JobIntentService;

import io.taptalk.TapTalk.Manager.TAPChatManager;
import io.taptalk.TapTalk.Manager.TAPNotificationManager;

public class TapTalkEndAppService extends JobIntentService {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        for (String instanceKey : TapTalk.getInstanceKeys()) {
            new Thread(() -> TAPNotificationManager.getInstance(instanceKey).saveNotificationMessageMapToPreference()).start();
            TAPChatManager.getInstance(instanceKey).saveIncomingMessageAndDisconnect();
        }
        stopSelf();
    }
}
