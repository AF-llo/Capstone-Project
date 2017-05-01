package de.ironcoding.fitsim.app.service;

import android.support.annotation.StringDef;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by larsl on 01.05.2017.
 */

public class EventJobService extends JobService {

    public static final String EXTRA_JOB_EVENT = "de.ironcoding.extra.job_event";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({EVENT_REFRESH_BODY, EVENT_RELAXE_MUSCLES, EVENT_SHRINK_MUSCLES, EVENT_ATHLETE_READY, EVENT_ATHLETE_DIGESTED})
    public @interface Event {}
    public static final String EVENT_REFRESH_BODY = "relax_body";
    public static final String EVENT_RELAXE_MUSCLES = "relax_muscles";
    public static final String EVENT_SHRINK_MUSCLES = "shrink_muscles";
    public static final String EVENT_ATHLETE_READY = "athlete_ready";
    public static final String EVENT_ATHLETE_DIGESTED = "athlete_digested";

    @Override
    public boolean onStartJob(JobParameters job) {
        @Event String event = job.getTag();
        startService(JobScheduledIntentService.getIntent(this, event));
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        @Event String event = job.getTag();
        startService(JobStoppedIntentService.getIntent(this, event));
        return true;
    }
}
