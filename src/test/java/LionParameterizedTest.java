package org.example;

import com.example.Animal;
import com.example.Lion;
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

    // Параметры для теста (пол, количество котят, ожидаемое значение hasMane)
    private String sex;
    private int kittensCount;
    private boolean expectedHasMane;

    // Конструктор для Parameterized (принимает параметры теста)
    public LionParameterizedTest(String sex, int kittensCount, boolean expectedHasMane) {
        this.sex = sex;
        this.kittensCount = kittensCount;
        this.expectedHasMane = expectedHasMane;
    }

    // Метод возвращает коллекцию параметров для теста
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Самец", 2, true},      // самец, 2 котёнка (по умолчанию)
                {"Самка", 5, false},     // самка, 5 котят
                {"Самец", 0, true},      // самец, 0 котят (граничный случай)
                {"Самка", 100, false}    // самка, 100 котят (большое количество)
        });
    }

    @Test
    public void testLionConstructionAndBasicMethods() throws Exception {
        // Создать мок объекта Animal
        Animal mockAnimal = Mockito.mock(Animal.class);

        // Создать экземпляр Lion с заданными параметрами
        Lion lion = new Lion(sex, mockAnimal, kittensCount);

        // Проверить инъекцию зависимости (animal)
        assertEquals(mockAnimal, lion.animal);

        // Проверить инициализацию количества котят
        assertEquals(kittensCount, lion.defaultKittensCount);

        // Проверить метод getKittens() использован defaultKittensCount)
        assertEquals(kittensCount, lion.getKittens());

        // Проверить метод getKittens(int) с явным указанием количества
        int customKittens = 7;
        assertEquals(customKittens, lion.getKittens(customKittens));

        // Проверить наличие гривы (hasManeTrue)
        assertEquals(expectedHasMane, lion.hasManeTrue());

        // Проверить метод getFood() (использован мок, чтобы избежать исключений)
        List<String> expectedFood = Arrays.asList("Мясо", "Рыба");
        when(mockAnimal.getFood("Хищник")).thenReturn(expectedFood);
        assertEquals(expectedFood, lion.getFood());
    }
}
