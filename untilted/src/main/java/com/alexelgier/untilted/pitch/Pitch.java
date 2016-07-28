package com.alexelgier.untilted.pitch;

/**
 * Data Object for representation of pitches.
 * @author Alex Elgier
 *
 */
public class Pitch {

  private int root;
  private int branch;
  private int leaf;
  private int string;
  private int harmonic;
  private double midi;
  private double frequency;
  private String instrument;
  private int instrumentIndex;
  private Monzo factors;

  /**
   * Default constructor.
   * @param instrument Name of instrument. (CB, CB1, VLA2, etc.)
   * @param root Harmonic of root-group. (1, 3, 5, 7, 11, 13)
   * @param string String of string instrument. (1-4)
   * @param branch Harmonic relative to root-group of string fundamental. (1, 3, 5, 7, 11, 13)
   * @param leaf Harmonic relative to string fundamental. (1-9)
   * @param harmonic Harmonic relative to common low C.
   * @param midi Midi pitch value.
   * @param frequency Frequency value.
   */
  public Pitch(String instrument, int root, int string, int branch, int leaf, int harmonic,
               double midi, double frequency) {
    this.instrument = instrument;
    this.root = root;
    this.string = string;
    this.branch = branch;
    this.leaf = leaf;
    this.harmonic = harmonic;
    this.frequency = frequency;
    this.midi = midi;
    this.factors = Monzo.monzoByHarmonic(harmonic);
  }

  /**
   * Constructor that copies a given pitch.
   * @param pitch The pitch to be copied.
   */
  public Pitch(Pitch pitch) {
    this.instrument = pitch.getInstrument();
    this.root = pitch.getRoot();
    this.string = pitch.getString();
    this.branch = pitch.getBranch();
    this.leaf = pitch.getLeaf();
    this.harmonic = pitch.getHarmonic();
    this.frequency = pitch.getFrequency();
    this.midi = pitch.getMidi();
    this.factors = Monzo.monzoByHarmonic(this.harmonic);
    if (pitch.isIndexed()) {
      this.instrumentIndex = pitch.getInstrumentIndex();
    }
  }

  public int getString() {
    return string;
  }

  public int getRoot() {
    return root;
  }

  public int getBranch() {
    return branch;
  }

  public int getLeaf() {
    return leaf;
  }

  public int getHarmonic() {
    return harmonic;
  }

  public double getMidi() {
    return midi;
  }

  public double getFrequency() {
    return frequency;
  }

  public String getInstrument() {
    return instrument;
  }

  public void setInstrumentIndex(int index) {
    instrumentIndex = index;
  }

  public int getInstrumentIndex() {
    return instrumentIndex;
  }

  public String getIndexedInstrument() {
    return instrument + String.valueOf(instrumentIndex);
  }

  public boolean isIndexed() {
    return this.instrumentIndex != 0;
  }

  public Monzo getFactors() {
    return factors;
  }

  public double getTenneyHeight() {
    return factors.getTenneyHeight();
  }

}




