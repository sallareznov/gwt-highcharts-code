package org.moxieapps.gwt.highcharts.client.events;

/**
 * An interface that can be used as a callback handler when checkbox click events are fired on the series
 * of a chart, which occurs when the checkbox next to the series' name in the legend is clicked.  General usage is as follows:
 * <code><pre>
 * chart.setSeriesPlotOptions(new SeriesPlotOptions()
 *    .setSeriesCheckboxClickEventHandler(new SeriesCheckboxClickEventHandler() {
 *       public boolean onClick(SeriesCheckboxClickEvent clickEvent) {
 *          Window.alert("User clicked the checkbox state of the series: " + clickEvent.getSeriesName());
 *          return true;
 *       }
 *    )
 * });
 * </pre></code>
 * See the documentation on the {@link ChartClickEvent} class for more details on the data
 * available when a checkbox click event occurs.
 *
 * @author squinn@moxiegroup.com (Shawn Quinn)
 * @since 1.1.0
 */
public interface SeriesCheckboxClickEventHandler {

    /**
     * This method is fired whenever a checkbox click event occurs on a series.  See
     * the {@link SeriesCheckboxClickEvent} class for more details on the data available when this event is fired.
     * Return false to prevent the default action which is to toggle the select state of the series.
     *
     * @param seriesCheckboxClickEvent The details of the event that occurred.
     * @return The response to send back to the event handler function.  Return false to prevent
     * the default action which is to toggle the select state of the series.
     */
    public boolean onClick(SeriesCheckboxClickEvent seriesCheckboxClickEvent);

}
