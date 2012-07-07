/**
 * 
 */
package ch.krizi.exceptionhandler.handler.factory.utils;

import java.util.List;

import ch.krizi.exceptionhandler.handler.factory.AbstractExceptionHandlerFactory;

/**
 * 
 * TODO load all classes from classloader, wich extends
 * {@link AbstractExceptionHandlerFactory}.
 * 
 * @author krizi
 * 
 */
public class ExceptionHandlerFactoryUtilsDefault implements ExceptionHandlerFactoryUtils {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.krizi.exceptionhandler.handler.factory.utils.ExceptionHandlerFactoryUtils
	 * #getExceptionHandlerFactories()
	 */
	@Override
	public List<AbstractExceptionHandlerFactory> getExceptionHandlerFactories() {

		// new ClassFinder().findAllInterfaces(arg0, arg1);

		return null;
	}

}
