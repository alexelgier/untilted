package com.alexelgier.untilted.event.rhythm;

public class Rhythm {

  public static int LENGTH = 8;
  private int s1;
  private int s2;
  private int e1;
  private int e2;
  private double start;
  private double dur;

  /**
   * Default constructor.
   * @param s1 Start of event 1.
   * @param e1 End of event 1.
   * @param s2 Start of event 2.
   * @param e2 End of event 2.
   * @param start Start of bar.
   * @param dur Duration of bar.
   */
  public Rhythm(int s1, int e1, int s2, int e2, double start, double dur) {
    this.s1 = s1;
    this.e1 = e1;
    this.s2 = s2;
    this.e2 = e2;
    this.start = start;
    this.dur = dur;
  }

  public double getS1() {
    return start + dur / LENGTH * s1;
  }

  public double getS2() {
    return start + dur / LENGTH * s2;
  }

  public double getE1() {
    return start + dur / LENGTH * e1;
  }

  public double getE2() {
    return start + dur / LENGTH * e2;
  }
}
