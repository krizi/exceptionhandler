/**
 * 
 */
package ch.krizi.exceptionhandler.handler.factory.utils;

import java.lang.reflect.Constructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.krizi.exceptionhandler.handler.ExceptionHandler;

/**
 * Default Implementation for create new ExceptionHandler (use reflection).
 * 
 * @author krizi
 * 
 */
public class ExceptionHandlerUtilsDefault implements ExceptionHandlerUtils {
	private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerUtilsDefault.class);

	@Override
	public ExceptionHandler<?> createExceptionHandler(Class<?> throwingClass, Throwable throwable, String beanId,
			Class<? extends ExceptionHandler<?>> exceptionHandlerClass) {
		try {
			Constructor<? extends ExceptionHandler<?>> constructor = exceptionHandlerClass.getConstructor(
					throwingClass, throwable.getClass());
			return constructor.newInstance(throwingClass, throwable);
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				Object[] params = new Object[] { beanId, exceptionHandlerClass, e };
				logger.error("canot create new Instance of [id={}, class={}] ", params);
			}
		}

		return null;
	}

}
