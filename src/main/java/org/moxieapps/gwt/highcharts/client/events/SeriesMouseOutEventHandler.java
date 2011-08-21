package org.moxieapps.gwt.highcharts.client.events;

/**
 * An interface that can be used as a callback handler when series mouse out events are fired on the chart.
 * If the {@link org.moxieapps.gwt.highcharts.client.plotOptions.SeriesPlotOptions#setStickyTracking(boolean)}
 * option is true, the mouse out eventd oesn't happen before the mouse enters another graph or leaves the plot area.
 * General usage is as follows:
 * <code><pre>
 * chart.setSeriesPlotOptions(new SeriesPlotOptions()
 *    .setSeriesMouseOutEventHandler(new SeriesMouseOutEventHandler() {
 *       public boolean onMouseOut(SeriesMouseOutEvent event) {
 *          Window.alert("Moused out series: " + event.getSeriesName());
 *          return true;
 *       }
 *    )
 * });
 * </pre></code>
 *
 * @author squinn@moxiegroup.com (Shawn Quinn)
 * @since 1.1.0
 */
public interface SeriesMouseOutEventHandler {    

    /**
     * This method is fired whenever a mouse out event occurs on a series.  See
     * the {@link SeriesMouseOutEvent} class for more details on the data available when this event is fired.
     *
     * @param seriesMouseOutEvent The details of the event that occurred.
     * @return The response to send back to the event handler function
     */
    public boolean onMouseOut(SeriesMouseOutEvent seriesMouseOutEvent);
    
}
