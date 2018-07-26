package InputOutput;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import CustomExceptions.InvalidPathsInsideConfigurationException;
import Miscellaneous.DefinedStrings;

/*
 * Stores and loads the boolean which defines weather the current state of the tool is that the pipeline should run or not
 */

public class LoadAndSave {
	

	
	public JSONArray readBinaryOfPath(Path path) throws IOException {
		byte[] binary = Files.readAllBytes(path);
		JSONArray array = new JSONArray();
		for(int i = 0; i < binary.length; i++) {
			array.add(binary[i]);
		}
		return array;
	}
	
	public void saveBinaries(ArrayList<JSONObject> allJSONs) throws IOException {
		System.out.println("Started storing configuration values of given paths");
		ArrayList<String> list = new ArrayList<>();
		Path path = Paths.get(DefinedStrings.binaryFile.getValue());
		
		for(int i = 0; i < allJSONs.size(); i++) {
			list.add(allJSONs.get(i).toJSONString());
		}
		
		Files.write(path, list);
		
		System.out.println("Saved new configuration values of given paths");
	}
	
	public void updateSavedBinaries() throws IOException, InvalidPathsInsideConfigurationException, ParseException {
		ArrayList<JSONObject> allJSONs = new ArrayList<>();
		ArrayList<Path> allPaths = loadPaths();
		
		for(int i = 0; i < allPaths.size(); i++) {
			JSONObject newBinaries = new JSONObject();
			newBinaries.put(allPaths.get(i).getFileName().toString(), readBinaryOfPath(allPaths.get(i)));
			allJSONs.add(newBinaries);
		}
		saveBinaries(allJSONs);
	}
	
	
	/*
	 * Each Line contains a JSON which has the key value of the name of a binary
	 */
	public ArrayList<JSONObject> loadBinaries() throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		Path path = Paths.get(DefinedStrings.binaryFile.getValue());
		ArrayList<String> list = (ArrayList<String>) Files.readAllLines(path);
		ArrayList<JSONObject> allReadJsons = new ArrayList<>();
		
		
		for(int i = 0; i < list.size(); i++) {
			Object o = parser.parse(list.get(i));
			allReadJsons.add((JSONObject) o);
		}
		return allReadJsons;
	}
	
	/*
	 * loading all paths that are defined inside the configuration file for this tool
	 * 
	 */
	public ArrayList<Path> loadPaths() throws IOException, InvalidPathsInsideConfigurationException{
		Path path = Paths.get(DefinedStrings.PathsToConfigurationInterfaces.getValue());
		ArrayList<String> stringList = (ArrayList<String>) Files.readAllLines(path);
		ArrayList<Path> pathList = new ArrayList<>();
		
		for(int i = 0; i < stringList.size();i++) {
			//trim in case there are white spaces in the same line with the path to the file
			File file = new File(stringList.get(i).trim());
			if(file.exists()) {
				//trim in case there are white spaces in the same line with the path to the file
				Path pathToConfigInterfaceFile = Paths.get(stringList.get(i).trim());
				pathList.add(pathToConfigInterfaceFile);
			} else if((file != null && file.getPath().trim().length() == 0) || file.getPath().trim().startsWith("#")) {
				// Do nothing since "#" is commenting inside the file
			} else{
				throw new InvalidPathsInsideConfigurationException(file.getPath());
			}
		}

		return pathList;
	}
	
	
	
//	/* ** Outdated Code
//	 * Stores the variable which defines whether or not the pipeline should run when it is triggered
//	 */
//	public void saveRunPipelineBoolean(boolean runPipeline) throws IOException {
//		JSONObject json = new JSONObject();
//		ArrayList<String> list = new ArrayList<>();
//		Path path = Paths.get(DefinedStrings.runPipelineBooleanFile.getValue());
//		
//		json.put(DefinedStrings.runPipelineKey.getValue(),runPipeline);
//		list.add(json.toJSONString());
//		Files.write(path, list);
//	}
//	
//	public boolean loadRunPipelineBoolean() throws ParseException, IOException {	
//		JSONParser parser = new JSONParser();
//		Path path = Paths.get(DefinedStrings.runPipelineBooleanFile.getValue());
//		ArrayList<String> list = (ArrayList<String>) Files.readAllLines(path);	
//		
//		Object o = parser.parse(list.get(0));
//		JSONObject json = (JSONObject) o;
//		return (boolean) json.get(DefinedStrings.runPipelineKey.getValue());	
//	}
	
	
}
