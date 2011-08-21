package org.moxieapps.gwt.highcharts.client.events;

/**
 * An interface that can be used as a callback handler when click events are fired anywhere on the
 * plot area of the chart.  General usage is as follows:
 * <code><pre>
 * chart.setClickEventHandler(new ChartClickEventHandler() {
 *    public boolean onClick(ChartClickEvent clickEvent) {
 *       Window.alert("User clicked at " + clickEvent.getXAxisValue() + ", " + clickEvent.getYAxisValue());
 *       return true;
 *    }
 * });
 * </pre></code>
 * See the documentation on the {@link ChartClickEvent} class for more details on the data
 * available when a click event occurs.
 *
 * @author squinn@moxiegroup.com (Shawn Quinn)
 * @since 1.1.0
 */
public interface ChartClickEventHandler {

    /**
     * This method is fired whenever a click event occurs on the plot area of the chart.  See
     * the {@link ChartClickEvent} class for more details on the data available when this event is fired.
     *
     * @param chartClickEvent The details of the event that occurred.
     * @return The response to send back to the event handler function
     */
    public boolean onClick(ChartClickEvent chartClickEvent);

}
