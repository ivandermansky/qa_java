import com.example.Cat;
import com.example.Feline;
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
        String sound = cat.getSound();
        assertEquals("Мяу", sound);
    }

    @Test
    public void testGetFood_ReturnsExpectedList() throws Exception {
        List<String> actualFood = cat.getFood();
        assertEquals(expectedFood, actualFood);
    }

    @Test
    public void testGetFood_CallsEatMeatOnce() {
        try {
            cat.getFood();
            verify(mockFeline, times(1)).eatMeat();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Тест: проверка возвращаемого значения метода getKittens() без параметров
    @Test
    public void testFelineGetKittensNoParam_ReturnsConfiguredValue() {
        // Настроить мок: при вызове getKittens() вернуть 5
        when(mockFeline.getKittens()).thenReturn(5);

        // Вызвать метод
        int kittens = mockFeline.getKittens();

        // Проверить, что мок возвращает ожидаемое значение
        assertEquals(5, kittens);
    }

    // Тест: проверка, что метод getKittens() был вызван ровно один раз
    @Test
    public void testFelineGetKittensNoParam_CalledExactlyOnce() {
        when(mockFeline.getKittens()).thenReturn(5);

        // Вызываем метод один раз
        mockFeline.getKittens();

        // Проверяем, что метод был вызван ровно один раз без параметров
        verify(mockFeline, times(1)).getKittens();
    }

    // Тесты: граничные значения для getKittens(int)
    @Test
 public void testFelineGetKittens_CalledOnceWithZeroParameter() {
        // Настройка мока: при вызове с 0 вернуть 0
        when(mockFeline.getKittens(0)).thenReturn(0);

        // Вызов метода с параметром 0
        mockFeline.getKittens(0);

        // Проверка, что метод был вызван ровно один раз с параметром 0
        verify(mockFeline, times(1)).getKittens(0);
    }

// Тест: проверка, что метод getKittens() вызван один раз с параметром 999
    @Test
public void testFelineGetKittens_CalledOnceWithLargeNumber() {
        int largeNumber = 999;

        // Настройка мока: при вызове с 999 вернуть 999
        when(mockFeline.getKittens(largeNumber)).thenReturn(largeNumber);

        // Вызов метода с большим числом
        mockFeline.getKittens(largeNumber);

        // Проверка, что метод был вызван ровно один раз с параметром 999
        verify(mockFeline, times(1)).getKittens(largeNumber);
    }
}
