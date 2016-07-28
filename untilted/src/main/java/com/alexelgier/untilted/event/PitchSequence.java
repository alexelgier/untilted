package com.alexelgier.untilted.event;

import java.util.ArrayList;
import java.util.stream.Stream;

public class PitchSequence extends Event {

  private int root;
  private ArrayList<PitchEvent> events;

  /**
   * Default constructor.
   * @param root Root-group value.
   * @param start Start time.
   * @param end End time.
   */
  public PitchSequence(int root, double start, double end) {
    super(start, end);
    this.root = root;
    events = new ArrayList<PitchEvent>();
  }

  public PitchEvent get(int index) {
    return events.get(index);
  }

  public void add(PitchEvent pitchEvent) {
    events.add(pitchEvent);
  }

  public int getRoot() {
    return root;
  }

  public Stream<PitchEvent> getStream() {
    return events.stream();
  }
}
