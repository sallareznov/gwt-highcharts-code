/*
 * Copyright 2011 Moxie Group
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.moxieapps.gwt.highcharts.client.plotOptions;

import org.moxieapps.gwt.highcharts.client.Chart;
import org.moxieapps.gwt.highcharts.client.Color;
import org.moxieapps.gwt.highcharts.client.Series;

/**
 * Represents the general plot options available for all area spline type series, which can be set either generically
 * on the chart via the {@link Chart#setAreaSplinePlotOptions(AreaSplinePlotOptions)} )} method or directly on a
 * series via the {@link Series#setPlotOptions(PlotOptions)} method.
 * <p/>
 * Note that these options are only needed if you want to specifically control the general options
 * for all area spline type series in the entire chart.  If you instead want to control the options for all
 * series in the chart (not just those of an area spline type), then you can use the {@link SeriesPlotOptions}
 * class instead.  Or, if you want to control the plot options for just one series (and not all area spline type
 * series in the chart), use the {@link org.moxieapps.gwt.highcharts.client.Series#setPlotOptions(PlotOptions)} method.
 *
 * @author squinn@moxiegroup.com (Shawn Quinn)
 * @since 1.0.0
 */
public class AreaSplinePlotOptions extends PlotOptions<AreaSplinePlotOptions> {

    /**
     * Convenience method for setting the 'fillColor' option of the area plot options
     * to a RGB hex value.  Equivalent to:
     * <pre><code>
     *     areaSplinePlotOptions.setOption("fillColor", "#CCCCCC");
     * </code></pre>
     * Fill color or gradient for the area. When null, the series' color is used with the series' fillOpacity. Defaults to null.
     * <p/>
     * Note that this method is intended for setting the color to a simple RBG hex value.  If you instead
     * want to set a color to include an alpha channel or a gradient, use the {@link #setFillColor(org.moxieapps.gwt.highcharts.client.Color)}
     * version instead.
     *
     * @param fillColor The value to set as the 'fillColor' option of the area plots.
     * @return A reference to this {@link AreaSplinePlotOptions} instance for convenient method chaining.
     */
    public AreaSplinePlotOptions setFillColor(String fillColor) {
        return this.setOption("fillColor", fillColor);
    }

    /**
     * Convenience method for setting the 'fillColor' option of the area plot options, allowing for
     * colors with opacity or gradients.  Equivalent to:
     * <pre><code>
     *     areaSplinePlotOptions.setOption("fillColor", new Color()
     *        .setLinearGradient(0.0, 0.0, 1.0, 1.0)
     *        .addStop(new Color(255, 255, 255))
     *        .addStop(new Color(200, 200, 255))
     *     );
     * </code></pre>
     * Fill color or gradient for the area. When null, the series' color is used with the series' fillOpacity. Defaults to null.
     * <p/>
     * Note that this method is intended for setting the color to a gradient or color that includes
     * an alpha channel.  If you instead just want to set the color to a normal RGB hex value
     * you can use the {@link #setFillColor(String)} version instead.
     *
     * @param fillColor The color gradient or color with an alpha channel to set as the 'fillColor' option of the area plot.
     * @return A reference to this {@link AreaSplinePlotOptions} instance for convenient method chaining.
     */
    public AreaSplinePlotOptions setFillColor(Color fillColor) {
        return this.setOption("fillColor", fillColor != null ? fillColor.getOptionValue() : null);
    }

    /**
     * Convenience method for setting the 'fillOpacity' option of the area plot options.  Equivalent to:
     * <pre><code>
     *     areaSplinePlotOptions.setOption("fillOpacity", 0.5);
     * </code></pre>
     * Fill opacity for the area. Defaults to .75.
     *
     * @param fillOpacity Fill opacity for the area.
     * @return A reference to this {@link AreaSplinePlotOptions} instance for convenient method chaining.
     */
    public AreaSplinePlotOptions setFillOpacity(Number fillOpacity) {
        return this.setOption("fillOpacity", fillOpacity);
    }

    /**
     * Convenience method for setting the 'lineColor' option of the area plot options to a RGB hex value.  Equivalent to:
     * <pre><code>
     *     areaSplinePlotOptions.setOption("lineColor", "#CCCCCC");
     * </code></pre>
     * A separate color for the graph line. By default the line takes the color of the series, but the
     * lineColor setting allows setting a separate color for the line without altering the fillColor. Defaults to null.
     * <p/>
     * Note that this method is intended for setting the color to a simple RBG hex value.  If you instead
     * want to set a color to include an alpha channel or a gradient, use the {@link #setLineColor(Color)}
     * version instead.
     *
     * @param lineColor The value to set as the 'lineColor' option of the area plots.
     * @return A reference to this {@link AreaSplinePlotOptions} instance for convenient method chaining.
     */
    public AreaSplinePlotOptions setLineColor(String lineColor) {
        return this.setOption("lineColor", lineColor);
    }

    /**
     * Convenience method for setting the 'lineColor' option of the area plot options, allowing for
     * colors with opacity or gradients.  Equivalent to:
     * <pre><code>
     *     areaSplinePlotOptions.setOption("lineColor", new Color()
     *        .setLinearGradient(0.0, 0.0, 1.0, 1.0)
     *        .addStop(new Color(255, 255, 255))
     *        .addStop(new Color(200, 200, 255))
     *     );
     * </code></pre>
     * A separate color for the graph line. By default the line takes the color of the series, but the
     * lineColor setting allows setting a separate color for the line without altering the fillColor. Defaults to null.
     * <p/>
     * Note that this method is intended for setting the color to a gradient or color that includes
     * an alpha channel.  If you instead just want to set the color to a normal RGB hex value
     * you can use the {@link #setLineColor(String)} version instead.
     *
     * @param lineColor The color gradient or color with an alpha channel to set as the 'lineColor' option of the area plots.
     * @return A reference to this {@link AreaSplinePlotOptions} instance for convenient method chaining.
     */
    public AreaSplinePlotOptions setLineColor(Color lineColor) {
        return this.setOption("lineColor", lineColor != null ? lineColor.getOptionValue() : null);
    }

    /**
     * Convenience method for setting the 'threshold' option of the area plot options.  Equivalent to:
     * <pre><code>
     *     areaSplinePlotOptions.setOption("threshold", 0.5);
     * </code></pre>
     * The Y axis value to serve as the base for the area, for distinguishing between values above and
     * below a threshold. Defaults to 0.
     *
     * @param threshold The Y axis value to serve as the base for the area, for distinguishing between
     *                  values above and below a threshold.
     * @return A reference to this {@link AreaSplinePlotOptions} instance for convenient method chaining.
     */
    public AreaSplinePlotOptions setThreshold(String threshold) {
        return this.setOption("threshold", threshold);
    }

}
