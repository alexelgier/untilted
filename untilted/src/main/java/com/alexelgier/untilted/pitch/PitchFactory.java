package com.alexelgier.untilted.pitch;

import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class PitchFactory {

  private PitchSet pitchSet;

  /**
   * Default constructor. Fills own PitchSet from .csv file.
   * @throws IOException When "Cuerdas - PArray.csv" is not found.
   */
  public PitchFactory() throws IOException {
    String inst;
    int root;
    int string;
    int branch;
    int leaf;
    int harmonic;
    double midi;
    double frequency;
    InputStreamReader stream = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("pitch/allPitches.csv"));
    CSVReader reader = new CSVReader(stream);
    String[] nextLine;
    this.pitchSet = new PitchSet();
    while ((nextLine = reader.readNext()) != null) {
      inst = nextLine[0].trim();
      root = Integer.parseInt(nextLine[1]);
      string = Integer.parseInt(nextLine[2]);
      branch = Integer.parseInt(nextLine[3]);
      leaf = Integer.parseInt(nextLine[4]);
      harmonic = Integer.parseInt(nextLine[5]);
      midi = Double.parseDouble(nextLine[6]);
      frequency = Double.parseDouble(nextLine[7]);
      this.pitchSet.addPitch(new Pitch(inst,root,string,branch,leaf,harmonic,midi,frequency));
    }
    reader.close();
  }

  /**
   * Generates a chord for a root-group one instrument at a time in a random order.
   * @param pitch The first pitch used in building the chord.
   * @param dissonanceIndex Tenney Harmonic Index. (0.0 - 1.0)
   * @return A new Chord.
   */
  public Chord getChord(Pitch pitch, double dissonanceIndex) {
    ArrayList<String> instruments = new ArrayList<String>(Arrays.asList("CB1", "CB2",
                                      "VCL1", "VCL2", "VLA1", "VLA2", "VLN1", "VLN2"));
    Pitch newPitch = new Pitch(pitch);
    newPitch.setInstrumentIndex(1);
    instruments.remove(newPitch.getIndexedInstrument());
    Chord newChord = new Chord();
    newChord.addPitch(newPitch);

    OrderedPitchList pitchList;
    Random random = new Random();
    String instrument;
    int index;
    int root = pitch.getRoot();
    while (instruments.size() > 0) {
      instrument = instruments.get(random.nextInt(instruments.size()));
      instruments.remove(instrument);
      index = Character.getNumericValue(instrument.charAt(instrument.length() - 1));
      instrument = instrument.substring(0, instrument.length() - 1);
      pitchList = getOrderedInstPitchList(instrument, root, newChord);
      newPitch = new Pitch(pitchList.getPitchByDissonanceIndex(dissonanceIndex));
      newPitch.setInstrumentIndex(index);
      newChord.addPitch(newPitch);
    }
    return newChord;
  }

  /**
   * Generates a chord for a Root-group in relation to another chord.
   * @param chord The chord to be built upon.
   * @param root Root-group of the new chord.
   * @param dissonanceIndex Tenney Harmonic Index. (0.0 - 1.0)
   * @return A new Chord.
   */
  public Chord getChord(Chord chord, int root, double dissonanceIndex) {
    ArrayList<String> instruments = new ArrayList<String>(Arrays.asList("CB1", "CB2",
                                    "VCL1", "VCL2", "VLA1", "VLA2", "VLN1", "VLN2"));
    int index;
    String instrument;
    Pitch newPitch;
    OrderedPitchList pitchList;
    Random random = new Random();
    Chord totalChord = new Chord(chord);
    Chord newChord = new Chord();
    while (instruments.size() > 0) {
      instrument = instruments.get(random.nextInt(instruments.size()));
      instruments.remove(instrument);
      index = Character.getNumericValue(instrument.charAt(instrument.length() - 1));
      instrument = instrument.substring(0, instrument.length() - 1);
      pitchList = getOrderedInstPitchList(instrument, root, totalChord);
      newPitch = new Pitch(pitchList.getPitchByDissonanceIndex(dissonanceIndex));
      newPitch.setInstrumentIndex(index);
      newChord.addPitch(newPitch);
      totalChord.addPitch(newPitch);
    }
    return newChord;
  }

  private Pitch getPitchWalk(Pitch source) {
    List<Pitch> candidates = pitchSet.getStream()
                  .filter(pitch -> source.getRoot() == pitch.getRoot())
                  .filter(pitch -> source.getInstrument().equalsIgnoreCase(pitch.getInstrument()))
                  .filter(pitch -> ( Math.abs(source.getLeaf() - pitch.getLeaf()) == 1
                                     && source.getString() == pitch.getString())
                                     || ( Math.abs(source.getString() - pitch.getString()) == 1
                                     && source.getLeaf() == pitch.getLeaf()))
                  .collect(Collectors.toList());
    Random random = new Random();
    Pitch newPitch = new Pitch(candidates.get(random.nextInt(candidates.size())));
    newPitch.setInstrumentIndex(source.getInstrumentIndex());
    return newPitch;
  }

  /**
   * Returns a new Chord one step away from input.
   * @param chord The input chord.
   * @return A new Chord.
   */
  public Chord getChordWalk(Chord chord) {
    Chord newChord = new Chord();
    chord.getStream()
         .map(p -> getPitchWalk(p))
         .forEach(p -> newChord.addPitch(p));
    return newChord;
  }

  /**
   * Picks a simple note in relation to a branch from a Root-group.
   * @param root Root-group pitch will belong to.
   * @param branch Branch pitch will belong to.
   * @return A pitch.
   */
  public Pitch getRandomRootPitch(int root, int branch) {
    List<Pitch> array = pitchSet.getStream()
                            .filter(p -> (p.getRoot() == root) && (p.getBranch() == branch))
                            .collect(Collectors.toList());
    OrderedPitchList pitchList = new OrderedPitchList(array);
    pitchList.sort();
    Random random = new Random();
    return pitchList.getPitchByDissonanceIndex(random.nextDouble() / 2);
  }

  private OrderedPitchList getOrderedInstPitchList(String inst, int root, Chord chord) {
    List<Pitch> list = pitchSet.getStream()
              .filter(p -> inst.equalsIgnoreCase(p.getInstrument()) && p.getRoot() == root)
              .collect(Collectors.toList());
    OrderedPitchList pitchList = new OrderedPitchList(list,36);
    pitchList.sort(chord);
    return pitchList;
  }

}
