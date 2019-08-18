package org.jonleb.characters.coc;

/**
 * This DiceRoller is made for CoC.
 * The rule is the following:
 *      the player roll 2d6 3 time and keep the 3 results and:
 *          - add 6 to all of them
 *          - remove from 19 the 3 results
 *      and he has the 6 results.
 */

public class DiceRollerCoC {

    private static int [] dices = new int [6];

    private DiceRollerCoC(){}

    public static int [] dices(DiceRollerType type){
        switch (type){
            case ROLL_2D6:
                int d1 = (int) Math.floor(Math.random() * (12-2) + 2);
                int d2 = (int) Math.floor(Math.random() * (12-2) + 2);
                int d3 = (int) Math.floor(Math.random() * (12-2) + 2);
                dices[0] = d1 + 6;
                dices[1] = d2 + 6;
                dices[2] = d3 + 6;
                dices[3] = 19 - d1;
                dices[4] = 19 - d2;
                dices[5] = 19 - d3;
        }
        return dices;
    }

}
