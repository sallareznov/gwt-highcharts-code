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
 * TODO: This is a place holder class which will be formalized once the Highstock
 * library is released.
 *
 * @author squinn@moxiegroup.com (Shawn Quinn)
 * @since 1.0.0
 */
public class RangeSelector extends Configurable<RangeSelector> {

    public RangeSelector setSelected(Number selected) {
        return this.setOption("selected", selected);
    }

    public RangeSelector setInputEnabled(boolean inputEnabled) {
        return this.setOption("inputEnabled", inputEnabled);
    }

}
