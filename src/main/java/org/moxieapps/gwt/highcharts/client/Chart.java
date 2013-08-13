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

package org.moxieapps.gwt.highcharts.client;

/**
 * The main GWT widget that can be constructed and then configured in order to add a Highcharts
 * chart into a GWT layout container.  Note that only standard chart types can be created with
 * this widget.  See the {@link StockChart} for other available chart types.
 * Basic usage is as follows:
 * <pre><code>
 * Chart chart = new Chart()
 *    .setType(Series.Type.SPLINE)
 *    .setChartTitleText("Nice Chart")
 *    .setMarginRight(10);
 * Series series = chart.createSeries()
 *   .addPoint(40)
 *   .addPoint(35)
 *   .addPoint(60);
 * chart.addSeries(series);
 * RootPanel.get().add(chart);
 * </code></pre>
 * For details on available options see the <a href="http://www.highcharts.com/ref/">Highcharts reference</a>.
 * <p/>
 * Note that in order for this widget to function you must have included the Highcharts javascript
 * library and any of its dependencies in the page that the widget will run inside of.  E.g.:
 * <pre><code>
 * &lt;script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"&gt;&lt;/script&gt;
 * &lt;script type="text/javascript" src="js/highcharts.js"&gt;&lt;/script&gt;
 * </code></pre><pre><code>
 * &lt;!-- Optionally, add a highcharts theme file --&gt;
 * &lt;script type="text/javascript" src="js/themes/gray.js"&gt;&lt;/script&gt;
 * </code></pre><pre><code>
 * &lt;!-- Optionally, include the highcharts exporting module --&gt;
 * &lt;script type="text/javascript" src="js/modules/exporting.js"&gt;&lt;/script&gt;
 * </code></pre>
 * Note that Highcharts supports other JS frameworks besides jQuery for its internal DOM manipulation
 * functionality.  So, if jQuery isn't your cup of tea check the
 * <a href="http://www.highcharts.com/documentation/how-to-use#installation">installation docs</a>
 * on the Highcharts site for more details.
 *
 * @author squinn@moxiegroup.com (Shawn Quinn)
 * @since 1.0.0
 */
public class Chart extends BaseChart<Chart> {

    /**
     * An enumeration of supported chart zoom types, which can be passed to the
     * {@link Chart#setZoomType(ZoomType)} method.  The zoom type controls in what
     * dimensions the user can zoom by dragging the mouse
     */
    public enum ZoomType {

        /**
         * Allow zoom horizontally on the X axis only.
         */
        X("x"),

        /**
         * Allow zoom vertically on the Y axis only.
         */
        Y("y"),

        /**
         * Allow zooming both horizontally and vertically (both axes).
         */
        X_AND_Y("xy");

        private ZoomType(String optionValue) {
            this.optionValue = optionValue;
        }

        private final String optionValue;

        public String toString() {
            return optionValue;
        }

    }


    /**
     * Create a new Highcharts chart instance as a GWT Widget that can then be added to
     * a GWT layout like any other widget. Note that the various methods that support
     * setting properties of the chart (e.g. {@link #setType(org.moxieapps.gwt.highcharts.client.Series.Type)},
     * {@link #setBackgroundColor(String)}, {@link #setOption(String, Object)}, etc.)
     * then support method chaining, allowing for syntax like the following:
     * <pre><code>
     * Chart chart = new Chart()
     *    .setType(Series.Type.SPLINE)
     *    .setChartTitleText("Nice Chart")
     *    .setMarginRight(10);
     * RootPanel.get().add(chart);
     * </code></pre>
     */
    public Chart() {
        super();
    }

    /**
     * Convenience method for setting the 'pane' options of the chart, which represents
     * a collection of options that apply only to polar charts and angular gauges.
     * <p/>
     * Note: many of the pane options are only available if the highcharts-more.js script is included in your GWT module. E.g.:
     * </p>
     * &lt;script type="text/javascript" src="js/highcharts-more.js"&gt;&lt;/script&gt;
     *
     * @param pane The pane options to apply to the chart.
     * @return A reference to this {@link Chart} instance for convenient method chaining.
     * @since 1.5.0
     */
    public Chart setPane(Pane pane) {
        return this.setOption("/pane", pane != null ? pane.getOptions() : null);
    }



    /**
     * Convenience method for setting the 'polar' option of the chart.  Equivalent to:
     * <pre><code>
     *     chart.setOption("/chart/polar", true);
     * </code></pre>
     * When true, cartesian charts like line, spline, area and column are transformed
     * into the polar coordinate system. Defaults to false.
     * <p>
     * Note: this option is only available if the highcharts-more.js script is included in your GWT module.
     * </p>
     *
     * @param polar The value to set as the 'polar' option on the chart.
     * @return A reference to this {@link Chart} instance for convenient method chaining.
     * @since 1.6.0
     */
    public Chart setPolar(boolean polar) {
        return this.setOption("/chart/polar", polar);
    }

    /**
     * Sets which dimensions the user can zoom by dragging the mouse.
     * Can be one of {@link Chart.ZoomType#X}, {@link Chart.ZoomType#Y} or
     * {@link Chart.ZoomType#X_AND_Y}. Defaults to null.
     * This is equivalent to setting the option manually with code like:
     * <pre><code>
     *     chart.setOption("/chart/zoomType", Chart.ZoomType.X);
     * </code></pre>
     *
     * @param zoomType One of the allowed zoom types.
     * @return A reference to this {@link Chart} instance for convenient method chaining.
     * @deprecated Use {@link BaseChart#setZoomType(BaseChart.ZoomType)}
     */
    public Chart setZoomType(Chart.ZoomType zoomType) {
        return this.setOption("/chart/zoomType", zoomType != null ? zoomType.toString() : null);
    }



    @Override
    protected String getChartTypeName() {
        return "Chart";
    }

}
