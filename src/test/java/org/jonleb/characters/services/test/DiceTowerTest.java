package org.jonleb.characters.services.test;

import lombok.extern.log4j.Log4j2;
import org.jonleb.characters.SpringBootCharacterApplication;
import org.jonleb.characters.services.DiceTower;
import org.jonleb.characters.services.RollType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.annotation.Validated;

import javax.validation.ConstraintViolationException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest(classes = {SpringBootCharacterApplication.class})
@Log4j2
@Validated
class DiceTowerTest {

    @Autowired
    DiceTower diceTower;

    @Test
    void roll_with_exception() {
        boolean valid = false;
        try {
            diceTower.roll(RollType.TOTAL, "3D34");
        } catch (ConstraintViolationException e) {
            log.debug(e.getMessage());
            valid = true;
        }
        assertTrue(valid);
    }

    @Test
    void roll_total_1_dice_type() {
        Map result;
        try {
            result = diceTower.roll(RollType.TOTAL_BY_TYPE, "3D6");
            int total = (int) result.get("3D6");
            assertTrue(total >= 3 && total <= 18);
        } catch (Exception e) {
            log.debug(e.getMessage());
            fail();
        }
    }

    @Test
    void roll_total_2_dice_type() {
        Map result;
        try {
            result = diceTower.roll(RollType.TOTAL, "3D6","2D10");
            int total = (int) result.get("3D6 2D10");
            assertTrue(total >= 5 && total <= 38);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void roll_separate_total_2_dice_type() {
        Map result;
        try {
            result = diceTower.roll(RollType.TOTAL_BY_TYPE, "3D6","2D10");
            int total3D6 = (int) result.get("3D6");
            int total2D10 = (int) result.get("2D10");
            assertTrue(result.containsKey("3D6") && result.containsKey("2D10"));
            assertTrue(total3D6 >= 3 && total3D6 <= 18 && total2D10 >= 2 && total2D10 <= 20);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void roll_total_1_dice_type_for_success() {
        Map result;
        try {
            result = diceTower.rollForSuccess(RollType.SUCCESS, 4, "3D6");
            int total = (int) result.get("3D6");
            log.debug(" Number of success: " + total);
            assertTrue(total >= 0 && total <= 3);
        } catch (Exception e) {
            log.debug(e.getMessage());
            fail();
        }
    }

    @Test
    void roll_exception_for_success(){
        try {
            diceTower.rollForSuccess(RollType.TOTAL, 4, "3D6");
            fail();
        } catch (ConstraintViolationException cve){
            assertTrue(true);
        }
    }

    @Test
    void roll_exception_for_success_wrong_number_sides_of_dices(){
        try {
            diceTower.rollForSuccess(RollType.SUCCESS, 4, "3");
            fail();
        } catch (ConstraintViolationException cve){
            assertTrue(true);
        }
    }

    @Test
    void roll_exception_for_roll(){
        try {
            diceTower.roll(RollType.SUCCESS,"3D6");
            fail();
        } catch (ConstraintViolationException cve){
            log.debug(cve.getMessage());
            assertTrue(true);
        }
    }

}