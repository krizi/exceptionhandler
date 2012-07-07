/**
 * 
 */
package ch.krizi.exceptionhandler.handler.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.krizi.exceptionhandler.handler.ExceptionHandler;
import ch.krizi.exceptionhandler.handler.factory.utils.ExceptionHandlerUtils;

/**
 * @author krizi
 * 
 */
public class ExceptionHandlerDefaultFactory extends AbstractExceptionHandlerFactory {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerDefaultFactory.class);

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
				exceptionHandlerList.add(createExceptionHandler(throwedClass, throwable, entry.getKey(),
						entry.getValue()));
			}
		}

		return exceptionHandlerList;
	}

	/**
	 * create a ExceptionHandler using {@link ExceptionHandlerUtils}
	 * 
	 * @param throwingClass
	 *            class where the exception occured
	 * @param beanId
	 *            id for creating a bean
	 * @param throwable
	 *            throwed exception
	 * @return
	 */
	protected ExceptionHandler<?> createExceptionHandler(Class<?> throwingClass, Throwable throwable, String beanId,
			Class<? extends ExceptionHandler<?>> exceptionHandlerClass) {
		ExceptionHandler<?> exceptionHandler = exceptionHandlerUtils.createExceptionHandler(throwingClass, throwable,
				beanId, exceptionHandlerClass);

		if (logger.isDebugEnabled()) {
			Object[] logParams = new Object[] { exceptionHandler.getClass(), throwingClass, beanId,
					throwable.getClass() };
			logger.debug("create ExceptionHandler[{}] by Params[class={}, springI={}, throwable={}]", logParams);
		}
		return exceptionHandler;
	}

	/**
	 * checks all ExceptionHandler if Exception could be handled.
	 * 
	 * @return true exceptions can be handled
	 */
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

	public ExceptionHandlerUtils getExceptionHandlerUtils() {
		return exceptionHandlerUtils;
	}

	public void setExceptionHandlerUtils(ExceptionHandlerUtils exceptionHandlerUtils) {
		this.exceptionHandlerUtils = exceptionHandlerUtils;
	}
}
