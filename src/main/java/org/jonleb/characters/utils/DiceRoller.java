package org.jonleb.characters.utils;

import javax.validation.Valid;
import javax.validation.constraints.Max;

/**
 * What I learn:
 *      https://stackoverflow.com/questions/57616419/how-to-validate-parameters-of-a-static-method-in-junit
 *      Not possible to validate static method or static constructor.
 *      So it means that I probably have to use a non static class in a wrapper as workaround.
 *
 */

public class DiceRoller {

    public DiceRoller(){}

    /**
     * Roll a set of dices of the same sides
     *
     * @param number: Number of dices to roll
     * @param sides: sides of the dices (limited to 2,4,6,8,10,12,20,100)
     * @return result of the roll
     */
    public int roll(@Max(20) @Valid Integer number, @DiceConstraint @Valid Integer sides){
        return (int) Math.floor(Math.random() * ((sides * number)-number) + number);
    }

}
