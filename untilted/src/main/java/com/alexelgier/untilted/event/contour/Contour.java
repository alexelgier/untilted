package com.alexelgier.untilted.event.contour;

import org.apache.commons.math3.exception.NumberIsTooSmallException;

import com.alexelgier.untilted.event.Event;

import java.util.Arrays;
import java.util.List;



/**
 * Abstract class for use in different types of contours.
 * @author Alex Elgier
 *
 */
public abstract class Contour extends Event {

  protected List<Value> values;

  /**
   * Default constructor.
   * @param start Start time.
   * @param end End time.
   * @param values Array of Values. (minimum 2 values)
   */
  public Contour(double start, double end, Value[] values) throws NumberIsTooSmallException {
    super(start, end);
    if (values.length <= 1) {
      throw new NumberIsTooSmallException(values.length, 1, false);
    }
    this.values = Arrays.asList(values);
  }

  public abstract double value(double xvalue);

  protected double[] getXValues() {
    return values.stream().mapToDouble(v -> v.getXValue()).toArray();
  }

  protected double[] getYValues() {
    return values.stream().mapToDouble(v -> v.getYValue()).toArray();
  }

}
