package org.jonleb.characters.web.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jonleb.characters.services.RollType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@SuppressWarnings("WeakerAccess")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiceTowerDescription {

    public DiceTowerDescription(RollType rollType, String dicesToRoll){
        this.rollType = rollType;
        this.dicesToRoll = dicesToRoll;
    }

    @NotNull
    @NotBlank
    private RollType rollType;

    private int limitSuccess = -1;

    @NotBlank
    @NotNull
    private String dicesToRoll;

}
