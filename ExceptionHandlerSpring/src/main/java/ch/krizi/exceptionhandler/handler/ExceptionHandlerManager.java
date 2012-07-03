/**
 * 
 */
package ch.krizi.exceptionhandler.handler;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import ch.krizi.exceptionhandler.handler.factory.AbstractExceptionHandlerFactory;

/**
 * @author krizi
 * 
 */
public class ExceptionHandlerManager {
	private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerManager.class);

	@Autowired
	private List<AbstractExceptionHandlerFactory> exceptionHandlerFactories;

	public List<AbstractExceptionHandlerFactory> getExceptionHandlerFactories() {
		return exceptionHandlerFactories;
	}

	public void setExceptionHandlerFactories(List<AbstractExceptionHandlerFactory> exceptionHandlerFactories) {
		Assert.notNull(exceptionHandlerFactories, "ExceptionHandlerFactory must not be null");
		this.exceptionHandlerFactories = exceptionHandlerFactories;
	}

	public void handleException(Class<? extends Throwable> classLogger, Throwable e) {
		Assert.notNull(e, "Exception must not be null");
		Assert.notEmpty(exceptionHandlerFactories, "no ExceptionHandlerFactories found");

		List<ExceptionHandler<?>> allExceptionHandler = new ArrayList<ExceptionHandler<?>>();

		for (AbstractExceptionHandlerFactory factory : exceptionHandlerFactories) {
			List<ExceptionHandler<?>> exceptionHandler = factory.getExceptionHandler(classLogger, e);
			if (exceptionHandler != null) {
				allExceptionHandler.addAll(exceptionHandler);
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("exceptoin [{}] will be handled by this ExceptionHandler [{}]", e.getClass(),
					toString(allExceptionHandler));
		}

		if (!CollectionUtils.isEmpty(allExceptionHandler)) {
			for (ExceptionHandler<?> handler : allExceptionHandler) {
				handler.handleException();
			}
		} else {
			logger.warn("didnt found any ExceptionHandler");
		}
	}

	private String toString(List<ExceptionHandler<?>> allExceptionHandler) {
		StringBuffer buffer = new StringBuffer();

		if (!CollectionUtils.isEmpty(allExceptionHandler)) {
			for (ExceptionHandler<?> eh : allExceptionHandler) {
				buffer.append(eh.getClass());
				buffer.append(", ");
			}
		} else {
			buffer.append("<null>");
		}

		return buffer.toString();
	}
}
