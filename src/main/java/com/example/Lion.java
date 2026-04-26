package com.example;

import java.util.List;
import com.example.Feline;


public class Lion {
    private boolean hasMane;
    private Feline feline;

    public Lion(String sex, Feline feline) throws Exception {
        if (feline == null) {
            throw new IllegalArgumentException("Feline не может быть null");
        }
        if ("Самец".equals(sex)) {
            hasMane = true;
        } else if ("Самка".equals(sex)) {
            hasMane = false;
        } else {
            throw new Exception("Используйте допустимые значения пола животного — самец или самка");
        }
        this.feline = feline;
    }

    public int getKittens() {

        return feline.getKittens();
    }

    public boolean doesHaveMane() {
        return hasMane;
    }

    public List<String> getFood() throws Exception {
        return feline.getFood("Хищник");
    }

    public String getFamily() {
        return feline.getFamily();
    }
}
