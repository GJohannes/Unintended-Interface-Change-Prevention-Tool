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

	/*
	 * Compares the binaries of the Files given in the Paths file to the stored binaries
	 * are the number of binaries and paths equal and all binaries are matching the previous binaries everything return true
	 * in case there are differences method returns false
	 *  
	 */
	public boolean areCurrentBinariesSameAsStoredOnes() throws IOException, ParseException, InvalidPathsInsideConfigurationException {
		LoadAndSave loadSave = new LoadAndSave();
		ArrayList<JSONObject> allOldBinaries = loadSave.loadBinaries();
		ArrayList<Path> allPaths = loadSave.loadPaths();	
		
		//precondition is that the same number of old interface binaries and number of paths to configuration files is there
		if(allOldBinaries.size() != allPaths.size()) {
			if(allOldBinaries.size() > allPaths.size()) {
				System.out.println("There are more old configuration binaries than Paths");
			} else {
				System.out.println("New paths detected - New files where added");
			}
			return false;
		}
		
		ArrayList<Path> filesThatHaveBeenChanged = new ArrayList<>();
		
		for(int i = 0; i < allOldBinaries.size(); i++) {
			inner: for(int j = 0; j < allPaths.size(); j++) {
				if(allOldBinaries.get(i).containsKey(allPaths.get(j).getFileName().toString())) {
					JSONArray binaryOfOldValue = (JSONArray) allOldBinaries.get(i).get(allPaths.get(j).getFileName().toString());
					JSONArray binaryOfCurrentTime = loadSave.readBinaryOfPath(allPaths.get(j));
					
					if(this.compareJSONArrays(binaryOfOldValue, binaryOfCurrentTime)) {
						// Do nothing since an "ok" is given
						break inner;
					//the current comparison showed that there are unequal files
					} else {
						filesThatHaveBeenChanged.add(allPaths.get(j));
						break inner;
					}
				// interface is existing but it is not stored in the safe file
				} else if(j == allPaths.size()-1) {
					filesThatHaveBeenChanged.add(allPaths.get(j));
					break inner;
				}
			}
		}
		if(filesThatHaveBeenChanged.size() == 0) {
			// no files have been changed. old states and current sates of all files are binary equal
			return true;
		} else {
			System.out.println("Files that have been changed:");
			for(int i = 0; i < filesThatHaveBeenChanged.size(); i++) {
				System.out.println(filesThatHaveBeenChanged.get(i));
			}
			return false;
		}
	}
	
	/*
	 * compares two JSON arrays weather they are equal or not. 
	 * Only works if the Arrays are only containing bytes which is given in this simple use case
	 * TODO Refactor in not prototypical implementation 
	 */
	public boolean compareJSONArrays(JSONArray first, JSONArray second) {
		if(first.size() != second.size()) {
			System.out.println("Interface with different binary size then the previous one detected");
			return false;
		}
		
		for(int i = 0; i < first.size(); i++){
			Number a = (Number) first.get(i);
			Number b = (Number) second.get(i);
			
			// equals since values from an JSONArray are stored as String
			//data conversion problems TODO REFACTOR?? --> currently working
			if(a.byteValue() != b.byteValue()){
				System.out.println("Binary mismatch detected!");
				System.out.println(first.get(i));
				System.out.println(second.get(i));
				return false;
				
			}
		}
		
		return true;
	}
}
