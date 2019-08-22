package org.jonleb.characters.controller;

import lombok.extern.log4j.Log4j2;
import org.jonleb.characters.utils.DiceConstraint;
import org.jonleb.characters.utils.DiceRoller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/dices")
@Validated
@Log4j2
public class DiceController {

    @GetMapping("/{sides}")
    public ResponseEntity<Integer> getCharacterById(@PathVariable("sides") @DiceConstraint int sides){
        int r = DiceRoller.roll(10, sides);
        return new ResponseEntity<Integer>(r, HttpStatus.OK);
    }

}
