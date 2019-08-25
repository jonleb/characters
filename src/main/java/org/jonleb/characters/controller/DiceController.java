package org.jonleb.characters.controller;

import lombok.extern.log4j.Log4j2;
import org.jonleb.characters.constraints.DiceConstraint;
import org.jonleb.characters.services.DiceTower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static org.jonleb.characters.services.RollType.TOTAL;


@RestController
@RequestMapping("/api/v1/dices")
@Validated
@Log4j2
public class DiceController {

    @Autowired
    DiceTower diceTower;

    @GetMapping("/{sides}")
    public ResponseEntity<Integer> getCharacterById(@PathVariable("sides") @DiceConstraint int sides){
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            Map r = diceTower.roll(TOTAL, "3D6");
            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            httpStatus = HttpStatus.BAD_REQUEST;
            e.printStackTrace();
        }
        return new ResponseEntity<Integer>(18, httpStatus);
    }

}
