package ch.krizi.exceptionhandler.wrapper.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to handle multiple {@link WrapException}
 * 
 * @author krizi
 * 
 */
@Documented
@Target({ ElementType.METHOD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface WrapExceptions {
	WrapException[] value();
}
