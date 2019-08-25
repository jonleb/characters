package org.jonleb.characters.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.jonleb.characters.constraints.DiceConstraint;

import javax.validation.Valid;

/**
 * What I learn:
 *      https://stackoverflow.com/questions/57616419/how-to-validate-parameters-of-a-static-method-in-junit
 *      Not possible to validate static method or static constructor.
 *      So it means that I probably have to use a non static class in a wrapper as workaround.
 *
 */

@Data
@AllArgsConstructor
public class DiceRoller {

    /**
     * Number of dices to roll
     */
    private int number = 1;

    /**
     * sides of the dices (limited to 2,4,6,8,10,12,20,100)
     */
    @Valid
    @DiceConstraint
    private int sides = 6;
    /**
     * Roll a set of dices of the same sides
     *
     * @return result of the roll
     */
    public int roll(){
        return (int) Math.floor(Math.random() * ((this.sides * this.number)-this.number) + this.number);
    }

}
