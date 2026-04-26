package com.example;

import java.util.List;

public class Lion {
    private String sex;
    private com.example.Predator predator;  // Зависимость теперь от интерфейса Predator
    private boolean hasMane;
    public int defaultKittensCount;

    // Конструктор теперь принимает Predator вместо Feline, потому что всё сломалось, пришлось всё писать заново и пришлось устранять так зависимость от Feline.
    // К тому же это же будет зависимость от Feline, разве нет?
    public Lion(String sex, com.example.Predator predator) throws Exception {
        if (predator == null) {
            throw new IllegalArgumentException("Predator не может быть null");
        }

        defaultKittensCount = 2; // значение по умолчанию

        if ("Самец".equals(sex)) {
            hasMane = true;
        } else if ("Самка".equals(sex)) {
            hasMane = false;
        } else {
            throw new Exception("Используйте допустимые значения пола животного — самец или самка");
        }
        this.sex = sex;
        this.predator = predator;
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
     - Получить список еды через зависимость от Predator.
     - Вернуть список продуктов питания
     - Выбросить Exception при ошибках получения пищи
     */
    public List<String> getFood() throws Exception {
        return predator.eatMeat();  // Вызвать метод интерфейса
    }

    // Геттер для тестирования — теперь возвращает Predator
    public Predator getPredator() {
        return this.predator;
    }

    // Геттер для пола
    public String getSex() {
        return this.sex;
    }
}
