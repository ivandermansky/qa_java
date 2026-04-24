package org.example;

import com.example.Cat;
import com.example.Feline;
import com.example.Predator;
import org.junit.Test;           // JUnit 4
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.assertEquals;  // JUnit 4 assertions
import static org.mockito.Mockito.*;

public class CatTest {

    @Test
    public void testGetSound() {
        // Создать объект Cat
        Cat cat = new Cat(Mockito.mock(Feline.class));

        // Вызвать тестируемый метод
        String sound = cat.getSound();

        // Проверить результат
        assertEquals("Мяу", sound);
    }

    @Test
    public void testGetFood() throws Exception {
        // Создать мок Predator (через мок Feline, так как конструктор Cat принимает Feline)
        Feline mockFeline = Mockito.mock(Feline.class);
        Predator predatorMock = mockFeline; // предполагаем, что Feline реализует Predator

        List<String> expectedFood = List.of("Мыши", "Птица", "Рыба");

        // Настроить поведение мока: при вызове eatMeat() вернуть ожидаемый список
        Mockito.when(predatorMock.eatMeat()).thenReturn(expectedFood);

        // Создать Cat с моковой зависимостью
        Cat cat = new Cat(mockFeline);

        // Вызвать тестируемый метод
        List<String> actualFood = cat.getFood();

        // Проверить результат
        assertEquals(expectedFood, actualFood);

        // Проверить, что метод eatMeat() был вызван ровно один раз
        Mockito.verify(predatorMock, Mockito.times(1)).eatMeat();
    }

    @Test
    public void testGetKittensWithDefault() {
        // Создать объект Cat с количеством котят по умолчанию (4)
        Cat cat = new Cat(Mockito.mock(Feline.class));

        // Вызвать тестируемый метод без параметров
        int kittens = cat.getKittens();

        // Проверить результат (по умолчанию возвращает 4)
        assertEquals(4, kittens);
    }

    @Test
    public void testGetKittensWithCustomDefault() {
        // Создать объект Cat с кастомным количеством котят по умолчанию (6)
        Feline mockFeline = Mockito.mock(Feline.class);
        Cat cat = new Cat(mockFeline, 6);

        // Вызвать тестируемый метод без параметров
        int kittens = cat.getKittens();

        // Проверить результат (возвращает 6 — значение, установленное при создании объекта)
        assertEquals(6, kittens);
    }

    @Test
    public void testGetKittensWithParameter() {
        // Создать объект Cat
        Cat cat = new Cat(Mockito.mock(Feline.class));
        int expectedKittensCount = 9;

        // Вызвать тестируемый метод с параметром
        int actualKittensCount = cat.getKittens(expectedKittensCount);

        // Проверить результат
        assertEquals(expectedKittensCount, actualKittensCount);
    }

    @Test
    public void testGetKittensWithParameterAndCustomDefault() {
        // Создать объект Cat с кастомным количеством котят по умолчанию (3)
        Feline mockFeline = Mockito.mock(Feline.class);
        Cat cat = new Cat(mockFeline, 3);
        int expectedKittensCount = 10;

        // Вызвать тестируемый метод с параметром — должен вернуть переданное значение
        int actualKittensCount = cat.getKittens(expectedKittensCount);

        // Проверить результат — игнорирует значение по умолчанию, возвращает переданное значение
        assertEquals(expectedKittensCount, actualKittensCount);
    }
}
