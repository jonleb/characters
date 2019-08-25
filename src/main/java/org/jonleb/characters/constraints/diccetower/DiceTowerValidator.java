package org.jonleb.characters.constraints.diccetower;

import lombok.extern.log4j.Log4j2;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

@Log4j2
public class DiceTowerValidator implements ConstraintValidator<DiceTowerConstraint, String[]> {

    @Override
    public boolean isValid(String args[], ConstraintValidatorContext constraintValidatorContext) {
        if (args == null || args.length == 0){
            return false;
        }

        boolean returnValue = true;
        for (String arg : args) {
            // Regular Expression

            boolean match = Pattern
                    .matches("^[1-9][D|d]([2]?[4]?[6]?[8]?([1][0])?([1][2])?([2][0])?([1][0][0])?)$", arg);
            if (!match) returnValue = false;
            if (log.isDebugEnabled()){
                log.debug("Is rolling dices " + arg + " are valid?: " + match);
            }
        }
        return returnValue;
    }
}
