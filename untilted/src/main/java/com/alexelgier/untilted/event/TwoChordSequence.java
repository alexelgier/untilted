package com.alexelgier.untilted.event;

import java.util.ArrayList;
import java.util.stream.Stream;

public class TwoChordSequence extends Event {

  private int root;
  private ArrayList<TwoChordEvent> chords;

  /**
   * Default constructor.
   * @param root Root-group value.
   * @param start Start time.
   * @param end End time.
   */
  public TwoChordSequence(int root, double start, double end) {
    super(start, end);
    this.root = root;
    chords = new ArrayList<TwoChordEvent>();
  }

  public TwoChordEvent get(int index) {
    return chords.get(index);
  }

  public void add(TwoChordEvent twoChordEvent) {
    chords.add(twoChordEvent);
  }

  public int getRoot() {
    return root;
  }

  public Stream<TwoChordEvent> getStream() {
    return chords.stream();
  }
}

