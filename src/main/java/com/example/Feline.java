package com.example;

import java.util.List;
import com.example.Animal;
import com.example.Predator;

public class Feline extends Animal implements Predator {

    @Override
    public List<String> eatMeat() throws Exception {

        return getFood("Хищник");
    }


    @Override
    public String getFamily() {

        return "Кошачьи";
    }


    public int getKittens() {

        return getKittens(1);
    }


    public int getKittens(int kittensCount) {

        return kittensCount;
    }

}
