package org.jonleb.characters.constraints.diccetower;

import lombok.extern.log4j.Log4j2;
import org.jonleb.characters.services.RollType;

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
        this.rollTypes = constraintAnnotation.rollTypes();
    }

    @Override
    public boolean isValid(Object[] parameters, ConstraintValidatorContext constraintValidatorContext) {
        boolean valid = false;
        RollType rollType = (RollType) parameters[0];
        RollType[] forSuccessList =  {RollType.SUCCESS, RollType.SUCCESS_BY_TYPE};
        boolean isForSuccess = Arrays.asList(forSuccessList).contains(rollType);
        if (isForSuccess && parameters.length == 3){
            valid = true;
        } else if (!isForSuccess && parameters.length == 2){
            valid = true;
        } else {
            valid = false;
        }
        return valid;
    }
}
