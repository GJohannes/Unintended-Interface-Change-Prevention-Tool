package Miscellaneous;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import CustomExceptions.InvalidPathsInsideConfigurationException;
import InputOutput.LoadAndSave;

public class CompareBinaries {

	
	public void compareCurrentBinariesToAlreadyStoredOnes() throws IOException, ParseException, InvalidPathsInsideConfigurationException {
		LoadAndSave loadSave = new LoadAndSave();
		ArrayList<JSONObject> allOldBinaries = loadSave.loadBinaries();
		ArrayList<Path> allPaths = loadSave.loadPaths();

		//precondition is that the same number of old interface binaries and number of paths to configuration files is there
		if(allOldBinaries.size() != allPaths.size()) {
			if(allOldBinaries.size() > allPaths.size()) {
				//notify that there are stored binaries that are no longer been referenced in the config file
			} else {
				//notify that there are paths that have no old binary value and are therefore new
			}
			//TODO Set run false
			System.out.println("set run false");
			return;
		}
		
		
		for(int i = 0; i < allOldBinaries.size(); i++) {
			inner: for(int j = 0; j < allPaths.size(); j++) {
				System.out.println(allPaths.get(j));
				if(allOldBinaries.get(i).containsKey(allPaths.get(j).getFileName().toString())) {
					JSONArray binaryOfOldValue = (JSONArray) allOldBinaries.get(i).get(allPaths.get(j).getFileName().toString());
					JSONArray binaryOfCurrentTime = loadSave.readBinaryOfPath(allPaths.get(j));
					
					if(this.compareJSONArrays(binaryOfOldValue, binaryOfCurrentTime)) {
						// Do nothing since an "ok" is given
						System.out.println("ok was given");
						break inner;
					} else {
						// TODO set run variable false since the binaries are different
						System.out.println("Set run false 1");
						break inner;
					}
				//set run variable false since a interface is existing but it is not stored in the safe file
				} else if(j == allPaths.size()-1) {
					System.out.println("Set run false 2");
				}
			}
		}
	}
	
	public boolean compareJSONArrays(JSONArray first, JSONArray second) {
		if(first.size() != second.size()) {
			System.out.println("different size");
			return false;
		}
		
		for(int i = 0; i < first.size(); i++){
			Number a = (Number) first.get(i);
			Number b = (Number) second.get(i);
			
			// equals since values from an JSONArray are stored as String
			//data conversion problems TODO REFACTOR?? --> currently working
			if(a.byteValue() != b.byteValue()){
				System.out.println("error detected");
				System.out.println(first.get(i));
				System.out.println(second.get(i));
				return false;
				
			}
		}
		
		return true;
	}
}
