package com.alexelgier.untilted.space;

import java.io.IOException;
import java.io.InputStreamReader;
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
    int xvalue;
    int yvalue;
    reader.readNext();
    while ((nextLine = reader.readNext()) != null) {
      name = nextLine[0].trim();
      root = Integer.parseInt(nextLine[1]);
      index = Integer.parseInt(nextLine[2]);
      xvalue = Integer.parseInt(nextLine[3]);
      yvalue = Integer.parseInt(nextLine[4]);
      key = name + index + root;
      newInstrument = new Instrument(name, root, index, xvalue, yvalue);
      this.instruments.put(key, newInstrument);
    }
    reader.close();
  }

  public InstrumentMap getChild(List<String> keys) {
    InstrumentMap newMap = new InstrumentMap();
    for (String key : keys) {
      newMap.addInstrument(key, this.instruments.get(key));;
    }
    return newMap;
  }

}
