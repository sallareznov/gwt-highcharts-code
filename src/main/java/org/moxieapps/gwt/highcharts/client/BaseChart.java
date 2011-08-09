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
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.json.client.*;
import com.google.gwt.user.client.ui.Widget;
import org.moxieapps.gwt.highcharts.client.labels.AxisLabelsData;
import org.moxieapps.gwt.highcharts.client.labels.DataLabelsData;
import org.moxieapps.gwt.highcharts.client.labels.StackLabelsData;
import org.moxieapps.gwt.highcharts.client.plotOptions.*;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * The common base class for both {@link Chart} types as well as {@link StockChart} types.
 * You should not use this class directly, but instead create an instance of one of those
 * sub types.
 *
 * @author squinn@moxiegroup.com (Shawn Quinn)
 * @see Chart
 * @see StockChart
 * @since 1.0
 */
public abstract class BaseChart<T> extends Widget {

    /**
     * This class can not be created directly, but instead create an instance of one of the
     * sub types such as {@link Chart} or {@link StockChart}.
     */
    protected BaseChart() {
        final DivElement divElement = Document.get().createDivElement();
        divElement.getStyle().setOverflow(com.google.gwt.dom.client.Style.Overflow.HIDDEN);
        divElement.setId(Document.get().createUniqueId());
        this.setElement(divElement);
    }

    /**
     * Convenience method for setting the 'alignTicks' option of the chart.  Equivalent to:
     * <pre><code>
     *     chart.setOption("/chart/alignTicks", false);
     * </code></pre>
     * When using multiple axis, the ticks of two or more opposite axes will automatically be aligned
     * by adding ticks to the axis or axes with the least ticks. This can be prevented by setting
     * alignTicks to false. If the grid lines look messy, it's a good idea to hide them for the
     * secondary axis by setting gridLineWidth to 0. Defaults to true.
     *
     * @param alignTicks The value to set as the 'alignTicks' option on the chart.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    public T setAlignTicks(boolean alignTicks) {
        return this.setOption("/chart/alignTicks", alignTicks);
    }

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
    public T setAnimation(boolean animation) {
        return this.setOption("/chart/animation", animation);
    }

    /**
     * Convenience method for setting the 'animation' options of the chart.  Equivalent to code like:
     * <pre><code>
     *     chart.setOption("/chart/animation/duration", 500);
     *     chart.setOption("/chart/animation/easing", "linear");
     * </code></pre>
     * Sets the overall animation for all chart updating. Animation can be disabled throughout
     * the chart by setting it to false here. It can be overridden for each individual API
     * method as a function parameter. The only animation not affected by this option is the
     * initial series animation, see {@link org.moxieapps.gwt.highcharts.client.plotOptions.PlotOptions#setAnimation(Animation)} method for
     * control over series animations that are added after the chart has been rendered.
     * <p/>
     * Note that this is intended for users that want to have finite control over the way animations
     * behave.  To simply enable/disable animations, you can use the {@link #setAnimation(boolean)}
     * method instead.
     * <p/>
     *
     * @param animation The custom animation to set as the default animation type for the chart.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    public T setAnimation(Animation animation) {
        return this.setOption("/chart/animation", animation.getOptions());
    }

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
    public T setBackgroundColor(String backgroundColor) {
        return this.setOption("/chart/backgroundColor", backgroundColor);
    }

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
    public T setBackgroundColor(Color backgroundColor) {
        return this.setOption("/chart/backgroundColor", backgroundColor != null ? backgroundColor.getOptionValue() : null);
    }

    /**
     * Convenience method for setting the 'borderColor' option of the chart to an RGB hex value.  Equivalent to:
     * <pre><code>
     *     chart.setOption("/chart/borderColor", "#CCCCCC");
     * </code></pre>
     * The RGB color of the outer chart border. The border is painted using vector graphic techniques to allow
     * rounded corners. Defaults to "#4572A7".
     * <p/>
     * Note that this method is intended for setting the color to a simple RBG hex value.  If you instead
     * want to set a color to include an alpha channel or a gradient, use the {@link #setBorderColor(Color)}
     * version instead.
     *
     * @param borderColor The value to set as the 'borderColor' option on the chart.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    public T setBorderColor(String borderColor) {
        return this.setOption("/chart/borderColor", borderColor);
    }

    /**
     * Convenience method for setting the 'borderColor' option of the chart, allowing for
     * colors with opacity or gradients.  Equivalent to:
     * <pre><code>
     *     chart.setOption("/chart/borderColor", new Color()
     *        .setLinearGradient(0.0, 0.0, 1.0, 1.0)
     *        .addStop(new Color(255, 255, 255))
     *        .addStop(new Color(200, 200, 255))
     *     );
     * </code></pre>
     * The color of the outer chart border. The border is painted using vector graphic techniques to allow
     * rounded corners. Defaults to "#4572A7".
     * <p/>
     * Note that this method is intended for setting the color to a gradient or color that includes
     * an alpha channel.  If you instead just want to set the color to a normal RGB hex value
     * you can use the {@link #setBorderColor(String)} version instead.
     *
     * @param borderColor The color gradient or color with an alpha channel to set as the 'borderColor' option on the chart.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    public T setBorderColor(Color borderColor) {
        return this.setOption("/chart/borderColor", borderColor != null ? borderColor.getOptionValue() : null);
    }

    /**
     * Convenience method for setting the 'borderRadius' option of the chart.  Equivalent to:
     * <pre><code>
     *     chart.setOption("/chart/borderRadius", 10);
     * </code></pre>
     * The corner radius of the outer chart border. Defaults to 5.
     *
     * @param borderRadius The corner radius of the outer chart border.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    public T setBorderRadius(Number borderRadius) {
        return this.setOption("/chart/borderRadius", borderRadius);
    }

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
    public T setBorderWidth(Number borderWidth) {
        return this.setOption("/chart/borderWidth", borderWidth);
    }

    /**
     * Convenience method for setting the 'subtitle/text' chart option.  Equivalent to:
     * <pre><code>
     *     chart.setOption("/subtitle/text", "Source: Wikipedia.com");
     * </code></pre>
     * The actual text of the chart subtitle. It can contain basic HTML text markup like
     * &lt;b&gt;, &lt;i&gt; and spans with style. Defaults to null.
     * <p/>
     * Note that for more control over the title, utilize the {@link #setChartSubtitle(ChartSubtitle)}
     * method instead.
     * <p/>
     * Also note that to hide the chart subtitle completely, simply set the text to null.
     *
     * @param subtitle Sets the subtitle of chart, or null to hide the subtitle.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    public T setChartSubtitleText(String subtitle) {
        return this.setOption("/subtitle/text", subtitle);
    }

    /**
     * Convenience method for setting the 'subtitle' chart options.  Equivalent to:
     * <pre><code>
     *     chart.setOption("/subtitle/text", "Source: Wikipedia.com");
     *     chart.setOption("/subtitle/align", ChartTitle.Align.Left);
     *     etc...
     * </code></pre>
     * <p/>
     * Note that if you call this method it will overwrite any existing
     * settings that have already been applied to the subtitle (e.g. if you
     * had previously called the {@link #setChartSubtitleText(String)} method that change
     * will get overwritten by this call.)
     * <p/>
     * Note that if you just want to change the text of the subtitle, you can simply
     * use the {@link #setChartSubtitleText(String)} method instead.
     *
     * @param subtitle Sets the chart subtitle options, or null to hide the subtitle.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    public T setChartSubtitle(ChartSubtitle subtitle) {
        return this.setOption("/subtitle", subtitle != null ? subtitle.getOptions() : null);
    }

    /**
     * Convenience method for setting the 'title/text' chart option.  Equivalent to:
     * <pre><code>
     *     chart.setOption("/title/text", "Sales by Year");
     * </code></pre>
     * The actual text of the chart title. It can contain basic HTML text markup like
     * &lt;b&gt;, &lt;i&gt; and spans with style. Defaults to "Chart title".
     * <p/>
     * Note that for more control over the title, utilize the {@link #setChartTitle(ChartTitle)}
     * method instead.
     * <p/>
     * Also note that to hide the chart title completely, simply set the text to null.
     *
     * @param title Sets the title of chart, or null to hide the title.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    public T setChartTitleText(String title) {
        return this.setOption("/title/text", title);
    }

    /**
     * Convenience method for setting the 'title' chart options.  Equivalent to:
     * <pre><code>
     *     chart.setOption("/title/text", "Sales by Year");
     *     chart.setOption("/title/align", ChartTitle.Align.Left);
     *     etc...
     * </code></pre>
     * <p/>
     * Note that if you call this method it will overwrite any existing
     * settings that have already been applied to the title (e.g. if you
     * had previously called the {@link #setChartTitleText(String)} method that change
     * will get overwritten by this call.)
     * <p/>
     * Note that if you just want to change the text of the title, you can simply
     * use the {@link #setChartTitleText(String)} method instead.
     *
     * @param title Sets the chart title options, or null to hide the title.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    public T setChartTitle(ChartTitle title) {
        return this.setOption("/title", title != null ? title.getOptions() : null);
    }

    /**
     * Convenience method for setting the 'className' option of the chart.  Equivalent to:
     * <pre><code>
     *     chart.setOption("/chart/className", "RedChart");
     * </code></pre>
     * A CSS class name to apply to the charts container div, allowing unique CSS styling
     * for each chart. Defaults to "".
     *
     * @param className A CSS class name to apply to the charts container div.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    public T setClassName(String className) {
        return this.setOption("/chart/className", className);
    }

    /**
     * Sets the default colors for the chart's series. When all colors are used, new colors are pulled from the start again.
     * Note that the colors for each series can be overridden via the {@link org.moxieapps.gwt.highcharts.client.plotOptions.PlotOptions#setColor(String)} mechanism.
     * <p/>
     * Default colors are:  "#4572A7", "#AA4643", "#89A54E", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"
     *
     * @param colors An array of colors to use as the defaults for each series.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    public T setColors(String... colors) {
        return this.setOption("/colors", colors);
    }

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
    public T setCredits(Credits credits) {
        return this.setOption("/credits", credits != null ? credits.getOptions() : null);
    }

    // TODO: Add support for "events"

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
    public T setHeight(Number height) {
        return this.setOption("/chart/height", height);
    }

    /**
     * Convenience method for setting the 'ignoreHiddenSeries' option of the chart.  Equivalent to:
     * <pre><code>
     *     chart.setOption("/chart/ignoreHiddenSeries", false);
     * </code></pre>
     * If true, the axes will scale to the remaining visible series once one series is hidden.
     * If false, hiding and showing a series will not affect the axes or the other series.
     * For stacks, once one series within the stack is hidden, the rest of the stack will close
     * in around it even if the axis is not affected. Defaults to true.
     *
     * @param ignoreHiddenSeries If true, the axes will scale to the remaining visible series
     *                           once one series is hidden. If false, hiding and showing a series
     *                           will not affect the axes or the other series.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    public T setIgnoreHiddenSeries(boolean ignoreHiddenSeries) {
        return this.setOption("/chart/ignoreHiddenSeries", ignoreHiddenSeries);
    }

    /**
     * Convenience method for setting the 'inverted' option of the chart.  Equivalent to:
     * <pre><code>
     *     chart.setOption("/inverted", true);
     * </code></pre>
     * Whether to invert the axes so that the x axis is horizontal and y axis is vertical. When true, the
     * x axis is reversed by default. If a bar plot is present in the chart, it will be inverted automatically. Defaults to false.
     *
     * @param inverted Whether to invert the axes so that the x axis is horizontal and y axis is vertical.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    public T setInverted(boolean inverted) {
        return this.setOption("/chart/inverted", inverted);
    }

    /**
     * Sets HTML labels that can be positioned anywhere in the chart area.  Each label can contain
     * HTML as well as CSS style properties that can be used to position the label anywhere in the
     * chart area.  See the {@link #setLabelsStyle(Style)} method to control the default style for
     * each label.
     * <p/>
     *
     * @param labelItems An array of LabelItem instances that contain the HTML of the label as well
     *                   as a "style" object that can be used to position each label.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    public T setLabelItems(LabelItem... labelItems) {
        return this.setOption("/labels/items", labelItems);
    }

    /**
     * Sets the default CSS style options that will be shared by all label items that are added to the
     * chart via the {@link #setLabelItems(LabelItem...)} method.  The default label style
     * is simply:
     * <ul>
     * <li><b>color</b>: "#3E576F"</li>
     * </ul>
     *
     * @param style Shared CSS styles for all label items.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    public T setLabelsStyle(Style style) {
        return this.setOption("/labels/style", style != null ? style.getOptions() : null);
    }

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
    public T setLegend(Legend legend) {
        return this.setOption("/legend", legend != null ? legend.getOptions() : null);
    }

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
     * user that something is going on, for example while retrieving new data via a GWT remoting
     * request. The "Loading..." text itself is not part of this configuration object, but part of
     * the {@link Lang} object.
     *
     * @param loading The options to apply to the loading area of the chart.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    public T setLoading(Loading loading) {
        return this.setOption("/loading", loading != null ? loading.getOptions() : null);
    }

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
    public T setMargin(Number marginTop, Number marginRight, Number marginBottom, Number marginLeft) {
        JSONArray margins = new JSONArray();
        margins.set(0, new JSONNumber(marginTop.doubleValue()));
        margins.set(1, new JSONNumber(marginRight.doubleValue()));
        margins.set(2, new JSONNumber(marginBottom.doubleValue()));
        margins.set(3, new JSONNumber(marginLeft.doubleValue()));
        return this.setOption("/chart/margin", margins);
    }

    /**
     * Convenience method for setting the 'marginTop' option of the chart.  Equivalent to:
     * <pre><code>
     *     chart.setOption("/chart/marginTop", 100);
     * </code></pre>
     * The margin between the top outer edge of the chart and the plot area. Use this to set a fixed
     * pixel value for the margin as opposed to the default dynamic margin.
     * See also {@link #setSpacingTop(Number)}. Defaults to null.
     *
     * @param marginTop The margin between the top outer edge of the chart and the plot area.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    public T setMarginTop(Number marginTop) {
        return this.setOption("/chart/marginTop", marginTop);
    }

    /**
     * Convenience method for setting the 'marginRight' option of the chart.  Equivalent to:
     * <pre><code>
     *     chart.setOption("/chart/marginRight", 100);
     * </code></pre>
     * The margin between the right outer edge of the chart and the plot area. Use this to set
     * a fixed pixel value for the margin as opposed to the default dynamic margin.
     * See also {@link #setSpacingRight(Number)}. Defaults to null.
     *
     * @param marginRight The margin between the right outer edge of the chart and the plot area.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    public T setMarginRight(Number marginRight) {
        return this.setOption("/chart/marginRight", marginRight);
    }

    /**
     * Convenience method for setting the 'marginBottom' option of the chart.  Equivalent to:
     * <pre><code>
     *     chart.setOption("/chart/marginBottom", 100);
     * </code></pre>
     * The margin between the bottom outer edge of the chart and the plot area. Use this to set
     * a fixed pixel value for the margin as opposed to the default dynamic margin.
     * See also {@link #setSpacingBottom(Number)}. Defaults to null.
     *
     * @param marginBottom The margin between the bottom outer edge of the chart and the plot area.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    public T setMarginBottom(Number marginBottom) {
        return this.setOption("/chart/marginBottom", marginBottom);
    }

    /**
     * Convenience method for setting the 'marginBottom' option of the chart.  Equivalent to:
     * <pre><code>
     *     chart.setOption("/chart/marginLeft", 100);
     * </code></pre>
     * The margin between the left outer edge of the chart and the plot area. Use this to set a
     * fixed pixel value for the margin as opposed to the default dynamic margin.
     * See also {@link #setSpacingLeft(Number)}. Defaults to null.
     *
     * @param marginLeft The margin between the left outer edge of the chart and the plot area.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    public T setMarginLeft(Number marginLeft) {
        return this.setOption("/chart/marginLeft", marginLeft);
    }

    /**
     * Convenience method for setting the 'plotBackgroundColor' option of the chart to an RGB hex value.  Equivalent to:
     * <pre><code>
     *     chart.setOption("/chart/plotBackgroundColor", "#CCCCCC");
     * </code></pre>
     * The RGB background color for the plot area. Defaults to null.
     * <p/>
     * Note that this method is intended for setting the color to a simple RBG hex value.  If you instead
     * want to set a color to include an alpha channel or a gradient, use the {@link #setPlotBackgroundColor(Color)}
     * version instead.
     *
     * @param plotBackgroundColor The value to set as the 'plotBackgroundColor' option on the chart.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    public T setPlotBackgroundColor(String plotBackgroundColor) {
        return this.setOption("/chart/plotBackgroundColor", plotBackgroundColor);
    }

    /**
     * Convenience method for setting the 'plotBackgroundColor' option of the chart, allowing for
     * colors with opacity or gradients.  Equivalent to:
     * <pre><code>
     *     chart.setOption("/chart/plotBackgroundColor", new Color()
     *        .setLinearGradient(0.0, 0.0, 1.0, 1.0)
     *        .addStop(new Color(255, 255, 255))
     *        .addStop(new Color(200, 200, 255))
     *     );
     * </code></pre>
     * The background color or gradient for the plot area. Defaults to null.
     * <p/>
     * Note that this method is intended for setting the color to a gradient or color that includes
     * an alpha channel.  If you instead just want to set the color to a normal RGB hex value
     * you can use the {@link #setPlotBackgroundColor(String)} version instead.
     *
     * @param plotBackgroundColor The color gradient or color with an alpha channel to set as the 'plotBackgroundColor' option on the chart.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    public T setPlotBackgroundColor(Color plotBackgroundColor) {
        return this.setOption("/chart/plotBackgroundColor", plotBackgroundColor != null ? plotBackgroundColor.getOptionValue() : null);
    }

    /**
     * Convenience method for setting the 'plotBorderColor' option of the chart to an RGB hex value.  Equivalent to:
     * <pre><code>
     *     chart.setOption("/chart/plotBorderColor", "#CCCCCC");
     * </code></pre>
     * The RGB color of the inner chart or plot area border. Defaults to "#C0C0C0".
     * <p/>
     * Note that this method is intended for setting the color to a simple RBG hex value.  If you instead
     * want to set a color to include an alpha channel or a gradient, use the {@link #setPlotBorderColor(Color)}
     * version instead.
     *
     * @param plotBorderColor The value to set as the 'plotBorderColor' option on the chart.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    public T setPlotBorderColor(String plotBorderColor) {
        return this.setOption("/chart/plotBorderColor", plotBorderColor);
    }

    /**
     * Convenience method for setting the 'plotBorderColor' option of the chart, allowing for
     * colors with opacity or gradients.  Equivalent to:
     * <pre><code>
     *     chart.setOption("/chart/plotBorderColor", new Color()
     *        .setLinearGradient(0.0, 0.0, 1.0, 1.0)
     *        .addStop(new Color(255, 255, 255))
     *        .addStop(new Color(200, 200, 255))
     *     );
     * </code></pre>
     * The color (or gradient) of the inner chart or plot area border. Defaults to "#C0C0C0".
     * <p/>
     * Note that this method is intended for setting the color to a gradient or color that includes
     * an alpha channel.  If you instead just want to set the color to a normal RGB hex value
     * you can use the {@link #setPlotBorderColor(String)} version instead.
     *
     * @param plotBorderColor The color gradient or color with an alpha channel to set as the 'plotBorderColor' option on the chart.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    public T setPlotBorderColor(Color plotBorderColor) {
        return this.setOption("/chart/plotBorderColor", plotBorderColor != null ? plotBorderColor.getOptionValue() : null);
    }

    /**
     * Convenience method for setting the 'plotBorderWidth' option of the chart.  Equivalent to:
     * <pre><code>
     *     chart.setOption("/chart/plotBorderWidth", 10);
     * </code></pre>
     * The pixel width of the plot area border. Defaults to 0.
     *
     * @param plotBorderWidth The pixel width of the plot area border.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    public T setPlotBorderWidth(Number plotBorderWidth) {
        return this.setOption("/chart/plotBorderWidth", plotBorderWidth);
    }

    /**
     * Convenience method for setting the 'plotShadow' option of the chart.  Equivalent to:
     * <pre><code>
     *     chart.setOption("/chart/plotShadow", false);
     * </code></pre>
     * Whether to apply a drop shadow to the plot area. Requires that plotBackgroundColor be set. Defaults to false.
     *
     * @param plotShadow Whether to apply a drop shadow to the plot area.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    public T setPlotShadow(boolean plotShadow) {
        return this.setOption("/chart/plotShadow", plotShadow);
    }

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
    public T setReflow(boolean reflow) {
        return this.setOption("/chart/reflow", reflow);
    }

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
    public T setShadow(boolean shadow) {
        return this.setOption("/chart/shadow", shadow);
    }

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
    public T setShowAxes(boolean showAxes) {
        return this.setOption("/chart/showAxes", showAxes);
    }

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
    public T setSpacingTop(Number spacingTop) {
        return this.setOption("/chart/spacingTop", spacingTop);
    }

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
    public T setSpacingRight(Number spacingRight) {
        return this.setOption("/chart/spacingRight", spacingRight);
    }

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
    public T setSpacingBottom(Number spacingBottom) {
        return this.setOption("/chart/spacingBottom", spacingBottom);
    }

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
    public T setSpacingLeft(Number spacingLeft) {
        return this.setOption("/chart/spacingLeft", spacingLeft);
    }

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
    public T setStyle(Style style) {
        return this.setOption("/chart/style", style != null ? style.getOptions() : null);
    }

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
    public T setSymbols(Marker.Symbol... symbols) {
        JSONArray symbolsArray = null;
        if (symbols != null) {
            symbolsArray = new JSONArray();
            for (int i = 0; i < symbols.length; i++) {
                Marker.Symbol symbol = symbols[i];
                symbolsArray.set(i, new JSONString(symbol.toString()));
            }
        }
        return this.setOption("/symbols", symbolsArray);
    }

    // We need to maintain a local reference to tooltip to deal with the formatter function
    private ToolTip toolTip;

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
    public T setToolTip(ToolTip toolTip) {
        this.toolTip = toolTip;
        return this.setOption("/tooltip", toolTip != null ? toolTip.getOptions() : null);
    }

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
    public T setType(Series.Type type) {
        return this.setOption("/chart/type", type.toString());
    }

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
    public T setWidth(Number width) {
        return this.setOption("/chart/width", width);
    }

    /**
     * Update the size of the chart to match the size of the container that this widget was inserted
     * inside of.
     *
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    public T setSizeToMatchContainer() {
        return this.setSize(this.getOffsetWidth(), this.getOffsetHeight());
    }

    /**
     * A helper method which can be used to conveniently set the width of the DOM element that contains
     * the chart to "100%".  Note that for more precise control over the size of the chart see the
     * {@link #setSize(int, int)} and {@link #setSizeToMatchContainer()} methods.
     *
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    public T setWidth100() {
        this.getElement().getStyle().setWidth(100, com.google.gwt.dom.client.Style.Unit.PCT);
        return returnThis();
    }

    /**
     * A helper method which can be used to conveniently set the height of the DOM element that contains
     * the chart to "100%".  Note that for more precise control over the size of the chart see the
     * {@link #setSize(int, int)} and {@link #setSizeToMatchContainer()} methods.
     *
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    public T setHeight100() {
        this.getElement().getStyle().setHeight(100, com.google.gwt.dom.client.Style.Unit.PCT);
        return returnThis();
    }

    /**
     * Updates the size of the chart to match the given width and height, using the default animation options.
     * Note that if you simply want to set the size of the chart to match the container in which it was inserted,
     * you can simply call the {@link #setSizeToMatchContainer()} method instead.
     *
     * @param width  The new pixel width of the chart.
     * @param height The new pixel height of the chart.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    public T setSize(int width, int height) {
        return this.setSize(width, height, true);
    }

    /**
     * Updates the size of the chart to match the given width and height, explicitly setting whether or
     * not the resize should be animated.
     *
     * @param width    The new pixel width of the chart.
     * @param height   The new pixel height of the chart.
     * @param animated When true, the resize will be animated with default animation options.  The animation
     *                 can also be configured by call the {@link #setSize(int, int, Animation)} method instead.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    public T setSize(int width, int height, boolean animated) {
        return this.setSize(width, height, animated ? new Animation() : null);
    }

    /**
     * Updates the size of the chart to match the given width and height, allowing for custom control
     * over how the animation will be resized.
     *
     * @param width     The new pixel width of the chart.
     * @param height    The new pixel height of the chart.
     * @param animation The custom animation to use as the resize animation for the chart.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    public T setSize(int width, int height, Animation animation) {

        // Avoid monkeying with anything if we got bad values
        if (width <= 0 || height <= 0) {
            return returnThis();
        }

        if (isRendered()) {
            // If we're already on the screen, then do the resize natively
            if (animation == null || animation.getOptions() == null) {
                nativeSetSize(chart, width, height, animation == null);
            } else {
                nativeSetSize(chart, width, height, animation.getOptions().getJavaScriptObject());
            }
        } else {
            // In we're invoked before the chart is rendered, keep the width and height around in our configuration
            setOption("/chart/width", width);
            setOption("/chart/height", height);
        }

        return returnThis();

    }

    // Delegate responsibility for managing configuration options to an anonymous helper class
    private Configurable configurable = new Configurable() {
    };

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
    public T setOption(String path, Object value) {
        configurable.setOption(path, value);
        return returnThis();
    }

    // Helper method to avoid having to do the cast and warning handling in multiple places
    public T returnThis() {
        @SuppressWarnings({"unchecked", "UnnecessaryLocalVariable"})
        T instance = (T) this;
        return instance;
    }

    // We need to maintain a local reference to the plot options in order to handle the potential callback formatter functions
    private AreaPlotOptions areaPlotOptions;
    private AreaSplinePlotOptions areaSplinePlotOptions;
    private BarPlotOptions barPlotOptions;
    private ColumnPlotOptions columnPlotOptions;
    private LinePlotOptions linePlotOptions;
    private PiePlotOptions piePlotOptions;
    private SeriesPlotOptions seriesPlotOptions;
    private ScatterPlotOptions scatterPlotOptions;
    private SplinePlotOptions splinePlotOptions;

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
    public T setAreaPlotOptions(AreaPlotOptions areaPlotOptions) {
        this.areaPlotOptions = areaPlotOptions;
        return this.setOption("/plotOptions/area", areaPlotOptions.getOptions());
    }


    /**
     * Updates the options that all area spline type series within the chart will use by default.  The settings can then
     * be overridden for each individual series via the {@link Series#setPlotOptions(PlotOptions)} method.
     * <p/>
     * Note that changing the plot options on a chart that has already been rendered will only affect
     * series that are subsequently added to the chart (and will not impact any of the series that are already
     * rendered in the chart.)
     *
     * @param areaSplinePlotOptions The options to set on the chart as the default settings for all area spline type series
     *                              that are part of this chart.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    public T setAreaSplinePlotOptions(AreaSplinePlotOptions areaSplinePlotOptions) {
        this.areaSplinePlotOptions = areaSplinePlotOptions;
        return this.setOption("/plotOptions/areaspline", areaSplinePlotOptions.getOptions());
    }

    /**
     * Updates the options that all bar type series within the chart will use by default.  The settings can then
     * be overridden for each individual series via the {@link Series#setPlotOptions(PlotOptions)} method.
     * <p/>
     * Note that changing the plot options on a chart that has already been rendered will only affect
     * series that are subsequently added to the chart (and will not impact any of the series that are already
     * rendered in the chart.)
     *
     * @param barPlotOptions The options to set on the chart as the default settings for all bar type series
     *                       that are part of this chart.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    public T setBarPlotOptions(BarPlotOptions barPlotOptions) {
        this.barPlotOptions = barPlotOptions;
        return this.setOption("/plotOptions/bar", barPlotOptions.getOptions());
    }

    /**
     * Updates the options that all column type series within the chart will use by default.  The settings can then
     * be overridden for each individual series via the {@link Series#setPlotOptions(PlotOptions)} method.
     * <p/>
     * Note that changing the plot options on a chart that has already been rendered will only affect
     * series that are subsequently added to the chart (and will not impact any of the series that are already
     * rendered in the chart.)
     *
     * @param columnPlotOptions The options to set on the chart as the default settings for all column type series
     *                          that are part of this chart.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    public T setColumnPlotOptions(ColumnPlotOptions columnPlotOptions) {
        this.columnPlotOptions = columnPlotOptions;
        return this.setOption("/plotOptions/column", columnPlotOptions.getOptions());
    }

    /**
     * Updates the options that all line type series within the chart will use by default.  The settings can then
     * be overridden for each individual series via the {@link Series#setPlotOptions(PlotOptions)} method.
     * <p/>
     * Note that changing the plot options on a chart that has already been rendered will only affect
     * series that are subsequently added to the chart (and will not impact any of the series that are already
     * rendered in the chart.)
     *
     * @param linePlotOptions The options to set on the chart as the default settings for all line type series
     *                        that are part of this chart.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    public T setLinePlotOptions(LinePlotOptions linePlotOptions) {
        this.linePlotOptions = linePlotOptions;
        return this.setOption("/plotOptions/line", linePlotOptions.getOptions());
    }

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
    public T setPiePlotOptions(PiePlotOptions piePlotOptions) {
        this.piePlotOptions = piePlotOptions;
        return this.setOption("/plotOptions/pie", piePlotOptions.getOptions());
    }

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
    public T setSeriesPlotOptions(SeriesPlotOptions seriesPlotOptions) {
        this.seriesPlotOptions = seriesPlotOptions;
        return this.setOption("/plotOptions/series", seriesPlotOptions.getOptions());
    }

    /**
     * Updates the options that all scatter type series within the chart will use by default.  The settings can then
     * be overridden for each individual series via the {@link Series#setPlotOptions(PlotOptions)} method.
     * <p/>
     * Note that changing the plot options on a chart that has already been rendered will only affect
     * series that are subsequently added to the chart (and will not impact any of the series that are already
     * rendered in the chart.)
     *
     * @param scatterPlotOptions The options to set on the chart as the default settings for all scatter type series
     *                           that are part of this chart.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    public T setScatterPlotOptions(ScatterPlotOptions scatterPlotOptions) {
        this.scatterPlotOptions = scatterPlotOptions;
        return this.setOption("/plotOptions/scatter", scatterPlotOptions.getOptions());
    }

    /**
     * Updates the options that all spline type series within the chart will use by default.  The settings can then
     * be overridden for each individual series via the {@link Series#setPlotOptions(PlotOptions)} method.
     * <p/>
     * Note that changing the plot options on a chart that has already been rendered will only affect
     * series that are subsequently added to the chart (and will not impact any of the series that are already
     * rendered in the chart.)
     *
     * @param splinePlotOptions The options to set on the chart as the default settings for all spline type series
     *                          that are part of this chart.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    public T setSplinePlotOptions(SplinePlotOptions splinePlotOptions) {
        this.splinePlotOptions = splinePlotOptions;
        return this.setOption("/plotOptions/spline", splinePlotOptions.getOptions());
    }

    // Purposefully not using the generic "List" interface here in order to optimize GWT performance.
    private ArrayList<Series> seriesList = new ArrayList<Series>();

    /**
     * Create a new data series that can be configured, and then added to this chart instance via the
     * {@link #addSeries(Series)} method.
     *
     * @return The series that was created (which will need to be added to the chart after it is configured
     *         via {@link #addSeries(Series)}.
     */
    public Series createSeries() {
        return new Series(this);
    }

    /**
     * Add the given data series to the chart, using the default options.  Note, see the
     * {@link #addSeries(Series, boolean, boolean)} and {@link #addSeries(Series, boolean, Animation)} for more
     * control over how the series will be drawn and animated when added to a chart that has already been rendered.
     *
     * @param series The data series to add to the chart, including its general configuration and plot options.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    public T addSeries(Series series) {
        return this.addSeries(series, true, true);
    }

    /**
     * Add a data series to this chart, controlling the redraw and animation options.
     *
     * @param series    The data series to add to the chart, including its general configuration and plot options.
     * @param redraw    'true' to force the chart to redraw immediately, or 'false' to wait until the
     *                  {@link #redraw()} method is invoked.
     * @param animation When 'true', the series updating will be animated with default animation options.
     *                  Note, use the {@link #addSeries(Series, boolean, Animation)} method for more control over the animation options.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    public T addSeries(Series series, boolean redraw, boolean animation) {
        return addSeries(series, redraw, animation ? new Animation() : null);
    }

    /**
     * Add a data series to this chart, controlling the redraw and animation options.
     *
     * @param series    The data series to add to the chart, including its general configuration and plot options.
     * @param redraw    'true' to force the chart to redraw immediately, or 'false' to wait until the
     *                  {@link #redraw()} method is invoked.
     * @param animation Custom animation to use when the series is being updated.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    public T addSeries(Series series, boolean redraw, Animation animation) {

        // Whether or not we've been rendered yet or not, maintain a reference to all of the series that we're managing
        seriesList.add(series);

        if (isRendered()) {
            final JavaScriptObject seriesOptions = convertSeriesToJSON(series).getJavaScriptObject();
            if (animation == null || animation.getOptions() == null) {
                final boolean animationFlag = animation != null;
                nativeAddSeries(chart, seriesOptions, redraw, animationFlag);
            } else {
                final JavaScriptObject animationOptions = animation.getOptions().getJavaScriptObject();
                nativeAddSeries(chart, seriesOptions, redraw, animationOptions);
            }
            series.setRendered(true);

            // Once we're rendered, we're maintaining the point state in the DOM, so we can dump our internal list to save memory
            series.clearInternalPointsList();
        }

        return returnThis();
    }

    /**
     * Return an array of all the {@link Series} instances that have been added to this chart (either before or
     * after the chart was rendered), or an empty array if none have been added yet.  Note that we're returning
     * an array instead of a java.util.List as the generic interface types require a bit of a performance hit
     * in GWT to use.
     *
     * @return An array of the Series instances currently within the chart, or an empty array if none have yet
     *         been added.
     */
    public Series[] getSeries() {
        return seriesList.toArray(new Series[seriesList.size()]);
    }

    /**
     * Remove the given series from the chart, automatically redrawing the chart after the series
     * has been removed.  See the {@link #removeSeries(Series, boolean)} method if you're performing
     * multiple options on the chart at once and need tighter control over when the chart is redrawn.
     *
     * @param series The series instance to remove from the chart.
     * @return 'true' if the series was a part of this chart and successfully removed, or 'false' if the
     *         given series was not a part of this chart and therefore couldn't be removed.
     */
    public boolean removeSeries(Series series) {
        return removeSeries(series, true);
    }

    /**
     * Remove the given series from the chart, explicitly controlling whether or not the chart will
     * be redrawn after the series is removed.
     *
     * @param series The series instance to remove from the chart.
     * @param redraw Whether to redraw the chart after the series is removed. When you're performing multiple
     *               options on the chart, it is highly recommended that the redraw option be set to false, and instead
     *               explicitly call the {@link Chart#redraw()} method after you're done updating the chart.
     * @return 'true' if the series was a part of this chart and successfully removed, or 'false' if the
     *         given series was not a part of this chart and therefore couldn't be removed.
     */
    public boolean removeSeries(Series series, boolean redraw) {
        if(!seriesList.remove(series)) {
            return false;
        }
        final JavaScriptObject nativeSeries = nativeGet(chart, series.getId());
        if(nativeSeries != null) {
            nativeRemoveSeries(chart, nativeSeries, redraw);
        }
        return true;
    }

    /**
     * A convenience method to run through and remove all of the series that are currently part of the chart
     * instance, only performing a redraw after they're all done.  See the {@link Series#remove()} or
     * {@link #removeSeries(Series)} method if you instead want to remove a single series from the chart.
     * Also see the {@link #removeAllSeries(boolean)} method if you need more control over when the chart
     * is redrawn.
     *
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    public T removeAllSeries() {
        removeAllSeries(true);
        return returnThis();
    }

    /**
     * A convenience method to run through and remove all of the series that are currently part of the chart
     * instance, with explicit control over the animation options.  See the {@link Series#remove()} or
     * {@link #removeSeries(Series)} method if you instead want to remove a single series from the chart.
     *
     * @param redraw Whether to redraw the chart after the series is removed. When you're performing multiple
     *               options on the chart, it is highly recommended that the redraw option be set to false, and instead
     *               explicitly call the {@link Chart#redraw()} method after you're done updating the chart.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    public T removeAllSeries(boolean redraw) {
        // Clone the collection so that we can remove from the main list while iterating over the clone
        ArrayList<Series> clonedList = new ArrayList<Series>(seriesList);
        for (Iterator<Series> iterator = clonedList.iterator(); iterator.hasNext(); ) {
            Series series = iterator.next();
            // Only redraw after the vary last series is removed (unless not requested to at all)
            if (iterator.hasNext()) {
                removeSeries(series, false);
            } else {
                removeSeries(series, redraw);
            }
        }
        return returnThis();
    }

    /**
     * Hide the loading screen. Options for the loading screen are defined via {@link Chart#setLoading(Loading)}.
     * Should be used in conjunction with the {@link #showLoading(String)} method.
     *
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    public T hideLoading() {
        nativeHideLoading(chart);
        return returnThis();
    }

    /**
     * Dim the chart's plot area and show a loading label text. Options for the loading screen are defined via
     * {@link Chart#setLoading(Loading)}.  A custom text can be given as a parameter, otherwise the default
     * message specified via the {@link Lang} options will be used.
     * Should be used in conjunction with the {@link #hideLoading()} method.
     *
     * @param message A custom message to appear as the loading text.
     * @return A reference to this {@link BaseChart} instance for convenient method chaining.
     */
    public T showLoading(String message) {
        nativeShowLoading(chart, message);
        return returnThis();
    }

    // Purposefully using a specific type instead of the generic List interface for enhanced GWT performance
    private ArrayList<XAxis> xAxes = new ArrayList<XAxis>();
    private ArrayList<YAxis> yAxes = new ArrayList<YAxis>();

    /**
     * Retrieve a reference to the XAxis for this chart (guaranteed non-null), so that it can then be
     * configured or operated on.  Note that you should use this method when you only have one x-axis
     * to operate on.  If you have multiple x-axis, then utilize the {@link #getXAxis(int)} method instead.
     *
     * @return A reference to the XAxis for this chart (never null);
     */
    public XAxis getXAxis() {
        return getXAxis(0);
    }

    /**
     * Retrieve a reference to a specific XAxis for this chart (guaranteed non-null), so that it can then be
     * configured or operated on.  Note that you should use this method when you only have multiple x-axis
     * to operate on.  If you have only a single y-axis, then utilize the {@link #getXAxis()} method instead.
     *
     * @param axisIndex The numeric index (zero based) of the XAxis to return.
     * @return A reference to the XAxis at the given index for this chart (never null).
     */
    public XAxis getXAxis(int axisIndex) {
        if (axisIndex < xAxes.size()) {
            return xAxes.get(axisIndex);
        }
        for (int i = xAxes.size(); i <= axisIndex; i++) {
            xAxes.add(new XAxis(this));
        }
        return xAxes.get(axisIndex);
    }

    /**
     * Retrieve a reference to the YAxis for this chart (guaranteed non-null), so that it can then be
     * configured or operated on.  Note that you should use this method when you only have one y-axis
     * to operate on.  If you have multiple y-axis, then utilize the {@link #getYAxis(int)} method instead.
     *
     * @return A reference to the YAxis for this chart (never null);
     */
    public YAxis getYAxis() {
        return getYAxis(0);
    }

    /**
     * Retrieve a reference to a specific YAxis for this chart (guaranteed non-null), so that it can then be
     * configured or operated on.  Note that you should use this method when you only have multiple y-axis
     * to operate on.  If you have only a single y-axis, then utilize the {@link #getYAxis()} method instead.
     *
     * @param axisIndex The numeric index (zero based) of the YAxis to return.
     * @return A reference to the YAxis at the given index for this chart (never null).
     */
    public YAxis getYAxis(int axisIndex) {
        if (axisIndex < yAxes.size()) {
            return yAxes.get(axisIndex);
        }
        for (int i = yAxes.size(); i <= axisIndex; i++) {
            yAxes.add(new YAxis(this));
        }
        return yAxes.get(axisIndex);
    }

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
    public T redraw() {
        if (isRendered()) {
            nativeRedraw(chart);
        }
        return returnThis();
    }

    /**
     * Returns true if the chart has already been rendered to the DOM, or false otherwise.
     *
     * @return 'true' if the chart has been rendered to the DOM
     */
    public boolean isRendered() {
        return chart != null;
    }

    private JavaScriptObject chart;

    @Override
    protected void onLoad() {

        // Build some arrays that we can pass into the JS function so that it knows how many custom callback functions it needs to wire
        // up on the client side for formatter functions.
        JSONArray xAxisLabelFormatters = new JSONArray();
        for (int i = 0, xAxesSize = xAxes.size(); i < xAxesSize; i++) {
            XAxis xAxis = xAxes.get(i);
            xAxisLabelFormatters.set(i, JSONBoolean.getInstance(xAxis.getLabels() != null && xAxis.getLabels().getFormatter() != null));
        }
        JSONArray yAxisLabelFormatters = new JSONArray();
        JSONArray yAxisStackLabelFormatters = new JSONArray();
        for (int i = 0, yAxesSize = yAxes.size(); i < yAxesSize; i++) {
            YAxis yAxis = yAxes.get(i);
            yAxisLabelFormatters.set(i, JSONBoolean.getInstance(yAxis.getLabels() != null && yAxis.getLabels().getFormatter() != null));
            yAxisStackLabelFormatters.set(i, JSONBoolean.getInstance(yAxis.getStackLabels() != null && yAxis.getStackLabels().getFormatter() != null));
        }

        // Build a similar object for dealing with all of the data label formatters that may be set on the plot options
        JSONObject plotOptionsLabelFormatters = new JSONObject();
        plotOptionsLabelFormatters.put("area", hasDataLabelsFormatter(areaPlotOptions));
        plotOptionsLabelFormatters.put("areaspline", hasDataLabelsFormatter(areaSplinePlotOptions));
        plotOptionsLabelFormatters.put("bar", hasDataLabelsFormatter(barPlotOptions));
        plotOptionsLabelFormatters.put("column", hasDataLabelsFormatter(columnPlotOptions));
        plotOptionsLabelFormatters.put("line", hasDataLabelsFormatter(linePlotOptions));
        plotOptionsLabelFormatters.put("pie", hasDataLabelsFormatter(piePlotOptions));
        plotOptionsLabelFormatters.put("series", hasDataLabelsFormatter(seriesPlotOptions));
        plotOptionsLabelFormatters.put("scatter", hasDataLabelsFormatter(scatterPlotOptions));
        plotOptionsLabelFormatters.put("spline", hasDataLabelsFormatter(splinePlotOptions));

        // And one more for dealing with any data label formatters that have been applied directly to a series
        JSONArray seriesLabelFormatters = new JSONArray();
        for (int i = 0, seriesListSize = seriesList.size(); i < seriesListSize; i++) {
            Series series = seriesList.get(i);
            seriesLabelFormatters.set(i, hasDataLabelsFormatter(series));
        }

        chart = nativeRenderChart(
            getChartTypeName(),
            createNativeOptions(),
            toolTip != null && toolTip.getToolTipFormatter() != null,
            xAxisLabelFormatters.getJavaScriptObject(),
            yAxisLabelFormatters.getJavaScriptObject(),
            yAxisStackLabelFormatters.getJavaScriptObject(),
            plotOptionsLabelFormatters.getJavaScriptObject(),
            seriesLabelFormatters.getJavaScriptObject()
        );

        // Now that we're rendered we're going to switch to maintaining everything within the DOM, so we can dump
        // any series data that we were managing internally
        for (Series series : seriesList) {
            series.clearInternalPointsList();
            series.setRendered(true);
        }

    }

    /**
     * To be overridden in a sub class to return the JS type name of the chart instance that
     * should be created when the chart is rendered.  E.g. "Chart", "StockChart", etc.
     *
     * @return The name of the JS class type that should be created when this chart is rendered.
     */
    protected abstract String getChartTypeName();

    private JSONBoolean hasDataLabelsFormatter(PlotOptions plotOptions) {
        return JSONBoolean.getInstance(plotOptions != null &&
            plotOptions.getDataLabels() != null &&
            plotOptions.getDataLabels().getFormatter() != null
        );
    }

    private JSONBoolean hasDataLabelsFormatter(Series series) {
        return JSONBoolean.getInstance(series != null &&
            series.getPlotOptions() != null &&
            series.getPlotOptions().getDataLabels() != null &&
            series.getPlotOptions().getDataLabels().getFormatter() != null
        );
    }


    @Override
    protected void onUnload() {
        if (isRendered()) {
            nativeDestroy(chart);
            chart = null;
        }
    }

    private JavaScriptObject createNativeOptions() {
        JSONObject options = configurable.getOptions();
        if (options == null) {
            options = new JSONObject();
        }

        // #1: We need to merge the options they provided with the additional detail we know internally,
        // and first we need to set which DOM element the chart should be rendered within
        JSONValue chartValue = options.get("chart");
        if (chartValue == null || chartValue.isObject() == null) {
            chartValue = new JSONObject();
            options.put("chart", chartValue);
        }
        final JSONObject chartObject = (JSONObject) options.get("chart");
        chartObject.put("renderTo", new JSONString(this.getElement().getId()));

        // #2: We need to setup whatever data series needs to be rendered initially in the chart
        if (seriesList.size() > 0) {
            final JSONValue seriesValue = options.get("series");
            if (seriesValue == null || seriesValue.isArray() == null) {
                options.put("series", new JSONArray());
            }
            final JSONArray seriesArray = (JSONArray) options.get("series");
            for (int i = 0, seriesListSize = seriesList.size(); i < seriesListSize; i++) {
                Series series = seriesList.get(i);
                JSONObject seriesOptions = convertSeriesToJSON(series);
                seriesArray.set(i, seriesOptions);
            }
        }

        // #3: We need to add references to our axis so that we can later lookup the
        // axis by id (as well as pass along any configuration options that were applied to the axis)
        options.put("xAxis", convertToJSONValue(xAxes.toArray(new Configurable[xAxes.size()])));
        options.put("yAxis", convertToJSONValue(yAxes.toArray(new Configurable[yAxes.size()])));

        // For debugging the raw options that we're passing to the chart on startup, uncomment the following line
        // com.google.gwt.user.client.Window.alert(options.toString());

        return options.getJavaScriptObject();
    }

    private JSONObject convertSeriesToJSON(Series series) {
        JSONObject seriesOptions = series.getOptions();
        if (seriesOptions == null) {
            seriesOptions = new JSONObject();
        }
        JSONValue dataValue = seriesOptions.get("data");
        if (dataValue == null || dataValue.isArray() == null) {
            seriesOptions.put("data", new JSONArray());
        }
        copyPointsToJSONArray(series.getPoints(), (JSONArray) seriesOptions.get("data"));
        return seriesOptions;
    }

    private static JSONValue convertToJSONValue(Configurable... configurables) {
        if (configurables.length > 1) {
            JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < configurables.length; i++) {
                jsonArray.set(i, configurables[i].getOptions());
            }
            return jsonArray;
        } else if (configurables.length == 1) {
            return configurables[0].getOptions();
        }
        return JSONNull.getInstance();
    }

    private static void copyPointsToJSONArray(Point[] points, JSONArray jsonArray) {
        for (int i = 0, pointsLength = points.length; i < pointsLength; i++) {
            final Point point = points[i];
            jsonArray.set(i, convertPointToJSON(point));
        }
    }

    private static JSONValue convertPointToJSON(Point point) {
        final JSONObject options = point.getOptions();
        if (options != null) {
            return addPointScalarValues(point, options);
        } else if (point.getX() != null) {
            return addPointScalarValues(point, new JSONObject());
        } else if (point.getY() != null) {
            return new JSONNumber(point.getY().doubleValue());
        } else {
            return JSONNull.getInstance();
        }
    }

    // Purposefully package scope
    static JSONValue addPointScalarValues(Point point, JSONObject options) {
        if (point.getX() != null) {
            options.put("x", new JSONNumber(point.getX().doubleValue()));
        }
        if (point.getY() != null) {
            options.put("y", new JSONNumber(point.getY().doubleValue()));
        }
        return options;
    }

    // Purposefully constrained to package scope
    JavaScriptObject get(String id) {
        if (isRendered()) {
            return nativeGet(chart, id);
        } else {
            return null;
        }
    }

    private native JavaScriptObject nativeRenderChart(String chartTypeName,
                                                      JavaScriptObject options,
                                                      boolean toolTipFormatterFlag,
                                                      JavaScriptObject xAxisLabelFormatterFlags,
                                                      JavaScriptObject yAxisLabelFormatterFlags,
                                                      JavaScriptObject yAxisStackLabelFormatterFlags,
                                                      JavaScriptObject plotOptionsLabelsFormatterFlags,
                                                      JavaScriptObject seriesLabelsFormatterFlags) /*-{

        var self = this;

        // Add in GWT interceptor callback functions for the various formatters so that we can move from
        // the native JS world back to the Java world...
        if (toolTipFormatterFlag) {
            options.tooltip = options.tooltip || {};
            options.tooltip.formatter = function() {
                var result = self.@org.moxieapps.gwt.highcharts.client.BaseChart::toolTipFormatterCallback(Lcom/google/gwt/core/client/JavaScriptObject;)(this);
                if (result == null) {
                    return false;
                }
                return result;
            };
        }

        // X axis label formatters
        for (i = 0; i < xAxisLabelFormatterFlags.length; i++) {
            if (!xAxisLabelFormatterFlags[i]) continue;
            var xAxis = xAxisLabelFormatterFlags.length == 1 ? options.xAxis : options.xAxis[i];
            xAxis.labels = xAxis.labels || {};
            xAxis.labels.formatter = function() {
                return self.@org.moxieapps.gwt.highcharts.client.BaseChart::xAxisLabelFormatterCallback(Lcom/google/gwt/core/client/JavaScriptObject;I)(this, arguments.callee.index);
            };
            xAxis.labels.formatter = i;
        }

        // Y axis label formatters
        for (i = 0; i < yAxisLabelFormatterFlags.length && i < yAxisStackLabelFormatterFlags.length; i++) {
            var yAxis = yAxisLabelFormatterFlags.length == 1 ? options.yAxis : options.yAxis[i];
            if (yAxisLabelFormatterFlags[i]) {
                yAxis.labels = yAxis.labels || {};
                yAxis.labels.formatter = function() {
                    return self.@org.moxieapps.gwt.highcharts.client.BaseChart::yAxisLabelFormatterCallback(Lcom/google/gwt/core/client/JavaScriptObject;I)(this, arguments.callee.index);
                };
                yAxis.labels.formatter.index = i;
            }
            if (yAxisStackLabelFormatterFlags[i]) {
                yAxis.stackLabels = yAxis.stackLabels || {};
                yAxis.stackLabels.formatter = function() {
                    return self.@org.moxieapps.gwt.highcharts.client.BaseChart::yAxisStackLabelFormatterCallback(Lcom/google/gwt/core/client/JavaScriptObject;I)(this, arguments.callee.index);
                };
                yAxis.stackLabels.formatter.index = i;
            }
        }

        // Plot options data label formatters
        for (var type in plotOptionsLabelsFormatterFlags) {
            if (type.indexOf("gwt") < 0 && plotOptionsLabelsFormatterFlags[type]) {
                options.plotOptions = options.plotOptions || {};
                options.plotOptions[type] = options.plotOptions[type] || {};
                options.plotOptions[type].dataLabels = options.plotOptions[type].dataLabels || {};
                var seriesType = type;
                options.plotOptions[type].dataLabels.formatter = function() {
                    return self.@org.moxieapps.gwt.highcharts.client.BaseChart::plotOptionsLabelsFormatterCallback(Lcom/google/gwt/core/client/JavaScriptObject;Ljava/lang/String;)(this, seriesType);
                };
            }
        }

        // Series override label formatters
        for (i = 0; i < seriesLabelsFormatterFlags.length; i++) {
            if (!seriesLabelsFormatterFlags[i]) continue;
            var series = options.series[i];
            series.dataLabels = series.dataLabels || {};
            series.dataLabels.formatter = function() {
                return self.@org.moxieapps.gwt.highcharts.client.BaseChart::seriesLabelsFormatterCallback(Lcom/google/gwt/core/client/JavaScriptObject;I)(this, arguments.callee.index);
            };
            series.dataLabels.formatter.index = i;
        }

        // Draw the chart!
        return new $wnd.Highcharts[chartTypeName](options);
    }-*/;

    @SuppressWarnings({"UnusedDeclaration"})
    private String toolTipFormatterCallback(JavaScriptObject nativeData) {
        if (toolTip == null || toolTip.getToolTipFormatter() == null) {
            return null;
        }
        return toolTip.getToolTipFormatter().format(new ToolTipData(nativeData));
    }

    @SuppressWarnings({"UnusedDeclaration"})
    private String xAxisLabelFormatterCallback(JavaScriptObject nativeData, int axisIndex) {
        if (xAxes == null || xAxes.size() <= axisIndex ||
            xAxes.get(axisIndex).getLabels() == null ||
            xAxes.get(axisIndex).getLabels().getFormatter() == null) {
            return null;
        }
        return xAxes.get(axisIndex).getLabels().getFormatter().format(new AxisLabelsData(nativeData));
    }

    @SuppressWarnings({"UnusedDeclaration"})
    private String yAxisLabelFormatterCallback(JavaScriptObject nativeData, int axisIndex) {
        if (yAxes == null || yAxes.size() <= axisIndex ||
            yAxes.get(axisIndex).getLabels() == null ||
            yAxes.get(axisIndex).getLabels().getFormatter() == null) {
            return null;
        }
        return yAxes.get(axisIndex).getLabels().getFormatter().format(new AxisLabelsData(nativeData));
    }

    @SuppressWarnings({"UnusedDeclaration"})
    private String yAxisStackLabelFormatterCallback(JavaScriptObject nativeData, int axisIndex) {
        if (yAxes == null || yAxes.size() <= axisIndex ||
            yAxes.get(axisIndex).getStackLabels() == null ||
            yAxes.get(axisIndex).getStackLabels().getFormatter() == null) {
            return null;
        }
        return yAxes.get(axisIndex).getStackLabels().getFormatter().format(new StackLabelsData(nativeData));
    }

    @SuppressWarnings({"UnusedDeclaration"})
    private String plotOptionsLabelsFormatterCallback(JavaScriptObject nativeData, String type) {
        PlotOptions plotOptions = null;
        if ("area".equals(type)) plotOptions = areaPlotOptions;
        if ("areaspline".equals(type)) plotOptions = areaSplinePlotOptions;
        if ("bar".equals(type)) plotOptions = barPlotOptions;
        if ("column".equals(type)) plotOptions = columnPlotOptions;
        if ("line".equals(type)) plotOptions = linePlotOptions;
        if ("pie".equals(type)) plotOptions = piePlotOptions;
        if ("scatter".equals(type)) plotOptions = scatterPlotOptions;
        if ("series".equals(type)) plotOptions = seriesPlotOptions;
        if ("spline".equals(type)) plotOptions = splinePlotOptions;

        if (plotOptions == null || plotOptions.getDataLabels() == null || plotOptions.getDataLabels().getFormatter() == null) {
            return null;
        }
        return plotOptions.getDataLabels().getFormatter().format(new DataLabelsData(nativeData));
    }

    @SuppressWarnings({"UnusedDeclaration"})
    private String seriesLabelsFormatterCallback(JavaScriptObject nativeData, int seriesIndex) {
        if (seriesList == null || seriesList.size() <= seriesIndex ||
            seriesList.get(seriesIndex).getPlotOptions() == null ||
            seriesList.get(seriesIndex).getPlotOptions().getDataLabels() == null ||
            seriesList.get(seriesIndex).getPlotOptions().getDataLabels().getFormatter() == null) {
            return null;
        }
        return seriesList.get(seriesIndex).getPlotOptions().getDataLabels().getFormatter().format(new DataLabelsData(nativeData));
    }

    private static native void nativeRedraw(JavaScriptObject chart) /*-{
        chart.redraw();
    }-*/;

    private static native void nativeDestroy(JavaScriptObject chart) /*-{
        chart.destroy();
    }-*/;

    private static native void nativeSetSize(JavaScriptObject chart, int width, int height, boolean animated) /*-{
        chart.setSize(width, height, animated);
    }-*/;

    private static native void nativeSetSize(JavaScriptObject chart, int width, int height, JavaScriptObject animation) /*-{
        chart.setSize(width, height, animation);
    }-*/;

    private static native JavaScriptObject nativeGet(JavaScriptObject chart, String id) /*-{
        return chart.get(id);
    }-*/;

    private static native void nativeAddSeries(JavaScriptObject chart, JavaScriptObject seriesOptions, boolean redraw, boolean animationFlag) /*-{
        chart.addSeries(seriesOptions, redraw, animationFlag);
    }-*/;

    private static native void nativeAddSeries(JavaScriptObject chart, JavaScriptObject seriesOptions, boolean redraw, JavaScriptObject animationOptions) /*-{
        chart.addSeries(seriesOptions, redraw, animationOptions);
    }-*/;

    private static native void nativeRemoveSeries(JavaScriptObject chart, JavaScriptObject nativeSeries, boolean redraw) /*-{
        nativeSeries.remove(redraw);
    }-*/;

    private static native void nativeHideLoading(JavaScriptObject chart) /*-{
        chart.hideLoading();
    }-*/;

    private static native void nativeShowLoading(JavaScriptObject chart, String message) /*-{
        chart.showLoading(message);
    }-*/;

}
