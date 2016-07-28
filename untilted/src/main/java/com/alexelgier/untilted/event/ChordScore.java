package com.alexelgier.untilted.event;

public class ChordScore extends Event {

  private ChordSequence chordSeq;
  private TwoChordSequence[] twoChordSeqs;

  /**
   * Event that contains a Downbeat ChordSequence, and optional TwoChordSequences.
   * @param chordSeq The ChordSequence on downbeats.
   * @param twoSeqs The additional TwoChordSequences.
   * @param start Start time.
   * @param end End time
   */
  public ChordScore(ChordSequence chordSeq, TwoChordSequence[] twoSeqs, double start, double end) {
    super(start, end);
    this.chordSeq = chordSeq;
    this.twoChordSeqs = twoSeqs;
  }

  public ChordSequence getChordSeq() {
    return chordSeq;
  }

  public TwoChordSequence[] getTwoChordSeqs() {
    return twoChordSeqs;
  }

}
