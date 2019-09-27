package org.jonleb.dicetower.web.controller;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.extern.log4j.Log4j2;
import org.jonleb.dicetower.services.DiceTower;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/dices")
@Validated
@Log4j2
public class DiceController {
    private final DiceTower diceTower;
    private Counter diceCounter;
    private final Timer responses;

    public DiceController(DiceTower diceTower, MeterRegistry registry) {
        this.diceTower = diceTower;
        diceCounter = registry.counter("rest.dicetower","dices", "d6");
        responses = registry.timer("rest.dicetower", "dices", "timer");
    }


    @RequestMapping(
            method = RequestMethod.GET,
            path = "/roll",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    @Timed("dicetower_roll")
    public ResponseEntity<Map> roll(@RequestBody DiceTowerDescription diceTowerDescription){
        if (log.isDebugEnabled())
            log.debug(diceTowerDescription.toString());

        Map result = null;

        String [] dices = diceTowerDescription.getDicesToRoll().trim().split(" ");
        for (int dice = dices.length - 1; dice >= 0; dice--) {
            if (dices[dice].contains("D6")){
                diceCounter.increment();
            }
        }
        switch (diceTowerDescription.getRollType()){
            case TOTAL:
            case TOTAL_BY_TYPE:
                result = diceTower.roll(
                        diceTowerDescription.getRollType(),
                        diceTowerDescription.getDicesToRoll().trim().split(" "));
                break;
            case SUCCESS:
            case SUCCESS_BY_TYPE:
                result = diceTower.rollForSuccess(
                        diceTowerDescription.getRollType(),
                        diceTowerDescription.getLimitSuccess(),
                        diceTowerDescription.getDicesToRoll().trim().split(" "));
                break;
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List> validationErrorHandler(ConstraintViolationException cve){
        List<String> errors = new ArrayList<>(cve.getConstraintViolations().size());
        cve.getConstraintViolations().forEach(constraintViolation -> {
            errors.add(constraintViolation.getMessage());
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}