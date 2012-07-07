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

	/**
	 * throwed exception
	 */
	protected E exception;

	/**
	 * logger for the class where the exception was throwed
	 */
	protected Logger classLogger;

	/**
	 * class where the exception was throwed
	 */
	protected Class<?> throwingClass;

	/**
	 * Constructor for a exception-handler.
	 * 
	 * @param throwingClass
	 *            class where the exception was throwed
	 * @param exception
	 *            throwed exception
	 */
	public ExceptionHandler(Class<?> throwingClass, E exception) {
		if (throwingClass == null) {
			throw new IllegalArgumentException("Throwing CLass must not be null");
		} else if (exception == null) {
			throw new IllegalArgumentException("Exception must not be null");
		}
		this.throwingClass = throwingClass;
		this.classLogger = LoggerFactory.getLogger(throwingClass);
		this.exception = exception;
	}

	/**
	 * handles Exception
	 */
	public abstract void handleException();

	/**
	 * 
	 * @return returns the throwed exception
	 */
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
