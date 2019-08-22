package org.jonleb.characters.coc.test;

import lombok.extern.log4j.Log4j2;
import org.jonleb.characters.SpringBootCharacterApplication;
import org.jonleb.characters.coc.DiceRollerCoC;
import org.jonleb.characters.coc.DiceRollerType;
import org.jonleb.characters.utils.DiceRoller;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.executable.ExecutableValidator;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;

@Log4j2
@SpringBootTest(classes = {SpringBootCharacterApplication.class})
public class DiceRollerCoCTest {

    static {
        executableValidator = Validation.buildDefaultValidatorFactory().getValidator().forExecutables();;
    }
    private static ExecutableValidator executableValidator;

    @BeforeEach
    public void setUp() {

    }

    //@Test
    public void testDices(){
        int [] dices = DiceRollerCoC.dices(DiceRollerType.ROLL_2D6);
        log.debug("Dice results are: "+ Arrays.toString(dices));
        Assertions.assertTrue(dices[0] != dices[3] && dices[0] + dices[3] == 25);
        Assertions.assertTrue(dices[1] != dices[4] && dices[1] + dices[4] == 25);
        Assertions.assertTrue(dices[2] != dices[5] && dices[2] + dices[5] == 25);
    }

    @Test
    void roll() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        log.debug("Test of DiceRoller.roll(1000, 1000)");

        Method roll = DiceRoller.class.getDeclaredMethod("roll", Integer.class,Integer.class);
        Annotation[][] parameterAnnotations = roll.getParameterAnnotations();

        Arrays.stream(parameterAnnotations)
                .forEach(v->
                        Arrays.stream(v)
                                .forEach(vv -> {
                                    log.debug(vv.toString());
                                })
                );

        log.debug("Invocation " + roll.invoke(null, 1000, 1000));

        Object [] parameterValues = {1000, 1000};
        Set<ConstraintViolation<Object>> violations
                = executableValidator.validateParameters(
                        DiceRoller.class,
                        roll,
                        parameterValues);
        log.debug(violations.size());

    }
}
