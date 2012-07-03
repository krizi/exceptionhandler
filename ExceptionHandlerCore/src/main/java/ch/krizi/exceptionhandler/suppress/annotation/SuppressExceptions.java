/**
 * 
 */
package ch.krizi.exceptionhandler.suppress.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Suppress Exceptions. Default suppress all Exceptions (Throwable.class).
 * 
 * @author krizi
 * 
 */
@Documented
@Target({ ElementType.METHOD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface SuppressExceptions {
	Class<? extends Throwable>[] value() default { Throwable.class };
}
