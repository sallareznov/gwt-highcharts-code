package org.moxieapps.gwt.highcharts.client.events;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Provides access to the raw information provided by Highcharts when a user mouses out of a specific
 * point in a series.  This class should not be instantiated directly, but instead you should create
 * a {@link PointMouseOutEventHandler} and register it via the
 * {@link org.moxieapps.gwt.highcharts.client.plotOptions.SeriesPlotOptions#setPointMouseOutEventHandler(PointMouseOutEventHandler)}
 * method in order to access mouse out events.
 *
 * @author squinn@moxiegroup.com (Shawn Quinn)
 * @since 1.1.0
 */
public class PointMouseOutEvent extends PointEvent {

    /**
     * This constructor is intended for internal use only.  You should not create mouse out events
     * directly, but instead should register a {@link PointClickEventHandler}.
     *
     * @param event The native javascript object containing the details of the original event that was fired.
     * @param point The native javascript object that represents the point instance that the event was triggered on.
     */
    public PointMouseOutEvent(JavaScriptObject event, JavaScriptObject point) {
        super(event, point);
    }

}

