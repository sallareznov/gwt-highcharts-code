package org.moxieapps.gwt.highcharts.client.events;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Provides access to the raw information provided by Highcharts when a specific point in a series
 * is removed via the Point.remove() method.  This class should not be instantiated directly, but
 * instead you should create a {@link PointRemoveEventHandler} and register it via the
 * {@link org.moxieapps.gwt.highcharts.client.plotOptions.SeriesPlotOptions#setPointRemoveEventHandler(PointRemoveEventHandler)}
 * method in order to access remove events.
 *
 * @author squinn@moxiegroup.com (Shawn Quinn)
 * @since 1.1.0
 */
public class PointRemoveEvent extends PointEvent {

    /**
     * This constructor is intended for internal use only.  You should not create remove events
     * directly, but instead should register a {@link PointRemoveEventHandler}.
     *
     * @param event The native javascript object containing the details of the original event that was fired.
     * @param point The native javascript object that represents the point instance that the event was triggered on.
     */
    public PointRemoveEvent(JavaScriptObject event, JavaScriptObject point) {
        super(event, point);
    }

}
