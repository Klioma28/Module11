package Module11;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Bill", "Max", "Alex", "Ivan", "Peter");
        System.out.println("Task 1");
        System.out.println(new Main().task1(names));
        System.out.println("\nTask 2");
        System.out.println(new Main().task2(names).toString());
        System.out.println("\nTask 3");
        new Main().task3(new String[]{"1, 2, 0", "4, 5"});
        System.out.println("\n\nTask 4");
        System.out.println(new Main().task4(12, 25214903917L, 11, 1L << 48, 28).collect(Collectors.toList()));
        System.out.println("\nTask 5");
        System.out.println("Size of second stream is bigger");
        System.out.println(Main.zip(Stream.of(1, 1, 1, 1, 1), Stream.of(2, 3, 4, 5, 6, 7, 8, 9)).collect(Collectors.toList()));
        System.out.println("Size of first stream is bigger");
        System.out.println(Main.zip(Stream.of(1.01, 1.03, 1.05, 1.07, 1.09), Stream.of(2.02, 3.04, 4.06)).collect(Collectors.toList()));
        System.out.println("Sizes of streams are equal");
        System.out.println(Main.zip(Stream.of("John", "Bill", "Max"), Stream.of("Alex", "Ivan", "Peter")).collect(Collectors.toList()));

    }

    public String task1(List<String> names){
        int size = names.size();
        int limit = size / 2 + Math.min(size % 2, 1);
        List<String> oddNames = Stream.iterate(0, i -> i + 2)
                .limit(limit)
                .map(names::get)
                .collect(Collectors.toList());
        StringBuilder result = new StringBuilder();
        int i = 1;
        for (String s:oddNames) {
            result.append(i).append(". ").append(s).append(", ");
            i+=2;
        }
        return result.substring(0, result.length()-2);
    }

    public List<String> task2(List<String> names){
        return names.stream()
                .map(String::toUpperCase)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    public void task3(String[] numbersArray){
        String numbers = numbersArray[0] + ", " + numbersArray[1];
        int[] sorted = Arrays.stream(numbers.split(", "))
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();
        for (int i:sorted) {
            if(i >= 5){
                System.out.print(i);
                break;
            }else System.out.print(i + ", ");
        }
    }

    public Stream<Long> task4(long seed, long a, long c, long m, long amount){
        Stream<Long> newStream = Stream.of(seed);
        long prev_num = seed;
        long num;
        for(int i = 1; i < amount; i++){
            num = ((prev_num * a) + c) % m;
            newStream = Stream.concat(newStream, Stream.of(num));
            prev_num = num;
        }
        return newStream;
    }

    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second){
        List<T> list1 = first.collect(Collectors.toList());
        List<T> list2 = second.collect(Collectors.toList());
        List<T> mixedList;
        int min_size = Math.min(list1.size(), list2.size());

        mixedList = list1.subList(0, min_size);

        for (int i = 0; i < min_size; i++){
            mixedList.add(i*2+1, list2.get(i));
        }
        return mixedList.stream();
    }
}