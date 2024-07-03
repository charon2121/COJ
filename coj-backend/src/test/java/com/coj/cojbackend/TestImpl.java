package com.coj.cojbackend;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Testable
public class TestImpl implements Test {
    @Test
    public void test() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        int[] array = new int[]{1, 3, 4, 5};

        System.out.println(Arrays.toString(array));
        System.out.println(list.toString());

        System.out.println("test");
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
