package com.alexelgier.untilted.csound;

import com.alexelgier.untilted.event.ChordEvent;
import com.alexelgier.untilted.event.ChordScore;
import com.alexelgier.untilted.event.ChordSequence;
import com.alexelgier.untilted.event.PitchEvent;
import com.alexelgier.untilted.event.PitchSequence;
import com.alexelgier.untilted.event.TwoChordEvent;
import com.alexelgier.untilted.event.TwoChordSequence;
import com.alexelgier.untilted.pitch.Pitch;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Translates several objects into CSound language.
 * @author Alex Elgier
 *
 */
public class Translator {

  private static double AMP = 0.2;

  /** Translates a PitchSequence into CSound.
   * @param pitchSeq The PitchSequence.
   * @return A String with CSound data.
   */
  public String pitchSequence(PitchSequence pitchSeq) {
    Function<PitchEvent, String> pitchEventToString = new Function<PitchEvent, String>() {
      public String apply(PitchEvent pitchevent) {
        return "i1 " + pitchevent.getStart() + " "
                     + pitchevent.getDur() + " "
                     + pitchevent.getPitch().getFrequency() + " "
                     + AMP + String.format("%n");
      }
    };
    return pitchSeq.getStream().map(pitchEventToString).collect(Collectors.joining());
  }

  /**
   * Translates a ChordSequence into CSound.
   * @param chordSeq The ChordSequence.
   * @return A string with CSound data.
   */
  public String chordSequence(ChordSequence chordSeq) {
    Function<ChordEvent, String> chordEventToString = new Function<ChordEvent, String>() {
      public String apply(ChordEvent chord) {
        double start = chord.getStart();
        double dur = chord.getDur();
        Function<Pitch, String> pitchToString = new Function<Pitch, String>() {
          public String apply(Pitch pitch) {
            return "i1 " + String.valueOf(start) + " " + String.valueOf(dur) + " "
                         + String.valueOf(pitch.getFrequency()) + " "
                         + String.valueOf(AMP) + String.format("%n");
          }
        };
        return chord.getStream().map(pitchToString).collect(Collectors.joining());
      }
    };
    return chordSeq.getStream().map(chordEventToString).collect(Collectors.joining());
  }

  /**
   * Translates a TwoChordSequence into CSound.
   * @param twoChordSeq The TwoChordSequence.
   * @return A string with CSound data.
   */
  public String twoChordSequence(TwoChordSequence twoChordSeq) {
    Function<TwoChordEvent, String> twoChordEventToString = new Function<TwoChordEvent, String>() {
      public String apply(TwoChordEvent chord) {
        double start1 = chord.getStart1();
        double dur1 = chord.getDur1();
        Function<Pitch, String> pitchToString1 = new Function<Pitch, String>() {
          public String apply(Pitch pitch) {
            return "i1 " + String.valueOf(start1) + " " + String.valueOf(dur1) + " "
                         + String.valueOf(pitch.getFrequency()) + " "
                         + String.valueOf(AMP) + String.format("%n");
          }
        };
        String score = chord.getStream1().map(pitchToString1).collect(Collectors.joining());
        double start2 = chord.getStart2();
        double dur2 = chord.getDur2();
        Function<Pitch, String> pitchToString2 = new Function<Pitch, String>() {
          public String apply(Pitch pitch) {
            return "i1 " + String.valueOf(start2) + " " + String.valueOf(dur2) + " "
                         + String.valueOf(pitch.getFrequency()) + " "
                         + String.valueOf(AMP) + String.format("%n");
          }
        };
        score += chord.getStream2().map(pitchToString2).collect(Collectors.joining());
        return score;
      }
    };
    return twoChordSeq.getStream().map(twoChordEventToString).collect(Collectors.joining());
  }

  /**
   * Translates a ChordScore into CSound.
   * @param chordScore A ChordScore.
   * @return A String with CSound data.
   */
  public String chordScore(ChordScore chordScore) {
    String score = chordSequence(chordScore.getChordSeq());
    TwoChordSequence[] twoChordSeqs = chordScore.getTwoChordSeqs();
    for (int i = 0; i < twoChordSeqs.length; i++) {
      score = score + twoChordSequence(twoChordSeqs[i]);
    }
    return score;
  }

  public String finalScore(String score) throws IOException, URISyntaxException {
    return getHeader() + String.format("%n") + score + String.format("%n") + getFooter();
  }

  private String getHeader() throws IOException, URISyntaxException {
    URI headerPath = getClass().getClassLoader().getResource("csound/Header.txt").toURI();
    return new String(Files.readAllBytes(Paths.get(headerPath)));
  }

  private String getFooter() throws IOException, URISyntaxException {
    URI footerPath = getClass().getClassLoader().getResource("csound/Header.txt").toURI();
    return new String(Files.readAllBytes(Paths.get(footerPath)));
  }

}
