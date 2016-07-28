package com.alexelgier.untilted.event;

import com.alexelgier.untilted.event.rhythm.Rhythm;
import com.alexelgier.untilted.pitch.Chord;
import com.alexelgier.untilted.pitch.Pitch;

import java.util.stream.Stream;

/**
 * An event that has two chords arranged in a rhythm.
 * @author Alex Elgier
 *
 */
public class TwoChordEvent extends Event {

  private Rhythm rhythm;
  private Chord chord1;
  private Chord chord2;

  /**
   * Default constructor.
   * @param c1 First chord.
   * @param c2 Second chord.
   * @param rhythm The rhythm positioning c1 and c2.
   * @param start Start of event (not start of c1)
   * @param end End of event (not end of c2)
   */
  public TwoChordEvent(Chord c1, Chord c2, Rhythm rhythm, double start, double end) {
    super(start, end);
    this.chord1 = c1;
    this.chord2 = c2;
    this.rhythm = rhythm;
  }

  public Stream<Pitch> getStream1() {
    return chord1.getStream();
  }

  public Stream<Pitch> getStream2() {
    return chord2.getStream();
  }

  public double getStart1() {
    return rhythm.getS1();
  }

  public double getStart2() {
    return rhythm.getS2();
  }

  public double getEnd1() {
    return rhythm.getE1();
  }

  public double getEnd2() {
    return rhythm.getE2();
  }

  public double getDur1() {
    return getEnd1() - getStart1();
  }

  public double getDur2() {
    return getEnd2() - getStart2();
  }

}
