package MainClassPackage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import CustomExceptions.InterfaceHasChangedException;
import CustomExceptions.InvalidPathsInsideConfigurationException;
import InputOutput.LoadAndSave;
import Miscellaneous.CompareBinaries;
import Miscellaneous.DefinedStrings;
import Miscellaneous.programmPreconditions;

public class MainClass {

	public static void main(String[] args) throws IOException, ParseException, InvalidPathsInsideConfigurationException, InterfaceHasChangedException {
		args = new String[2];
		args[0] = "pipelinePart";
		// args[0] = "acceptNewInterfaces";

		programmPreconditions preconditions = new programmPreconditions();
		preconditions.createNonExistingFiles();

		if (args.length > 0 && args[0].equals(DefinedStrings.updateInterfaceValuesArgument.getValue())) {
			LoadAndSave loadSave = new LoadAndSave();
			loadSave.updateSavedBinaries();
		} else if (args.length > 0 && args[0].equals(DefinedStrings.pipelinePartArgument.getValue())) {
			CompareBinaries compare = new CompareBinaries();
			if (compare.areCurrentBinariesSameAsStoredOnes()) {
				System.out.println("No configuration changes detected");
			} else {
				throw new InterfaceHasChangedException();
			}
		} else {
			System.out.println("First argument has to be " + DefinedStrings.updateInterfaceValuesArgument.getValue()
					+ " or " + DefinedStrings.pipelinePartArgument.getValue());
		}
	}
}
