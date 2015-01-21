/*
 * File: $URL: svn+ssh://svn/svn/vekia-framework/trunk/hightchartsgwt/src/main/java/org/moxieapps/gwt/highcharts/client/Drilldown.java $
 * $Id: Drilldown.java 680 2014-10-03 15:18:34Z sdiagne $
 * Copyright: Vekia
 *
 * Last change:
 * $Date: 2014-10-03 17:18:34 +0200 (ven., 03 oct. 2014) $
 * $Author: sdiagne $
 */
package org.moxieapps.gwt.highcharts.client;

import java.util.Iterator;
import java.util.List;

import org.moxieapps.gwt.highcharts.client.events.DrilldownEventHandler;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONArray;

/**
 * @author sdiagne
 *
 */
public class Drilldown {
    
    private JSONArray series;
    private DrilldownEventHandler drilldownEventHandler;
    private boolean enabled;
    
    /**
     * 
     */
    public Drilldown() {
	series = new JSONArray();
	enabled = false;
    }

    public Drilldown setSeries(List<PieSerie> pieData) {
	int i = 0;
	final Iterator<PieSerie> pieDataIterator = pieData.iterator();
	while (pieDataIterator.hasNext()) {
	    final PieSerie currentPieData = pieDataIterator.next();
	    series.set(i++, currentPieData.getJSONObject());
	}
	return this;
    }
    
    /**
     * @return the series
     */
    public JSONArray getSeries() {
	return series;
    }
    
    /**
     * @return the drilldownEventHandler
     */
    public DrilldownEventHandler getDrilldownEventHandler() {
	return drilldownEventHandler;
    }
    
    /**
     * @param drilldownEventHandler the drilldownEventHandler to set
     */
    public void setDrilldownEventHandler(DrilldownEventHandler drilldownEventHandler) {
	this.drilldownEventHandler = drilldownEventHandler;
    }
    
    public boolean isEnabled() {
	return enabled;
    }
    
    public Drilldown setEnabled(boolean enabled) {
	this.enabled = enabled;
	return this;
    }
    
    public static native JavaScriptObject setNativeSeries() /*-{
	return {series: [{
            name: 'Microsoft Internet Explorer',
            id: 'Microsoft Internet Explorer',
            data: [
                ['v8.0', 26.61],
                ['v9.0', 16.96],
                ['v6.0', 6.40],
                ['v7.0', 3.55],
                ['v8.0', 0.09]
            ]
        }, {
            name: 'Chrome',
            id: 'Chrome',
            data: [
                ['v18.0', 8.01],
                ['v19.0', 7.73],
                ['v17.0', 1.13],
                ['v16.0', 0.45],
                ['v18.0', 0.26],
                ['v14.0', 0.25],
                ['v20.0', 0.24],
                ['v15.0', 0.18],
                ['v12.0', 0.16],
                ['v13.0', 0.13],
                ['v11.0', 0.10],
                ['v10.0', 0.09]
            ]
        }, {
            name: 'Firefox',
            id: 'Firefox',
            data: [
                ['v12', 6.72],
                ['v11', 4.72],
                ['v13', 2.16],
                ['v3.6', 1.87],
                ['v10.0', 0.90],
                ['v9.0', 0.65],
                ['v8.0', 0.55],
                ['v4.0', 0.50],
                ['v3.0', 0.36],
                ['v3.5', 0.36],
                ['v6.0', 0.32],
                ['v5.0', 0.31],
                ['v7.0', 0.29],
                ['v14', 0.10],
                ['v2.0', 0.09]
            ]
        }, {
            name: 'Safari',
            id: 'Safari',
            data: [
                ['v5.1', 3.53],
                ['v5.0', 0.85],
                ['v4.0', 0.14],
                ['v4.1', 0.12],
            ]
        }, {
            name: 'Opera',
            id: 'Opera',
            data: [
                ['v11.x', 1.30],
                ['v12.x', 0.15],
                ['v10.x', 0.09]
            ]
        }]};
    }-*/;
}
