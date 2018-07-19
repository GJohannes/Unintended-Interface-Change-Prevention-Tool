package MainClassPackage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import CustomExceptions.InvalidPathsInsideConfigurationException;
import InputOutput.LoadAndSave;

public class MainClass {

	public static void main(String[] args) throws IOException, ParseException, InvalidPathsInsideConfigurationException {
		
		if(args[0].equals("acceptNewInterfaces")) {
			
		} else if (args[0].equals("pipelinePart")) {
			
		} else {
			System.out.println("First argument has to be acceptNewInterfaces or pipelinePart");
		}
		
		
		
		
		
		
		
		
		
		
		
		System.out.println("initial state");
		
		
		JSONObject json = new JSONObject();
		LoadAndSave loadSave = new LoadAndSave();
		//load.saveRunPipelineBoolean(false);
		ArrayList<Path> allPaths = loadSave.loadPaths();
		
		for(int i = 0; i < allPaths.size();i++) {
			
			JSONArray array = new JSONArray();
			byte [] binary = loadSave.readBinaryOfPath(allPaths.get(i));
			
			
			for(int j = 0; j < binary.length; j++) {
				
				array.add(binary[j]);
			}
			
			
			
			
			json.put(allPaths.get(i).toString(), array.toJSONString());
		}
		
		
		
		System.out.println(json);
		
	}

}
