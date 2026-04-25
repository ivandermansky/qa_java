package com.example;

import java.util.List;

public class Lion {
    public com.example.Animal animal;
    private boolean hasMane;
    public int defaultKittensCount;

    //  Сделан один основной конструктор с тремя параметрами.

    public Lion(String sex, Animal animal, int defaultKittensCount) throws Exception {
        if (animal == null) {
            throw new IllegalArgumentException("Animal не может быть null");
        }

        if (defaultKittensCount <= 0) {
            defaultKittensCount = 2;
        }

        if ("Самец".equals(sex)) {
            hasMane = true;
        } else if ("Самка".equals(sex)) {
            hasMane = false;
        } else {
            throw new Exception("Используйте допустимые значения пола животного — самец или самка");
        }
        this.animal = animal;
        this.defaultKittensCount = defaultKittensCount;
    }

    
     //Сеттер для внедрения зависимости Animal.        
    void setAnimal(com.example.Animal animal) {
        if (animal == null) {
            throw new IllegalArgumentException("Animal не может быть null");
        }
        this.animal = animal;
    }

    /*
     - Получить количество котят с использованием значения по умолчанию.
     - Вернуть количество котят
     */
    public int getKittens() {
        return getKittens(defaultKittensCount);
    }

    /*
     - Получить количество котят с указанным параметром.
     - Параметр kittensCount это желаемое количество котят
     - Вернуть указанное количество котят
     */
    public int getKittens(int kittensCount) {
        return kittensCount;
    }

    /*
     - Проверить, есть ли у льва грива.
     - Вернуть true, если есть грива, иначе false
     */
    public boolean hasManeTrue() {
        return hasMane;
    }

    /*
     - Получить список еды через зависимость от Animal.
     - Вернуть список продуктов питания
     - Выбросить Exception при ошибках получения пищи
     */
    public List<String> getFood() throws Exception {
        return animal.getFood("Хищник");
    }

    // Геттер для тестирования
    public com.example.Animal getAnimal() {
        return this.animal;
    }
}
