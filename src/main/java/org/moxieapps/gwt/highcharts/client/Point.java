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
 * Represents a single data point that can be added to a {@link Series}.  As an extension
 * of {@link Configurable} each point instance can also optionally have configuration options set on it.
 * Standard example:
 * <code><pre>
 *  chart.addSeries(chart.createSeries()
 *     .setName("Browser share")
 *     .setPoints(new Point[] {
 *        new Point(15, 45.0),
 *        new Point(25, 26.8),
 *        new Point(35, 12.8),
 *        new Point(46, 8.5),
 *        new Point(55, 6.2),
 *        new Point(65, 0.7)
 *     })
 * );
 * </pre></code>
 * </p>
 * Advanced pie chart example (where the points represent categories and values for each category):
 * <code><pre>
 *  chart.addSeries(chart.createSeries()
 *     .setName("Browser share")
 *     .setPoints(new Point[]{
 *        new Point("Firefox", 45.0),
 *        new Point("IE", 26.8),
 *        new Point("Chrome", 12.8)
 *            .setSliced(true)
 *            .setSelected(true),
 *        new Point("Safari", 8.5),
 *        new Point("Opera", 6.2),
 *        new Point("Others", 0.7)
 *     })
 * );
 * </pre></code>
 *
 * @author squinn@moxiegroup.com (Shawn Quinn)
 * @since 1.0
 */
public class Point extends Configurable<Point> {

    private Number y;
    private Number x;

    /**
     * Create a new point, setting only the Y axis value that the point should be
     * rendered at within the series.
     *
     * @param y The Y value that the point should be rendered at within the series.
     */
    public Point(Number y) {
        this.y = y;
    }

    /**
     * Create a new point, setting both the value that the point should be rendered
     * at on the X and Y axis within the series.
     *
     * @param x The X value that the point should be rendered at within the series.
     * @param y The Y value that the point should be rendered at within the series.
     */
    public Point(Number x, Number y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Create a new point, setting the Y axis value that the point should be
     * rendered at within the series as well as the "name" property of the point.
     *
     * @param name The value to set as the "property" of the point.
     * @param y The Y value that the point should be rendered at within the series.
     */
    public Point(String name, Number y) {
        setName(name);
        this.y = y;
    }

    /**
     * Retrieve the Y value of where point should be rendered at within the series.
     * @return The Y value of the point (should always be non null).
     */
    public Number getY() {
        return y;
    }

    /**
     * Retrieve the X value of where point should be rendered at within the series.
     *
     * @return The X value of the point, or null if no X value was set.
     */
    public Number getX() {
        return x;
    }

    /**
     * Convenience method for setting the 'color' option of the point.  Equivalent to:
     * <pre><code>
     *     point.setOption("color", "#CC0000");
     * </code></pre>
     * Individual color for the point. Defaults to null.
     *
     * @param color The value to set as the point's color.
     * @return A reference to this {@link Point} instance for convenient method chaining.
     */
    public Point setColor(String color) {
        return this.setOption("color", color);
    }

    /**
     * Convenience method for setting the 'name' option of the point.  Equivalent to:
     * <pre><code>
     *     point.setOption("name", "Green Bears");
     * </code></pre>
     * The name of the point as shown in the legend, tooltip, dataLabel etc. Defaults to "".
     *
     * @param name The value to set as the point's name.
     * @return A reference to this {@link Point} instance for convenient method chaining.
     */
    public Point setName(String name) {
        return this.setOption("name", name);
    }

    public String getName() {
        // TODO
        return null;
    }

    /**
     * Convenience method for setting the 'selected' option of the point.  Equivalent to:
     * <pre><code>
     *     point.setOption("selected", true);
     * </code></pre>
     * Whether the point is selected or not.
     *
     * @param selected Whether the point is selected or not.
     * @return A reference to this {@link Point} instance for convenient method chaining.
     */
    public Point setSelected(boolean selected) {
        return this.setOption("selected", selected);
    }

    /**
     * Convenience method for setting the 'sliced' option of the point.  Equivalent to:
     * <pre><code>
     *     point.setOption("sliced", false);
     * </code></pre>
     * Pie series only. Whether to display a slice offset from the center. Defaults to false.
     *
     * @param sliced The value to set as the point's 'sliced' option.
     * @return A reference to this {@link Point} instance for convenient method chaining.
     */
    public Point setSliced(boolean sliced) {
        return this.setOption("sliced", sliced);
    }

    // Purposefully package scope
    boolean isSingleValue() {
        return this.getOptions() == null && this.getX() == null;
    }

}
