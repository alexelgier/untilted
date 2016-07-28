package com.alexelgier.untilted.pitch;

import java.util.HashSet;
import java.util.stream.Stream;

/**
 * Data Object that keeps pitches in a HashSet.
 * @author Alex Elgier
 *
 */
public class PitchSet {

  protected HashSet<Pitch> pitches;

  public PitchSet() {
    this.pitches = new HashSet<Pitch>();
  }

  public void addPitch(Pitch pitch) {
    pitches.add(pitch);
  }

  public Stream<Pitch> getStream() {
    return pitches.stream();
  }

  public int size() {
    return pitches.size();
  }

}
