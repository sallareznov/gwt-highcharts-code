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

import java.util.Iterator;
import java.util.List;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;

/**
 * @author sdiagne
 *
 */
public class PieSerie {
    
    private String id;
    private String name;
    private JSONArray data;
    private int currentIndex;
    private boolean drilldown;
    
    /**
     * 
     */
    public PieSerie(String id, String name) {
	this.id = id;
	this.name = name;
	this.data = new JSONArray();
	this.currentIndex = 0;
    }
    
    public JSONObject getJSONObject() {
	final JSONObject jsonPieData = new JSONObject();
	jsonPieData.put("id", new JSONString(id));
	jsonPieData.put("name", new JSONString(name));
	jsonPieData.put("data", data);
	return jsonPieData;
    }
    
    /**
     * @return the id
     */
    public String getId() {
	return id;
    }
    
    /**
     * @return the name
     */
    public String getName() {
	return name;
    }
    
    /**
     * @return the data
     */
    public JSONArray getData() {
	return data;
    }
    
    public boolean getDrilldown() {
	return drilldown;
    }
    
    public void setId(String id) {
	this.id = id;
    }
    
    public void addData(String key, double value) {
	if (key == null) {
	    return;
	}
	final JSONArray newData = new JSONArray();
	newData.set(0, new JSONString(key));
	newData.set(1, new JSONNumber(value));
	data.set(currentIndex++, newData);
    }
    
    public void addData(PieData pieData) {
	addData(pieData.getKey(), pieData.getValue());
    }
    
    public void addData(List<PieData> data) {
	if (data == null) {
	    return;
	}
	final Iterator<PieData> dataIterator = data.iterator();
	while (dataIterator.hasNext()) {
	    final PieData currentPieData = dataIterator.next();
	    addData(currentPieData.getKey(), currentPieData.getValue());
	}
    }
    
    public void setData(List<PieData> data) {
	if (data == null) {
	    return;
	}
	this.data = new JSONArray();
	addData(data);
    }
    
    /**
     * @param drilldown the drilldown to set
     */
    public void setDrilldown(boolean drilldown) {
	this.drilldown = drilldown;
    }
    
    /*-
     *  series: [{
                id: 'food',
                name: 'Food',
                data: [{
                    name: 'Apple',
                    y: 1.5,
                    drilldown: 'apple'
                },
                    ['Banana', 1],
                    ['Peer', 0.5],
                    ['Pineapple', 1]
                ]
            }, {

                id: 'apple',
                data: [['1/6' ,1],
                      ['1/3' , 2],
                      ['1/2' , 3]]
            },{
                id: 'animals',
                name: 'Animals',
                data: [{
                    name: 'Cats',
                    y: 5,
                    drilldown: 'cats'
                }, ['Dogs', 2],
                    ['Cows', 1],
                    ['Sheep', 1],
                    ['Pigs', 1]
                ]
            }, {

                id: 'cats',
                data: [1, 2, 3]
            }]
        
    })-*/
     

}
