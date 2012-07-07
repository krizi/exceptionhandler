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
 * defines that the Exceptions should be handled here
 * 
 * @author krizi
 * 
 */
@Documented
@Target({ ElementType.METHOD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface HandleException {
	/**
	 * defines the handled Exceptions
	 * 
	 */
	Class<? extends Throwable>[] exceptions() default { RuntimeException.class };
}
