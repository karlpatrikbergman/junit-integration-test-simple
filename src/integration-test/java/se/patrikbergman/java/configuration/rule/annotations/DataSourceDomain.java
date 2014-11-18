package se.patrikbergman.java.configuration.rule.annotations;

import se.patrikbergman.java.configuration.environment.Domain;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSourceDomain {
	Domain domain() default Domain.EU;
}
