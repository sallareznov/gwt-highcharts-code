package org.moxieapps.gwt.highcharts.client.events;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Provides access to the raw information provided by Highcharts when a user clicks on the legend item
 * associated with a series.  This class should not be instantiated directly, but instead you should create
 * a {@link SeriesLegendItemClickEventHandler} and register it via the
 * {@link org.moxieapps.gwt.highcharts.client.plotOptions.SeriesPlotOptions#setSeriesLegendItemClickEventHandler(SeriesLegendItemClickEventHandler)}
 * method in order to access legend item click events.
 *
 * @author squinn@moxiegroup.com (Shawn Quinn)
 * @since 1.1.0
 */
public class SeriesLegendItemClickEvent extends SeriesEvent {

    /**
     * This constructor is intended for internal use only.  You should not create click events
     * directly, but instead should register a {@link SeriesLegendItemClickEventHandler}.
     *
     * @param event The native javascript object containing the details of the original event that was fired.
     * @param series The native javascript object that represents the series instance that the event was triggered on.
     */
    public SeriesLegendItemClickEvent(JavaScriptObject event, JavaScriptObject series) {
        super(event, series);
    }

    /**
     * Returns true if the series is now visible, false if it is invisible
     *
     * @return Whether or not the series is visible.
     */
    public native boolean isVisible() /*-{
        return this.@org.moxieapps.gwt.highcharts.client.events.SeriesEvent::series.visible;
    }-*/;

}