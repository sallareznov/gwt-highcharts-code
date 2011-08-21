package org.moxieapps.gwt.highcharts.client.events;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Provides access to the raw information provided by Highcharts when a load event occurs.
 * This class is really provided for symmetry and future proofing the API, as in the current
 * version no information is provided on load events.
 *
 * @author squinn@moxiegroup.com (Shawn Quinn)
 * @since 1.1.0
 */
public class ChartLoadEvent {

    @SuppressWarnings({"FieldCanBeLocal", "UnusedDeclaration"})
    private JavaScriptObject event;

    /**
     * This constructor is intended for internal use only.  You should not create load events
     * directly, but instead should register a {@link ChartLoadEventHandler}.
     *
     * @param event The native javascript object containing the details of the original event that was fired.
     */
    public ChartLoadEvent(JavaScriptObject event) {
        this.event = event;
    }

}
