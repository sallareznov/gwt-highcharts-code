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
 * Represents the configuration options available for controlling the way date and time information
 * will be displayed. For a datetime axis, the scale will automatically adjust to the appropriate unit. This configuration
 * class controls the default string format representations used for each unit. Defaults to:
 * <ul>
 * <li>second: '%H:%M:%S'</li>
 * <li>minute: '%H:%M'</li>
 * <li>hour: '%H:%M'</li>
 * <li>day: '%e. %b'</li>
 * <li>week: '%e. %b'</li>
 * <li>month: '%b \'%y'</li>
 * <li>year: '%Y</li>
 * </ul>
 * Available replacement codes for the day of date are:
 * <ul>
 * <li>'a': Short weekday, like 'Mon'</li>
 * <li>'A': Long weekday, like 'Monday'</li>
 * <li>'d': Two digit day of the month, 01 to 31</li>
 * <li>'e': Day of the month, 1 through 31</li>
 * </ul>
 * Available replacement codes for the month of the date are:
 * <ul>
 * <li>'b': Short month, like 'Jan'</li>
 * <li>'B': Long month, like 'January'</li>
 * <li>'m': Two digit month number, 01 through 12</li>
 * </ul>
 * Available replacement codes for the year of the date are:
 * <ul>
 * <li>'y': Two digits year, like 09 for 2009</li>
 * <li>'Y': Four digits year, like 2009</li>
 * </ul>
 * Available replacement codes for the time portions are:
 * <ul>
 * <li>'H': Two digits hours in 24h format, 00 through 23</li>
 * <li>'I': Two digits hours in 12h format, 00 through 11</li>
 * <li>'l': Hours in 12h format, 1 through 12</li>
 * <li>'M': Two digits minutes, 00 through 59</li>
 * <li>'p': Upper case AM or PM</li>
 * <li>'P': Lower case AM or PM</li>
 * <li>'S': Two digits seconds, 00 through  59</li>
 * </ul>
 * Example usage:
 * <code><pre>
 *   axis.setDateTimeLabelFormats(
 *     new DateTimeLabelFormats()
 *       .setHour("%I %p")
 *       .setMinute("%I:%M %p")
 *   );
 * </pre></code>
 *
 * @author squinn@moxiegroup.com (Shawn Quinn)
 * @since 1.0.0
 */
public class DateTimeLabelFormats extends Configurable<DateTimeLabelFormats> {

    /**
     * Convenience method for setting the 'millisecond' format.  Equivalent to:
     * <pre><code>
     *     dateTimeLabelFormats.setOption("millisecond", "%H:%M:%S.%L");
     * </code></pre>
     *
     * @param millisecond The format to use when displaying labels in units of milliseconds.
     * @return A reference to this {@link DateTimeLabelFormats} instance for convenient method chaining.
     * @since 1.5.0
     */
	public DateTimeLabelFormats setMillisecond(String millisecond){
		return this.setOption("millisecond", millisecond);
	}

    /**
     * Convenience method for setting the 'second' format.  Equivalent to:
     * <pre><code>
     *     dateTimeLabelFormats.setOption("second", "%H:%M:%S");
     * </code></pre>
     *
     * @param second The format to use when displaying labels in units of seconds.
     * @return A reference to this {@link DateTimeLabelFormats} instance for convenient method chaining.
     */
    public DateTimeLabelFormats setSecond(String second) {
        return this.setOption("second", second);
    }

    /**
     * Convenience method for setting the 'minute' format.  Equivalent to:
     * <pre><code>
     *     dateTimeLabelFormats.setOption("minute", "%H:%M");
     * </code></pre>
     *
     * @param minute The format to use when displaying labels in units of minutes.
     * @return A reference to this {@link DateTimeLabelFormats} instance for convenient method chaining.
     */
    public DateTimeLabelFormats setMinute(String minute) {
        return this.setOption("minute", minute);
    }

    /**
     * Convenience method for setting the 'hour' format.  Equivalent to:
     * <pre><code>
     *     dateTimeLabelFormats.setOption("hour", "%H:%M");
     * </code></pre>
     *
     * @param hour The format to use when displaying labels in units of hours.
     * @return A reference to this {@link DateTimeLabelFormats} instance for convenient method chaining.
     */
    public DateTimeLabelFormats setHour(String hour) {
        return this.setOption("hour", hour);
    }

    /**
     * Convenience method for setting the 'day' format.  Equivalent to:
     * <pre><code>
     *     dateTimeLabelFormats.setOption("day", "%e. %b");
     * </code></pre>
     *
     * @param day The format to use when displaying labels in units of days.
     * @return A reference to this {@link DateTimeLabelFormats} instance for convenient method chaining.
     */
    public DateTimeLabelFormats setDay(String day) {
        return this.setOption("day", day);
    }

    /**
     * Convenience method for setting the 'week' format.  Equivalent to:
     * <pre><code>
     *     dateTimeLabelFormats.setOption("week", "%e. %b");
     * </code></pre>
     *
     * @param week The format to use when displaying labels in units of weeks.
     * @return A reference to this {@link DateTimeLabelFormats} instance for convenient method chaining.
     */
    public DateTimeLabelFormats setWeek(String week) {
        return this.setOption("week", week);
    }

    /**
     * Convenience method for setting the 'month' format.  Equivalent to:
     * <pre><code>
     *     dateTimeLabelFormats.setOption("month", "%b \'%y");
     * </code></pre>
     *
     * @param month The format to use when displaying labels in units of month.
     * @return A reference to this {@link DateTimeLabelFormats} instance for convenient method chaining.
     */
    public DateTimeLabelFormats setMonth(String month) {
        return this.setOption("month", month);
    }

    /**
     * Convenience method for setting the 'year' format.  Equivalent to:
     * <pre><code>
     *     dateTimeLabelFormats.setOption("year", "%Y");
     * </code></pre>
     *
     * @param year The format to use when displaying labels in units of year.
     * @return A reference to this {@link DateTimeLabelFormats} instance for convenient method chaining.
     */
    public DateTimeLabelFormats setYear(String year) {
        return this.setOption("year", year);
    }

}
