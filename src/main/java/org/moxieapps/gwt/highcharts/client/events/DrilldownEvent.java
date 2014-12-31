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

import org.moxieapps.gwt.highcharts.client.Drilldown;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * @author sdiagne
 *
 */
public class DrilldownEvent {
    
    private JavaScriptObject event;
    private Drilldown drilldown;
    
    /**
     * 
     */
    public DrilldownEvent(JavaScriptObject event, Drilldown drilldown) {
	this.event = event;
	this.drilldown = drilldown;
    }
    
    /**
     * @return the drilldown
     */
    public Drilldown getDrilldown() {
	return drilldown;
    }
    
    /**
     * @return the event
     */
    public JavaScriptObject getNativeEvent() {
	return event;
    }

}
