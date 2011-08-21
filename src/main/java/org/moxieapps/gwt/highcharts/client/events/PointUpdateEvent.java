package org.moxieapps.gwt.highcharts.client.events;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Provides access to the raw information provided by Highcharts when a specific point in a series
 * is updated programatically.  This class should not be instantiated directly, but
 * instead you should create a {@link PointUpdateEventHandler} and register it via the
 * {@link org.moxieapps.gwt.highcharts.client.plotOptions.SeriesPlotOptions#setPointUpdateEventHandler(PointUpdateEventHandler)}
 * method in order to access update events.
 *
 * @author squinn@moxiegroup.com (Shawn Quinn)
 * @since 1.1.0
 */
public class PointUpdateEvent extends PointEvent {

    /**
     * This constructor is intended for internal use only.  You should not create update events
     * directly, but instead should register a {@link PointUpdateEventHandler}.
     *
     * @param event The native javascript object containing the details of the original event that was fired.
     * @param point The native javascript object that represents the point instance that the event was triggered on.
     */
    public PointUpdateEvent(JavaScriptObject event, JavaScriptObject point) {
        super(event, point);
    }

    // TODO: Add access to the new options of the point

}
