package com.training.horses_race.datamodelmax;

import com.training.horses_race.datamodel.*;

import java.util.List;

/**
 * Created by ivc4 on 17.10.2016.
 */
public class Racecourse extends AbstractModel {
    private String name;

//    private List<RaceCard> raceCard;
//    private Country country;


    public Racecourse() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}