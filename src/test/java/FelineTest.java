import com.example.Feline;
import com.example.Animal;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class FelineTest {
    @Test
    public void testEatMeat_ReturnsExpectedFoodList() throws Exception {

        // Создаётся мок для зависимости (Animal), а не для тестируемого объекта
        Animal mockAnimal = Mockito.mock(Animal.class);
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");

        try {
            Mockito.when(mockAnimal.getFood("Хищник")).thenReturn(expectedFood);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Создаётся реальный объект Feline, ему передаётся мок-зависимость
        Feline feline = new Feline();
        feline.setAnimal(mockAnimal); // внедряется зависимость

        List<String> actualFood = feline.eatMeat();

        assertEquals(expectedFood, actualFood);
    }

    // Создан отдельный тест для Mockito.verify
    @Test
    public void testEatMeat_CallsGetFoodWithCorrectParameterOnce() throws Exception {
        Animal mockAnimal = Mockito.mock(Animal.class);

        // Создать реальный объект Feline с мок-зависимостью
        Feline feline = new Feline();
        feline.setAnimal(mockAnimal);

        try {
            feline.eatMeat();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            Mockito.verify(mockAnimal, Mockito.times(1)).getFood("Хищник");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
