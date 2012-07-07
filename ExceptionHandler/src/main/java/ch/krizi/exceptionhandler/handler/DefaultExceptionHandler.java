/**
 * 
 */
package ch.krizi.exceptionhandler.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.krizi.exceptionhandler.handler.annotation.ExceptionHandlerConfig;

/**
 * Default ExceptionHandler. Only log the throwable with the classlogger on
 * level Error.
 * 
 * @author krizi
 * 
 */
@ExceptionHandlerConfig(Throwable.class)
public class DefaultExceptionHandler extends ExceptionHandler<Throwable> {

	private static final Logger logger = LoggerFactory.getLogger(DefaultExceptionHandler.class);

	public DefaultExceptionHandler(Class<?> clazz, Throwable exception) {
		super(clazz, exception);
	}

	@Override
	public void handleException() {
		if (logger.isDebugEnabled()) {
			logger.debug("handle Exception");
		}

		if (classLogger.isErrorEnabled()) {
			classLogger.error("Exceptin handled", exception);
		}
	}
}
