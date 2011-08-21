package org.moxieapps.gwt.highcharts.client.events;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * The base class of all events that are triggered on a series, and includes methods for accessing the general
 * properties of the series, such as its name or id.
 *
 * @author squinn@moxiegroup.com (Shawn Quinn)
 * @since 1.1.0
 */
public abstract class SeriesEvent extends MouseEvent {

    @SuppressWarnings({"FieldCanBeLocal", "UnusedDeclaration"})
    private JavaScriptObject series;

    /**
     * We can only be created by instantiating one of our sub classes.
     *
     * @param event The native javascript object containing the details of the original event that was fired.
     * @param series The native javascript object that represents the series instance that the event was triggered on.
     */
    protected SeriesEvent(JavaScriptObject event, JavaScriptObject series) {
        super(event);
        this.series = series;
    }

    /**
     * Retrieve the unique id of the series that received the event. This id can then be used
     * to obtain the Series instance itself via the
     * {@link org.moxieapps.gwt.highcharts.client.BaseChart#getSeries(String)} method.
     *
     * @return The unique id of the series that the event was triggered on.
     */
    public native String getSeriesId() /*-{
        return this.@org.moxieapps.gwt.highcharts.client.events.SeriesEvent::series.options.id;
    }-*/;

    /**
     * Return the name of the series that the event was received on.
     *
     * @return The name of the series that the event was received on.
     */
    public native String getSeriesName() /*-{
        return this.@org.moxieapps.gwt.highcharts.client.events.SeriesEvent::series.name;
    }-*/;

}
