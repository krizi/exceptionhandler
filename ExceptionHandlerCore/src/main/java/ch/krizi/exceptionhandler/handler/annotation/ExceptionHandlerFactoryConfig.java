package ch.krizi.exceptionhandler.handler.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Configuration for the ExceptionHandlerFactory
 * 
 * @author krizi
 * 
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExceptionHandlerFactoryConfig {

	/**
	 * defindes wich packages should be handled by this factory
	 */
	String[] packages();
}
