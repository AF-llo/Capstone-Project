package de.ironcoding.fitsim.util;

import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;

import de.ironcoding.fitsim.app.service.EventJobService;

/**
 * Created by larsl on 01.05.2017.
 */

public class Jobber {

    private FirebaseJobDispatcher dispatcher;

    private AppSettings appSettings;

    public Jobber(FirebaseJobDispatcher dispatcher, AppSettings appSettings) {
        this.dispatcher = dispatcher;
        this.appSettings = appSettings;
    }

    public void scheduleRefreshBodyJobIfNotScheduled() {
        if(appSettings.getRefreshJobScheduledFromSettings()) {
            return;
        }
        int duration = (int) GameTimeUtil.durationInSeconds(GameTimeUtil.HOURS_PER_GAME_DAY);
        Job refreshJob = dispatcher.newJobBuilder()
                .setService(EventJobService.class)
                .setTag(EventJobService.EVENT_REFRESH_BODY)
                .setRecurring(true)
                .setTrigger(Trigger.executionWindow(duration, duration))
                .setReplaceCurrent(false)
                .setLifetime(Lifetime.FOREVER)
                .build();
        dispatcher.schedule(refreshJob);
        appSettings.writeRefreshJobScheduledToSettings(true);
    }
}
