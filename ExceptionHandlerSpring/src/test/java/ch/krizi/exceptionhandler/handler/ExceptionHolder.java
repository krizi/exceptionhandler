/**
 * 
 */
package ch.krizi.exceptionhandler.handler;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.krizi.exceptionhandler.handler.annotation.ExceptionHandlerConfig;

/**
 * Schreibt jede Exception in eine Liste, welche von Spring gemanaged wird.
 * 
 * @author krizi
 * 
 */
@ExceptionHandlerConfig(Throwable.class)
public class ExceptionHolder extends ExceptionHandler<Throwable> {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionHolder.class);

	@Resource(name = "exceptionBag")
	private List<Throwable> exceptionBag;

	public ExceptionHolder(Class<? extends Throwable> throwingClass, Throwable exception) {
		super(throwingClass, exception);
	}

	@Override
	public void handleException() {
		if (logger.isDebugEnabled()) {
			logger.debug("add Exception [{}] to ExceptionBag", exception.getClass());
		}
		exceptionBag.add(exception);
	}

	public List<Throwable> getExceptionBag() {
		return exceptionBag;
	}

	public void setExceptionBag(List<Throwable> exceptionBag) {
		this.exceptionBag = exceptionBag;
	}
}
