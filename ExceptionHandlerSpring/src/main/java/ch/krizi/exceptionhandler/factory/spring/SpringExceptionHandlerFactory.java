/**
 * 
 */
package ch.krizi.exceptionhandler.factory.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ch.krizi.exceptionhandler.handler.ExceptionHandler;
import ch.krizi.exceptionhandler.handler.factory.AbstractExceptionHandlerFactory;
import ch.krizi.exceptionhandler.handler.factory.utils.ExceptionHandlerUtils;

/**
 * @author krizi
 * 
 */
public class SpringExceptionHandlerFactory extends AbstractExceptionHandlerFactory {
	private static final Logger logger = LoggerFactory.getLogger(SpringExceptionHandlerFactory.class);

	@Autowired
	protected ExceptionHandlerUtils exceptionHandlerUtils;

	protected Map<String, Class<? extends ExceptionHandler<?>>> mapping;

	@Override
	public List<ExceptionHandler<?>> getExceptionHandler(Class<?> throwedClass, Throwable throwable) {
		List<ExceptionHandler<?>> exceptionHandlerList = new ArrayList<ExceptionHandler<?>>();

		if (logger.isDebugEnabled()) {
			logger.debug("search ExceptionHandler by [class={}, throwable={}]", throwedClass, throwable.getClass());
		}

		for (Entry<String, Class<? extends ExceptionHandler<?>>> entry : mapping.entrySet()) {
			Class<? extends ExceptionHandler<?>> exceptionHandlerClass = entry.getValue();

			boolean canHandleException = canHandleException(throwable, exceptionHandlerClass);
			if (logger.isDebugEnabled()) {
				Object[] logParams = new Object[] { exceptionHandlerClass, throwable.getClass(), canHandleException };
				logger.debug("ExceptionHandler[{}] can handle Exception[{}]: {}", logParams);
			}

			if (canHandleException) {
				exceptionHandlerList.add(createExceptionHandler(throwedClass, entry.getKey(), throwable));
			}
		}

		return exceptionHandlerList;
	}

	protected ExceptionHandler<?> createExceptionHandler(Class<?> classLogger, String springId,
			Throwable throwable) {

		ExceptionHandler<?> exceptionHandler = exceptionHandlerUtils.getExceptionHandler(classLogger, springId,
				throwable);

		if (logger.isDebugEnabled()) {
			Object[] logParams = new Object[] { exceptionHandler.getClass(), classLogger, springId,
					throwable.getClass() };
			logger.debug("create ExceptionHandler[{}] by Params[class={}, springI={}, throwable={}]", logParams);
		}
		return exceptionHandler;
	}

	@Override
	public boolean hasSupportedExceptionHandler(Throwable exception) {
		// TODO Auto-generated method stub
		return false;
	}

	public Map<String, Class<? extends ExceptionHandler<?>>> getMapping() {
		return mapping;
	}

	public void setMapping(Map<String, Class<? extends ExceptionHandler<?>>> mapping) {
		this.mapping = mapping;
	}
}
