package com.alexelgier.untilted.event.contour;

import org.apache.commons.math3.analysis.interpolation.HermiteInterpolator;

public class HermiteContour extends Contour {

  private HermiteInterpolator interp;

  /**
   * Contour that uses Hermite interpolation, with derivative of first and last element set to 0.
   * @param start Start time.
   * @param end End time.
   * @param values Array of Values.
   */
  public HermiteContour(double start, double end, Value[] values) {
    super(start, end, values);
    this.interp = new HermiteInterpolator();
    this.loadValues();
  }

  @Override
  public double value(double xvalue) {
    double[] values = interp.value(xvalue);
    return values[0];
  }

  private void loadValues() {
    double xvalue;
    double[] yvalue;
    double[] derivative;
    xvalue = values.get(0).getXValue();
    yvalue = new double[] { values.get(0).getYValue() };
    derivative = new double [] { 0.0 };
    interp.addSamplePoint(xvalue, yvalue, derivative);
    for (int i = 1; i < values.size() - 1; i++) {
      xvalue = values.get(i).getXValue();
      yvalue = new double [] { values.get(i).getYValue() };
      interp.addSamplePoint(xvalue, yvalue);
    }
    xvalue = values.get(values.size() - 1).getXValue();
    yvalue = new double [] { values.get(values.size() - 1).getYValue() };
    interp.addSamplePoint(xvalue, yvalue, derivative);
  }

}
