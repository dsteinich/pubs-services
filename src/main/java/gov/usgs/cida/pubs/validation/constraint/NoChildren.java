package gov.usgs.cida.pubs.validation.constraint;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;

import gov.usgs.cida.pubs.validation.nochildren.NoChildrenValidatorForAffiliation;
import gov.usgs.cida.pubs.validation.nochildren.NoChildrenValidatorForPublicationSeries;

@Target(TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy={
		NoChildrenValidatorForPublicationSeries.class,
		NoChildrenValidatorForAffiliation.class
})
@Documented
public @interface NoChildren {

	String message() default "{pubs.children.exist}";
	Class<?>[] groups() default {};
	public abstract Class<?>[] payload() default {};

	String[] propertyName() default {};
}