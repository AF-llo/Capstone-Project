package de.ironcoding.fitsim.app.service;

import android.content.Intent;
import android.widget.RemoteViewsService;

import de.ironcoding.fitsim.app.widget.HighscoreWidgetFactory;

/**
 * Created by larsl on 03.05.2017.
 */

public class HighscoreWidgetRemoteViewService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new HighscoreWidgetFactory(this);
    }
}
