package de.ironcoding.fitsim.util;

import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;

import de.ironcoding.fitsim.app.service.EventJobService;
import timber.log.Timber;

/**
 * Created by larsl on 01.05.2017.
 */

public class Jobber {

    private static final float REFRESH_BODY_DURATION = GameTimeUtil.HOURS_PER_GAME_DAY;
    private static final float RELAX_MUSCLES_DURATION = GameTimeUtil.HOURS_PER_GAME_DAY / 2;
    private static final long HIGHSCORE_UPDATE_INTERVAL = 60000; // 1800000

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
        int duration = (int) GameTimeUtil.gameDurationInSeconds(REFRESH_BODY_DURATION);
        Job refreshJob = dispatcher.newJobBuilder()
                .setService(EventJobService.class)
                .setTag(EventJobService.EVENT_REFRESH_BODY)
                .setRecurring(true)
                .setTrigger(Trigger.executionWindow(duration, duration))
                .setReplaceCurrent(true)
                .setLifetime(Lifetime.FOREVER)
                .build();
        dispatcher.mustSchedule(refreshJob);
        appSettings.writeRefreshJobScheduledToSettings(true);
        Timber.d("Schedule refreshBody every %d sec", duration);
    }

    public void scheduleRelaxMusclesJobIfNotScheduled() {
        if (appSettings.getRelaxMusclesJobScheduledFromSettings()) {
            return;
        }
        int duration = (int) GameTimeUtil.gameDurationInSeconds(RELAX_MUSCLES_DURATION);
        Job refreshJob = dispatcher.newJobBuilder()
                .setService(EventJobService.class)
                .setTag(EventJobService.EVENT_RELAXE_MUSCLES)
                .setRecurring(true)
                .setTrigger(Trigger.executionWindow(duration, duration))
                .setReplaceCurrent(true)
                .setLifetime(Lifetime.FOREVER)
                .build();
        dispatcher.mustSchedule(refreshJob);
        appSettings.writeRelaxMusclesJobScheduledToSettings(true);
        Timber.d("Schedule %s every %d sec", EventJobService.EVENT_RELAXE_MUSCLES, duration);
    }

    public void scheduleHighscoreJogEvent() {
        dispatcher.cancel(EventJobService.EVENT_UPDATE_HIGHSCORE); // // TODO: 03.05.2017 remove
        int duration = GameTimeUtil.millisInSeconds(HIGHSCORE_UPDATE_INTERVAL);
        Job refreshJob = dispatcher.newJobBuilder()
                .setService(EventJobService.class)
                .setTag(EventJobService.EVENT_UPDATE_HIGHSCORE)
                .setRecurring(true)
                .setTrigger(Trigger.executionWindow(duration, duration))
                .setReplaceCurrent(false)
                .setLifetime(Lifetime.FOREVER)
                .build();
        dispatcher.mustSchedule(refreshJob);
        Timber.d("Schedule %s every %d sec", EventJobService.EVENT_UPDATE_HIGHSCORE, duration);
    }

    public void scheduleAthleteReadyJob(long duration) {
        int durationInSeconds = GameTimeUtil.millisInSeconds(duration);
        Job refreshJob = dispatcher.newJobBuilder()
                .setService(EventJobService.class)
                .setTag(EventJobService.EVENT_ATHLETE_READY)
                .setTrigger(Trigger.executionWindow(durationInSeconds, durationInSeconds))
                .setReplaceCurrent(true)
                .setLifetime(Lifetime.FOREVER)
                .build();
        dispatcher.mustSchedule(refreshJob);
        Timber.d("Schedule %s in %d sec", EventJobService.EVENT_ATHLETE_READY, durationInSeconds);
    }

    public void scheduleAthleteDigestedJob(long duration) {
        int durationInSeconds = GameTimeUtil.millisInSeconds(duration);
        Job refreshJob = dispatcher.newJobBuilder()
                .setService(EventJobService.class)
                .setTag(EventJobService.EVENT_ATHLETE_DIGESTED)
                .setTrigger(Trigger.executionWindow(durationInSeconds, durationInSeconds))
                .setReplaceCurrent(true)
                .setLifetime(Lifetime.FOREVER)
                .build();
        dispatcher.mustSchedule(refreshJob);
        Timber.d("Schedule %s in %d sec", EventJobService.EVENT_ATHLETE_DIGESTED, durationInSeconds);
    }
}
