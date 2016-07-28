package com.alexelgier.untilted.event;

/**
 * An abstract class for use in different implementations.
 * @author Alex Elgier
 *
 */
public abstract class Event {

  protected double start;
  protected double end;
  protected double dur;

  /**
   * Default constructor.
   * @param start Start of event.
   * @param end End of event.
   */
  public Event(double start, double end) {
    this.start = start;
    this.end = end;
    this.dur = end - start;
  }

  public double getStart() {
    return start;
  }

  public double getEnd() {
    return end;
  }

  public double getDur() {
    return dur;
  }

}
