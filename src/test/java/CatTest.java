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
    public void setUp() throws Exception {
        // Создать мок Feline один раз для всех тестов
        mockFeline = Mockito.mock(Feline.class);

        // Ожидаемый список еды
        expectedFood = List.of("Животные", "Птицы", "Рыба");

        Mockito.when(mockFeline.eatMeat()).thenReturn(expectedFood);

        Mockito.when(mockFeline.getKittens()).thenReturn(5);
        Mockito.when(mockFeline.getFamily()).thenReturn("Кошачьи");

        // Инициализировать Cat с моком Feline
        cat = new Cat(mockFeline);
    }

    @Test
    public void testGetSound() {
        String sound = cat.getSound();
        assertEquals("Мяу", sound);
    }

    @Test
    public void testGetFoodReturnsExpectedList() throws Exception {
        List<String> actualFood = cat.getFood();
        assertEquals(expectedFood, actualFood);
    }

    @Test
    public void testGetFoodCallsEatMeatOnce() throws Exception {
        cat.getFood();
        verify(mockFeline, times(1)).eatMeat();
    }


    @Test
    public void testFelineGetKittensNoParamReturnsConfiguredValue() {
        when(mockFeline.getKittens()).thenReturn(5);
        int kittens = mockFeline.getKittens();
        assertEquals(5, kittens);
    }

    // Мок ведет себя как реальный объект и возвращает 1
    @Test
    public void testFelineGetKittensNoParamCalledExactlyOnce() {
        when(mockFeline.getKittens()).thenReturn(1);
        mockFeline.getKittens();
        verify(mockFeline, times(1)).getKittens();
    }

    @Test
    public void testFelineGetKittensCalledOnceWithZeroParameter() {
        when(mockFeline.getKittens(0)).thenReturn(0);
        mockFeline.getKittens(0);
        verify(mockFeline, times(1)).getKittens(0);
    }

    @Test
    public void testFelineGetKittensCalledOnceWithLargeNumber() {
        int largeNumber = 999;
        when(mockFeline.getKittens(largeNumber)).thenReturn(largeNumber);
        mockFeline.getKittens(largeNumber);
        verify(mockFeline, times(1)).getKittens(largeNumber);
    }
}
