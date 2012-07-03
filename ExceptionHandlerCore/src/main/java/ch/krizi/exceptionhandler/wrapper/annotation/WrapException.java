/**
 * 
 */
package ch.krizi.exceptionhandler.wrapper.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import ch.krizi.exceptionhandler.wrapper.DefaultExceptionWrapper;
import ch.krizi.exceptionhandler.wrapper.ExceptionWrapper;

/**
 * Annotaion to wrap Exception.
 * 
 * @author krizi
 * 
 */
@Documented
@Target({ ElementType.METHOD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface WrapException {
	Class<? extends Throwable> catchException();

	Class<? extends Throwable> throwAs();

	String message() default "Wrapped exception";

	Class<? extends ExceptionWrapper<?, ?>> using() default DefaultExceptionWrapper.class;

}
