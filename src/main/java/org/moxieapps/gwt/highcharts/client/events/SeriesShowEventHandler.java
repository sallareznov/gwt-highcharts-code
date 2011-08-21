package org.moxieapps.gwt.highcharts.client.events;

/**
 * An interface that can be used as a callback handler when series show events are fired on the chart.
 * General usage is as follows:
 * <code><pre>
 * chart.setSeriesPlotOptions(new SeriesPlotOptions()
 *    .setSeriesShowEventHandler(new SeriesShowEventHandler() {
 *       public boolean onShow(SeriesShowEvent event) {
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
public interface SeriesShowEventHandler {

    /**
     * This method is fired whenever a series is hidden.
     *
     * @param seriesShowEvent The details of the event that occurred.
     * @return The response to send back to the event handler function
     */
    public boolean onShow(SeriesShowEvent seriesShowEvent);

}