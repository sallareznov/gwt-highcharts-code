package org.moxieapps.gwt.highcharts.client.events;

/**
 * An interface that can be used as a callback handler when series hide events are fired on the chart.
 * General usage is as follows:
 * <code><pre>
 * chart.setSeriesPlotOptions(new SeriesPlotOptions()
 *    .setSeriesHideEventHandler(new SeriesHideEventHandler() {
 *       public boolean onHide(SeriesHideEvent event) {
 *          Window.alert("Series hidden: " + event.getSeriesName());
 *          return true;
 *       }
 *    )
 * });
 * </pre></code>
 *
 * @author squinn@moxiegroup.com (Shawn Quinn)
 * @since 1.1.0
 */
public interface SeriesHideEventHandler {

    /**
     * This method is fired whenever a series is hidden.
     *
     * @param seriesHideEvent The details of the event that occurred.
     * @return The response to send back to the event handler function
     */
    public boolean onHide(SeriesHideEvent seriesHideEvent);

}
