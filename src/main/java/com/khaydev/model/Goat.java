package com.khaydev.model;

public class Goat extends Animal implements Locomotion {
    @Override
    protected String getSound() {
        return "bleat";
    }

    @Override
    public String eats() {
        return "grass";
    }

    @Override
    public String getLocomotion() {
        return "walks";
    }
}
