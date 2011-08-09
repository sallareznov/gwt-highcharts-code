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

/**
 * An object that represents the state information that will be passed to any custom
 * {@link ToolTipFormatter} to allow for custom strings to be included within the tooltip area.
 * See the {@link ToolTipFormatter#format(ToolTipData)} method for more details on the capabilities
 * that custom formatters can provide.
 *
 * @author squinn@moxiegroup.com (Shawn Quinn)
 * @since 1.0
 */
public class ToolTipData {

    @SuppressWarnings({"FieldCanBeLocal"})
    private JavaScriptObject data;

    // Purposefully restricted to package scope
    ToolTipData(JavaScriptObject data) {
        this.data = data;
    }

    /**
     * Return the percentage value of the point (which represents the point's percentage
     * of the total).  Stacked series and pies only.
     *
     * @return The percentage value of the point.
     */
    public native double getPercentage() /*-{
        return this.@org.moxieapps.gwt.highcharts.client.ToolTipData::data.percentage;
    }-*/;

    /**
     * Return the name of the series that the point is a part of.
     *
     * @return The name of the series that the point is a part of
     */
    public native String getSeriesName() /*-{
        return this.@org.moxieapps.gwt.highcharts.client.ToolTipData::data.series.name;
    }-*/;

    /**
     * Return the name of the point (e.g. "point.name").
     *
     * @return The name of the point that the tooltip is over.
     */
    public native String getPointName() /*-{
        return this.@org.moxieapps.gwt.highcharts.client.ToolTipData::data.point.name;
    }-*/;

    /**
     * Return the total value at this point's x value.  Stacked series only.
     *
     * @return The total value at this point's x value (only applicable in stacked series).
     */
    public native double getTotal() /*-{
        return this.@org.moxieapps.gwt.highcharts.client.ToolTipData::data.total;
    }-*/;

    /**
     * Return the total value at this point's x value as a long.  Stacked series only.
     *
     * @return Return the total value at this point's x value as a long.
     */
    public long getTotalAsLong() {
        return ((Double)getTotal()).longValue();
    }

    /**
     * Return the x value of the point as a double.  An exception will be thrown
     * if the native value of the object is not a number.
     *
     * @return The x value of the point as a double.
     */
    public native double getXAsDouble() /*-{
        return this.@org.moxieapps.gwt.highcharts.client.ToolTipData::data.x;
    }-*/;

    /**
     * Return the x value of the point as a long.  An exception will be thrown
     * if the native value of the object is not a number.
     *
     * @return The x value of the point as a long.
     */
    public long getXAsLong() {
        return ((Double)getXAsDouble()).longValue();
    }

    /**
     * Return the x value of the point as a string.  An exception will be thrown
     * if the native value of the object is not a string.
     *
     * @return The x value of the point as a string.
     */
    public native String getXAsString() /*-{
        return this.@org.moxieapps.gwt.highcharts.client.ToolTipData::data.x;
    }-*/;


    /**
     * Return the y value of the point as a double.  An exception will be thrown
     * if the native value of the object is not a number.
     *
     * @return The y value of the point as a double.
     */
    public native double getYAsDouble() /*-{
        return this.@org.moxieapps.gwt.highcharts.client.ToolTipData::data.y;
    }-*/;

    /**
     * Return the y value of the point as a long.  An exception will be thrown
     * if the native value of the object is not a number.
     *
     * @return The y value of the point as a long.
     */
    public long getYAsLong() {
        return ((Double)getYAsDouble()).longValue();
    }

    /**
     * Return the y value of the point as a string.  An exception will be thrown
     * if the native value of the object is not a string.
     *
     * @return The y value of the point as a string.
     */
    public native String getYAsString() /*-{
        return this.@org.moxieapps.gwt.highcharts.client.ToolTipData::data.y;
    }-*/;


    // TODO: Add "point" and "points" accessors
}
