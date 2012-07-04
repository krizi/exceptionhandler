/**
 * 
 */
package ch.krizi.exceptionhandler.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract Exeption-Handler.
 * 
 * @author krizi
 * 
 */
public abstract class ExceptionHandler<E extends Throwable> {
	private boolean handleSubtypes = true;
	protected E exception;
	protected Logger classLogger;
	protected Class<?> throwingClass;

	public ExceptionHandler(Class<?> throwingClass, E exception) {
		this.throwingClass = throwingClass;
		this.classLogger = LoggerFactory.getLogger(throwingClass);
		this.exception = exception;
	}

	public abstract void handleException();

	public E getException() {
		return this.exception;
	}

	public boolean isHandleSubtypes() {
		return handleSubtypes;
	}

	public void setHandleSubtypes(boolean handleSubtypes) {
		this.handleSubtypes = handleSubtypes;
	}
}
