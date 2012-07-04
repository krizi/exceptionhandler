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
 * LŠdt die ExceptionHandler aus dem Spring-Context.
 * 
 * @author krizi
 * 
 */
public class ExceptionHandlerUtilsSpringImpl implements ExceptionHandlerUtils {
	private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerUtilsSpringImpl.class);

	@Autowired
	private ApplicationContext applicationContext;

	public ExceptionHandler<?> getExceptionHandler(Class<?> classLogger, String beanId, Throwable throwable) {
		if (applicationContext.isSingleton(beanId)) {
			if (logger.isWarnEnabled()) {
				logger.warn("Bean [id={}] is not a Singleton!", beanId);
			}
		}
		ExceptionHandler<?> exceptionHandler = (ExceptionHandler<?>) applicationContext.getBean(beanId, classLogger,
				throwable);

		return exceptionHandler;
	}

}
