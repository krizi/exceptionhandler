/**
 * 
 */
package ch.krizi.exceptionhandler.factory.spring.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import ch.krizi.exceptionhandler.handler.ExceptionHandler;
import ch.krizi.exceptionhandler.handler.factory.utils.ExceptionHandlerUtils;

/**
 * Laedt die ExceptionHandler aus dem Spring-Context.
 * 
 * @author krizi
 * 
 */
public class ExceptionHandlerUtilsSpring implements ExceptionHandlerUtils {
	private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerUtilsSpring.class);

	@Autowired
	private ApplicationContext applicationContext;

	public ExceptionHandler<?> createExceptionHandler(Class<?> classLogger, Throwable throwable, String beanId,
			Class<? extends ExceptionHandler<?>> exceptionHandlerClass) {

		if (applicationContext.isSingleton(beanId)) {
			if (logger.isWarnEnabled()) {
				logger.warn("Bean [id={}, class={}] is not a Singleton!", beanId, exceptionHandlerClass);
			}
		}
		ExceptionHandler<?> exceptionHandler = (ExceptionHandler<?>) applicationContext.getBean(beanId, classLogger,
				throwable);

		if (!exceptionHandler.getClass().equals(exceptionHandlerClass)) {
			if (logger.isWarnEnabled()) {
				logger.warn("ExceptionHandler [{}] should be type of [{}]", exceptionHandler.getClass(),
						exceptionHandlerClass);
			}
		}

		return exceptionHandler;
	}

}
