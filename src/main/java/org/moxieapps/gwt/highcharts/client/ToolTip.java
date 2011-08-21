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

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONBoolean;

/**
 * A configurable class that can be used to represent custom ToolTip options for the
 * chart, which can then be set on the chart (via the {@link Chart#setToolTip(ToolTip)} method.)
 * The tooltip appears when the user hovers over a series or point.
 * Example usage:
 * <code><pre>
 *   chart.setToolTip(
 *     new ToolTip()
 *       .setBorderColor("#CC0000")
 *       .setShadow(true)
 *   );
 * </pre></code>
 *
 * @author squinn@moxiegroup.com (Shawn Quinn)
 * @since 1.0.0
 */
public class ToolTip extends Configurable<ToolTip> {

    /**
     * Convenience method for setting the 'backgroundColor' option for the tool tips.  Equivalent to:
     * <pre><code>
     *     toolTip.setOption("backgroundColor", "#CCCCCC");
     * </code></pre>
     * The RGB background color for the tooltip.  Defaults to white with a slight opacity.
     * <p/>
     * Note that this method is intended for setting the color to a simple RBG hex value.  If you instead
     * want to set a color to include an alpha channel or a gradient, use the
     * {@link #setBackgroundColor(Color)} method.
     *
     * @param backgroundColor The RGB background color for the tooltip.
     * @return A reference to this {@link ToolTip} instance for convenient method chaining.
     */
    public ToolTip setBackgroundColor(String backgroundColor) {
        return this.setOption("backgroundColor", backgroundColor);
    }

    /**
     * Convenience method for setting the 'backgroundColor' option for the tool tips to a gradient or color with
     * an alpha channel .  Equivalent to:
     * <pre><code>
     *     toolTip.setOption("backgroundColor", new Color()
     *        .setLinearGradient(0.0, 0.0, 1.0, 1.0)
     *        .addStop(new Color(255, 255, 255))
     *        .addStop(new Color(200, 200, 255))
     *     ));
     * </code></pre>
     * The background color for the tooltip (as a gradient or color with an alpha channel).  Defaults to "rgba(255, 255, 255, .85)".
     * <p/>
     * Note that this method is intended for setting the background to a gradient or color with an alpha
     * channel.  If you instead want to just set the color to a standard RGB hex value use the
     * {@link #setBackgroundColor(String)} method instead.
     *
     * @param backgroundColor The color gradient or color with an alpha channel to set as the 'backgroundColor' option on the tool tip.
     * @return A reference to this {@link ToolTip} instance for convenient method chaining.
     */
    public ToolTip setBackgroundColor(Color backgroundColor) {
        return this.setOption("backgroundColor", backgroundColor != null ? backgroundColor.getOptionValue() : null);
    }


    /**
     * Convenience method for setting the 'borderColor' option for the tool tips.  Equivalent to:
     * <pre><code>
     *     toolTip.setOption("borderColor", "#CCCCCC");
     * </code></pre>
     * The color of the tooltip border. When null, the border takes the color of the corresponding series or point.
     * Defaults to "auto".
     * <p/>
     * Note that this method is intended for setting the color to a simple RBG hex value.  If you instead
     * want to set a color to include an alpha channel or a gradient, use the
     * {@link #setBorderColor(Color)} method.
     *
     * @param borderColor The color of the tooltip border.
     * @return A borderColor to this {@link ToolTip} instance for convenient method chaining.
     */
    public ToolTip setBorderColor(String borderColor) {
        return this.setOption("borderColor", borderColor);
    }

    /**
     * Convenience method for setting the 'borderColor' option for the tool tips to a gradient or color with
     * an alpha channel .  Equivalent to:
     * <pre><code>
     *     toolTip.setOption("borderColor", new Color()
     *        .setLinearGradient(0.0, 0.0, 1.0, 1.0)
     *        .addStop(new Color(255, 255, 255))
     *        .addStop(new Color(200, 200, 255))
     *     ));
     * </code></pre>
     * The border color for the tooltip (as a gradient or color with an alpha channel). When null, the border
     * takes the color of the corresponding series or point.  Defaults to "auto".
     * <p/>
     * Note that this method is intended for setting the border to a gradient or color with an alpha
     * channel.  If you instead want to just set the color to a standard RGB hex value use the
     * {@link #setBackgroundColor(String)} method instead.
     *
     * @param borderColor The color gradient or color with an alpha channel to set as the 'borderColor' option on the tool tip.
     * @return A reference to this {@link ToolTip} instance for convenient method chaining.
     */
    public ToolTip setBorderColor(Color borderColor) {
        return this.setOption("borderColor", borderColor != null ? borderColor.getOptionValue() : null);
    }

    /**
     * Convenience method for setting the 'borderRadius' option for the tool tips.  Equivalent to:
     * <pre><code>
     *     toolTip.setOption("borderRadius", 8);
     * </code></pre>
     * The radius of the rounded border corners. Defaults to 5.
     *
     * @param borderRadius The radius of the rounded border corners.
     * @return A reference to this {@link ToolTip} instance for convenient method chaining.
     */
    public ToolTip setBorderRadius(Number borderRadius) {
        return this.setOption("borderRadius", borderRadius);
    }

    /**
     * Convenience method for setting the 'borderWidth' option for the tool tips.  Equivalent to:
     * <pre><code>
     *     toolTip.setOption("borderWidth", 3);
     * </code></pre>
     * The pixel width of the tooltip border. Defaults to 2.
     *
     * @param borderWidth The pixel width of the tooltip border.
     * @return A reference to this {@link ToolTip} instance for convenient method chaining.
     */
    public ToolTip setBorderWidth(Number borderWidth) {
        return this.setOption("borderWidth", borderWidth);
    }

    /**
     * Convenience method for setting the 'crosshairs' option for the tool tips for both axis.  Equivalent to:
     * <pre><code>
     *     toolTip.setOption("crosshairs", true);
     * </code></pre>
     * Display crosshairs to connect the points with their corresponding axis values.  Crosshairs are disabled
     * by default.
     * <p/>
     * Note that see the overloaded versions of this method for other ways to control the crosshair configurations.
     *
     * @param crosshairs Whether or not to display crosshairs to connect the points with their corresponding axis values.
     * @return A reference to this {@link ToolTip} instance for convenient method chaining.
     */
    public ToolTip setCrosshairs(boolean crosshairs) {
        return this.setOption("crosshairs", crosshairs);
    }

    /**
     * Convenience method for setting the 'crosshairs' option for the tool tips for each axis separately.  Equivalent to:
     * <pre><code>
     *     toolTip.setOption("crosshairs", true, true);
     * </code></pre>
     * Display crosshairs to connect the points with their corresponding axis values.  Crosshairs are disabled
     * by default.
     * <p/>
     * Note that see the overloaded versions of this method for other ways to control the crosshair configurations.
     *
     * @param xCrosshairs Whether or not to display the x axis crosshair.
     * @param yCrosshairs Whether or not to display the y axis crosshair.
     * @return A reference to this {@link ToolTip} instance for convenient method chaining.
     */
    public ToolTip setCrosshairs(boolean xCrosshairs, boolean yCrosshairs) {
        JSONArray jsonArray = new JSONArray();
        jsonArray.set(0, JSONBoolean.getInstance(xCrosshairs));
        jsonArray.set(0, JSONBoolean.getInstance(yCrosshairs));
        return this.setOption("crosshairs", jsonArray);
    }

    // TODO: Add crosshairs options for taking an array of objects

    /**
     * Convenience method for setting the 'enabled' option for the tool tips.  Equivalent to:
     * <pre><code>
     *     toolTip.setOption("enabled", false);
     * </code></pre>
     * Enable or disable the tool tips. Defaults to true.
     *
     * @param enabled Whether or not to enable or disable the tool tips.
     * @return A reference to this {@link ToolTip} instance for convenient method chaining.
     */
    public ToolTip setEnabled(boolean enabled) {
        return this.setOption("enabled", enabled);
    }

    private ToolTipFormatter toolTipFormatter;

    /**
     * Sets a custom formatter on the tooltip that can be used to control the contents and styling
     * of the text that appears in the tooltip.  See the {@link ToolTipFormatter} interface, and
     * in particular the {@link ToolTipFormatter#format(ToolTipData)} method for more details on
     * the capabilities available to custom formatters.
     *
     * @param toolTipFormatter The custom formatter to use for the tooltips (if not given a built-in
     *                         generic formatter is used when tooltips are enabled).
     * @return A reference to this {@link ToolTip} instance for convenient method chaining.
     */
    public ToolTip setFormatter(ToolTipFormatter toolTipFormatter) {
        this.toolTipFormatter = toolTipFormatter;
        return this;
    }

    // Purposefully restricted to package scope
    ToolTipFormatter getToolTipFormatter() {
        return this.toolTipFormatter;
    }

    /**
     * Convenience method for setting the 'shadow' option for the tool tips.  Equivalent to:
     * <pre><code>
     *     toolTip.setOption("shadow", false);
     * </code></pre>
     * Whether to apply a drop shadow to the tooltip. Defaults to true.
     *
     * @param shadow Whether to apply a drop shadow to the tooltip.
     * @return A reference to this {@link ToolTip} instance for convenient method chaining.
     */
    public ToolTip setShadow(boolean shadow) {
        return this.setOption("shadow", shadow);
    }

    /**
     * Convenience method for setting the 'shared' option for the tool tips.  Equivalent to:
     * <pre><code>
     *     toolTip.setOption("shared", true);
     * </code></pre>
     * When the tooltip is shared, the entire plot area will capture mouse movement, and tooltip
     * texts for all series will be shown in a single bubble. This is recommended for single series
     * charts and for iPad optimized sites. Defaults to false.
     *
     * @param shared Whether or not to shared the same tool tip between points.
     * @return A reference to this {@link ToolTip} instance for convenient method chaining.
     */
    public ToolTip setShared(boolean shared) {
        return this.setOption("shared", shared);
    }

    /**
     * Convenience method for setting the 'snap' option for the tool tips.  Equivalent to:
     * <pre><code>
     *     toolTip.setOption("snap", 40);
     * </code></pre>
     * Proximity snap for graphs or single points. Does not apply to bars, columns and pie slices.
     * It defaults to 10 for mouse-powered devices and 25 for touch devices.
     *
     * @param snap The proximity snap for graphs or single points.
     * @return A reference to this {@link ToolTip} instance for convenient method chaining.
     */
    public ToolTip setSnap(Number snap) {
        return this.setOption("snap", snap);
    }

    /**
     * Convenience method for setting the 'style' options of the tooltip.  Equivalent to:
     * <pre><code>
     *     toolTip.setOption("/style/fontWeight", "bold");
     *     toolTip.setOption("/style/fontFamily", "serif");
     *     etc.
     * </code></pre>
     * CSS styles for the tooltip. The tooltip can also be styled through the CSS
     * class ".highcharts-tooltip". Default value:
     * <ul>
     * <li>color: '#333333'</li>
     * <li>fontSize: '9pt'</li>
     * <li>padding: '5px'</li>
     * </ul>
     *
     * @param style CSS styles for the tooltip.
     * @return A reference to this {@link ToolTip} instance for convenient method chaining.
     */
    public ToolTip setStyle(Style style) {
        return this.setOption("style", style != null ? style.getOptions() : null);
    }

}
