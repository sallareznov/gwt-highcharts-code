package org.moxieapps.gwt.highcharts.client.events;

/**
 * An interface that can be used as a callback handler whenever the core chart's "load" event is fired.
 * General usage is as follows:
 * <code><pre>
 * chart.setLoadEventHandler(new ChartLoadEventHandler() {
 *    public boolean onLoad(ChartLoadEvent loadEvent) {
 *       Window.alert("The chart has been loaded");
 *       return true;
 *    }
 * });
 * </pre></code>
 *
 * @author squinn@moxiegroup.com (Shawn Quinn)
 * @since 1.1.0
 */
public interface ChartLoadEventHandler {

    /**
     * This method is fired whenever the chart's load event occurs.
     *
     * @param chartLoadEvent The details of the event that occurred.
     * @return The response to send back to the event handler function
     */
    public boolean onLoad(ChartLoadEvent chartLoadEvent);
}
