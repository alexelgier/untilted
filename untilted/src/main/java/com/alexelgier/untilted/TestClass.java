package com.alexelgier.untilted;

import com.alexelgier.untilted.csound.Translator;
import com.alexelgier.untilted.event.ChordScore;
import com.alexelgier.untilted.event.EventFactory;
import com.alexelgier.untilted.event.contour.HermiteContour;
import com.alexelgier.untilted.event.contour.Value;
import com.alexelgier.untilted.space.Centroid;
import com.alexelgier.untilted.space.InstrumentMap;
import com.alexelgier.untilted.space.MainInstrumentMap;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class TestClass {

  /**
   * Main method.
   * @param args No use.
   */
  public static void main(String[] args) {
    try {
      //init factories and output objects
      EventFactory eventFactory = new EventFactory();
      Translator translator = new Translator();

      //generate music
      Value[] values = { new Value(0, 1),
                         new Value(72, 0.5),
                         new Value(216, 0.125) };
      HermiteContour contour = new HermiteContour(0, 108, values);
      ChordScore chordScore1 = eventFactory.chordScore(0, 6, 6, contour, 1, 3, 5, 7, 11, 13);

      //translate to csound
      String score = translator.chordScore(chordScore1);
      score = translator.finalScore(score);

      //translate to lilypond

      //output
      System.out.print(score);


      MainInstrumentMap map = new MainInstrumentMap();
      Centroid centroid = map.getCentroid();
      System.out.print("\nCentroid X: " + centroid.getX()
      + " - Y: " + centroid.getY()
      + " - Deviance: " + centroid.getDeviance()
      + " - Range: " + centroid.getRange());
      ArrayList<String> keys = new ArrayList<String>();
      chordScore1.getChordSeq().get(0).getChord().getStream()
                 .forEach(p -> keys.add(p.getKey()));
      InstrumentMap newMap = map.getChild(keys);
      centroid = newMap.getCentroid();
      System.out.print("\nCentroid X: " + centroid.getX()
      + " - Y: " + centroid.getY()
      + " - Deviance: " + centroid.getDeviance()
      + " - Range: " + centroid.getRange());

    } catch (IOException exception) {
      // TODO Auto-generated catch block
      exception.printStackTrace();
    } catch (URISyntaxException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}
