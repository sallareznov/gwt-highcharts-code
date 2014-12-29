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

package org.moxieapps.gwt.highcharts.client.events;

/**
 * An interface that can be used as a callback handler when click events are fired on the series
 * of a chart.  General usage is as follows:
 * <code><pre>
 * chart.setSeriesPlotOptions(new SeriesPlotOptions()
 *    .setSeriesClickEventHandler(new SeriesClickEventHandler() {
 *       public boolean onClick(SeriesClickEvent clickEvent) {
 *          Window.alert("User clicked on the series near the point: " + clickEvent.getNearestPointName());
 *          return true;
 *       }
 *    )
 * });
 * </pre></code>
 * See the documentation on the {@link ChartClickEvent} class for more details on the data
 * available when a click event occurs.
 *
 * @author squinn@moxiegroup.com (Shawn Quinn)
 * @since 1.1.0
 */
public interface PointDropEventHandler {

    public boolean onDrop(PointDropEvent event);

}
