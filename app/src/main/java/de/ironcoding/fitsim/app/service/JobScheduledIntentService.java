package de.ironcoding.fitsim.app.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import javax.inject.Inject;
import javax.inject.Named;

import de.ironcoding.fitsim.app.FitSimApp;
import de.ironcoding.fitsim.app.injection.DbRepositoryModule;
import de.ironcoding.fitsim.logic.Athlete;
import de.ironcoding.fitsim.repository.AthleteRepository;

/**
 * Created by larsl on 01.05.2017.
 */

public class JobScheduledIntentService extends IntentService {

    public static final String ACTION_JOB_SCHEDULED = "de.ironcoding.action.job_scheduled";

    @Inject
    @Named(DbRepositoryModule.REPOSITORY_DB)
    AthleteRepository athleteRepository;

    public static Intent getIntent(Context context, @EventJobService.Event String event) {
        Intent intent = new Intent(context, JobScheduledIntentService.class);
        intent.putExtra(EventJobService.EXTRA_JOB_EVENT, event);
        return intent;
    }

    public JobScheduledIntentService() {
        super(JobScheduledIntentService.class.getSimpleName());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ((FitSimApp)getApplicationContext()).getAppComponent().injectJobScheduledService(this);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        @EventJobService.Event String event = intent.getExtras().getString(EventJobService.EXTRA_JOB_EVENT);
        if (TextUtils.isEmpty(event)) {
            Log.d(JobStoppedIntentService.class.getSimpleName(), "onHandleIntent: event was empty");
            return;
        }
        Log.d(JobStoppedIntentService.class.getSimpleName(), "onHandleIntent - Event: " + event);
        switch (event) {
            case EventJobService.EVENT_REFRESH_BODY:
                Athlete athlete = athleteRepository.loadAthlete();
                athlete.refreshBody();
                athleteRepository.updateAthlete(athlete);
                sendChangedBroadcast(event);
                break;

        }
    }

    private void sendChangedBroadcast(@EventJobService.Event String event) {
        Intent intent = new Intent(ACTION_JOB_SCHEDULED);
        intent.putExtra(EventJobService.EXTRA_JOB_EVENT, event);
        sendBroadcast(intent);
    }
}
