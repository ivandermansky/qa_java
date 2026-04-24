package com.example;

import java.util.List;

public class Cat {

    com.example.Predator predator;
    private int defaultKittensCount; // количество котят по умолчанию

    // Конструктор с инъекцией зависимости и параметром для количества котят
    public Cat(Feline feline, int defaultKittensCount) {
        this.predator = feline;
        this.defaultKittensCount = defaultKittensCount;
    }

    // Конструктор без параметра количества котят, устанавливает значение по умолчанию — 4
    public Cat(Feline feline) {
        this(feline, 4);
    }

    public String getSound() {
        return "Мяу";
    }

    public List<String> getFood() throws Exception {
        return predator.eatMeat();
    }

    // Получает количество котят с использованием значения по умолчанию
    public int getKittens() {
        return getKittens(defaultKittensCount);
    }

    // Получает количество котят с указанным параметром
    public int getKittens(int kittensCount) {
        return kittensCount;
    }
}
