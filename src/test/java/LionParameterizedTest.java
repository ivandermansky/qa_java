package org.example;

import com.example.Animal;
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

// Параметры для теста (пол, количество котят, ожидаемое значение hasMane)
private String sex;
private int kittensCount;
private boolean expectedHasMane;

private Animal mockAnimal;
private List<String> expectedFood;

// Конструктор для Parameterized (принимает параметры теста)
public LionParameterizedTest(String sex, int kittensCount, boolean expectedHasMane) {
    this.sex = sex;
    this.kittensCount = kittensCount;
    this.expectedHasMane = expectedHasMane;
}

// Метод возвращает коллекцию параметров для теста
@Parameterized.Parameters(name = "Тестовые данные: пол={0}, котят={1}, грива={2}")
    public static Collection<Object[]> data() {
      return Arrays.asList(new Object[][] {
            {"Самец", 2, true},      // самец, 2 котёнка (по умолчанию)
            {"Самка", 5, false},     // самка, 5 котят
            {"Самец", 0, true},      // самец, 0 котят (граничный случай)
            {"Самка", 100, false}    // самка, 100 котят (большое количество)
      });
    }

@Before
public void setUp() {
    // Создать и настроить мок один раз перед каждым тестом
    mockAnimal = Mockito.mock(Animal.class);
    expectedFood = Arrays.asList("Мясо", "Рыба");
    try {
        when(mockAnimal.getFood("Хищник")).thenReturn(expectedFood);
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
}

 @Test
 public void testLionConstruction_InjectionAndDefaultKittens() throws Exception {
  // Создать экземпляр Lion с заданными параметрами
  Lion lion = new Lion(sex, mockAnimal, kittensCount);

  // Проверить инъекцию зависимости (animal)
  assertEquals(mockAnimal, lion.getAnimal());

  // Проверить инициализацию количества котят
  int expectedKittensCount = kittensCount <= 0 ? 2 : kittensCount;
  assertEquals(expectedKittensCount, lion.defaultKittensCount);
 }

 @Test
 public void testGetKittens_UsesDefaultCount() throws Exception {
   Lion lion = new Lion(sex, mockAnimal, kittensCount);
   int expectedKittensCount = kittensCount <= 0 ? 2 : kittensCount;

   // Проверить метод getKittens() с использованием defaultKittensCount
   assertEquals(expectedKittensCount, lion.getKittens());
 }

 @Test
 public void testHasMane_CorrectValue() throws Exception {
   Lion lion = new Lion(sex, mockAnimal, kittensCount);

   // Проверить наличие гривы (hasManeTrue)
   assertEquals(expectedHasMane, lion.hasManeTrue());
 }

 @Test
 public void testGetFood_ReturnsFromDependency() throws Exception {
   Lion lion = new Lion(sex, mockAnimal, kittensCount);

   // Проверить метод getFood()
   assertEquals(expectedFood, lion.getFood());
 }

 // Непараметризованный тест для проверок, которые не зависят от параметров
 @Test
 public void testGetKittens_WithParameter_AlwaysReturnsGivenValue() throws Exception {
   Lion lion = new Lion("Самец", mockAnimal, 2); // любые параметры
   int customKittens = 7;

   // Проверить метод getKittens(int) с явным указанием количества
   assertEquals(customKittens, lion.getKittens(customKittens));
 }
}
