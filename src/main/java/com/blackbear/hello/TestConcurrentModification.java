package com.blackbear.hello;

import java.util.ArrayList;
import java.util.Iterator;

public class TestConcurrentModification {

    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("cc");
        arrayList.add("vv");
        arrayList.add("aa");

        Iterator<String> it = arrayList.iterator();

        while (it.hasNext()) {
            String item = it.next();
            if ("aa".equals(item)) {
                it.remove();
            }
        }

        System.out.println(arrayList.size());
    }
}
