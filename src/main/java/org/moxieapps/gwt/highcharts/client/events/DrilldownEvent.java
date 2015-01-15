/*
 * File: $URL$
 * $Id$
 * Copyright: Vekia
 *
 * Last change:
 * $Date$
 * $Author$
 */
package org.moxieapps.gwt.highcharts.client.events;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * @author sdiagne
 *
 */
public class DrilldownEvent {
    
    private JavaScriptObject event;
    private boolean drilldown;
    
    /**
     * 
     */
    public DrilldownEvent(JavaScriptObject event, boolean drilldown) {
	this.event = event;
	this.drilldown = drilldown;
    }
    
    /**
     * @return the drilldown
     */
    public boolean getDrilldown() {
	return drilldown;
    }
    
    /**
     * @return the event
     */
    public JavaScriptObject getNativeEvent() {
	return event;
    }
    
}
