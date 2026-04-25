package org.example;

import com.example.Cat;
import com.example.Feline;
import com.example.Predator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CatTest {

    private Cat cat;
    private Feline mockFeline;
    private List<String> expectedFood;

    @Before
    public void setUp() {
        // Создать мок Feline один раз для всех тестов
        mockFeline = Mockito.mock(Feline.class);

        // Настроить мок для метода eatMeat()
        expectedFood = List.of("Мыши", "Птица", "Рыба");
        try {
            Mockito.when(mockFeline.eatMeat()).thenReturn(expectedFood);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Инициализировать Cat с моком Feline
        cat = new Cat(mockFeline);
    }

    @Test
    public void testGetSound() {
        // Вызвать тестируемый метод
        String sound = cat.getSound();

        // Проверить результат
        assertEquals("Мяу", sound);
    }

    @Test
    public void testGetFood_ReturnsExpectedList() throws Exception {
        List<String> actualFood = cat.getFood();
        assertEquals(expectedFood, actualFood);
    }

    // Создан отдельный тест для Mockito.verify
    @Test
    public void testGetFoodEatMeatOnce() {
        try {
            cat.getFood();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            Mockito.verify(mockFeline, Mockito.times(1)).eatMeat();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGetKittensWithDefault() {
        // Вызвать тестируемый метод без параметров
        int kittens = cat.getKittens();

        // Проверить результат (по умолчанию возвращает 4)
        assertEquals(4, kittens);
    }

    @Test
    public void testGetKittensWithCustomDefault() {
        // Для теста с кастомным значением создать отдельный объект
        Feline mockFelineCustom = Mockito.mock(Feline.class);
        Cat catCustom = new Cat(mockFelineCustom, 6);

        // Вызвать тестируемый метод без параметров
        int kittens = catCustom.getKittens();

        // Проверить результат (возвращает 6 — значение, установленное при создании объекта)
        assertEquals(6, kittens);
    }

    @Test
    public void testGetKittensWithParameter() {
        int expectedKittensCount = 9;

        // Вызвать тестируемый метод с параметром
        int actualKittensCount = cat.getKittens(expectedKittensCount);

        // Проверить результат
        assertEquals(expectedKittensCount, actualKittensCount);
    }

    @Test
    public void testGetKittensWithParameterAndCustomDefault() {
        // Для теста с кастомным значением создаётся отдельный объект
        Feline mockFelineCustom = Mockito.mock(Feline.class);
        Cat catCustom = new Cat(mockFelineCustom, 3);
        int expectedKittensCount = 10;

        // Вызвать тестируемый метод с параметром — должен вернуть переданное значение
        int actualKittensCount = catCustom.getKittens(expectedKittensCount);

        // Проверить результат — игнорирует значение по умолчанию, возвращает переданное значение
        assertEquals(expectedKittensCount, actualKittensCount);
    }
}
