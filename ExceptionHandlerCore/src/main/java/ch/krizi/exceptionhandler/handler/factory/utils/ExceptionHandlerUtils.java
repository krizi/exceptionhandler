/**
 * 
 */
package ch.krizi.exceptionhandler.handler.factory.utils;

import ch.krizi.exceptionhandler.handler.ExceptionHandler;

/**
 * Utils-Klasse um die Exceptionhandler Context abhängig zu verarbeiten.
 * 
 * @author krizi
 * 
 */
public interface ExceptionHandlerUtils {

	/**
	 * Lädt den ExceptionHandler aus dem Context.
	 * 
	 * @param classLogger
	 * @param throwable
	 * @param beanId
	 * @param exceptionHandlerClass
	 * @return
	 */
	public ExceptionHandler<?> createExceptionHandler(Class<?> classLogger, Throwable throwable, String beanId,
			Class<? extends ExceptionHandler<?>> exceptionHandlerClass);
}
