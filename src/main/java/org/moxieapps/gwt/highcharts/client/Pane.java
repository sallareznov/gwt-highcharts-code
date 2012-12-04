package org.moxieapps.gwt.highcharts.client;

/**
 * A configurable class that will allow you to control the pane options of the charts, which
 * applies only to polar charts and angular gauges.  This configuration object holds general
 * options for the combined X and Y axes set. Each xAxis or yAxis can reference the pane by index.
 * An instance of this class can be constructed and then
 * set on the chart via the {@link org.moxieapps.gwt.highcharts.client.Chart#setPane(Pane)} method.
 * <p/>
 * Note: mane of the pane options are only available if the highcharts-more.js script is included in your GWT module. E.g.:
 * </p>
 * &lt;script type="text/javascript" src="js/highcharts-more.js"&gt;&lt;/script&gt;
 *
 * @author squinn@moxiegroup.com (Shawn Quinn)
 * @since 1.5.0
 */
public class Pane extends Configurable<Pane> {

    /**
     * Convenience method for setting the 'startAngle' option of the pane.  Equivalent to:
     * <pre><code>
     *     pane.setOption("startAngle", 90);
     * </code></pre>
     * The start angle of the polar X axis or gauge axis, given in degrees where 0 is north. Defaults to 0.
     *
     * @param startAngle The start angle fo the polar X axis of the chart.
     * @return A reference to this {@link Pane} instance for convenient method chaining.
     */
    public Pane setStartAngle(Number startAngle) {
        return this.setOption("startAngle", startAngle);
    }

    /**
     * Convenience method for setting the 'endAngle' option of the pane.  Equivalent to:
     * <pre><code>
     *     pane.setOption("endAngle", 180);
     * </code></pre>
     * The end angle of the polar X axis or gauge value axis, given in degrees where 0 is north. Defaults to startAngle + 360.
     *
     * @param endAngle The end angle of the polar X axis or gauge value axis.
     * @return A reference to this {@link Pane} instance for convenient method chaining.
     */
    public Pane setEndAngle(Number endAngle) {
        return this.setOption("endAngle", endAngle);
    }

    // TODO: Add 'background' and 'center' configuration methods

}
