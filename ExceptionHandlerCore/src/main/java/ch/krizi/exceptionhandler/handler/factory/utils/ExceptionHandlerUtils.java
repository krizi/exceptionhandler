/**
 * 
 */
package ch.krizi.exceptionhandler.handler.factory.utils;

import ch.krizi.exceptionhandler.handler.ExceptionHandler;

/**
 * Utils-Klasse um die Exceptionhandler Context abh�ngig zu verarbeiten.
 * 
 * @author krizi
 * 
 */
public interface ExceptionHandlerUtils {

	/**
	 * L�dt den ExceptionHandler aus dem Context.
	 * 
	 * @param classLogger
	 * @param beanId
	 * @param throwable
	 * @return
	 */
	public ExceptionHandler<?> getExceptionHandler(Class<? extends Throwable> classLogger, String beanId,
			Throwable throwable);
}
