package de.wurstsushi.schluckspecht.intents;


import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import de.wurstsushi.schluckspecht.receivers.BeerReceiver;

public class PendingIntentFactory {

    private final Context context;

    public PendingIntentFactory(Context context) {

        this.context = context;
    }

    private PendingIntent zeroArgActionIntent(String action) {

        Intent intent = new Intent(context, BeerReceiver.class);
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public PendingIntent beerBought() {

        return zeroArgActionIntent(BeerReceiver.ACTION_BEER_BOUGHT);
    }

    public PendingIntent beerPostpone() {

        return zeroArgActionIntent(BeerReceiver.ACTION_BEER_POSTPONE);
    }
}
