package com.alexelgier.untilted.space;

public class Point {

  protected double xvalue;
  protected double yvalue;

  public Point(double xvalue, double yvalue) {
    this.xvalue = xvalue;
    this.yvalue = yvalue;
  }

  public double getX() {
    return xvalue;
  }

  public void setXvalue(double xvalue) {
    this.xvalue = xvalue;
  }

  public double getY() {
    return yvalue;
  }

  public void setYvalue(double yvalue) {
    this.yvalue = yvalue;
  }

}
