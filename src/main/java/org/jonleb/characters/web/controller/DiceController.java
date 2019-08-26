package org.jonleb.characters.web.controller;

import lombok.extern.log4j.Log4j2;
import org.jonleb.characters.services.DiceTower;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api/v1/dices")
@Validated
@Log4j2
public class DiceController {

    private final DiceTower diceTower;

    public DiceController(DiceTower diceTower) {
        this.diceTower = diceTower;
    }


    @RequestMapping(
            method = RequestMethod.GET,
            path = "/roll",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public ResponseEntity<Map> roll(@RequestBody DiceTowerDescription diceTowerDescription){

        Map result = null;

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
}
