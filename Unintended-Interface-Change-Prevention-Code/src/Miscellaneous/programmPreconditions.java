package Miscellaneous;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.json.simple.JSONObject;

import InputOutput.LoadAndSave;

/*
 * checks weather required files are already existing
 * if not missing files are created by this class
 */
public class programmPreconditions {
	public void createNonExistingFiles() throws IOException {
		LoadAndSave loadSave = new LoadAndSave();
		ArrayList<JSONObject> emptylist = new ArrayList<>();

		Path pathToConfigInterfaces = Paths.get(DefinedStrings.PathsToConfigurationInterfaces.getValue());
		if (!pathToConfigInterfaces.toFile().exists()) {
			loadSave.createConfigurationFileToPaths();
		}

		/*
		 * If file with binaries is non existent then create it with the default save
		 * functionality 
		 * TODO: IF program behavior should be that first run working as
		 * a pipelinePart and does not have to accept interfaces, use the method updateSavedBinaries()
		 */
		Path pathToBinaryFile = Paths.get(DefinedStrings.binaryFile.getValue());
		if (!pathToBinaryFile.toFile().exists()) {
			loadSave.saveBinaries(emptylist);
		}

	}
}
