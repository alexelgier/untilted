package com.alexelgier.untilted.event;

import java.util.ArrayList;
import java.util.stream.Stream;

public class ChordSequence extends Event {

  private int root;
  private ArrayList<ChordEvent> chords;

  /**
   * Default constructor.
   * @param root Root-group value.
   * @param start Start time.
   * @param end End time.
   */
  public ChordSequence(int root, double start, double end) {
    super(start, end);
    this.root = root;
    chords = new ArrayList<ChordEvent>();
  }

  public ChordEvent get(int index) {
    return chords.get(index);
  }

  public void add(ChordEvent chordEvent) {
    chords.add(chordEvent);
  }

  public int getRoot() {
    return root;
  }

  public Stream<ChordEvent> getStream() {
    return chords.stream();
  }
}
