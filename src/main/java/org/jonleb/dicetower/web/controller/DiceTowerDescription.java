package org.jonleb.dicetower.web.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jonleb.dicetower.services.RollType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@SuppressWarnings("all")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiceTowerDescription {

    public DiceTowerDescription(RollType rollType, String dicesToRoll){
        this.rollType = rollType;
        this.dicesToRoll = dicesToRoll;
    }

    /**
     * Type of rolling dices
     */
    @NotNull
    @NotBlank
    private RollType rollType;

    /**
     * Determine the minimal number to have a success
     *
     * Optional
     */
    private int limitSuccess = -1;

    /**
     * Set of dices to roll.
     * ex
     * - "2D6"
     * - "3D6 2D4"
     */
    @NotBlank
    @NotNull
    private String dicesToRoll;

}
