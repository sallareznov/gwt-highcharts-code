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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Document;
import com.google.gwt.json.client.JSONObject;
import org.moxieapps.gwt.highcharts.client.plotOptions.PlotOptions;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Manages a data series (and its options) that can then be added to a {@link Chart}.  As an extension
 * of {@link Configurable} each series instance can also optionally have configuration options set on it.
 * In order to create a new series, utilize the {@link Chart#createSeries()} method, configure it, add data
 * to it, and then add it to the chart via the {@link Chart#addSeries(Series)} method.  Note that a series
 * can be modified after the chart has been rendered as well, which allows for support of dynamic charts or series
 * that change in some way based on user behavior.  Simple example of setting up a series on a chart:
 * <code><pre>
 * Series series = chart.createSeries()
 *   .setName("Random Stuff")
 *   .addPoint(40)
 *   .addPoint(35)
 *   .addPoint(60);
 * chart.addSeries(series);
 * </pre></code>
 * Example of updating all of the points in a series at once:
 * <code><pre>
 * chart.addSeries(chart.createSeries()
 *   .setName("Random Stuff")
 *   .setPoints(new Number[] { 40, 35, 60 })
 * );
 * </pre></code>
 * Example of changing the options for one point in the series:
 * <code><pre>
 * chart.addSeries(chart.createSeries()
 *   .setName("Random Stuff")
 *   .addPoint(40)
 *   .addPoint(new Point(35).setColor("#BF0B23"))
 *   .addPoint(60)
 * );
 * </pre></code>
 *
 * @author squinn@moxiegroup.com (Shawn Quinn)
 * @since 1.0
 */
public class Series extends Configurable<Series> {

    /**
     * An enumeration of supported series types, which can be passed to methods such as
     * {@link Series#setType(Series.Type)} or {@link Chart#setType(Series.Type)}.
     */
    public enum Type {

        /**
         * Show the series as an area filled in beneath a non-curved line
         */
        AREA("area"),

        /**
         * Show the series as an area filled in beneath a curved line
         */

        AREA_SPLINE("areaspline"),

        /**
         * Show the series as horizontal bars
         */
        BAR("bar"),

        /**
         * Show the series as vertical bars
         */
        COLUMN("column"),

        /**
         * Show the series as a sequence of connected straight lines
         */
        LINE("line"),

        /**
         * Show the series as a pie chart
         */
        PIE("pie"),

        /**
         * Show the series as a scatter plot
         */
        SCATTER("scatter"),

        /**
         * Show the series as a sequence of lines that are rendered as a spline to appear as a smooth curve
         */
        SPLINE("spline");

        private Type(String optionName) {
            this.optionName = optionName;
        }

        private final String optionName;

        public String toString() {
            return optionName;
        }

    }

    // Purposefully set to package scope
    BaseChart chart;

    // The unique id for this series, that we can use to access the native series instance later if changes
    // come into the series after it is rendered
    private String id;

    /**
     * Use the {@link Chart#createSeries()} method to create new series instances.
     *
     * @param chart The chart instance that this series is being created within.
     */
    Series(BaseChart chart) {
        this.chart = chart;
        id = Document.get().createUniqueId();
        setOption("id", id);
    }

    /**
     * Convenience method for setting the 'name' option of the series.  Equivalent to:
     * <pre><code>
     *     series.setOption("name", "My Chart");
     * </code></pre>
     * The name of the series as shown in the legend, tooltip etc. Defaults to "".
     *
     * @param name The string to set as the series name.
     * @return A reference to this {@link Series} instance for convenient method chaining.
     */
    public Series setName(String name) {
        return this.setOption("name", name);
    }

    /**
     * Convenience method for setting the 'stack' option of the series to a string.  Equivalent to:
     * <pre><code>
     *     series.setOption("stack", "Stack 1");
     * </code></pre>
     * The 'stack' option allows grouping series in a stacked chart. Defaults to null.  Note
     * that you can also set this option to a number instead via the {@link #setStack(Number)} method.
     *
     * @param stack The string to set as the 'stack' option on the series.
     * @return A reference to this {@link Series} instance for convenient method chaining.
     */
    public Series setStack(String stack) {
        return this.setOption("stack", stack);
    }

    /**
     * Convenience method for setting the 'stack' option of the series to a number.  Equivalent to:
     * <pre><code>
     *     series.setOption("stack", 1);
     * </code></pre>
     * The 'stack' option allows grouping series in a stacked chart. Defaults to null.  Note
     * that you can also set this option to a string instead via the {@link #setStack(String)} method.
     *
     * @param stack The number to set as the 'stack' option on the series.
     * @return A reference to this {@link Series} instance for convenient method chaining.
     */
    public Series setStack(Number stack) {
        return this.setOption("stack", stack);
    }

    /**
     * Sets the type of this series (which controls the way the series will be rendered), using
     * an enumeration type in order to ensure a correct value is passed.  This is equivalent to
     * setting the option manually with code like:
     * <pre><code>
     *     series.setOption("type", "line");
     * </code></pre>
     * Note that if you don't set this explicitly the default series type is {@link Series.Type#LINE}.
     *
     * @param type One of the supported series types.
     * @return A reference to this {@link Series} instance for convenient method chaining.
     */
    public Series setType(Type type) {
        return this.setOption("type", type.toString());
    }

    /**
     * Convenience method for setting the 'xAxis' property of the series to a number.  Equivalent to:
     * <pre><code>
     *     series.setOption("xAxis", 1);
     * </code></pre>
     * When using dual or multiple x axes, this number defines which xAxis the particular series is
     * connected to. It refers to the index of the axis in the xAxis array, with 0 being the first.
     * Defaults to 0.
     *
     * @param xAxis The number to set as the 'xAxis' option on the series.
     * @return A reference to this {@link Series} instance for convenient method chaining.
     */
    public Series setXAxis(Number xAxis) {
        return this.setOption("xAxis", xAxis);
    }

    /**
     * Convenience method for setting the 'yAxis' property of the series to a number.  Equivalent to:
     * <pre><code>
     *     series.setOption("yAxis", 1);
     * </code></pre>
     * When using dual or multiple y axes, this number defines which yAxis the particular series is
     * connected to. It refers to the index of the axis in the yAxis array, with 0 being the first.
     * Defaults to 0.
     *
     * @param yAxis The number to set as the 'yAxis' option on the series.
     * @return A reference to this {@link Series} instance for convenient method chaining.
     */
    public Series setYAxis(Number yAxis) {
        return this.setOption("yAxis", yAxis);
    }

    // Need to maintain a refernce to the plot options set on us in order to deal with potential custom data label formatters
    private PlotOptions plotOptions;

    /**
     * Updates the plot options for this series to reflect the given options.  The default
     * options for all series in the chart (of each type) can be set on the {@link Chart} instance,
     * and therefore you only need to use this method if you want to override the plot options for
     * this series only.
     * <p/>
     * Note that the general {@link PlotOptions} type only represents the abstract base class of all the
     * different series types.  To call this method you need to instantiate one of the concrete
     * sub-classes instead (e.g. {@link org.moxieapps.gwt.highcharts.client.plotOptions.AreaPlotOptions},
     * {@link org.moxieapps.gwt.highcharts.client.plotOptions.BarPlotOptions}, etc.).
     * <p/>
     * Also note that changing the plot options for a series after it has been rendered to the screen
     * does not change the way the live series will appear.  If you need to change the plot options
     * after the series is rendered, you'd instead need to first {@link #remove()} the series from
     * the chart and then re-add it to the chart via the {@link Chart#addSeries(Series)} method.
     *
     * @param plotOptions The plot options to use for this series (or one of its sub-types)
     * @return A reference to this {@link Series} instance for convenient method chaining.
     */
    public Series setPlotOptions(PlotOptions plotOptions) {

        this.plotOptions = plotOptions;

        if (plotOptions == null) {
            return this;
        }

        // Applying plot options directly to a series is a little different as the optoins
        // get applied directly to the root configuration object, instead of off within their
        // own sub object like almost everything else works.  So, we need to merge the given
        // options with our root options
        JSONObject plotOptionsJSON = plotOptions.getOptions();

        if(plotOptionsJSON != null) {
            for (String key : plotOptionsJSON.keySet()) {
                this.setOption(key, plotOptionsJSON.get(key));
            }
        }

        return this;
    }

    // Purposefully package scope
    PlotOptions getPlotOptions() {
        return this.plotOptions;
    }

    /**
     * Simplest way to add a point using the default options, and setting only the Y value
     * that the point should be rendered at within the series.  See the various overloaded
     * versions of the <code>addPoint()</code> method for more control over the way the
     * point is rendered.
     *
     * @param y The value on the Y axis that the point should be drawn at within the series.
     * @return A reference to this {@link Series} instance for convenient method chaining.
     */
    public Series addPoint(Number y) {
        return this.addPoint(new Point(y));
    }

    /**
     * Add a point to the series with a specific value on the Y axis, controlling the
     * options on how the change will be drawn to the series.
     *
     * @param y         The value on the Y axis that the point should be drawn at within the series.
     * @param redraw    Whether to redraw the chart after the point is added. When adding more than one
     *                  point, it is highly recommended that the redraw option be set to false, and instead
     *                  {@link Chart#redraw()} is explicitly called
     *                  after the adding of points is finished.
     * @param shift     Defaults to false. When shift is true, one point is shifted off the start of the
     *                  series as one is appended to the end. Use this option for live charts monitoring
     *                  a value over time.
     * @param animation Defaults to true. When true, the graph will be animated with default animation options.
     *                  Note, see the {@link #addPoint(Number, boolean, boolean, Animation)} method
     *                  for more control over how the animation will run.
     * @return A reference to this {@link Series} instance for convenient method chaining.
     */
    public Series addPoint(Number y, boolean redraw, boolean shift, boolean animation) {
        return this.addPoint(new Point(y), redraw, shift, animation);
    }

    /**
     * Add a point to the series with a specific value on the Y axis, controlling the
     * options on how the change will be drawn to the series.
     *
     * @param y         The value on the Y axis that the point should be drawn at within the series.
     * @param redraw    Whether to redraw the chart after the point is added. When adding more than one
     *                  point, it is highly recommended that the redraw option be set to false, and instead
     *                  {@link Chart#redraw()} is explicitly called
     *                  after the adding of points is finished.
     * @param shift     Defaults to false. When shift is true, one point is shifted off the start of the
     *                  series as one is appended to the end. Use this option for live charts monitoring
     *                  a value over time.
     * @param animation The custom animation to use when adding the point to the series.
     * @return A reference to this {@link Series} instance for convenient method chaining.
     */
    public Series addPoint(Number y, boolean redraw, boolean shift, Animation animation) {
        return this.addPoint(new Point(y), redraw, shift, animation);
    }

    /**
     * Simple way to add a point using the default options, and setting only the X and Y value
     * that the point should be rendered at within the series.  See the various overloaded
     * versions of the <code>addPoint()</code> method for more control over the way the
     * point is rendered.
     *
     * @param x The value on the X axis that the point should be drawn at within the series.
     * @param y The value on the Y axis that the point should be drawn at within the series.
     * @return A reference to this {@link Series} instance for convenient method chaining.
     */
    public Series addPoint(Number x, Number y) {
        return this.addPoint(new Point(x, y));
    }

    /**
     * Add a point to the series with a specific value on the X and Y axis, controlling the
     * options on how the change will be drawn to the series.
     *
     * @param x         The value on the X axis that the point should be drawn at within the series.
     * @param y         The value on the Y axis that the point should be drawn at within the series.
     * @param redraw    Whether to redraw the chart after the point is added. When adding more than one
     *                  point, it is highly recommended that the redraw option be set to false, and instead
     *                  {@link Chart#redraw()} is explicitly called
     *                  after the adding of points is finished.
     * @param shift     Defaults to false. When shift is true, one point is shifted off the start of the
     *                  series as one is appended to the end. Use this option for live charts monitoring
     *                  a value over time.
     * @param animation Defaults to true. When true, the graph will be animated with default animation options.
     *                  Note, see the {@link #addPoint(Number, Number, boolean, boolean, Animation)} method
     *                  for more control over how the animation will run.
     * @return A reference to this {@link Series} instance for convenient method chaining.
     */
    public Series addPoint(Number x, Number y, boolean redraw, boolean shift, boolean animation) {
        return this.addPoint(new Point(x, y), redraw, shift, animation);
    }

    /**
     * Add a point to the series with a specific value on the X and Y axis, controlling the
     * options on how the change will be drawn to the series.
     *
     * @param x         The value on the X axis that the point should be drawn at within the series.
     * @param y         The value on the Y axis that the point should be drawn at within the series.
     * @param redraw    Whether to redraw the chart after the point is added. When adding more than one
     *                  point, it is highly recommended that the redraw option be set to false, and instead
     *                  {@link Chart#redraw()} is explicitly called
     *                  after the adding of points is finished.
     * @param shift     Defaults to false. When shift is true, one point is shifted off the start of the
     *                  series as one is appended to the end. Use this option for live charts monitoring
     *                  a value over time.
     * @param animation The custom animation to use when adding the point to the series.
     * @return A reference to this {@link Series} instance for convenient method chaining.
     */
    public Series addPoint(Number x, Number y, boolean redraw, boolean shift, Animation animation) {
        return this.addPoint(new Point(x, y), redraw, shift, animation);
    }

    /**
     * Add a point to the series accepting the default options on how the point will be drawn.
     *
     * @param point The point to add to the series (which, in turn, can have its own configuration options).
     * @return A reference to this {@link Series} instance for convenient method chaining.
     */
    public Series addPoint(Point point) {
        return this.addPoint(point, true, false, true);
    }

    /**
     * Add a point to the series with a specific value on the X and Y axis, controlling the
     * options on how the change will be drawn to the series.
     *
     * @param point     The point to add to the series (which, in turn, can have its own configuration options).
     * @param redraw    Whether to redraw the chart after the point is added. When adding more than one
     *                  point, it is highly recommended that the redraw option be set to false, and instead
     *                  {@link Chart#redraw()} is explicitly called
     *                  after the adding of points is finished.
     * @param shift     Defaults to false. When shift is true, one point is shifted off the start of the
     *                  series as one is appended to the end. Use this option for live charts monitoring
     *                  a value over time.
     * @param animation Defaults to true. When true, the graph will be animated with default animation options.
     *                  Note, see the {@link #addPoint(Point, boolean, boolean, Animation)} method
     *                  for more control over how the animation will run.
     * @return A reference to this {@link Series} instance for convenient method chaining.
     */
    public Series addPoint(Point point, boolean redraw, boolean shift, boolean animation) {
        return this.addPoint(point, redraw, shift, animation ? new Animation() : null);
    }

    /**
     * Add a point to the series with a specific value on the X and Y axis, controlling the
     * options on how the change will be drawn to the series.
     *
     * @param point     The point to add to the series (which, in turn, can have its own configuration options).
     * @param redraw    Whether to redraw the chart after the point is added. When adding more than one
     *                  point, it is highly recommended that the redraw option be set to false, and instead
     *                  {@link Chart#redraw()} is explicitly called
     *                  after the adding of points is finished.
     * @param shift     Defaults to false. When shift is true, one point is shifted off the start of the
     *                  series as one is appended to the end. Use this option for live charts monitoring
     *                  a value over time.
     * @param animation The custom animation to use when adding the point to the series.
     * @return A reference to this {@link Series} instance for convenient method chaining.
     */
    public Series addPoint(Point point, boolean redraw, boolean shift, Animation animation) {
        if (isRendered()) {
            // We'll store the point directly in the DOM if we've already been rendered
            final JavaScriptObject nativeSeries = chart.get(this.id);
            if (nativeSeries != null) {
                if (animation == null || animation.getOptions() == null) {
                    final boolean animationFlag = animation != null;
                    if (point.isSingleValue()) {
                        nativeAddPoint(nativeSeries, point.getY(), redraw, shift, animationFlag);
                    } else {
                        nativeAddPoint(nativeSeries, convertPointToJavaScriptObject(point), redraw, shift, animationFlag);
                    }
                } else {
                    final JavaScriptObject animationOptions = animation.getOptions().getJavaScriptObject();
                    if (point.isSingleValue()) {
                        nativeAddPoint(nativeSeries, point.getY(), redraw, shift, animationOptions);
                    } else {
                        nativeAddPoint(nativeSeries, convertPointToJavaScriptObject(point), redraw, shift, animationOptions);
                    }
                }

            }
        } else {
            // If we haven't been rendered, then store the point in ourselves for now
            points.add(point);
        }
        return this;
    }

    /**
     * Remove this series from the chart it is a part of.
     * @return 'true' if the series was a part of an active chart and successfully removed, or 'false' if the
     *         given series had not yet been added to any chart.
     */
    public boolean remove() {
        return chart.removeSeries(this);
    }

    private static JavaScriptObject convertPointToJavaScriptObject(Point point) {
        final JSONObject options = point.getOptions() != null ? point.getOptions() : new JSONObject();
        Chart.addPointScalarValues(point, options);
        return options.getJavaScriptObject();
    }

    /**
     * Apply a new set of data (Y values only) to the series and automatically redraw it.  If you need
     * more control than just simply setting the y values of each data point, then use the
     * {@link #setPoints(Point[])} method instead.
     *
     * @param yValues The array of Y values to set on the data series (replacing any data already in place)
     * @return A reference to this {@link Series} instance for convenient method chaining.
     */
    public Series setPoints(Number[] yValues) {
        return this.setPoints(yValues, true);
    }

    /**
     * Apply a new set of data (Y values only) to the series and optionally redraw it.  If you need
     * more control than just simply setting the y values of each data point, then use the
     * {@link #setPoints(Point[], boolean)} method instead.
     *
     * @param yValues The array of Y values to set on the data series (replacing any data already in place)
     * @param redraw  Whether to redraw the chart after the series is altered. If doing more operations
     *                on the chart, it is a good idea to set redraw to false and then call
     *                {@link Chart#redraw()} after.
     * @return A reference to this {@link Series} instance for convenient method chaining.
     */
    public Series setPoints(Number[] yValues, boolean redraw) {
        this.points.clear();
        for (Number yValue : yValues) {
            this.addPoint(yValue);
        }
        return this;
    }

    /**
     * Apply a new set of data (X and Y values) to the series and automatically redraw it.  If you need
     * more control than just simply setting the x and y values of each data point, then use the
     * {@link #setPoints(Point[])} method instead.
     *
     * @param values A two dimensional array of values, where the main array is the list of points and
     *               each inner array contains two values representing the X and Y values respectively.
     * @return A reference to this {@link Series} instance for convenient method chaining.
     */
    public Series setPoints(Number[][] values) {
        return this.setPoints(values, true);
    }

    /**
     * Apply a new set of data (X and Y values) to the series and optionally redraw it.  If you need
     * more control than just simply setting the x and y values of each data point, then use the
     * {@link #setPoints(Point[])} method instead.
     *
     * @param values A two dimensional array of values, where the main array is the list of points and
     *               each inner array contains two values representing the X and Y values respectively.
     * @param redraw  Whether to redraw the chart after the series is altered. If doing more operations
     *                on the chart, it is a good idea to set redraw to false and then call
     *                {@link Chart#redraw()} after.
     * @return A reference to this {@link Series} instance for convenient method chaining.
     */
    public Series setPoints(Number[][] values, boolean redraw) {
        this.points.clear();
        for (Number[] xyValue : values) {
            this.addPoint(xyValue[0], xyValue[1]);
        }
        return this;
    }

    /**
     * Apply a new set of data to the series and automatically redraw it.
     *
     * @param points The array of points to set on the data series (replacing any data already in place)
     * @return A reference to this {@link Series} instance for convenient method chaining.
     */
    public Series setPoints(Point[] points) {
        return this.setPoints(points, true);
    }

    /**
     * Apply a new set of data to the series and optionally redraw it.
     *
     * @param points The array of points to set on the data series (replacing any data already in place)
     * @param redraw Whether to redraw the chart after the series is altered. If doing more operations
     *               on the chart, it is a good idea to set redraw to false and then call
     *               {@link Chart#redraw()} after.
     * @return A reference to this {@link Series} instance for convenient method chaining.
     */
    public Series setPoints(Point[] points, boolean redraw) {
        this.points.clear();
        Collections.addAll(this.points, points);
        if (isRendered()) {
            // TODO
        }
        return this;
    }

    /**
     * Retrieve the array of points that have been added to this series.
     *
     * @return The array of points, or an empty array (non null) if no points have been added to the series yet.
     */
    public Point[] getPoints() {
        return points.toArray(new Point[points.size()]);
    }

    /**
     * Internal method used to retrieve the unique id generated for this series.
     * @return The unique id of this series
     */
    String getId() {
        return id;
    }

    // Purposefully not using the generic "List" interface here in order optimize GWT performance.
    private ArrayList<Point> points = new ArrayList<Point>();

    // Purposefully setting to package scope
    void clearInternalPointsList() {
        this.points.clear();
    }

    boolean rendered = false;

    // Purposefully restricting to package scope
    void setRendered(boolean flag) {
        this.rendered = flag;
    }

    private boolean isRendered() {
        return this.rendered;
    }

    private static native JavaScriptObject nativeAddPoint(JavaScriptObject series, JavaScriptObject options, boolean redraw, boolean shift, JavaScriptObject animation) /*-{
        series.addPoint(options, redraw, shift, animation);
    }-*/;

    private static native JavaScriptObject nativeAddPoint(JavaScriptObject series, JavaScriptObject options, boolean redraw, boolean shift, boolean animation) /*-{
        series.addPoint(options, redraw, shift, animation);
    }-*/;

    private static native JavaScriptObject nativeAddPoint(JavaScriptObject series, Number value, boolean redraw, boolean shift, JavaScriptObject animation) /*-{
        series.addPoint(options, redraw, shift, animation);
    }-*/;

    private static native JavaScriptObject nativeAddPoint(JavaScriptObject series, Number value, boolean redraw, boolean shift, boolean animation) /*-{
        series.addPoint(options, redraw, shift, animation);
    }-*/;


}
