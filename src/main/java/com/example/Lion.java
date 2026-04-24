package com.example;

import java.util.List;

public class Lion {
    public Animal animal;
    private boolean hasMane;
    public int defaultKittensCount; // количество котят по умолчанию

    /*
     Конструктор с инъекцией зависимости через базовый класс Animal
     - sex - пол животного ("Самец" или "Самка")
     - Зависимость от базового класса Animal
     - Выбросить Exception если указан недопустимый пол
    */
    public Lion(String sex, com.example.Animal animal) throws Exception {
        this(sex, animal, 2); // вызов конструктора с параметром количества котят (по умолчанию — 2)
    }

    /*
     - Конструктор с параметром для установки количества котят
     - sex - пол животного
     - animal - зависимость от базового класса Animal
     - defaultKittensCount - количество котят по умолчанию
     - выбросить Exception если указан недопустимый пол
    */
    public Lion(String sex, com.example.Animal animal, int defaultKittensCount) throws Exception {
        if ("Самец".equals(sex)) {
            hasMane = true;
        } else if ("Самка".equals(sex)) {
            hasMane = false;
        } else {
            throw new Exception("Используйте допустимые значения пола животного — самец или самка");
        }
        this.animal = animal; // инъекция зависимости
        this.defaultKittensCount = defaultKittensCount;
    }

    /*
     - Получает количество котят с использованием значения по умолчанию
     - Вернуть количество котят
    */
    public int getKittens() {
        return getKittens(defaultKittensCount);
    }

    /*
     - Получает количество котят с указанным параметром
     - kittensCount - желаемое количество котят
     - Вернуть указанное количество котят
    */
    public int getKittens(int kittensCount) {
        return kittensCount;
    }

    /*
     - Проверяет, есть ли у льва грива
     - Вернуть true, если есть грива, иначе false
     */
    public boolean hasManeTrue() {
        return hasMane;
    }

    /*
     - Получает список еды через зависимость от Animal
     - Вернуть список продуктов питания
     - Выбросить Exception при ошибках получения пищи
    */
    public List<String> getFood() throws Exception {
        return animal.getFood("Хищник");
    }
}
