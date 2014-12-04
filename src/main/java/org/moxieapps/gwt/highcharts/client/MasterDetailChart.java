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
import org.moxieapps.gwt.highcharts.client.plotOptions.AreaPlotOptions;
import org.moxieapps.gwt.highcharts.client.plotOptions.Marker.Symbol;
import org.moxieapps.gwt.highcharts.client.plotOptions.PiePlotOptions;
import org.moxieapps.gwt.highcharts.client.plotOptions.SeriesPlotOptions;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;

/**
 * This widget represents a zoomable chart. It allows you to control a display-only chart (detail chart)
 * with a selectable chart (master chart). A selection in the master chart refreshs the detail chart
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
 * final Chart detailChart = zoomableChart.getdetailChart();
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
 * detailChart.setChartTitleText("The title of my graph");
 * 
 * 	detailChart.getXAxis().setType(Axis.Type.DATE_TIME)
 * 		// .setTickInterval(24 * 3600 * 1000) // one week
 * 		.setTickWidth(0).setGridLineWidth(1).setAxisTitleText("My X axis").setOption("minRange", 3 * 24 * 3600 * 1000)
 * 		.setOption("minTickInterval", 24 * 3600 * 1000);
 * 
 * 	detailChart.getYAxis().setAxisTitleText("My Y axis")
 * 		.setLabels(new YAxisLabels().setAlign(Labels.Align.LEFT).setX(3).setY(16).setFormatter(new AxisLabelsFormatter() {
 * 		    public String format(AxisLabelsData axisLabelsData) {
 * 			return NumberFormat.getFormat("#,###").format(axisLabelsData.getValueAsDouble());
 * 		    }
 * 		})).setShowFirstLabel(false).setMaxZoom(0.1);
 * </code>
 * </pre>
 * 
 * @author sdiagne
 */
public class MasterDetailChart extends Composite implements IChart {

    private Chart detailChart;
    private Chart masterChart;
    private PlotBand beforePlotBand;
    private PlotBand afterPlotBand;
    private Number minXCharts;
    private Number maxXCharts;
    private Series[] initialSeries;

    private final Color PLOT_BAND_COLOR = new Color(0, 0, 0, 0.2);

    private FlowPanel zePanel;

    /**
     * creates a zoomable chart and inject the series in both the detail and the master chart
     * 
     * @param series
     */
    public MasterDetailChart(Series... series) {
	detailChart = new Chart();
	masterChart = new Chart();
	minXCharts = GREATEST_NUMBER;
	maxXCharts = NULL_NUMBER;
	initialSeries = new Series[series.length];
	System.arraycopy(series, 0, initialSeries, 0, series.length);
	for (int i = 0; i < series.length; i++) {
	    final Series currentSeries = series[i];
	    detailChart.addSeries(detailChart.createSeries().setName(currentSeries.getName()).setIndex(i).setPoints(currentSeries.getPoints())
		    .setPlotOptions(currentSeries.getPlotOptions()));
	    masterChart.addSeries(masterChart.createSeries().setName(currentSeries.getName()).setIndex(i).setPoints(currentSeries.getPoints())
		    .setPlotOptions(currentSeries.getPlotOptions()));
	    // detailChart.addSeries(detailChart.createSeries().setName("data" + i).setIndex(i).setPoints(currentSeries.getPoints()));
	    // masterChart.addSeries(masterChart.createSeries().setName("data" + i).setIndex(i).setPoints(currentSeries.getPoints()));
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
	SeriesPlotOptions plotOptions = new SeriesPlotOptions();
	enableLegendInteractionEvent(plotOptions);
	masterChart.setSeriesPlotOptions(plotOptions);

	zePanel = new FlowPanel();
	zePanel.add(detailChart);
	zePanel.add(masterChart);
	zePanel.setHeight("100%");
	initWidget(zePanel);
    }

    /**
     * return an array with the minimum and the maximum abscissa of the points of the series passed as a parameter
     * 
     * @param series
     *            the series
     * @return an array with the minimum and the maximum abscissa
     */
    private Number[] getMinMax(Series series) {
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
	detailChart.setType(Type.LINE);
	final Legend masterChartLegend = new Legend();
	final Legend detailChartLegend = new Legend();
	masterChartLegend.setEnabled(true);
	detailChartLegend.setEnabled(false);
	masterChart.setLegend(masterChartLegend);
	detailChart.setLegend(detailChartLegend);
	detailChart.setReflow(true);
	masterChart.setReflow(true);
	detailChart.setCredits(new Credits().setEnabled(false));
	masterChart.setCredits(new Credits().setEnabled(false));
	masterChart.getElement().setAttribute("style", "width:100%;height:35%");
	detailChart.getElement().setAttribute("style", "width:100%;height:65%");

    }

    /**
     * sets the width of the charts (the charts must have the same width)
     * 
     * @param width
     *            the width to set
     */
    public void setChartsWidth(Number width) {
	detailChart.setWidth(width);
	masterChart.setWidth(width);
    }

    /**
     * sets the height of the detail chart
     * 
     * @param height
     *            the height to set
     */
    public void setDetailChartHeight(Number height) {
	detailChart.setWidth(height);
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
     * @return the detailChart
     */
    public Chart getDetailChart() {
	return detailChart;
    }

    /**
     * @return the masterChart
     */
    public Chart getMasterChart() {
	return masterChart;
    }

    /**
     * handle the event of selection by drawing plot band in the master chart and refreshing the detail chart (the detail chart will display the
     * points
     * belonging to the selection)
     */
    private void enableSelectionEvent() {
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
     * a click on a item of the legend will hide/display the corresponding graph in the detail chart
     */
    public void enableLegendInteractionEvent(SeriesPlotOptions plotOptions) {
	// plotOptions.setMarker(new Marker().setEnabled(false));
	// plotOptions.setHoverStateEnabled(false);
	final SeriesLegendItemClickEventHandler legendItemClickEventHandler = new SeriesLegendItemClickEventHandler() {

	    @Override
	    public boolean onClick(SeriesLegendItemClickEvent event) {
		final int seriesIndex = masterChart.getSeries(event.getSeriesId()).getIndex();
		final boolean currentVisibility = masterChart.getSeries()[seriesIndex].isVisible();
		detailChart.getSeries()[seriesIndex].setVisible(!currentVisibility);
		masterChart.getSeries()[seriesIndex].setVisible(!currentVisibility);
		return false;
	    }
	};
	plotOptions.setSeriesLegendItemClickEventHandler(legendItemClickEventHandler);

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
    private void drawPointsInSeries(int index, double minX, double maxX) {
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
	detailChart.getSeries()[index].setPoints(pointsToDraw.toArray(new Point[pointsToDraw.size()]));
    }

    /**
     * draw bands before <code>minX</code> and after <code>maxX</code> to highlight the selection (the interval <code>[minX; maxX]</code>)
     * 
     * @param minX
     *            the minimum abscissa of the selection
     * @param maxX
     *            the maximum abscissa of the selection
     */
    private void drawPlotBands(double minX, double maxX) {
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

    @Override
    public IChart setColors(String... colors) {
	detailChart.setColors(colors);
	masterChart.setColors(colors);
	return this;
    }

    @Override
    public IChart setExporting(Exporting exporting) {
	detailChart.setExporting(exporting);
	masterChart.setExporting(exporting);
	return this;
    }

    @Override
    public IChart setHeight(Number height) {
	detailChart.setHeight((height.intValue() * 3) / 4);
	masterChart.setHeight(height.intValue() / 4);
	return this;
    }

    @Override
    public IChart setLoading(Loading loading) {
	detailChart.setLoading(loading);
	masterChart.setLoading(loading);
	return this;
    }

    @Override
    public IChart setMargin(Number marginTop, Number marginRight, Number marginBottom, Number marginLeft) {
	detailChart.setMargin(marginTop, marginRight, marginBottom, marginLeft);
	masterChart.setMargin(marginTop, marginRight, marginBottom, marginLeft);
	return this;
    }

    @Override
    public IChart setShadow(boolean shadow) {
	detailChart.setShadow(shadow);
	masterChart.setShadow(shadow);
	return this;
    }

    @Override
    public IChart setShowAxes(boolean showAxes) {
	detailChart.setShowAxes(showAxes);
	masterChart.setShowAxes(showAxes);
	return this;
    }

    @Override
    public IChart setSpacingTop(Number spacingTop) {
	detailChart.setSpacingTop(spacingTop);
	masterChart.setSpacingTop(spacingTop);
	return this;
    }

    @Override
    public IChart setSpacingRight(Number spacingRight) {
	detailChart.setSpacingRight(spacingRight);
	masterChart.setSpacingRight(spacingRight);
	return this;
    }

    @Override
    public IChart setSpacingBottom(Number spacingBottom) {
	detailChart.setSpacingBottom(spacingBottom);
	masterChart.setSpacingBottom(spacingBottom);
	return this;
    }

    @Override
    public IChart setSpacingLeft(Number spacingLeft) {
	detailChart.setSpacingLeft(spacingLeft);
	masterChart.setSpacingLeft(spacingLeft);
	return this;
    }

    @Override
    public IChart setStyle(Style style) {
	detailChart.setStyle(style);
	masterChart.setStyle(style);
	return this;
    }

    @Override
    public IChart setSymbols(Symbol... symbols) {
	detailChart.setSymbols(symbols);
	masterChart.setSymbols(symbols);
	return this;
    }

    @Override
    public void setTitle(String title) {
	detailChart.setTitle(title);
	masterChart.setTitle(null);
    }

    @Override
    public void setTitle(String title, String subtitle) {
	detailChart.setTitle(title, subtitle);
	masterChart.setTitle((String) null, (String) null);
    }

    @Override
    public IChart setToolTip(ToolTip toolTip) {
	detailChart.setToolTip(toolTip);
	masterChart.setToolTip(toolTip);
	return this;
    }

    @Override
    public IChart setType(Type type) {
	detailChart.setType(type);
	masterChart.setType(type);
	return this;
    }

    @Override
    public IChart setWidth(Number width) {
	detailChart.setWidth(width);
	masterChart.setWidth(width);
	return this;
    }

    @Override
    public IChart setSize(int width, int height) {
	masterChart.setSize(width, height * 35 / 100);
	detailChart.setSize(width, height * 65 / 100);
	return this;
    }

    @Override
    public void setSize(String width, String height) {
	super.setSize(width, height);
    }

    @Override
    public IChart showLoading(String message) {
	detailChart.showLoading(message);
	masterChart.showLoading(message);
	return this;
    }

    @Override
    public IChart redraw() {
	detailChart.redraw();
	masterChart.redraw();
	return this;
    }

    @Override
    public IChart setOption(String path, Object value) {
	detailChart.setOption(path, value);
	masterChart.setOption(path, value);
	return this;
    }

    @Override
    public IChart setAnimation(boolean animation) {
	detailChart.setAnimation(animation);
	masterChart.setAnimation(animation);
	return this;
    }

    @Override
    public XAxis getXAxis() {
	return detailChart.getXAxis();
    }

    @Override
    public YAxis getYAxis() {
	return detailChart.getYAxis();
    }

    @Override
    public IChart setLegend(Legend legend) {
	return this;
    }

    @Override
    public IChart addSeries(Series series) {
	detailChart.addSeries(series);
	masterChart.addSeries(series);
	return this;
    }

    @Override
    public IChart setSeriesPlotOptions(SeriesPlotOptions seriesPlotOptions) {
	detailChart.setSeriesPlotOptions(seriesPlotOptions);
	masterChart.setSeriesPlotOptions(seriesPlotOptions);
	return this;
    }

    @Override
    public IChart setBackgroundColor(String backgroundColor) {
	detailChart.setBackgroundColor(backgroundColor);
	masterChart.setBackgroundColor(backgroundColor);
	return this;
    }

    @Override
    public IChart setBackgroundColor(Color backgroundColor) {
	detailChart.setBackgroundColor(backgroundColor);
	masterChart.setBackgroundColor(backgroundColor);
	return this;
    }

    @Override
    public IChart setBorderWidth(Number borderWidth) {
	detailChart.setBorderWidth(borderWidth);
	masterChart.setBorderWidth(borderWidth);
	return this;
    }

    @Override
    public IChart setPiePlotOptions(PiePlotOptions piePlotOptions) {
	return this;
    }

    @Override
    public IChart setCredits(Credits credits) {
	detailChart.setCredits(credits);
	masterChart.setCredits(credits);
	return this;
    }

    @Override
    public IChart setAreaPlotOptions(AreaPlotOptions areaPlotOptions) {
	detailChart.setAreaPlotOptions(areaPlotOptions);
	masterChart.setAreaPlotOptions(areaPlotOptions);
	return this;
    }

    @Override
    public Series createSeries() {
	return null;
    }

    @Override
    public IChart setReflow(boolean reflow) {
	detailChart.setReflow(true);
	masterChart.setReflow(true);
	return this;
    }

}
