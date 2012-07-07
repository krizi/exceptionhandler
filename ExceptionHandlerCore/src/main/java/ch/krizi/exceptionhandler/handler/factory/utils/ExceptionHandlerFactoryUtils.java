/**
 * 
 */
package ch.krizi.exceptionhandler.handler.factory.utils;

import java.util.List;

import ch.krizi.exceptionhandler.handler.factory.AbstractExceptionHandlerFactory;

/**
 * 
 * 
 * @author krizi
 * 
 */
public interface ExceptionHandlerFactoryUtils {

	/**
	 * retrieves all available Factories
	 * 
	 * @return all available {@link AbstractExceptionHandlerFactory}
	 */
	public List<AbstractExceptionHandlerFactory> getExceptionHandlerFactories();
}
