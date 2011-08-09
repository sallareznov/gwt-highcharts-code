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
import org.moxieapps.gwt.highcharts.client.Series;

/**
 * Represents the general plot options available for all series types, which can be set either generically
 * on the chart via the {@link Chart#setSeriesPlotOptions(SeriesPlotOptions)} method or directly on a
 * series via the {@link Series#setPlotOptions(PlotOptions)} method.
 *
 * @author squinn@moxiegroup.com (Shawn Quinn)
 * @since 1.0
 */
public class SeriesPlotOptions extends PlotOptions<SeriesPlotOptions> {

    // Series plot options match the options inherited from the base class, so there's nothing for us
    // to add here.  But, we still need this class as the concrete implementation of the plot options,
    // as well as future proofing the API in the case that the base options of each plot option type
    // are ever different than the generic options for all types.

}
