package de.wurstsushi.schluckspecht.notifications;


import android.app.Notification;
import android.content.Context;
import android.support.v7.app.NotificationCompat;

import de.wurstsushi.schluckspecht.R;
import de.wurstsushi.schluckspecht.intents.PendingIntentFactory;

public class NotificationFactory {

    private final Context context;
    private final PendingIntentFactory factory;

    public static final Integer NEW_BEER_NOTIFICATION_ID = 0;

    public NotificationFactory(Context context) {

        this.context = context;
        this.factory = new PendingIntentFactory(context);
    }

    public Notification newBeerNotification() {

        return new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.icon__beer)
                .setContentTitle("Neues Bier!")
                .setContentText("Hast du einen neuen Kasten Bier gekauft?")
                .addAction(R.drawable.ic_done, "BIER", factory.beerBought())
                .addAction(R.drawable.ic_schedule, "Später", factory.beerPostpone())
                .build();
    }
}
