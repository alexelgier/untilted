package com.alexelgier.untilted.pitch;

/**
 * Data Object for representation of chords. Extends PitchSet.
 * @author Alex Elgier
 *
 */
public class Chord extends PitchSet {

  public Chord(Chord chord) {
    super();
    chord.getStream().forEach(p -> addPitch(p));
  }

  public Chord() {
    super();
  }

  // TODO: make avg th method
  /**
   * Calculates the average Tenney Height of intervals in chord.
   * @return Average Tenney Height.
   */
  public double getAvgTenneyHeight() {
    return 0;
  }

  // TODO: make roughness method.
  /**
   * Calculates the average roughness of the chord.
   * @return The average roughness.
   */
  public double getRoughness() {
    return 0;
  }

  /**
   * Calculates average Tenney Height of a pitch relative to a chord.
   * @param pitch The pitch to be compared.
   * @return Average Tenney Height.
   */
  public double getAvgTenneyHeightFromPitch(Pitch pitch) {
    double sum = 0;
    int count = 0;
    Monzo m1 = pitch.getFactors();
    for (Pitch p: pitches) {
      sum += Monzo.monzoByPair(m1, p.getFactors()).getTenneyHeight();
      count++;
    }
    return sum / count;
  }

}
