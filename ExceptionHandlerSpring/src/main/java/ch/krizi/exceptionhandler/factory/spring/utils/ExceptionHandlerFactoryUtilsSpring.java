/**
 * 
 */
package ch.krizi.exceptionhandler.factory.spring.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ch.krizi.exceptionhandler.handler.factory.AbstractExceptionHandlerFactory;
import ch.krizi.exceptionhandler.handler.factory.utils.ExceptionHandlerFactoryUtils;

/**
 * Utils for FactoryHandler in a Spring-Context.
 * 
 * @author krizi
 * 
 */
public class ExceptionHandlerFactoryUtilsSpring implements ExceptionHandlerFactoryUtils {

	/**
	 * Autowired loads all Beans (wich extends
	 * {@link AbstractExceptionHandlerFactory}) from Spring-Context.
	 */
	@Autowired
	private List<AbstractExceptionHandlerFactory> exceptionHandlerFactories;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.krizi.exceptionhandler.handler.factory.utils.ExceptionHandlerFactoryUtils
	 * #getExceptionHandlerFactories()
	 */
	@Override
	public List<AbstractExceptionHandlerFactory> getExceptionHandlerFactories() {
		return exceptionHandlerFactories;
	}

}
