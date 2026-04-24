package org.example;

import com.example.Animal;
import com.example.Lion;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class LionTest {



    @Test(expected = Exception.class)
    public void testConstructorWithInvalidSex() throws Exception {
        // Создать мок Animal
        Animal mockAnimal = mock(Animal.class);

        // Попытка создать Lion с некорректным полом — должно вызвать Exception
        new Lion("Неизвестный пол", mockAnimal);
    }

    @Test
    public void testAnimalInjection() throws Exception {
        // Создать мок Animal
        Animal mockAnimal = Mockito.mock(Animal.class);

        // Создать Lion
        Lion lion = new Lion("Самец", mockAnimal);

        // Проверить, что зависимость animal была корректно внедрена
        assertEquals(mockAnimal, lion.animal);
    }

    @Test
    public void testDefaultKittensCountInitialization() throws Exception {
        // Создать мок Animal
        Animal mockAnimal = Mockito.mock(Animal.class);

        // Создать Lion с кастомным количеством котят по умолчанию (7)
        Lion lion = new Lion("Самка", mockAnimal, 7);

        // Проверить, что defaultKittensCount был корректно инициализирован
        assertEquals(7, lion.defaultKittensCount);
    }

    @Test
    public void testGetKittensWithMinValue() throws Exception {
        // Создать мок Animal
        Animal mockAnimal = Mockito.mock(Animal.class);

        // Создать Lion с минимальным количеством котят по умолчанию (0)
        Lion lion = new Lion("Самка", mockAnimal, 0);

        // Вызвать тестируемый метод без параметров
        int kittens = lion.getKittens();

        // Проверить результат (возвращает 0 — значение, установленное при создании объекта)
        assertEquals(0, kittens);
    }

    @Test
    public void testGetKittensWithMaxValue() throws Exception {
        // Создать мок Animal
        Animal mockAnimal = Mockito.mock(Animal.class);

        // Создать Lion с большим количеством котят по умолчанию (100)
        Lion lion = new Lion("Самка", mockAnimal, 100);

        // Вызвать тестируемый метод без параметров
        int kittens = lion.getKittens();

        // Проверить результат (возвращает 100 — значение, установленное при создании объекта)
        assertEquals(100, kittens);
    }

    @Test
    public void testHasManeLogicBranchCoverage() throws Exception {
        // Тестируем обе ветви логики в конструкторе: hasMane = true и hasMane = false
        Animal mockAnimal = Mockito.mock(Animal.class);

        // Случай 1: самец (hasMane = true)
        Lion maleLion = new Lion("Самец", mockAnimal);
        assertTrue(maleLion.hasManeTrue());

        // Случай 2: самка (hasMane = false)
        Lion femaleLion = new Lion("Самка", mockAnimal);
        assertFalse(femaleLion.hasManeTrue());
    }

    @Test
    public void testGetFoodExceptionHandling() throws Exception {
        // Создать мок Animal, который выбрасывает Exception при вызове getFood
        Animal mockAnimal = Mockito.mock(Animal.class);
        Mockito.when(mockAnimal.getFood("Хищник")).thenThrow(new Exception("Ошибка получения пищи"));

        Lion lion = new Lion("Самец", mockAnimal);

        try {
            lion.getFood();
            fail("Должен был быть выброшен Exception");
        } catch (Exception e) {
            // Exception при ошибке получения пищи
            assertTrue(true);
        }
    }
}
