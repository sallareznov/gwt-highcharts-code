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
import org.moxieapps.gwt.highcharts.client.events.ChartClickEventHandler;
import org.moxieapps.gwt.highcharts.client.events.ChartSelectionEvent;
import org.moxieapps.gwt.highcharts.client.events.ChartSelectionEventHandler;
import org.moxieapps.gwt.highcharts.client.events.ContextButtonClickHandler;
import org.moxieapps.gwt.highcharts.client.events.SeriesLegendItemClickEvent;
import org.moxieapps.gwt.highcharts.client.events.SeriesLegendItemClickEventHandler;
import org.moxieapps.gwt.highcharts.client.plotOptions.AreaPlotOptions;
import org.moxieapps.gwt.highcharts.client.plotOptions.Marker.Symbol;
import org.moxieapps.gwt.highcharts.client.plotOptions.PiePlotOptions;
import org.moxieapps.gwt.highcharts.client.plotOptions.SeriesPlotOptions;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NodeList;
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

    private FlowPanel container;

    /**
     * creates a zoomable chart and inject the series in both the detail and the master chart
     * 
     * @param series
     */
    public MasterDetailChart(Series... series) {
	detailChart = new Chart();
	masterChart = new Chart();
	minXCharts = BIGGEST_NUMBER;
	maxXCharts = SMALLEST_NUMBER;
	initialSeries = new Series[series.length];
	System.arraycopy(series, 0, initialSeries, 0, series.length);
	for (int i = 0; i < series.length; i++) {
	    final Series currentSeries = series[i];
	    detailChart.addSeries(detailChart.createSeries().setName(currentSeries.getName()).setIndex(i).setPoints(currentSeries.getPoints())
		    .setPlotOptions(currentSeries.getPlotOptions()));
	    masterChart.addSeries(masterChart.createSeries().setName(currentSeries.getName()).setIndex(i).setPoints(currentSeries.getPoints())
		    .setPlotOptions(currentSeries.getPlotOptions()));
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

	container = new FlowPanel();
	container.add(detailChart);
	container.add(masterChart);
	container.setHeight("100%");
	initWidget(container);
	// bug : obliged to call the setZoom() method, otherwise the graph doesn't render correctly on selection
	setZoom(minXCharts.doubleValue(), maxXCharts.doubleValue());
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
		detailChart.getXAxis().setEndOnTick(false);
		detailChart.getXAxis().setStartOnTick(false);
		detailChart.getXAxis().setExtremes(minX, maxX);

		reorderButtons();
		return false;
	    }
	});
    }

    private void reorderButtons() {
	final DivElement masterChartElement = masterChart.getDivElement();
	final NodeList<Element> gElements = masterChartElement.getElementsByTagName("g");
	List<Element> buttonElements = new ArrayList<Element>();
	for (int i = 0; i < gElements.getLength(); i++) {
	    final Element gElement = gElements.getItem(i);
	    if (gElement.getAttribute("class").equals("highcharts-button")) {
		buttonElements.add(gElement);
	    }
	}
	for (Element button : buttonElements) {
	    Element parent = button.getParentElement();
	    parent.removeChild(button);
	    parent.appendChild(button);
	}
    }

    /**
     * a click on a item of the legend will hide/display the corresponding graph in the detail chart
     */
    public void enableLegendInteractionEvent(SeriesPlotOptions plotOptions) {
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

    public void removePlotBands() {
	final Axis<?> xAxis = masterChart.getXAxis();
	xAxis.removePlotBand(beforePlotBand);
	xAxis.removePlotBand(afterPlotBand);
    }

    public void handleClick() {
	detailChart.getXAxis().setExtremes(minXCharts, maxXCharts);
	removePlotBands();
    }

    private static final Number SMALLEST_NUMBER = new Number() {

	private static final long serialVersionUID = 1L;

	@Override
	public long longValue() {
	    return Long.MIN_VALUE;
	}

	@Override
	public int intValue() {
	    return Integer.MIN_VALUE;
	}

	@Override
	public float floatValue() {
	    return Float.MIN_VALUE;
	}

	@Override
	public double doubleValue() {
	    return Double.MIN_VALUE;
	}
    };

    private static final Number BIGGEST_NUMBER = new Number() {

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
	final Exporting detailExporting = exporting.clone();
	final Exporting masterExporting = exporting.clone();

	masterExporting.addCustomButton("customButton",
		new ContextButton().setX(-30).setSymbol(ContextButton.Symbol.CIRCLE).setClickHandler(new ContextButtonClickHandler() {

		    @Override
		    public void onClick() {
			handleClick();
		    }
		}).setTitleKey("resetZoom"));

	detailChart.setExporting(detailExporting);
	masterChart.setExporting(masterExporting);
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

    public void setZoom(double minX, double maxX) {
	drawPlotBands(minX, maxX);
	detailChart.getXAxis().setEndOnTick(false);
	detailChart.getXAxis().setStartOnTick(false);
	detailChart.getXAxis().setExtremes(minX, maxX);
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

    @Override
    public Legend getLegend() {
	return masterChart.getLegend();
    }

    @Override
    public JavaScriptObject getNativeChart() {
	return detailChart.getNativeChart();
    }

    @Override
    public IChart setClickEventHandler(ChartClickEventHandler chartClickEventHandler) {
	return this;
    }

    @Override
    public Series[] getSeries() {
	return detailChart.getSeries();
    }

    @Override
    public SeriesPlotOptions getSeriesPlotOptions() {
	return detailChart.getSeriesPlotOptions();
    }

    /**
     * @return the exporting of the master chart
     */
    @Override
    public Exporting getExporting() {
	return masterChart.getExporting();
    }

}