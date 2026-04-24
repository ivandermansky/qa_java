import com.example.Feline;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class FelineTest {
    @Test
    public void testEatMeat() throws Exception {
        // Создать шпион (spy) для Feline
        Feline feline = spy(new Feline());

        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");

        // Настроить поведение метода getFood
        doReturn(expectedFood).when(feline).getFood("Хищник");

        // Вызвать тестируемый метод
        List<String> actualFood = feline.eatMeat();

        // Проверить результат
        assertEquals(expectedFood, actualFood);

        // Проверить, что метод getFood был вызван ровно один раз
        verify(feline, Mockito.times(1)).getFood("Хищник");
    }

    @Test
    public void testGetKittensWithDefault() {
        // Создать объект Feline с количеством котят по умолчанию (3)
        Feline feline = new Feline();

        // Вызвать тестируемый метод без параметров
        int kittens = feline.getKittens();

        // Проверить результат (по умолчанию возвращает 3)
        assertEquals(3, kittens);
    }

    @Test
    public void testGetKittensWithCustomDefault() {
        // Создать объект Feline с кастомным количеством котят по умолчанию (5)
        Feline feline = new Feline(5);

        // Вызвать тестируемый метод без параметров
        int kittens = feline.getKittens();

        // Проверить результат (возвращает 5 — значение, установленное при создании объекта)
        assertEquals(5, kittens);
    }

    @Test
    public void testGetKittensWithParameter() {
        // Создать объект Feline
        Feline feline = new Feline();
        int expectedKittensCount = 7;

        // Вызвать тестируемый метод с параметром
        int actualKittensCount = feline.getKittens(expectedKittensCount);

        // Проверить результат
        assertEquals(expectedKittensCount, actualKittensCount);
    }

    @Test
    public void testGetKittensWithParameterAndCustomDefault() {
        // Создать объект Feline с кастомным количеством котят по умолчанию (2)
        Feline feline = new Feline(2);
        int expectedKittensCount = 8;

        // Вызвать тестируемый метод с параметром — должен вернуть переданное значение
        int actualKittensCount = feline.getKittens(expectedKittensCount);

        // Проверить результат — игнорирует значение по умолчанию, возвращает переданное значение
        assertEquals(expectedKittensCount, actualKittensCount);
    }
}
