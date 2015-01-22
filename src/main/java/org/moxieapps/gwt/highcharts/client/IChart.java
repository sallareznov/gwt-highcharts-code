/*
 * File: $URL$
 * $Id$
 * Copyright: Vekia
 *
 * Last change:
 * $Date$
 * $Author$
 */
package org.moxieapps.gwt.highcharts.client;

import org.moxieapps.gwt.highcharts.client.events.ChartClickEventHandler;
import org.moxieapps.gwt.highcharts.client.plotOptions.AreaPlotOptions;
import org.moxieapps.gwt.highcharts.client.plotOptions.Marker;
import org.moxieapps.gwt.highcharts.client.plotOptions.PiePlotOptions;
import org.moxieapps.gwt.highcharts.client.plotOptions.PlotOptions;
import org.moxieapps.gwt.highcharts.client.plotOptions.SeriesPlotOptions;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.ui.IsWidget;

/**
 * @author sdiagne
 *
 * @param <T>
 */
public interface IChart extends IsWidget {

    /**
     * Sets the default colors for the chart's series. When all colors are used, new colors are pulled from the start again.
     * Note that the colors for each series can be overridden via the {@link org.moxieapps.gwt.highcharts.client.plotOptions.PlotOptions#setColor(String)} mechanism.
     * <p/>
     * Default colors are:  "#2f7ed8", "#0d233a", "#8bbc21", "#910000", "#1aadce", "#492970", "#f28f43", "#77a1e5", "#c42525", "#a6c96a"
     * <p/>
     * Note: in Highcharts 2.x, default colors were: "#4572A7", "#AA4643", "#89A54E", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"
     * @param colors An array of colors to use as the defaults for each series.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    IChart setColors(String... colors);

    /**
     * Convenience method for setting the 'exporting' options of the chart.  Equivalent to:
     * <pre><code>
     *     chart.setOption("/exporting/enabled", true);
     *     chart.setOption("/exporting/width", 600);
     *     etc..
     * </code></pre>
     * Note that the "exporting" module must be included in the page in order for the exporting
     * options to apply.  E.g.:
     * <p/>
     * &lt;script type="text/javascript" src="js/modules/exporting.js"&gt;&lt;/script&gt;
     *
     * @param exporting The options to apply to the exporting area of the chart.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     * @see #setNavigation(Navigation)
     * @since 1.1.0
     */
    IChart setExporting(Exporting exporting);

    /**
     * Convenience method for setting the 'height' option of the chart.  Equivalent to:
     * <pre><code>
     *     chart.setOption("/chart/height", 300);
     * </code></pre>
     * An explicit height for the chart. By default the height is calculated from the offset
     * height of the containing element. Defaults to null.
     *
     * @param height An explicit height for the chart.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    IChart setHeight(Number height);


    /**
     * Convenience method for setting the 'loading' options of the chart.  Equivalent to:
     * <pre><code>
     *     chart.setOption("/loading/showDuration", 150);
     *     chart.setOption("/loading/hideDuration", 60);
     *     etc..
     * </code></pre>
     * The loading options control the appearance of the loading screen that covers the plot area
     * on chart operations. This screen only appears after an explicit call to
     * {@link Chart#showLoading(String)}. It is a utility for developers to communicate to the end
     * user that something is going on, for example while retrieving new data via a GWChartType remoting
     * request. The "Loading..." text itself is not part of this configuration object, but part of
     * the {@link Lang} object.
     *
     * @param loading The options to apply to the loading area of the chart.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    IChart setLoading(Loading loading);

    /**
     * Convenience method for setting the 'margin' option of the chart.  Equivalent to:
     * <pre><code>
     *     chart.setOption("/chart/margin", 0, 10, 0, 15);
     * </code></pre>
     * The margin between the outer edge of the chart and the plot area. Use the options
     * {@link #setMarginTop(Number)}, {@link #setMarginRight(Number)}, {@link #setMarginBottom(Number)}, and
     * {@link #setMarginLeft(Number)} for shorthand setting of one option.
     * <p/>
     * Since version 2.1, the margin is 0 by default. The actual space is dynamically calculated from
     * the offset of axis labels, axis title, title, subtitle and legend in addition to the
     * {@link #setSpacingTop(Number)}, {@link #setSpacingRight(Number)}, {@link #setSpacingBottom(Number)},
     * and {@link #setSpacingLeft(Number)} options.
     *
     * @param marginTop    The margin between the top outer edge of the chart and the plot area.
     * @param marginRight  The margin between the right outer edge of the chart and the plot area.
     * @param marginBottom The margin between the bottom outer edge of the chart and the plot area.
     * @param marginLeft   The margin between the left outer edge of the chart and the plot area.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    IChart setMargin(Number marginTop, Number marginRight, Number marginBottom, Number marginLeft);

    /**
     * Convenience method for setting the 'shadow' option of the chart.  Equivalent to:
     * <pre><code>
     *     chart.setOption("/chart/shadow", true);
     * </code></pre>
     * Whether to apply a drop shadow to the outer chart area. Requires that backgroundColor be set. Defaults to false.
     *
     * @param shadow If true, apply a drop shadow to the outer chart area.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    IChart setShadow(boolean shadow);

    /**
     * Convenience method for setting the 'showAxes' option of the chart.  Equivalent to:
     * <pre><code>
     *     chart.setOption("/chart/showAxes", true);
     * </code></pre>
     * Whether to show the axes initially. This only applies to empty charts where series are added
     * dynamically, as axes are automatically added to cartesian series. Defaults to false.
     *
     * @param showAxes If true, show the axes initially (only applies to empty charts when the series
     *                 are added dynamically).
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    IChart setShowAxes(boolean showAxes);

    /**
     * Convenience method for setting the 'spacingTop' option of the chart.  Equivalent to:
     * <pre><code>
     *     chart.setOption("/chart/spacingTop", 100);
     * </code></pre>
     * The space between the top edge of the chart and the content (plot area, axis title and labels,
     * title, subtitle or legend in top position). Defaults to 10.
     *
     * @param spacingTop The space between the top edge of the chart and the content.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    IChart setSpacingTop(Number spacingTop);

    /**
     * Convenience method for setting the 'spacingRight' option of the chart.  Equivalent to:
     * <pre><code>
     *     chart.setOption("/chart/spacingRight", 100);
     * </code></pre>
     * The space between the right edge of the chart and the content (plot area, axis title and labels,
     * title, subtitle or legend in top position). Defaults to 10.
     *
     * @param spacingRight The space between the right edge of the chart and the content.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    IChart setSpacingRight(Number spacingRight);

    /**
     * Convenience method for setting the 'spacingBottom' option of the chart.  Equivalent to:
     * <pre><code>
     *     chart.setOption("/chart/spacingBottom", 100);
     * </code></pre>
     * The space between the bottom edge of the chart and the content (plot area, axis title and labels,
     * title, subtitle or legend in top position). Defaults to 15.
     *
     * @param spacingBottom The space between the bottom edge of the chart and the content.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    IChart setSpacingBottom(Number spacingBottom);

    /**
     * Convenience method for setting the 'spacingLeft' option of the chart.  Equivalent to:
     * <pre><code>
     *     chart.setOption("/chart/spacingLeft", 100);
     * </code></pre>
     * The space between the left edge of the chart and the content (plot area, axis title and labels,
     * title, subtitle or legend in top position). Defaults to 10.
     *
     * @param spacingLeft The space between the left edge of the chart and the content.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    IChart setSpacingLeft(Number spacingLeft);

    /**
     * Convenience method for setting the 'style' options of the chart.  Equivalent to:
     * <pre><code>
     *     chart.setOption("/chart/style/fontWeight", "bold");
     *     chart.setOption("/chart/style/fontFamily", "serif");
     *     etc.
     * </code></pre>
     * Additional CSS styles to apply inline to the container div. Note that since the default
     * font styles are applied in the renderer, it is ignorant of the individual chart options
     * and must be set globally. Defaults to:
     * <ul>
     * <li>fontFamily: '"Lucida Grande", "Lucida Sans Unicode", Verdana, Arial, Helvetica, sans-serif'</li>
     * <li>fontSize: '12px'</li>
     * </ul>
     *
     * @param style An object containing the style properties to set on the chart.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    IChart setStyle(Style style);

    /**
     * Sets the default symbols for the series point markers. When all symbols are used, new symbols
     * are pulled from the start again.  Note that the symbols for each series can be overridden via the
     * {@link org.moxieapps.gwt.highcharts.client.plotOptions.Marker#setSymbol(org.moxieapps.gwt.highcharts.client.plotOptions.Marker.Symbol)} method
     * (which can be set on the series via its plot options, see
     * {@link Series#setPlotOptions(org.moxieapps.gwt.highcharts.client.plotOptions.PlotOptions)}).
     * <p/>
     * Default symbols are:  Circle, Diamond, Square, Triangle, and Triangle-Down
     *
     * @param symbols An array of symbols to use as the default symbols for the series point markers.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    IChart setSymbols(Marker.Symbol... symbols);

    /**
     * sets a new title for the chart
     * @param title the title to set
     * @see setTitle(ChartTitle)
     */
    void setTitle(String title);

    /**
     * sets a new title and a new subtitle for the chart
     * @param title the title to set
     * @param subtitle the subtitle to set
     * @see setTitle(ChartTitle, ChartSubtitle)
     */
    void setTitle(String title, String subtitle);

    /**
     * Convenience method for setting the 'tooltip' option of the chart.  Equivalent to:
     * <pre><code>
     *     chart.setOption("/tooltip/borderColor", "#CCCCCC");
     *     chart.setOption("/tooltip/shadow", true);
     *     etc..
     * </code></pre>
     * The tooltip appears when the user hovers over a series or point.
     *
     * @param toolTip The options to apply to the tooltip of the chart.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    IChart setToolTip(ToolTip toolTip);

    /**
     * Sets the defaults series type for the chart (which controls the way the series will be rendered), using
     * an enumeration type in order to ensure a correct value is passed.  This is equivalent to
     * setting the option manually with code like:
     * <pre><code>
     *     chart.setOption("/chart/type", "line");
     * </code></pre>
     * Note that if you don't set this explicitly the default series type is {@link Series.Type#LINE}.
     *
     * @param type One of the supported series types.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    IChart setType(Series.Type type);

    /**
     * Convenience method for setting the 'width' option of the chart.  Equivalent to:
     * <pre><code>
     *     chart.setOption("/chart/width", 800);
     * </code></pre>
     * An explicit width for the chart. By default the width is calculated from the offset
     * width of the containing element. Defaults to null.
     *
     * @param width An explicit width for the chart.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    IChart setWidth(Number width);

    /**
     * Updates the size of the chart to match the given width and height, using the default animation options.
     * Note that if you simply want to set the size of the chart to match the container in which it was inserted,
     * you can simply call the {@link #setSizeToMatchContainer()} method instead.
     *
     * @param width  The new pixel width of the chart.
     * @param height The new pixel height of the chart.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    IChart setSize(int width, int height);
    
    void setSize(String width, String height);

    /**
     * Dim the chart's plot area and show a loading label text. Options for the loading screen are defined via
     * {@link Chart#setLoading(Loading)}.  A custom text can be given as a parameter, otherwise the default
     * message specified via the {@link Lang} options will be used.
     * Should be used in conjunction with the {@link #hideLoading()} method.<p/>
     * This method has no effect if the chart has not yet been rendered.
     *
     * @param message A custom message to appear as the loading text.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    IChart showLoading(String message);

    /**
     * Redraw the chart after changes have been done to the data or axis extremes. All methods for
     * updating axes, series or points have a parameter for redrawing the chart. This is true by default.
     * But in many cases you want to do more than one operation on the chart before redrawing, for
     * example add a number of points. In those cases it is a waste of resources to redraw the chart
     * for each new point added. So you add the points and call <code>redraw()</code> after.
     * <p/>
     * The <code>redraw()</code> method only redraws those parts of the chart that are actually changed.
     * If the data of a series is changed and it doesn't affect the axes, only the series itself is redrawn.
     * If the new data requires the axis extremes to be altered, the axis and all other series depending
     * on it are redrawn.
     *
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    IChart redraw();
    
    /**
     * General purpose method to set an arbitrary option on the chart at any level,
     * using "/" characters to designate which level of option you'd like to set.
     * E.g., the following code:
     * <pre><code>
     * Chart chart = new Chart();
     * chart.setOption("/chart/type", "spline");
     * chart.setOption("/chart/marginRight", 10);
     * chart.setOption("/title/text", "Nice Chart");
     * </code></pre>
     * Would result in initializing HighCharts like the following:
     * <pre><code>
     * new HighCharts.Chart({
     *     chart: {
     *         type: "spline",
     *         marginRight: 10
     *     },
     *     title: {
     *         text: "Nice Chart"
     *     }
     * });
     * </code></pre>
     * Note that the beginning "/" is optional, so <code>chart.setOption("/thing", "piglet")</code> is
     * equivalent to <code>chart.setOption("thing", "piglet")</code>.
     * <p/>
     * For details on available options see the <a href="http://www.highcharts.com/ref/">Highcharts reference</a>.
     * <p/>
     * Note that, when possible, you'll ideally want to use one of the available type specific setter
     * methods instead of this general method.  E.g. instead of doing this:
     * <pre><code>
     * chart.setOption("/chart/type", "spline");
     * </code></pre>
     * Do this instead:
     * <pre><code>
     * chart.setType(Series.Type.SPLINE);
     * </code></pre>
     *
     * @param path  The path to the option to set (e.g. "/title/text");
     * @param value The value to set for the option (can be a String, Number, Boolean, or JSONObject)
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    IChart setOption(String path, Object value);
    
    /**
     * Convenience method for setting the 'animation' option of the chart.  Equivalent to:
     * <pre><code>
     *     chart.setOption("/chart/animation", true);
     * </code></pre>
     * Sets the overall animation for all chart updating. Animation can be disabled throughout
     * the chart by setting it to false here. It can be overridden for each individual API
     * method as a function parameter. The only animation not affected by this option is the
     * initial series animation, see {@link org.moxieapps.gwt.highcharts.client.plotOptions.PlotOptions#setAnimation(boolean)} method for
     * control over series animations that are added after the chart has been rendered.
     * <p/>
     * Note that more control over the animations is available by calling the
     * {@link #setAnimation(Animation)} method instead.
     * <p/>
     *
     * @param animation The value to set as the 'animation' option on the chart.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    IChart setAnimation(boolean animation);
    
    /**
     * Retrieve a reference to the XAxis for this chart (guaranteed non-null), so that it can then be
     * configured or operated on.  Note that you should use this method when you only have one x-axis
     * to operate on.  If you have multiple x-axis, then utilize the {@link #getXAxis(int)} method instead.
     *
     * @return A reference to the XAxis for this chart (never null);
     */
    XAxis getXAxis();
    
    /**
     * Retrieve a reference to the YAxis for this chart (guaranteed non-null), so that it can then be
     * configured or operated on.  Note that you should use this method when you only have one y-axis
     * to operate on.  If you have multiple y-axis, then utilize the {@link #getYAxis(int)} method instead.
     *
     * @return A reference to the YAxis for this chart (never null);
     */
    YAxis getYAxis();
    
    /**
     * Convenience method for setting the 'legend' options of the chart.  Equivalent to:
     * <pre><code>
     *     chart.setOption("/legend/borderColor", "#CCCCCC");
     *     chart.setOption("/legend/layout", Legend.Layout.HORIZONTAL);
     *     etc..
     * </code></pre>
     * The legend is a box containing a symbol and name for each series item or point item in the chart.
     *
     * @param legend The options to apply to the legend of the chart.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    IChart setLegend(Legend legend);
    
    /**
     * Add the given data series to the chart, using the default options.  Note, see the
     * {@link #addSeries(Series, boolean, boolean)} and {@link #addSeries(Series, boolean, Animation)} for more
     * control over how the series will be drawn and animated when added to a chart that has already been rendered.
     *
     * @param series The data series to add to the chart, including its general configuration and plot options.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    IChart addSeries(Series series);
    
    /**
     * Updates the general options that all series within the chart will use by default.  The settings can then
     * be overridden for each individual series via the {@link Series#setPlotOptions(PlotOptions)} method.
     * <p/>
     * Note that the general {@link SeriesPlotOptions} type only represents core options that area available for all series types.
     * To control options that are more specific to each series type, use one of the more specific methods instead (e.g.
     * {@link #setLinePlotOptions(org.moxieapps.gwt.highcharts.client.plotOptions.LinePlotOptions)},
     * {@link #setAreaPlotOptions(org.moxieapps.gwt.highcharts.client.plotOptions.AreaPlotOptions)}, etc.)
     * <p/>
     * Also note that changing the general plot options on a chart that has already been rendered will only affect
     * series that are subsequently added to the chart, and therefore will not impact any of the series that are already
     * rendered in the chart.
     *
     * @param seriesPlotOptions The options to set on the chart as the default settings for all series that are part of this chart.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    IChart setSeriesPlotOptions(SeriesPlotOptions seriesPlotOptions);
    
    /**
     * Convenience method for setting the 'backgroundColor' option of the chart to an RGB hex value.  Equivalent to:
     * <pre><code>
     *     chart.setOption("/chart/backgroundColor", "#CCCCCC");
     * </code></pre>
     * The RGB background color for the outer chart area. Defaults to "#FFFFFF".
     * <p/>
     * Note that this method is intended for setting the color to a simple RBG hex value.  If you instead
     * want to set a color to include an alpha channel or a gradient, use the {@link #setBackgroundColor(Color)}
     * version instead.
     *
     * @param backgroundColor The value to set as the 'backgroundColor' option on the chart.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    IChart setBackgroundColor(String backgroundColor);

    /**
     * Convenience method for setting the 'backgroundColor' option of the chart, allowing for
     * colors with opacity or gradients.  Equivalent to:
     * <pre><code>
     *     chart.setOption("/chart/backgroundColor", new Color()
     *        .setLinearGradient(0.0, 0.0, 1.0, 1.0)
     *        .addStop(new Color(255, 255, 255))
     *        .addStop(new Color(200, 200, 255))
     *     );
     * </code></pre>
     * The background color or gradient for the outer chart area. Defaults to "#FFFFFF".
     * <p/>
     * Note that this method is intended for setting the color to a gradient or color that includes
     * an alpha channel.  If you instead just want to set the color to a normal RGB hex value
     * you can use the {@link #setBackgroundColor(String)} version instead.
     *
     * @param backgroundColor The color gradient or color with an alpha channel to set as the 'backgroundColor' option on the chart.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    IChart setBackgroundColor(Color backgroundColor);
    
    /**
     * Convenience method for setting the 'borderWidth' option of the chart.  Equivalent to:
     * <pre><code>
     *     chart.setOption("/chart/borderWidth", 10);
     * </code></pre>
     * The pixel width of the outer chart border. The border is painted using vector graphic
     * techniques to allow rounded corners. Defaults to 0.
     *
     * @param borderWidth The corner radius of the outer chart border.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    IChart setBorderWidth(Number borderWidth);
    
    /**
     * Updates the options that all pie type series within the chart will use by default.  The settings can then
     * be overridden for each individual series via the {@link Series#setPlotOptions(PlotOptions)} method.
     * <p/>
     * Note that changing the plot options on a chart that has already been rendered will only affect
     * series that are subsequently added to the chart (and will not impact any of the series that are already
     * rendered in the chart.)
     *
     * @param piePlotOptions The options to set on the chart as the default settings for all pie type series
     *                       that are part of this chart.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    IChart setPiePlotOptions(PiePlotOptions piePlotOptions);
    
    /**
     * Convenience method for setting the 'credits' options of the chart.  Equivalent to:
     * <pre><code>
     *     chart.setOption("/credits/text", "Presented by Snoopy");
     *     chart.setOption("/credits/href", "http://www.peanuts.com/");
     *     etc..
     * </code></pre>
     * Highchart by default puts a credits label in the lower right corner of the chart. This
     * can be changed using these options.
     *
     * @param credits The options to apply to the credits area of the chart.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    IChart setCredits(Credits credits);
    
    /**
     * Updates the options that all area type series within the chart will use by default.  The settings can then
     * be overridden for each individual series via the {@link Series#setPlotOptions(PlotOptions)} method.
     * <p/>
     * Note that changing the plot options on a chart that has already been rendered will only affect
     * series that are subsequently added to the chart (and will not impact any of the series that are already
     * rendered in the chart.)
     *
     * @param areaPlotOptions The options to set on the chart as the default settings for all area type series
     *                        that are part of this chart.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    IChart setAreaPlotOptions(AreaPlotOptions areaPlotOptions);
    
    /**
     * Create a new data series that can be configured, and then added to this chart instance via the
     * {@link #addSeries(Series)} method.
     *
     * @return The series that was created (which will need to be added to the chart after it is configured
     *         via {@link #addSeries(Series)}.
     */
    Series createSeries();
    
    /**
     * Convenience method for setting the 'reflow' option of the chart.  Equivalent to:
     * <pre><code>
     *     chart.setOption("/chart/reflow", false);
     * </code></pre>
     * Whether to reflow the chart to fit the width of the container div on resizing
     * the window. Defaults to true.
     *
     * @param reflow If true, reflow the chart to fit the width of the container div on resizing
     *               the window.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    IChart setReflow(boolean reflow);
    
    Legend getLegend();
    
    /**
     * Returns a pointer to the native Highcharts or Highstock chart instance that this GWT BaseChart
     * instance is associated with.  Note that this method will only return a non-null value if it
     * is called after the chart has been rendered.  For advanced use-cases only.
     *
     * @return The native Highcharts or Highstock JS chart instance that this BaseChart is associated with, or
     *         null if the chart has not yet been rendered.
     * @since 1.4.0
     */
    JavaScriptObject getNativeChart();
    
    /**
     * Set a callback handler that will be invoked whenever the user clicks on the plot background.
     * Information where the user clicked can then be found through the {@link org.moxieapps.gwt.highcharts.client.events.ChartClickEvent} instance
     * that is passed to the handler's {@link org.moxieapps.gwt.highcharts.client.events.ChartClickEventHandler#onClick(org.moxieapps.gwt.highcharts.client.events.ChartClickEvent)} method.
     *
     * @param chartClickEventHandler The handler that should be invoked whenever a click event occurs.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     * @since 1.1.0
     */
    IChart setClickEventHandler(ChartClickEventHandler chartClickEventHandler);
    
    /**
     * Return an array of all the {@link Series} instances that have been added to this chart (either before or
     * after the chart was rendered), or an empty array if none have been added yet. Note that we're returning
     * an array instead of a java.util.List as the generic interface types require a bit of a performance hit
     * in GWT to use.
     *
     * @return An array of the Series instances currently within the chart, or an empty array if none have yet
     *         been added.
     */
    Series[] getSeries();
    
    SeriesPlotOptions getSeriesPlotOptions();
    
    Exporting getExporting();
    
//    IChart setDrilldown(Drilldown drilldown);

}