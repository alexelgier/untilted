package com.alexelgier.untilted.space;

public class Centroid extends Point {

  private double deviance;
  private double range;

  public Centroid(double xvalue, double yvalue, double deviance, double range) {
    super(xvalue, yvalue);
    this.deviance = deviance;
    this.range = range;
  }

  public double getDeviance() {
    return deviance;
  }

  public double getRange() {
    return range;
  }

}
