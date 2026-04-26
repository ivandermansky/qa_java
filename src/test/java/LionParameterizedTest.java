package org.example;

import com.example.Feline;
import com.example.Lion;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class LionParameterizedTest {

    // Параметры для теста (пол, ожидаемое значение hasMane)
    private String sex;
    private boolean expectedHasMane;

    private Feline mockFeline;
    private List<String> expectedFood;

    // Конструктор для Parameterized (принимает параметры теста)
    public LionParameterizedTest(String sex, boolean expectedHasMane) {
        this.sex = sex;
        this.expectedHasMane = expectedHasMane;
    }

    // Метод возвращает коллекцию параметров для теста
    @Parameterized.Parameters(name = "Тестовые данные: пол={0}, грива={1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"Самец", true},      // самец, грива есть
                {"Самка", false}      // самка, гривы нет
        });
    }

    @Before
    public void setUp() {
        // Создать и настроить мок один раз перед каждым тестом
        mockFeline = Mockito.mock(Feline.class);
        expectedFood = Arrays.asList("Животные", "Птицы", "Рыба");
        try {
            when(mockFeline.eatMeat()).thenReturn(expectedFood);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void testGetKittens_UsesDefaultCount() throws Exception {
        Lion lion = new Lion(sex, mockFeline);

        assertEquals(2, lion.getKittens());
    }

    @Test
    public void testHasMane_CorrectValue() throws Exception {
        Lion lion = new Lion(sex, mockFeline);

        // Проверить наличие гривы (hasManeTrue)
        assertEquals(expectedHasMane, lion.hasManeTrue());
    }

    @Test
    public void testGetFood_ReturnsFromDependency() throws Exception {
        Lion lion = new Lion(sex, mockFeline);

        // Проверить метод getFood()
        assertEquals(expectedFood, lion.getFood());
    }

    // Непараметризованный тест для проверок, которые не зависят от параметров
    @Test
    public void testGetKittens_WithParameter_AlwaysReturnsGivenValue() throws Exception {
        Lion lion = new Lion("Самец", mockFeline); // любые параметры
        int customKittens = 7;

        // Проверить метод getKittens(int) с явным указанием количества
        assertEquals(customKittens, lion.getKittens(customKittens));
    }
}
