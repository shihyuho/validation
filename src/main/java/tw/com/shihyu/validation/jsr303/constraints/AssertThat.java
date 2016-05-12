package tw.com.shihyu.validation.jsr303.constraints;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import tw.com.shihyu.validation.jsr303.AssertThatValidator;

/**
 * Assert the giving expression true <br>
 * 
 * @author Matt S.Y. Ho
 */
@Documented
@Constraint(validatedBy = {AssertThatValidator.class})
@Target({TYPE, METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
public @interface AssertThat {

  public static @interface Namespace {
    String prefix();

    Class<?> clazz();
  }

  String message() default "{tw.com.softleader.commons.validation.constraints.AssertThat.message}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  /**
   * 
   * @return the expression to evaluate
   */
  String value();

  /**
   * default <code>'this'</code>
   * 
   * @return the alias for the value object used in expression
   */
  String alias() default "this";

  /**
   * bind more namespace to evaluate
   * 
   * @return
   */
  Namespace[] namespaces() default {};

  /**
   * Indicate propertyNode for ConstraintViolation
   * 
   * @return
   */
  String propertyNode() default "";

  /**
   * Defines several {@link AssertThat} annotations on the same element.
   */
  @Target({TYPE, METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
  @Retention(RUNTIME)
  @Documented
  @interface List {
    AssertThat[] value();
  }
}
