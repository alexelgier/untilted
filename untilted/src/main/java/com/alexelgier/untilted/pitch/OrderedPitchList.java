package com.alexelgier.untilted.pitch;

import java.util.ArrayList;
import java.util.List;

/**
 * Data object that keeps pitches in a list, with sorting methods.
 * @author Alex Elgier
 *
 */
public class OrderedPitchList {

  private ArrayList<Pitch> pitches;

  public OrderedPitchList(List<Pitch> list, int size) {
    this.pitches = new ArrayList<Pitch>(size);
    this.pitches.addAll(list);
  }

  public OrderedPitchList(List<Pitch> list) {
    this.pitches = new ArrayList<Pitch>();
    this.pitches.addAll(list);
  }

  public void addPitch(Pitch pitch) {
    pitches.add(pitch);
  }

  public Pitch getPitch(int index) {
    return pitches.get(index);
  }

  //TODO: check this
  /**
   * Returns the Pitch with index of (array size*dissonanceindex)
   * @param dissonanceIndex Dissonance index. (0.0 - 1.0)
   * @return A Pitch.
   */
  public Pitch getPitchByDissonanceIndex(double dissonanceIndex) {
    int index = (int)Math.rint((pitches.size() - 1) * dissonanceIndex);
    if (index > pitches.size() - 1) {
      index = pitches.size() - 1;
    } else if (index < 0) {
      index = 0;
    }
    return pitches.get(index);
  }

  public int size() {
    return pitches.size();
  }

  /**
   * Sorts according to individual pitches height.
   */
  public void sort() {
    pitches.sort((Pitch p1, Pitch p2)
        -> Double.compare(p1.getTenneyHeight(), p2.getTenneyHeight()));
  }

  /**
   * Sorts according to pitches avg. Tenney Height from chord.
   * @param chord The chord to be compared to.
   */
  public void sort(Chord chord) {
    pitches.sort((Pitch p1, Pitch p2)
        -> Double.compare(chord.getAvgTenneyHeightFromPitch(p1),
                          chord.getAvgTenneyHeightFromPitch(p2)));
  }

  /**
   * Sorts according to pitches Tenney Height from another pitch.
   * @param pitch The pitch to be compared to.
   */
  public void sort(Pitch pitch) {
    pitches.sort((Pitch p1, Pitch p2)
        -> Double.compare(Monzo.monzoByPair(p1.getFactors(), pitch.getFactors()).getTenneyHeight(),
                        Monzo.monzoByPair(p2.getFactors(), pitch.getFactors()).getTenneyHeight()));
  }

}
