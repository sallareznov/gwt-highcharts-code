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

package org.moxieapps.gwt.highcharts.client.labels;

/**
 * A common base class for both {@link DataLabels} and {@link PieDataLabels} to prevent code duplication
 * while still maintaining a cleaner way for the user to utilize the method chaining with the generics
 * in place.  You should not use this class directly, but instead use one of the base classes.
 *
 * @author squinn@moxiegroup.com (Shawn Quinn)
 * @since 1.0
 */
public abstract class BaseDataLabels<T extends BaseDataLabels> extends Labels<T> {

    private DataLabelsFormatter dataLabelsFormatter;

    /**
     * Sets a custom formatter for the labels that can be used to control how the text of the
     * data labels will be displayed.  See the {@link DataLabelsFormatter} interface, and
     * in particular the {@link DataLabelsFormatter#format(DataLabelsData)} method for more details on
     * the capabilities available to custom formatters.
     *
     * @param dataLabelsFormatter The custom formatter to use for the labels (if not given a built-in
     *                            generic formatter is used which simply returns the Y value of the point).
     * @return A reference to this {@link DataLabels} instance for convenient method chaining.
     */
    public T setFormatter(DataLabelsFormatter dataLabelsFormatter) {
        this.dataLabelsFormatter = dataLabelsFormatter;
        @SuppressWarnings({"unchecked", "UnnecessaryLocalVariable"})
        final T instance = (T) this;
        return instance;
    }

    /**
     * Returns the custom data labels formatter that has been applied to the labels, or null if the
     * built-in generic formatter is being used instead.
     *
     * @return The custom data labels formatter that has been applied, or null if it has not been set.
     */
    public DataLabelsFormatter getFormatter() {
        return this.dataLabelsFormatter;
    }

}
