package org.jonleb.characters.services;

import lombok.extern.log4j.Log4j2;
import org.jonleb.characters.constraints.dicetower.DiceTowerConstraint;
import org.jonleb.characters.constraints.dicetower.RollConstraint;
import org.jonleb.characters.utils.DiceRoller;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("DuplicatedCode")
@Log4j2
@Service
@Validated
public class DiceTower {

    private final DiceRoller diceRoller = new DiceRoller(1,6);

    @RollConstraint(rollTypes = {RollType.TOTAL, RollType.TOTAL_BY_TYPE})
    public Map<String, Integer> roll(
            @Valid RollType rollType,
            @DiceTowerConstraint String ... args){
        Map<String, Integer> result = null;

        switch (rollType) {
            case TOTAL:
                result = rollForTotal(args);
                break;
            case TOTAL_BY_TYPE:
                result = rollForTotalBySide(args);
                break;
        }
        return result;
    }

    @RollConstraint(rollTypes = {RollType.SUCCESS, RollType.SUCCESS_BY_TYPE})
    public Map<String, Integer> rollForSuccess(
            @Valid RollType rollType,
            int limitSuccess,
            @DiceTowerConstraint String ... args){

        Map<String, Integer> result = null;

        switch (rollType) {
            case SUCCESS:
                result = rollForSuccess(limitSuccess,args);
                break;
            case SUCCESS_BY_TYPE:
                result = rollForSuccessBySide(limitSuccess, args);
                break;
        }
        return result;
    }

    private Map<String, Integer> rollForTotal(String ... args){
        Map<String, Integer> result = new HashMap<>(args.length);
        int total = 0;
        String key = "";
        String [] n;
        for (String arg : args) {
            n = arg.split("[D|d]");
            total += this.diceRoller.roll(Integer.parseInt(n[0]), Integer.parseInt(n[1]));
            key = key.concat(arg + " ");
        }
        result.put(key.trim(),total);
        return result;
    }

    private Map<String, Integer> rollForTotalBySide(String ... args){
        Map<String, Integer> result = new HashMap<>(args.length);
        String [] n;
        for (String arg : args) {
            n = arg.split("[D|d]");
            result.put(arg,this.diceRoller.roll(Integer.parseInt(n[0]), Integer.parseInt(n[1])));
        }
        return result;
    }

    private Map<String, Integer> rollForSuccess(int limitSuccess, String ... args){
        Map<String, Integer> result = new HashMap<>(args.length);
        String key = "";
        int successNumber = 0;
        String [] n;
        int loops;
        for(String arg: args){
            n =arg.split("[D|d]");
            loops = Integer.parseInt(n[0]);
            for (int i =0; i < loops; i++){
                if ( this.diceRoller.roll(1,Integer.parseInt(n[1])) >= limitSuccess){
                    successNumber ++;
                }
            }
            key = key.concat(arg + " ");
        }
        result.put(key.trim(),successNumber);
        return result;
    }

    private Map<String, Integer> rollForSuccessBySide(int limitSuccess, String ... args){
        Map<String, Integer> result = new HashMap<>(args.length);
        String key = "";
        int successNumber = 0;
        String [] n;
        int loops;
        for(String arg: args){
            n = arg.split("[D|d]");
            loops = Integer.parseInt(n[0]);
            for (int i =0; i < loops; i++){
                if (this.diceRoller.roll(1, Integer.parseInt(n[1])) >= limitSuccess){
                    successNumber ++;
                }
            }
            result.put(key.trim(),successNumber);
            successNumber = 0;
        }

        return result;
    }


}
