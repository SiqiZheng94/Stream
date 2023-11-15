package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;



// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);



        List<Integer> evenNumbers = numbers.stream()
                                        .filter(number->number%2==0)
                                        .collect(Collectors.toList());
        System.out.println(evenNumbers);

        List<Integer> doubleNumbers = numbers.stream()
                                        .map(number->number*2)
                                        .collect(Collectors.toList());
        System.out.println(doubleNumbers);

        List<Integer> sortedNumbers = numbers.stream()
                                      .sorted()
                                      .collect(Collectors.toList());
        System.out.println(sortedNumbers);

        int sum = numbers.stream()
                .reduce(0,(x,y)->x+y);
        System.out.println(sum);

        numbers.stream()
                //.forEach(number->System.out.println(number));
                .forEach(System.out::println);

        //List<Integer> newList = numbers.stream().collect(Collectors.toList());
        List<Integer> newList = new ArrayList<>(numbers);
        System.out.println(newList);




        //Copy a file "students.csv" to the root directory of this project.
        try{
            //Stream<String> lines = Files.lines(Path.of("students.csv"));
            List<String> allLines = Files.readAllLines(Paths.get("/Users/siqizheng/IdeaProjects/Stream/src/main/java/org/example/students.csv"));
            allLines.forEach(System.out::println);

            Stream<String> dataLines = allLines.stream().skip(1);
            List<Student> students = dataLines
                    .map(line-> {
                        String[] parts = line.split(",");
                        if (parts.length != 4) {
                            return null;
                        }
                        try {
                            int id = Integer.parseInt(parts[0].trim());
                            String name = parts[1].trim();
                            String postalCode = parts[2].trim();
                            int age = Integer.parseInt(parts[3].trim());
                            return new Student(id, name, postalCode, age);
                        } catch (NumberFormatException e) {
                            return null;
                        }
                    })
                    .filter(student -> student!=null)
                    .distinct()
                    .collect(Collectors.toList());

            students.forEach(System.out::println);
        } catch (IOException e){
            e.printStackTrace();
        }




    }

}