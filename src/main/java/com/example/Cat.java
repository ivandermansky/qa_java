package com.example;

import java.util.List;
import com.example.Feline;
import com.example.Predator;

public class Cat {

    Predator predator;

    public Cat(Feline feline) {
        this.predator = feline;
    }

    public String getSound() {

        return "Мяу";
    }


    public List<String> getFood() throws Exception {

        return predator.eatMeat();
    }


}
