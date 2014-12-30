/*
 * File: $URL$
 * $Id$
 * Copyright: Vekia
 *
 * Last change:
 * $Date$
 * $Author$
 */
package org.moxieapps.gwt.highcharts.client;

/**
 * @author sdiagne
 *
 */
public class PieData {
    
    private String key;
    private double value;
    
    /**
     * 
     */
    public PieData(String key, double value) {
	this.key = key;
	this.value = value;
    }
    
    /**
     * @return the key
     */
    public String getKey() {
	return key;
    }
    
    /**
     * @return the value
     */
    public double getValue() {
	return value;
    }

}
