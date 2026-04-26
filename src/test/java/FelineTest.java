import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class FelineTest {
    private com.example.Feline feline;
    private List<String> expectedMeatFood;


    /*
     Инициализация тестовых объектов перед каждым тестом:
     - feline: экземпляр Feline для тестирования
     - expectedMeatFood: ожидаемый список пищи для хищников
     */
    @Before
    public void setUp() {
        feline = new com.example.Feline();
        expectedMeatFood = List.of("Животные", "Птицы", "Рыба");
    }

    @Test
    public void testEatMeat_ReturnsCorrectFoodList() throws Exception {
        // Вызвать тестируемый метод
        List<String> actualFood = feline.eatMeat();

        // Проверить, что результат соответствует ожидаемому
        assertEquals(expectedMeatFood, actualFood);
    }

    @Test
    public void testGetFamily_ReturnsCorrectFamily() {
        String family = feline.getFamily();
        assertEquals("Кошачьи", family);
    }

    @Test
    public void testGetKittens_ReturnsDefaultCountWhenNoParam() {
        int kittens = feline.getKittens();
        assertEquals(1, kittens);
    }

    @Test
    public void testGetKittens_ReturnsParamValueWhenProvided() {
        int expectedKittensCount = 7;
        int actualKittensCount = feline.getKittens(expectedKittensCount);
        assertEquals(expectedKittensCount, actualKittensCount);
    }

    @Test
    public void testGetKittens_ReturnsAnotherParamValue() {
        int expectedKittensCount = 8;
        int actualKittensCount = feline.getKittens(expectedKittensCount);
        assertEquals(expectedKittensCount, actualKittensCount);
    }
}
