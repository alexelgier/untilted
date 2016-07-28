package com.alexelgier.untilted.event.contour;

import org.apache.commons.math3.analysis.interpolation.LinearInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;

public class LineContour extends Contour {

  private PolynomialSplineFunction function;

  /**
   * Contour that interpolates linearly.
   * @param start Start time.
   * @param end End time.
   * @param values Array of Values.
   */
  public LineContour(double start, double end, Value[] values) {
    super(start, end, values);
    LinearInterpolator interp = new LinearInterpolator();
    this.function = interp.interpolate(getXValues(), getYValues());
  }

  @Override
  public double value(double xvalue) {
    return function.value(xvalue);
  }

}
