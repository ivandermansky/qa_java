package org.example;

import com.example.Feline;
import com.example.Lion;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LionTest {

    @Test(expected = Exception.class)
    public void testConstructorWithWrongSex() throws Exception {
        Feline mockFeline = mock(Feline.class);
        new Lion("Неизвестный пол", mockFeline);
    }

    @Test
    public void testGetKittensDelegatesToFeline() throws Exception {
        Feline mockFeline = mock(Feline.class);
        when(mockFeline.getKittens()).thenReturn(5);

        Lion lion = new Lion("Самка", mockFeline);
        int kittens = lion.getKittens();

        assertEquals(5, kittens);
    }

    @Test
    public void testHasManeLogicBranchCoverage() throws Exception {
        Feline mockFeline = mock(Feline.class);

        // Случай 1: самец (hasMane = true)
        Lion maleLion = new Lion("Самец", mockFeline);
        assertTrue(maleLion.doesHaveMane());

        // Случай 2: самка (hasMane = false)
        Lion femaleLion = new Lion("Самка", mockFeline);
        assertFalse(femaleLion.doesHaveMane());
    }

    @Test(expected = Exception.class)
    public void testGetFoodThrowsExceptionWhenFelineThrows() throws Exception {
        Feline mockFeline = mock(Feline.class);
        when(mockFeline.getFood("Хищник")).thenThrow(new Exception("Ошибка получения пищи"));

        Lion lion = new Lion("Самец", mockFeline);
        lion.getFood();
    }

    @Test
    public void testGetFoodReturnsFromFeline() throws Exception {
        Feline mockFeline = mock(Feline.class);
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        when(mockFeline.getFood("Хищник")).thenReturn(expectedFood);

        Lion lion = new Lion("Самец", mockFeline);
        List<String> actualFood = lion.getFood();

        assertEquals(expectedFood, actualFood);
    }

    @Test
    public void testGetFamilyDelegatesToFeline() throws Exception {
        Feline mockFeline = mock(Feline.class);
        when(mockFeline.getFamily()).thenReturn("Кошачьи");

        Lion lion = new Lion("Самец", mockFeline);
        String family = lion.getFamily();

        assertEquals("Кошачьи", family);
    }

    @Test(expected = IllegalArgumentException.class)
   public void testConstructorWithNullFeline() throws Exception {
        new Lion("Самец", null);
    }
}
