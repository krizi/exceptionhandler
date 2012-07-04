/**
 * 
 */
package ch.krizi.exceptionhandler.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author krizi
 * 
 */
public class CommonExceptionHandler extends ExceptionHandler<Throwable> {

	private static final Logger logger = LoggerFactory
			.getLogger(CommonExceptionHandler.class);

	public CommonExceptionHandler(Class<?> clazz, Throwable exception) {
		super(clazz, exception);
	}

	@Override
	public void handleException() {
		if (logger.isErrorEnabled()) {
			logger.error("Exception Handled", this.exception);
		}
	}
}
