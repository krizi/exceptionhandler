/**
 * 
 */
package ch.krizi.exceptionhandler.handler;

import java.nio.charset.IllegalCharsetNameException;

import ch.krizi.exceptionhandler.handler.annotation.ExceptionHandlerConfig;

/**
 * @author krizi
 * 
 */
@ExceptionHandlerConfig(IllegalCharsetNameException.class)
public class UnreachedExceptionHandler extends ExceptionHandler<IllegalCharsetNameException> {

	public UnreachedExceptionHandler(Class<? extends IllegalCharsetNameException> throwingClass,
			IllegalCharsetNameException exception) {
		super(throwingClass, exception);
	}

	@Override
	public void handleException() {

	}

}
