package Miscellaneous;

public enum DefinedStrings {
	runPipelineBooleanFile("runPipelineBooleanFile"),
	runPipelineKey("runPipelineKey"),
	binaryFile("binaryFile"),
	PathsToConfigurationInterfaces("ConfigurationInterfaces.txt");

	private final String keyValue;
	
	DefinedStrings(String s) {
		keyValue = s;
	}

	public String getValue() {
		return keyValue;
	}

}