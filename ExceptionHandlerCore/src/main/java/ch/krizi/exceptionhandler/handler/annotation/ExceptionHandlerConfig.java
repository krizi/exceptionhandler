/**
 * 
 */
package ch.krizi.exceptionhandler.handler.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Configuration for ExceptionHandler
 * 
 * @author krizi
 * 
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExceptionHandlerConfig {

	/**
	 * defines if subtypes should be handled <b>default: true</b>
	 */
	boolean handleSubtypes() default true;

	/**
	 * defines what types of exceptions should be handled
	 */
	Class<? extends Throwable> value();

}
