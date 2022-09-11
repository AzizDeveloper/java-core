package com.ylab.complexHomework;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ComplexExamples {

    static class Person {
        final int id;

        final String name;

        Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Person person)) return false;
            return getId() == person.getId() && getName().equals(person.getName());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getId(), getName());
        }
    }

    private static Person[] RAW_DATA = new Person[]{
            new Person(0, "Harry"),
            new Person(0, "Harry"), // дубликат
            new Person(1, "Harry"), // тёзка
            new Person(2, "Harry"),
            new Person(3, "Emily"),
            new Person(4, "Jack"),
            new Person(4, "Jack"),
            new Person(5, "Amelia"),
            new Person(5, "Amelia"),
            new Person(6, "Amelia"),
            new Person(7, "Amelia"),
            new Person(8, "Amelia"),
    };
        /*  Raw data:

        0 - Harry
        0 - Harry
        1 - Harry
        2 - Harry
        3 - Emily
        4 - Jack
        4 - Jack
        5 - Amelia
        5 - Amelia
        6 - Amelia
        7 - Amelia
        8 - Amelia

        **************************************************

        Duplicate filtered, grouped by name, sorted by name and id:

        Amelia:
        1 - Amelia (5)
        2 - Amelia (6)
        3 - Amelia (7)
        4 - Amelia (8)
        Emily:
        1 - Emily (3)
        Harry:
        1 - Harry (0)
        2 - Harry (1)
        3 - Harry (2)
        Jack:
        1 - Jack (4)
     */

    public static void main(String[] args) {
        System.out.println("Raw data:");
        System.out.println();

        for (Person person : RAW_DATA) {
            System.out.println(person.id + " - " + person.name);
        }

        System.out.println();
        System.out.println("**************************************************");
        System.out.println();
        System.out.println("Duplicate filtered, grouped by name, sorted by name and id:");
        System.out.println();

        /*
        Task1
            Убрать дубликаты, отсортировать по идентификатору, сгруппировать по имени

            Что должно получиться
                Key:Amelia
                Value:4
                Key: Emily
                Value:1
                Key: Harry
                Value:3
                Key: Jack
                Value:1
         */
        System.out.println("\nTask1");

        if (RAW_DATA == null) {
            System.out.println("Please input valid data");
        } else {
            List<Person> personList = Arrays.asList(RAW_DATA);
            Map<String, Long> map = personList
                    .stream()
                    .filter(person -> person.getName() != null)
                    .distinct()
                    .collect(Collectors.groupingBy(Person::getName, Collectors.counting()));
            for (Map.Entry<String, Long> entry : map.entrySet()) {
                System.out.println("Key:" + entry.getKey() + "\nValue:" + entry.getValue());
            }
        }

        /*
        Task2

            [3, 4, 2, 7], 10 -> [3, 7] - вывести пару менно в скобках, которые дают сумму - 10
         */
        System.out.println("\nTask2");

        int[] intArray = {3, 4, 2, 7};
        System.out.println(findPair(null, 10));
        System.out.println(findPair(intArray, 10));



        /*
        Task3
            Реализовать функцию нечеткого поиска
                    fuzzySearch("car", "ca6$$#_rtwheel"); // true
                    fuzzySearch("cwhl", "cartwheel"); // true
                    fuzzySearch("cwhee", "cartwheel"); // true
                    fuzzySearch("cartwheel", "cartwheel"); // true
                    fuzzySearch("cwheeel", "cartwheel"); // false
                    fuzzySearch("lw", "cartwheel"); // false
         */
        System.out.println("\nTask3");

        System.out.println(fuzzySearch(null, "afjid"));
        System.out.println(fuzzySearch("dasads", null));

        System.out.println("fuzzySearch(\"car\", \"ca6$$#_rtwheel\") = " + fuzzySearch("car", "ca6$$#_rtwheel")); // true
        System.out.println("fuzzySearch(\"cwhl\", \"cartwheel\") = " + fuzzySearch("cwhl", "cartwheel")); // true
        System.out.println("fuzzySearch(    \"cwhee\", \"cartwheel\") = " + fuzzySearch("cwhee", "cartwheel")); // true
        System.out.println("fuzzySearch(\"cartwheel\", \"cartwheel\") = " + fuzzySearch("cartwheel", "cartwheel")); // true
        System.out.println("fuzzySearch(\"cwheeel\", \"cartwheel\") = " + fuzzySearch("cwheeel", "cartwheel")); // false
        System.out.println("fuzzySearch(\"lw\", \"cartwheel\") = " + fuzzySearch("lw", "cartwheel")); // false


    }

    static String findPair(int[] array, int numberToBeEqual) {
        if (array != null) {
            for (int i = 0; i < array.length - 1; i++) {
                for (int j = 1; j < array.length - i; j++) {
                    if (array[i] + array[i + j] == numberToBeEqual) {
                        return "[" + array[i] + ", " + array[i + j] + "]";
                    }
                }
            }
            return "Not found";
        } else return "Please input valid array!";
    }

    static boolean fuzzySearch(String searchingWord, String source) {
        if (searchingWord == null) {
            System.out.println("Please input valid searching word");
            return false;
        } else if (source == null) {
            System.out.println("Please input valid source word");
            return false;
        }
        String[] strings = searchingWord.split("");
        int[] positions = new int[searchingWord.length()];
        for (int i = 0; i < searchingWord.length(); i++) {
            positions[i] = source.indexOf(strings[i]);
            source = source.substring(source.indexOf(strings[i]) + 1);
        }
        boolean result = false;
        for (int i = 0; i < searchingWord.length() - 1; i++) {
            if (positions[i] <= positions[i + 1] && positions[i] >= 0) {
                result = true;
            } else result = false;
        }
        return result;
    }
}
