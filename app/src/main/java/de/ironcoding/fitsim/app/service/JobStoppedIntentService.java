package de.ironcoding.fitsim.app.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created by larsl on 01.05.2017.
 */

public class JobStoppedIntentService extends IntentService {

    public static Intent getIntent(Context context, @EventJobService.Event String event) {
        Intent intent = new Intent(context, JobScheduledIntentService.class);
        intent.putExtra(EventJobService.EXTRA_JOB_EVENT, event);
        return intent;
    }

    public JobStoppedIntentService() {
        super(JobStoppedIntentService.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        // TODO: 01.05.2017
    }
}
