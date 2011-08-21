package org.moxieapps.gwt.highcharts.client.events;

/**
 * An interface that can be used as a callback handler when series mouse over events are fired on the chart.
 * General usage is as follows:
 * <code><pre>
 * chart.setSeriesPlotOptions(new SeriesPlotOptions()
 *    .setSeriesMouseOverEventHandler(new SeriesMouseOverEventHandler() {
 *       public boolean onMouseOver(SeriesMouseOverEvent event) {
 *          Window.alert("Moused over series: " + event.getSeriesName());
 *          return true;
 *       }
 *    )
 * });
 * </pre></code>
 *
 * @author squinn@moxiegroup.com (Shawn Quinn)
 * @since 1.1.0
 */
public interface SeriesMouseOverEventHandler {

    /**
     * This method is fired whenever a mouse over event occurs on a series.  See
     * the {@link SeriesMouseOverEvent} class for more details on the data available when this event is fired.
     *
     * @param seriesMouseOverEvent The details of the event that occurred.
     * @return The response to send back to the event handler function
     */
    public boolean onMouseOver(SeriesMouseOverEvent seriesMouseOverEvent);

}
