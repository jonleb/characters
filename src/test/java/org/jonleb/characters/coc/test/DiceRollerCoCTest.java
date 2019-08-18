package org.jonleb.characters.coc.test;

import lombok.extern.log4j.Log4j2;
import org.jonleb.characters.application.SpringBootCharacterApplication;
import org.jonleb.characters.coc.DiceRollerCoC;
import org.jonleb.characters.coc.DiceRollerType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@Log4j2
@SpringBootTest(classes = SpringBootCharacterApplication.class)
public class DiceRollerCoCTest {

    @Test
    public void testDices(){
        int [] dices = DiceRollerCoC.dices(DiceRollerType.ROLL_2D6);
        log.debug("Dice results are: "+ Arrays.toString(dices));
        Assertions.assertTrue(dices[0] != dices[3] && dices[0] + dices[3] == 25);
        Assertions.assertTrue(dices[1] != dices[4] && dices[1] + dices[4] == 25);
        Assertions.assertTrue(dices[2] != dices[5] && dices[2] + dices[5] == 25);
    }
}
