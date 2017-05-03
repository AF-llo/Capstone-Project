package de.ironcoding.fitsim.app.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.RemoteViews;

import de.ironcoding.fitsim.R;
import de.ironcoding.fitsim.app.service.HighscoreWidgetRemoteViewService;
import de.ironcoding.fitsim.ui.activities.OnboardingActivity;
import timber.log.Timber;

/**
 * Implementation of App Widget functionality.
 */
public class HighscoreWidgetProvider extends AppWidgetProvider {

    public static final String ACTION_UPDATE = "de.iconcoding.fitsim.app.ACTION_DATA_UPDATED";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        Timber.d("onUpdate");
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            // Construct the RemoteViews object
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.highscore_widget_layout);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, OnboardingActivity.getIntent(context), 0);
            views.setOnClickPendingIntent(R.id.widget, pendingIntent);

            setRemoteAdapter(context, views);
            views.setEmptyView(R.id.widget_list, R.id.widget_empty);
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        Timber.d("onReceive");
        if (ACTION_UPDATE.equals(intent.getAction())) {
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, getClass()));
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_list);
        }
    }

    private void setRemoteAdapter(Context context, @NonNull final RemoteViews views) {
        views.setRemoteAdapter(R.id.widget_list, new Intent(context, HighscoreWidgetRemoteViewService.class));
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

