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

import com.google.gwt.core.client.JavaScriptObject;

/**
 * @author sdiagne
 *
 */
public class AnnotatedChart extends Chart {
    
    public AnnotatedChart() {
        super();
    }

    @Override
    protected String getChartTypeName() {
        return "Chart";
    }

    public void addText(String text,int seriesIndex, int pointIndex) {
	nativeAddText(getNativeChart(), text,seriesIndex, pointIndex);
    }
    
    private static native JavaScriptObject nativeAddText(JavaScriptObject chart, String text,int seriesIndex, int pointIndex) /*-{
    if (!chart.series[seriesIndex].visible) {
    return null;
    } else {
    return chart.renderer.text(text, chart.plotLeft+chart.series[seriesIndex].points[pointIndex].plotX+20, chart.series[seriesIndex].points[pointIndex].plotY+chart.plotTop-20)
            .attr({
                rotation: -45
            })
            .css({
                color: '#4572A7',
                fontSize: '16px'
            })
            .add();
            }
}-*/;

    public JavaScriptObject addText2(JavaScriptObject chart, String text, int seriesIndex, int pointIndex) {
	return nativeAddText(chart, text,seriesIndex, pointIndex);
    }
    
    public void addCircle(int seriesIndex, int pointIndex) {
	nativeAddCircle(getNativeChart(), seriesIndex, pointIndex);
    }

    public JavaScriptObject addCircle2(JavaScriptObject chart, int seriesIndex, int pointIndex) {
	return nativeAddCircle(chart, seriesIndex, pointIndex);
    }

    
    private static native JavaScriptObject nativeAddCircle(JavaScriptObject chart, int seriesIndex, int pointIndex) /*-{
    if (!chart.series[seriesIndex].visible) {
    return null;
    } else 
    return chart.renderer.circle(chart.plotLeft+chart.series[seriesIndex].points[pointIndex].plotX, chart.series[seriesIndex].points[pointIndex].plotY+chart.plotTop, 50)
    .on('dblclick',function(){alert('double clic sur une annotation');})
            .attr({
fill: '#FCFFC5',
        stroke: 'brown',
        'stroke-width': 1            })
            .add();

}-*/;
    
    private static native JavaScriptObject nativeGetPointsCoordinates(JavaScriptObject chart, int seriesIndex, int pointIndex) /*-{
	if (!chart.series[seriesIndex].visible) {
		return null;
	}
	return {x : chart.plotLeft + chart.series[seriesIndex].points[pointIndex].plotX, y : chart.series[seriesIndex].points[pointIndex].plotY + chart.plotTop};
}-*/;
    
    public JavaScriptObject getPointsCoordinates(JavaScriptObject chart, int seriesIndex, int pointIndex) {
	return nativeGetPointsCoordinates(getNativeChart(), seriesIndex, pointIndex);
    }
    
    public native float getX(JavaScriptObject point) /*-{
	return point.x;
    }-*/;
    
    public native float getY(JavaScriptObject point) /*-{
    	return point.y;
    }-*/;

    public JavaScriptObject addRect() {
	return nativeAddRect(getNativeChart());
    }

    public JavaScriptObject addRect2(JavaScriptObject chart) {
	return nativeAddRect(chart);
    }
    
    private static native JavaScriptObject nativeAddRectGen(JavaScriptObject chart, float x, float y, float width, float height, float cornerRadius, JavaScriptObject attr, JavaScriptObject css) /*-{
	var baseRect = chart.renderer.rect(x, y, width, height, cornerRadius);
	if (attr != null) {
		baseRect.attr(attr);
	}
	if (css != null) {
		baseRect.css(css);
	}
	return baseRect.add();
    }-*/;
    
    public JavaScriptObject addRectGen(JavaScriptObject chart, float x, float y, float width, float height, float cornerRadius, JavaScriptObject attr, JavaScriptObject css) {
	return nativeAddRectGen(chart, x, y, width, height, cornerRadius, attr, css);
    }
    
    private static native JavaScriptObject nativeAddCircleGen(JavaScriptObject chart, float centerX, float centerY, float radius, JavaScriptObject attr, JavaScriptObject css) /*-{
    	var baseCircle = chart.renderer.circle(centerX, centerY, radius);
    	if (attr != null) {
    		baseCircle.attr(attr);
    	}
    	if (css != null) {
    		baseCircle.css(css);
    	}
    	return baseCircle.add();
    }-*/;
    
    public JavaScriptObject addCircleGen(JavaScriptObject chart, float centerX, float centerY, float radius, JavaScriptObject attr, JavaScriptObject css) {
	return nativeAddCircleGen(chart, centerX, centerY, radius, attr, css);
    }
    
    private static native JavaScriptObject nativeAddTextGen(JavaScriptObject chart, String text, float x, float y, JavaScriptObject attr, JavaScriptObject css) /*-{
    	var baseText = chart.renderer.text(text, x, y);
    	if (attr != null) {
    		baseText.attr(attr);
    	}
    	if (css != null) {
    		baseText.css(css);
    	}
    	return baseText.add();
    }-*/;
    
    public JavaScriptObject addTextGen(JavaScriptObject chart, String text, float x, float y, JavaScriptObject attr, JavaScriptObject css) {
	return nativeAddTextGen(chart, text, x, y, attr, css);
    }
    
    private static native JavaScriptObject nativeAddPathGen(JavaScriptObject chart, JavaScriptObject pathArray, JavaScriptObject attr, JavaScriptObject css) /*-{
	var basePath = chart.renderer.path(pathArray);
	if (attr != null) {
		basePath.attr(attr);
	}
	if (css != null) {
		basePath.css(css);
	}
	return basePath.add();
    }-*/;
    
    public JavaScriptObject addPathGen(JavaScriptObject chart, JavaScriptObject pathArray, JavaScriptObject attr, JavaScriptObject css) {
	return nativeAddPathGen(chart, pathArray, attr, css);
    }
    
    private static native JavaScriptObject nativeAddImageGen(JavaScriptObject chart, String source, float x, float y, float width, float height, JavaScriptObject attr, JavaScriptObject css) /*-{
    	var baseImage = chart.renderer.image(source, x, y, width, height);
    	if (attr != null) {
    		baseImage.attr(attr);
    	}
    	if (css != null) {
    		baseImage.css(css);
    	}
    	return baseImage.add();
    }-*/;
    
    public JavaScriptObject addImageGen(JavaScriptObject chart, String source, float x, float y, float width, float height, JavaScriptObject attr, JavaScriptObject css) {
	return nativeAddImageGen(chart, source, x, y, width, height, attr, css);
    }
    
    private static native JavaScriptObject nativeAddArcGen(JavaScriptObject chart, float centerX, float centerY, float outerRadius, float innerRadius, float start, float end, JavaScriptObject attr, JavaScriptObject css) /*-{
    	var baseArc = chart.renderer.arc(centerX, centerY, outerRadius, innerRadius, start, end);
    	if (attr != null) {
    		baseArc.attr(attr);
    	}
    	if (css != null) {
    		baseArc.css(css);
    	}
    	return baseArc.add();
    }-*/;
    
    public JavaScriptObject addArcGen(JavaScriptObject chart, float centerX, float centerY, float outerRadius, float innerRadius, float start, float end, JavaScriptObject attr, JavaScriptObject css) {
	return nativeAddArcGen(chart, centerX, centerY, outerRadius, innerRadius, start, end, attr, css);
    }

    private static native JavaScriptObject nativeAddGroup(JavaScriptObject chart, String name) /*-{
	var baseGroup = chart.renderer.g(name);
	return baseGroup.add();
    }-*/;
    
    public JavaScriptObject addGroup(JavaScriptObject chart, String name) {
	return nativeAddGroup(chart, name);
    }
	
    private static native JavaScriptObject nativeAddRect(JavaScriptObject chart) /*-{
       return chart.renderer.rect(100, 100, 100, 100, 5)
            .attr({
                'stroke-width': 2,
                stroke: 'red',
                fill: 'yellow',
                zIndex: 3
            })
            .add();
    
	}-*/;

    public static native void removeAnnotation(JavaScriptObject annotation) /*-{
	annotation.element.remove();
    }-*/;

}
