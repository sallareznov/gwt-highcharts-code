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

import java.util.ArrayList;
import java.util.List;

import org.moxieapps.gwt.highcharts.client.BaseChart.ZoomType;
import org.moxieapps.gwt.highcharts.client.Series.Type;
import org.moxieapps.gwt.highcharts.client.events.ChartSelectionEvent;
import org.moxieapps.gwt.highcharts.client.events.ChartSelectionEventHandler;
import org.moxieapps.gwt.highcharts.client.events.SeriesLegendItemClickEvent;
import org.moxieapps.gwt.highcharts.client.events.SeriesLegendItemClickEventHandler;
import org.moxieapps.gwt.highcharts.client.plotOptions.Marker;
import org.moxieapps.gwt.highcharts.client.plotOptions.SeriesPlotOptions;

import com.google.gwt.user.client.ui.Widget;

/**
 * This widget represents a zoomable chart. It allows you to control a display-only chart (slave chart)
 * with a selectable chart (master chart). A selection in the master chart refreshs the slave chart
 * Basic usage is as follows:
 * 
 * <pre>
 * <code>
 * Series series1 = new Series(null);
 * Series series2 = new Series(null);
 * series1.setPoints(getData1());
 * series2.setPoints(getData2());
 * final ZoomableChart zoomableChart = new ZoomableChart(series1, series2);
 * final Chart masterChart = zoomableChart.getMasterChart();
 * final Chart slaveChart = zoomableChart.getSlaveChart();
 * masterChart.getXAxis().setType(Axis.Type.DATE_TIME).setTickInterval(7 * 24 * 3600 * 1000) // one week
 * 		.setTickWidth(0).setGridLineWidth(1).setMaxZoom(1 * 24 * 3600 * 1000);
 * 
 * 	masterChart.getYAxis().setAxisTitleText(null)
 * 		.setLabels(new YAxisLabels().setAlign(Labels.Align.LEFT).setEnabled(false).setX(3).setY(16).setFormatter(new AxisLabelsFormatter() {
 * 		    public String format(AxisLabelsData axisLabelsData) {
 * 			return NumberFormat.getFormat("#,###").format(axisLabelsData.getValueAsDouble());
 * 		    }
 * 		})).setShowFirstLabel(false).setShowFirstLabel(false);
 * 
 * slaveChart.setChartTitleText("The title of my graph");
 * 
 * 	slaveChart.getXAxis().setType(Axis.Type.DATE_TIME)
 * 		// .setTickInterval(24 * 3600 * 1000) // one week
 * 		.setTickWidth(0).setGridLineWidth(1).setAxisTitleText("My X axis").setOption("minRange", 3 * 24 * 3600 * 1000)
 * 		.setOption("minTickInterval", 24 * 3600 * 1000);
 * 
 * 	slaveChart.getYAxis().setAxisTitleText("My Y axis")
 * 		.setLabels(new YAxisLabels().setAlign(Labels.Align.LEFT).setX(3).setY(16).setFormatter(new AxisLabelsFormatter() {
 * 		    public String format(AxisLabelsData axisLabelsData) {
 * 			return NumberFormat.getFormat("#,###").format(axisLabelsData.getValueAsDouble());
 * 		    }
 * 		})).setShowFirstLabel(false).setMaxZoom(0.1);
 * </code>
 * </pre>
 * @author sdiagne
 */
public class MasterDetailChart extends Widget {

    private Chart slaveChart;
    private Chart masterChart;
    private PlotBand beforePlotBand;
    private PlotBand afterPlotBand;
    private Number minXCharts;
    private Number maxXCharts;
    private Series[] initialSeries;

    private final Color PLOT_BAND_COLOR = new Color(0, 0, 0, 0.2);

    /**
     * creates a zoomable chart and inject the series in both the slave and the master chart
     * 
     * @param series
     */
    public MasterDetailChart(Series... series) {
	slaveChart = new Chart();
	masterChart = new Chart();
	minXCharts = GREATEST_NUMBER;
	maxXCharts = NULL_NUMBER;
	initialSeries = new Series[series.length];
	System.arraycopy(series, 0, initialSeries, 0, series.length);
	for (int i = 0; i < series.length; i++) {
	    final Series currentSeries = series[i];
	    slaveChart.addSeries(slaveChart.createSeries().setName("data" + i).setIndex(i).setPoints(currentSeries.getPoints()).setPlotOptions(currentSeries.getPlotOptions()));
	    masterChart.addSeries(masterChart.createSeries().setName("data" + i).setIndex(i).setPoints(currentSeries.getPoints()).setPlotOptions(currentSeries.getPlotOptions()));
	    final Number[] minMaxSeries = getMinMax(currentSeries);
	    if (minMaxSeries[0].doubleValue() < minXCharts.doubleValue()) {
		minXCharts = minMaxSeries[0];
	    }
	    if (minMaxSeries[1].doubleValue() > maxXCharts.doubleValue()) {
		maxXCharts = minMaxSeries[1];
	    }
	}
	configureCharts();
	enableSelectionEvent();
	enableLegendInteractionEvent();
    }

    /**
     * return an array with the minimum and the maximum abscissa of the points of the series passed as a parameter
     * 
     * @param series
     *            the series
     * @return an array with the minimum and the maximum abscissa
     */
    public Number[] getMinMax(Series series) {
	final Number[] minMax = new Number[2];
	final Point[] points = series.getPoints();
	Number min = points[0].getX();
	Number max = points[0].getX();
	for (int i = 1; i < points.length; i++) {
	    final Point currentPoint = points[i];
	    if (currentPoint.getX().doubleValue() < min.doubleValue()) {
		min = currentPoint.getX();
	    }
	    if (currentPoint.getX().doubleValue() > max.doubleValue()) {
		max = currentPoint.getX();
	    }
	}
	minMax[0] = min;
	minMax[1] = max;
	return minMax;
    }

    private void configureCharts() {
	masterChart.setZoomType(ZoomType.X);
	masterChart.setType(Type.LINE);
	slaveChart.setType(Type.LINE);
	final Legend masterChartLegend = new Legend();
	final Legend slaveChartLegend = new Legend();
	masterChartLegend.setEnabled(true);
	slaveChartLegend.setEnabled(false);
	masterChart.setLegend(masterChartLegend);
	slaveChart.setLegend(slaveChartLegend);
    }

    /**
     * sets the width of the charts (the charts must have the same width)
     * 
     * @param width
     *            the width to set
     */
    public void setChartsWidth(Number width) {
	slaveChart.setWidth(width);
	masterChart.setWidth(width);
    }

    /**
     * sets the height of the slave chart
     * 
     * @param height
     *            the height to set
     */
    public void setSlaveChartHeight(Number height) {
	slaveChart.setWidth(height);
    }

    /**
     * sets the height of the master chart
     * 
     * @param height
     *            the height
     */
    public void setMasterChartHeight(Number height) {
	masterChart.setHeight(height);
    }

    /**
     * @return the slaveChart
     */
    public Chart getSlaveChart() {
	return slaveChart;
    }

    /**
     * @return the masterChart
     */
    public Chart getMasterChart() {
	return masterChart;
    }

    /**
     * handle the event of selection by drawing plot band in the master chart and refreshing the slave chart (the slave chart will display the points
     * belonging to the selection)
     */
    public void enableSelectionEvent() {
	masterChart.setSelectionEventHandler(new ChartSelectionEventHandler() {

	    @Override
	    public boolean onSelection(ChartSelectionEvent event) {
		final double minX = event.getXAxisMin();
		final double maxX = event.getXAxisMax();

		drawPlotBands(minX, maxX);

		for (int i = 0; i < initialSeries.length; i++) {
		    drawPointsInSeries(i, minX, maxX);
		}

		return false;
	    }
	});
    }

    /**
     * a click on a item of the legend will hide/display the corresponding graph in the slave chart
     */
    public void enableLegendInteractionEvent() {
	final SeriesPlotOptions plotOptions = new SeriesPlotOptions();
	plotOptions.setMarker(new Marker().setEnabled(false));
	plotOptions.setHoverStateEnabled(false);
	final SeriesLegendItemClickEventHandler legendItemClickEventHandler = new SeriesLegendItemClickEventHandler() {

	    @Override
	    public boolean onClick(SeriesLegendItemClickEvent event) {
		final int seriesIndex = masterChart.getSeries(event.getSeriesId()).getIndex();
		final boolean currentVisibility = masterChart.getSeries()[seriesIndex].isVisible();
		slaveChart.getSeries()[seriesIndex].setVisible(!currentVisibility);
		masterChart.getSeries()[seriesIndex].setVisible(!currentVisibility);
		return false;
	    }
	};
	plotOptions.setSeriesLegendItemClickEventHandler(legendItemClickEventHandler);

	masterChart.setSeriesPlotOptions(plotOptions);
    }

    /**
     * draw the points of the serie <code>index</code> in the interval <code>[minX; maxX]</code>
     * 
     * @param index
     *            the index
     * @param minX
     *            the minimum abscissa of the selection
     * @param maxX
     *            the maximum abscissa of the selection
     */
    public void drawPointsInSeries(int index, double minX, double maxX) {
	final Series series = initialSeries[index];
	final Point[] seriesPoints = series.getPoints();
	final List<Point> pointsToDraw = new ArrayList<Point>();
	int lastIndex = 0;
	// nearestIndex and diff serve to spot the nearest point if your selection doesn't have any
	int nearestIndex = 0;
	double diff = GREATEST_NUMBER.doubleValue();
	for (int i = 0; i < seriesPoints.length; i++) {
	    final Point point = seriesPoints[i];
	    if ((point.getX().doubleValue() >= minX) && (point.getX().doubleValue() <= maxX)) {
		pointsToDraw.add(point);
		lastIndex = i;
	    } else {
		final double currentDifference = point.getX().doubleValue() - maxX;
		if (currentDifference > 0 && currentDifference < diff) {
		    diff = currentDifference;
		    nearestIndex = i;
		}
	    }
	}

	if (pointsToDraw.size() <= 3) {
	    if (pointsToDraw.size() == 0) {
		lastIndex = nearestIndex;
	    }
	    pointsToDraw.clear();
	    int stepRight = 1;
	    int stepLeft = -1;

	    if (lastIndex + stepLeft < 0) {
		pointsToDraw.add(seriesPoints[lastIndex + stepRight]);
		stepRight++;
	    } else {
		pointsToDraw.add(0, seriesPoints[lastIndex + stepLeft]);
		stepLeft--;
	    }

	    if (lastIndex - 2 < 0) {
		pointsToDraw.add(seriesPoints[lastIndex + stepRight]);
		stepRight++;
	    } else {
		pointsToDraw.add(0, seriesPoints[lastIndex - 2]);
		stepLeft--;
	    }

	    pointsToDraw.add(seriesPoints[lastIndex]);

	    if (lastIndex + stepRight >= seriesPoints.length) {
		pointsToDraw.add(0, seriesPoints[lastIndex + stepLeft]);
		stepLeft--;
	    } else {
		pointsToDraw.add(seriesPoints[lastIndex + stepRight]);
	    }
	}
	slaveChart.getSeries()[index].setPoints(pointsToDraw.toArray(new Point[pointsToDraw.size()]));
    }

    /**
     * draw bands before <code>minX</code> and after <code>maxX</code> to highlight the selection (the interval <code>[minX; maxX]</code>)
     * 
     * @param minX
     *            the minimum abscissa of the selection
     * @param maxX
     *            the maximum abscissa of the selection
     */
    public void drawPlotBands(double minX, double maxX) {
	final Axis<?> xAxis = masterChart.getXAxis();

	// the band before the selection in the master chart
	if (beforePlotBand != null) {
	    xAxis.removePlotBand(beforePlotBand);
	}
	beforePlotBand = xAxis.createPlotBand();
	beforePlotBand.setFrom(minXCharts);
	beforePlotBand.setTo(minX);
	beforePlotBand.setColor(PLOT_BAND_COLOR);

	// the band after the selection in the master chart
	if (afterPlotBand != null) {
	    xAxis.removePlotBand(afterPlotBand);
	}
	afterPlotBand = xAxis.createPlotBand();
	afterPlotBand.setFrom(maxX);
	afterPlotBand.setTo(maxXCharts);
	afterPlotBand.setColor(PLOT_BAND_COLOR);

	xAxis.addPlotBands(beforePlotBand, afterPlotBand);
    }

    final Number NULL_NUMBER = new Number() {

	private static final long serialVersionUID = 1L;

	@Override
	public long longValue() {
	    return 0;
	}

	@Override
	public int intValue() {
	    return 0;
	}

	@Override
	public float floatValue() {
	    return 0;
	}

	@Override
	public double doubleValue() {
	    return 0;
	}
    };

    final Number GREATEST_NUMBER = new Number() {

	private static final long serialVersionUID = 1L;

	@Override
	public long longValue() {
	    return Long.MAX_VALUE;
	}

	@Override
	public int intValue() {
	    return Integer.MAX_VALUE;
	}

	@Override
	public float floatValue() {
	    return Float.MAX_VALUE;
	}

	@Override
	public double doubleValue() {
	    return Double.MAX_VALUE;
	}
    };

}
