package com.alexelgier.untilted.space;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import com.opencsv.CSVReader;

public class InstrumentMap {

	private HashMap<String, Instrument> instruments;
	
	public InstrumentMap() throws NumberFormatException, IOException {
		InputStreamReader stream = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("space/locations.csv"));
	    CSVReader reader = new CSVReader(stream);
	    String[] nextLine;
	    this.instruments = new HashMap<String, Instrument>();
	    Instrument newInstrument;
	    String name;
	    String key;
	    int root;
	    int index;
	    int xvalue;
	    int yvalue;
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
	
	public Centroid getCentroid() {
		return null;
	}
	
}
