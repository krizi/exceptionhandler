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
 * @author krizi
 * 
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExceptionHandlerConfig {
	boolean handleSubtypes() default true;

	Class<? extends Throwable> value();

}
