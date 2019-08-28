package org.jonleb.dicetower.constraints.dicetower;

import lombok.extern.log4j.Log4j2;
import org.jonleb.dicetower.services.RollType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;
import java.util.Arrays;

@Log4j2
@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class RollValidator implements ConstraintValidator<RollConstraint, Object[]> {

    private RollType[] rollTypes;

    @Override
    public void initialize(RollConstraint constraintAnnotation) {
        log.debug("#############################");
        log.debug(constraintAnnotation.rollTypes()[0] + " " + constraintAnnotation.rollTypes()[1]);
        log.debug("#############################");
        this.rollTypes = constraintAnnotation.rollTypes();
    }

    @Override
    public boolean isValid(Object[] parameters, ConstraintValidatorContext constraintValidatorContext) {
        boolean valid = false;
        RollType rollType = (RollType) parameters[0];
        if (!Arrays.asList(this.rollTypes).contains(rollType)) return false;
        switch (rollType){
            case SUCCESS:
            case SUCCESS_BY_TYPE:
                if (parameters.length == 3){
                    valid = true;
                }
                break;
            case TOTAL:
            case TOTAL_BY_TYPE:
                if (parameters.length == 2){
                    valid = true;
                }
                break;
        }
        return valid;
    }
}
