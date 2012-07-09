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
		if (logger.isTraceEnabled()) {
			Object[] params = new Object[] { throwingClass, throwable, beanId, exceptionHandlerClass };
			logger.trace(
					"create ExeptionHandler [throwingClass={}, throwable={}, beanId={}, exceptionHandlerClass={}]",
					params);
		}
		
		if (throwingClass == null) {
			throw new IllegalArgumentException("throwing Class must not be null");
		} else if (throwable == null) {
			throw new IllegalArgumentException("throwable must not be null");
		} else if (exceptionHandlerClass == null) {
			throw new IllegalArgumentException("ExceptionHandler Class must not be null");
		}

		try {
			Constructor<? extends ExceptionHandler<?>> constructor = exceptionHandlerClass.getConstructor(Class.class,
					Throwable.class);
			return constructor.newInstance(throwingClass, throwable);
		} catch (IllegalArgumentException e) {
			throw e;
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				Object[] params = new Object[] { beanId, exceptionHandlerClass, e };
				logger.error("canot create new Instance of [id={}, class={}] ", params);
			}
		}

		return null;
	}

}
