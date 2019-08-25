package org.jonleb.characters.constraints.diccetower;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DiceTowerValidator.class)
public @interface DiceTowerConstraint {
    String message() default "{dicetower.error}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
