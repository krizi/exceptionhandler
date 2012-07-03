/**
 * 
 */
package ch.krizi.exceptionhandler.handler.factory;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.krizi.exceptionhandler.handler.ExceptionHandler;
import ch.krizi.exceptionhandler.handler.annotation.ExceptionHandlerConfig;

/**
 * @author krizi
 * 
 */
public abstract class AbstractExceptionHandlerFactory {
	private static final Logger logger = LoggerFactory.getLogger(AbstractExceptionHandlerFactory.class);

	public abstract List<ExceptionHandler<?>> getExceptionHandler(Class<? extends Throwable> classLogger, Throwable exception);

	public abstract boolean hasSupportedExceptionHandler(Throwable exception);

	/**
	 * checks if the exception can be handled by the exceptionhandler
	 * 
	 * @param throwable
	 * @param exceptionHandlerClass
	 * @return
	 */
	protected boolean canHandleException(Throwable throwable, Class<? extends ExceptionHandler<?>> exceptionHandlerClass) {
		boolean canHandle = false;

		ExceptionHandlerConfig annotation = exceptionHandlerClass.getAnnotation(ExceptionHandlerConfig.class);
		Class<? extends Throwable> handledException = annotation.value();

		if (annotation.handleSubtypes()) {
			if (handledException.isInstance(throwable)) {
				canHandle = true;
			}
		} else {
			if (handledException.equals(throwable.getClass())) {
				canHandle = true;
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("can handle [Exception [{}], ExceptionHandler: [{}], Config: [{}] {} ", new Object[] {
					throwable.getClass(), exceptionHandlerClass, annotation, canHandle });
		}

		return canHandle;
	}
}
