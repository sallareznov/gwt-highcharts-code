package org.moxieapps.gwt.highcharts.client.events;

/**
 * An interface that can be used as a callback handler whenever the core chart's "redraw" event is fired.
 * General usage is as follows:
 * <code><pre>
 * chart.setRedrawEventHandler(new ChartRedrawEventHandler() {
 *    public boolean onRedraw(ChartRedrawEvent redrawEvent) {
 *       Window.alert("The chart has been redrawn");
 *       return true;
 *    }
 * });
 * </pre></code>
 *
 * @author squinn@moxiegroup.com (Shawn Quinn)
 * @since 1.1.0
 */
public interface ChartRedrawEventHandler {

    /**
     * This method is fired whenever the chart's redraw event occurs.
     *
     * @param chartRedrawEvent The details of the event that occurred.
     * @return The response to send back to the event handler function
     */
    public boolean onRedraw(ChartRedrawEvent chartRedrawEvent);

}
