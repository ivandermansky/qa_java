package com.example;

import java.util.List;

public class Feline extends Animal implements Predator {

    private int defaultKittensCount; // количество котят по умолчанию

    // Конструктор с параметром для установки количества котят
    public Feline(int defaultKittensCount) {
        this.defaultKittensCount = defaultKittensCount;
    }

    // Конструктор без параметров, устанавливает значение по умолчанию — 3
    public Feline() {
        this(3);
    }

    @Override
    public List<String> eatMeat() throws Exception {
        return getFood("Хищник");
    }



    public int getKittens() {
        return getKittens(defaultKittensCount);
    }

    public int getKittens(int kittensCount) {
        return kittensCount;
    }
}
