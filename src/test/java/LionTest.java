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

    // Параметры для теста
    private String sex;
    private boolean expectedHasMane;
    private int expectedKittens;
    private List<String> expectedFood;

    private Feline mockFeline;

    public LionParameterizedTest(String sex, boolean expectedHasMane, int expectedKittens, List<String> expectedFood) {
        this.sex = sex;
        this.expectedHasMane = expectedHasMane;
        this.expectedKittens = expectedKittens;
        this.expectedFood = expectedFood;
    }

    @Parameterized.Parameters(name = "Тестовые данные: пол={0}, грива={1}, котята={2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"Самец", true, 5, Arrays.asList("Животные", "Птицы", "Рыба")},
                {"Самка", false, 3, Arrays.asList("Животные", "Птицы")}
        });
    }

    @Before
    public void setUp() {
        // Создать мок
        mockFeline = Mockito.mock(Feline.class);

        // Настроить мок
        try {
            when(mockFeline.getKittens()).thenReturn(expectedKittens);
            when(mockFeline.getFamily()).thenReturn("Кошачьи");
            // Настроить getFood(String)
            when(mockFeline.getFood("Хищник")).thenReturn(expectedFood);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка настройки мока", e);
        }
    }

    @Test
    public void testHasManeCorrectValue() throws Exception {
        Lion lion = new Lion(sex, mockFeline);
        assertEquals(expectedHasMane, lion.doesHaveMane());
    }

    @Test
    public void testGetKittensReturnsCorrectCount() throws Exception {
        Lion lion = new Lion(sex, mockFeline);
        assertEquals(expectedKittens, lion.getKittens());
    }

    @Test
    public void testGetFoodReturnsFromDependency() throws Exception {
        Lion lion = new Lion(sex, mockFeline);
        assertEquals(expectedFood, lion.getFood());
    }

    @Test
    public void testGetFamilyReturnsFromFeline() throws Exception {
        Lion lion = new Lion(sex, mockFeline);
        assertEquals("Кошачьи", lion.getFamily());
    }
}
