package MainClassPackage;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import CustomExceptions.InterfaceHasChangedException;
import CustomExceptions.InvalidArgumentException;
import CustomExceptions.InvalidPathsInsideConfigurationException;

import InputOutput.LoadAndSave;

import Miscellaneous.CompareBinaries;
import Miscellaneous.DefinedStrings;
import Miscellaneous.programmPreconditions;

public class MainClass {

	public static void main(String[] args) throws IOException, ParseException, InvalidPathsInsideConfigurationException, InterfaceHasChangedException, InvalidArgumentException {
		programmPreconditions preconditions = new programmPreconditions();
		preconditions.createNonExistingFiles();

		if (args.length > 0 && args[0].equals(DefinedStrings.updateInterfaceValuesArgument.getValue())) {
			LoadAndSave loadSave = new LoadAndSave();
			loadSave.updateSavedBinaries();
		} else if (args.length > 0 && args[0].equals(DefinedStrings.pipelinePartArgument.getValue())) {
			CompareBinaries compare = new CompareBinaries();
			if (compare.areCurrentBinariesSameAsStoredOnes()) {
				System.out.println("Successful Execution - No Changes Detected");
			} else {
				throw new InterfaceHasChangedException();
			}
		} else {
			System.out.println("First argument has to be " + DefinedStrings.updateInterfaceValuesArgument.getValue()
					+ " or " + DefinedStrings.pipelinePartArgument.getValue());
			throw new InvalidArgumentException();
		}
	}
}
