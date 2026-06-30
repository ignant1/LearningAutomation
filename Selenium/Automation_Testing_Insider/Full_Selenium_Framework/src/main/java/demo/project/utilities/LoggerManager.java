package demo.project.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerManager {

	//return a logger instance for the provided class
	public static Logger getLogger(Class<?> cls) {
		return LogManager.getLogger();
	}
}
