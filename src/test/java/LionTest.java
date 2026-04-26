package org.example;

import com.example.Feline;
import com.example.Lion;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class LionTest {

    @Test(expected = Exception.class)
    public void testConstructorWithInvalidSex() throws Exception {
        // Создать мок Feline
        Feline mockFeline = mock(Feline.class);

        // Попытка создать Lion с некорректным полом — должно вызвать Exception
        new Lion("Неизвестный пол", mockFeline);
    }


    @Test
    public void testDefaultKittensCountInitialization() throws Exception {
        // Создать мок Feline
        Feline mockFeline = Mockito.mock(Feline.class);

        // Создать Lion (defaultKittensCount всегда 2)
        Lion lion = new Lion("Самка", mockFeline);

        // Проверить, что defaultKittensCount был корректно инициализирован
        assertEquals(2, lion.defaultKittensCount);
    }

    @Test
    public void testGetKittensWithMinValue() throws Exception {
        // Создать мок Feline
        Feline mockFeline = Mockito.mock(Feline.class);

        // Создать Lion с defaultKittensCount = 2
        Lion lion = new Lion("Самка", mockFeline);

        // Вызвать тестируемый метод без параметров
        int kittens = lion.getKittens();

        // Проверить результат
        assertEquals(2, kittens);
    }

    @Test
    public void testHasManeLogicBranchCoverage() throws Exception {
        // Тестировать обе ветви логики в конструкторе: hasMane = true и hasMane = false
        Feline mockFeline = Mockito.mock(Feline.class);

        // Случай 1: самец (hasMane = true)
        Lion maleLion = new Lion("Самец", mockFeline);
        assertTrue(maleLion.hasManeTrue());

        // Случай 2: самка (hasMane = false)
        Lion femaleLion = new Lion("Самка", mockFeline);
        assertFalse(femaleLion.hasManeTrue());
    }

    @Test
    public void testGetFoodExceptionHandling() throws Exception {
        // Создать мок Feline, который выбрасывает Exception при вызове eatMeat
        Feline mockFeline = Mockito.mock(Feline.class);
        Mockito.when(mockFeline.eatMeat()).thenThrow(new Exception("Ошибка получения пищи"));

        Lion lion = new Lion("Самец", mockFeline);

        try {
            lion.getFood();
            fail("Должен был быть выброшен Exception");
        } catch (Exception e) {
            // Exception при ошибке получения пищи
            assertTrue(true);
        }
    }

    @Test
    public void testGetFood_ReturnsFromFeline() throws Exception {
        // Создать мок Feline с ожидаемым результатом
        Feline mockFeline = Mockito.mock(Feline.class);
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        Mockito.when(mockFeline.eatMeat()).thenReturn(expectedFood);

        Lion lion = new Lion("Самец", mockFeline);

        // Вызвать метод getFood
        List<String> actualFood = lion.getFood();

        // Проверить, что результат соответствует ожидаемому
        assertEquals(expectedFood, actualFood);
    }

    @Test
    public void testGetSex_ReturnsCorrectValue() throws Exception {
        Feline mockFeline = Mockito.mock(Feline.class);

        // Создать льва с полом "Самец"
        Lion maleLion = new Lion("Самец", mockFeline);
        assertEquals("Самец", maleLion.getSex());

        // Создать льва с полом "Самка"
        Lion femaleLion = new Lion("Самка", mockFeline);
        assertEquals("Самка", femaleLion.getSex());
    }

    @Test
    public void testGetKittens_WithParameter_AlwaysReturnsGivenValue() throws Exception {
        Feline mockFeline = Mockito.mock(Feline.class);
        Lion lion = new Lion("Самец", mockFeline);
        int customKittens = 7;

        // Проверить метод getKittens(int) с явным указанием количества
        assertEquals(customKittens, lion.getKittens(customKittens));
    }
}
