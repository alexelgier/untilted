package com.alexelgier.untilted.space;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;

import com.opencsv.CSVReader;

public class MainInstrumentMap extends InstrumentMap {

  public MainInstrumentMap() throws NumberFormatException, IOException {
    super();
    InputStreamReader stream = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("space/locations.csv"));
    CSVReader reader = new CSVReader(stream);
    String[] nextLine;
    Instrument newInstrument;
    String name;
    String key;
    int root;
    int index;
    double xvalue;
    double yvalue;
    reader.readNext();  //skip header line
    while ((nextLine = reader.readNext()) != null) {
      name = nextLine[0].trim();
      index = Integer.parseInt(nextLine[1]);
      root = Integer.parseInt(nextLine[2]);
      xvalue = Double.parseDouble(nextLine[3]);
      yvalue = Double.parseDouble(nextLine[4]);
      //Scale coordinates to 1 = 0.5m
      xvalue = xvalue * 0.5;
      yvalue = yvalue * 0.5;
      key = name + index + root;
      newInstrument = new Instrument(name, root, index, xvalue, yvalue);
      this.instruments.put(key, newInstrument);
    }
    reader.close();
  }

  public InstrumentMap getChild(List<String> keys) throws NullPointerException {
    HashMap<String, Instrument> newMap = new HashMap<String, Instrument>();
    for (String key : keys) {
      newMap.put(key, instruments.get(key));
    }
    return new InstrumentMap(newMap);
  }

}
