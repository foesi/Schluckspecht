package de.wurstsushi.schluckspecht.receivers;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import de.wurstsushi.schluckspecht.notifications.NotificationFactory;

public class BeerReceiver extends BroadcastReceiver {

    public static final String ACTION_BEER_BOUGHT =
            "de.wurstsushi.schluckspecht.ACTION_BEER_BOUGHT";

    public static final String ACTION_BEER_POSTPONE =
            "de.wurstsushi.schluckspecht.ACTION_BEER_POSTPONE";

    private void cancelNotification(Context context, Integer notificationId) {

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.cancel(notificationId);

    }

    @Override
    public void onReceive(Context context, Intent intent) {

        switch (intent.getAction()) {

            case ACTION_BEER_BOUGHT:
                cancelNotification(context, NotificationFactory.NEW_BEER_NOTIFICATION_ID);
                break;
            case ACTION_BEER_POSTPONE:
                cancelNotification(context, NotificationFactory.NEW_BEER_NOTIFICATION_ID);
                break;
        }
    }
}
