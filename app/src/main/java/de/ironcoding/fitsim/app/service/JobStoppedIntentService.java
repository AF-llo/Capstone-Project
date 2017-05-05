package de.ironcoding.fitsim.app.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import javax.inject.Inject;

import de.ironcoding.fitsim.app.FitSimApp;
import de.ironcoding.fitsim.util.AppSettings;
import timber.log.Timber;

/**
 * Created by larsl on 01.05.2017.
 */

public class JobStoppedIntentService extends IntentService {

    @Inject
    AppSettings appSettings;

    public static Intent getIntent(Context context, @EventJobService.Event String event) {
        Intent intent = new Intent(context, JobScheduledIntentService.class);
        intent.putExtra(EventJobService.EXTRA_JOB_EVENT, event);
        return intent;
    }

    public JobStoppedIntentService() {
        super(JobStoppedIntentService.class.getSimpleName());
        ((FitSimApp)getApplicationContext()).getAppComponent().injectJobStoppedIntentService(this);
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
        // reset flags in settings to getIntent scheduled job again when app is started next time
        switch (event) {
            case EventJobService.EVENT_REFRESH_BODY:
                appSettings.writeRefreshJobScheduledToSettings(false);
                break;
            case EventJobService.EVENT_RELAXE_MUSCLES:
                appSettings.writeRelaxMusclesJobScheduledToSettings(false);
                break;

        }
    }
}
