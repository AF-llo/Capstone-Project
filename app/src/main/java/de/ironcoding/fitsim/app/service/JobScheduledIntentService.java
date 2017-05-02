package de.ironcoding.fitsim.app.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import javax.inject.Inject;
import javax.inject.Named;

import de.ironcoding.fitsim.app.FitSimApp;
import de.ironcoding.fitsim.app.injection.DbRepositoryModule;
import de.ironcoding.fitsim.logic.Athlete;
import de.ironcoding.fitsim.repository.AthleteRepository;
import timber.log.Timber;

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
        if (intent.getExtras() == null) {
            Timber.d("onHandleIntent: no extras!");
            return;
        }
        if (TextUtils.isEmpty(event)) {
            Timber.d("onHandleIntent: event was empty!");
            return;
        }
        Timber.d("onHandleIntent - Event: " + event);
        switch (event) {
            case EventJobService.EVENT_REFRESH_BODY:
                refreshBody();
                break;
            case EventJobService.EVENT_RELAXE_MUSCLES:
                relaxMuscles();
                break;
            case EventJobService.EVENT_ATHLETE_READY:
                athleteReady();
                break;
            case EventJobService.EVENT_ATHLETE_DIGESTED:
                athleteDigested();
                break;
        }
        sendChangedBroadcast(event);
    }
    private void refreshBody() {
        Athlete athlete = athleteRepository.loadAthlete();
        athlete.refreshBody();
        athleteRepository.updateAthlete(athlete);
    }

    private void relaxMuscles() {
        Athlete athlete = athleteRepository.loadAthlete();
        athlete.relaxMuscles();
        athleteRepository.updateAthlete(athlete);
    }

    private void athleteReady() {
        Athlete athlete = athleteRepository.loadAthlete();
        athlete.setReady();
        athleteRepository.updateAthlete(athlete);
    }

    private void athleteDigested() {
        Athlete athlete = athleteRepository.loadAthlete();
        athlete.goToRestRoom();
        athleteRepository.updateAthlete(athlete);
    }

    private void sendChangedBroadcast(@EventJobService.Event String event) {
        Intent intent = new Intent(ACTION_JOB_SCHEDULED);
        intent.putExtra(EventJobService.EXTRA_JOB_EVENT, event);
        sendBroadcast(intent);
    }
}
