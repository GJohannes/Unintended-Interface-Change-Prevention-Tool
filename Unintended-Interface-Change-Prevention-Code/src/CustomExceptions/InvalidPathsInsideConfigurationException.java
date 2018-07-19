package CustomExceptions;

import java.nio.file.Path;

public class InvalidPathsInsideConfigurationException extends Exception{

	public InvalidPathsInsideConfigurationException(String path) {
		System.out.println(path);
	}
}
