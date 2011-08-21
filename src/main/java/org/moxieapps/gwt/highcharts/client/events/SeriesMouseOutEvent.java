package org.moxieapps.gwt.highcharts.client.events;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Provides access to the raw information provided by Highcharts when a series mouse out event
 * occurs.  This class should  not be instantiated directly, but instead you should
 * create a {@link SeriesMouseOutEventHandler} and register it via the
 * {@link org.moxieapps.gwt.highcharts.client.plotOptions.SeriesPlotOptions#setSeriesMouseOutEventHandler(SeriesMouseOutEventHandler)}
 * method in order to access series mouse out events.
 *
 * @author squinn@moxiegroup.com (Shawn Quinn)
 * @since 1.1.0
 */
public class SeriesMouseOutEvent extends SeriesEvent {

    /**
     * This constructor is intended for internal use only.  You should not create mouse out events
     * directly, but instead should register a {@link SeriesMouseOutEventHandler}.
     *
     * @param event The native javascript object containing the details of the original event that was fired.
     * @param series The native javascript object that represents the series instance that the event was triggered on.
     */
    public SeriesMouseOutEvent(JavaScriptObject event, JavaScriptObject series) {
        super(event, series);
    }

}
