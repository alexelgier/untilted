package com.alexelgier.untilted.event;

import com.alexelgier.untilted.event.contour.Contour;
import com.alexelgier.untilted.event.rhythm.Rhythm;
import com.alexelgier.untilted.event.rhythm.RhythmFactory;
import com.alexelgier.untilted.pitch.Chord;
import com.alexelgier.untilted.pitch.Pitch;
import com.alexelgier.untilted.pitch.PitchFactory;

import java.io.IOException;

public class EventFactory {

  private PitchFactory pitchFactory;
  private RhythmFactory rhythmFactory;

  public EventFactory() throws IOException {
    pitchFactory = new PitchFactory();
    rhythmFactory = new RhythmFactory();
  }

  // TODO: Rewrite so twochordsequences are calculated against total chord.
  // OR delete ChordSequence and TwoChordSequence, in place a MultiChordEvent, and MultiChordSeq.
  // So as to generate each multichord sequentially, instead of a twochordseq on a chordseq.
  /**
   * Makes a variable number of ChordSequences and outputs them in a ChordScore.
   * @param start Start time.
   * @param chordDur Bar length.
   * @param count Number of bars.
   * @param contour Contour of Tenney Harmonic Index.
   * @param roots Root-groups involved in ChordScore, the first chord is assigned to downbeat.
   * @return A new ChordScore.
   */
  public ChordScore chordScore(double start, double chordDur, int count,
                               Contour contour, int...roots) {
    PitchSequence pitchSeq = pitchSequence(start, chordDur, count, roots[0], roots);
    ChordSequence singleSeq = chordSequence(pitchSeq, contour);
    ChordSequence totalSeq = new ChordSequence(1, start, singleSeq.end);
    singleSeq.getStream().forEach(c -> {
      Chord newChord = new Chord(c.getChord());
      totalSeq.add(chordEvent(newChord,c.getStart(), c.getDur()));
    } );
    TwoChordSequence[] twoSeqs = new TwoChordSequence[roots.length - 1];
    for (int i = 1; i < roots.length; i++) {
      twoSeqs[i - 1] = twoChordSequence(singleSeq, roots[i], contour);
      twoSeqs[i - 1].getStream().forEach(tc -> {
      
      
      });
    }
    ChordScore newScore = new ChordScore(singleSeq, twoSeqs, start, singleSeq.getEnd());
    return newScore;
  }

  private PitchEvent pitchEvent(Pitch pitch, double start, double dur) {
    return new PitchEvent(pitch, start, start + dur);
  }

  /**
   * Makes a PitchSequence.
   * @param start Start time.
   * @param pitchDur Pitch duration.
   * @param count Number of pitches.
   * @param root Root-group.
   * @param branches Variable amount of branches pitches will come from sequentially.
   * @return A new PitchSequence.
   */
  public PitchSequence pitchSequence(double start, double pitchDur, int count,
                                                     int root, int...branches) {
    Pitch newPitch;
    double totalDur = start + pitchDur * count * branches.length;
    PitchSequence pitchSeq = new PitchSequence(root, start, totalDur);
    double newStart = start;
    for (int i = 0; i < count; i++) {
      for (int p = 0; p < branches.length; p++) {
        newPitch = pitchFactory.getRandomRootPitch(root, branches[p]);
        pitchSeq.add(pitchEvent(newPitch,newStart,pitchDur));
        newStart += pitchDur;
      }
    }
    return pitchSeq;
  }

  private ChordEvent chordEvent(Chord chord, double start, double dur) {
    return new ChordEvent(chord, start, start + dur);
  }

  private TwoChordEvent twoChordEvent(Chord chord1, Chord chord2, double start, double dur) {
    Rhythm rhythm = rhythmFactory.getRhythm(start,dur);
    return new TwoChordEvent(chord1, chord2, rhythm, start, dur);
  }

  private ChordSequence chordSequence(PitchSequence pitchSeq, Contour contour) {
    ChordSequence chordSeq = new ChordSequence(pitchSeq.getRoot(), pitchSeq.getStart(),
                                                                    pitchSeq.getEnd());
    pitchSeq.getStream().forEach(p -> {
      Chord newChord = pitchFactory.getChord(p.getPitch(),contour.value(p.getStart()));
      chordSeq.add(chordEvent(newChord, p.getStart(), p.getDur()));
    });
    return chordSeq;
  }

  private TwoChordSequence twoChordSequence(ChordSequence chordSeq, int root, Contour contour) {
    TwoChordSequence newChordSeq = new TwoChordSequence(root, chordSeq.getStart(),
                                                              chordSeq.getEnd());
    chordSeq.getStream().forEach(c -> {
      double lessDissonant = contour.value(c.getStart());
      double moreDissonant = (1 - lessDissonant) /  3 + lessDissonant;
      Chord newChord = pitchFactory.getChord(c.getChord(), root, moreDissonant);
      Chord newChord2 = pitchFactory.getChord(c.getChord(), root, lessDissonant);
      newChordSeq.add(twoChordEvent(newChord, newChord2, c.start, c.dur));
    });
    return newChordSeq;
  }

  /*
  private TwoChordSequence twoChordSequence(ChordScore chordScore, int root, Contour contour) {
	    TwoChordSequence newChordSeq = new TwoChordSequence(root, chordScore.getStart(),
	                                                              chordScore.getEnd());
	    chordScore.getStream().forEach(c -> {
	      double lessDissonant = contour.value(c.getStart());
	      double moreDissonant = (1 - lessDissonant) /  3 + lessDissonant;
	      Chord newChord = pitchFactory.getChord(c.getChord(), root, moreDissonant);
	      Chord newChord2 = pitchFactory.getChord(c.getChord(), root, lessDissonant);
	      newChordSeq.add(twoChordEvent(newChord, newChord2, c.start, c.dur));
	    });
	    return newChordSeq;
	  }
  */
}
