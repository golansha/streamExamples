package streamsExamples;


import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Collections.reverseOrder;
import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.*;



public class MainApp {


    /**
     * @param list a list of integers
     * @param value the upper limit
     * @return a list of all elements with a value less than value.
     * Usage:
     * List<Integer> list = List.of(5, 4, 1, 3, 9, 8, 6, 7, 2, 0);
     * System.out.println(findLessThan5(list, 5));
     */
    public static List<Integer> filterLessThan(List<Integer> list, int value) {
        return list.stream()
            .filter(i -> i < value)
            .collect(toList());
    }

    /**
     * @param list a list of integers
     * @return a list of the square root of the first three even elements
     * Usage:
     * List<Integer> list = List.of(25, 49, 16, 36, 144, 6, 7, 2, 0);
     * System.out.println(findSquaresOfFirstThreeEven(list));
     */

   //  find square root of the first three even elements
    public static List<Double> findSquareRootOfFirstThreeEven(List<Integer> list) {
        return list.stream()
            .filter(i -> i % 2 == 0)
            .limit(3)
            .map(Math::sqrt)
            .collect(toList());
    }

    /**
     * @param strings An array of strings of lengt l
     * @param permutation An array of integers, which are a permutation of [0,l)
     * @return The permutation of the strings, based on the order in permutation
     * Usage:
     * int[] permutation = {5, 4, 1, 3, 9, 8, 6, 7, 2, 0};
     * String[] strings = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
     * System.out.println(permuteStrings(strings, permutation));
     */
    public static List<String> permuteStrings(String [] strings, int[] permutation) {

        return IntStream.of(permutation)
            .mapToObj(i -> strings[i])
            .collect(toList());
    }

    /**
     * @param until the limit
     * @return return a string representing the sum of numbers from 1 to limit-1
     * Usage:
     * sumOfConsecutive(10)
     */
    public static String sumOfConsecutive(int until) {

        int sum1 = IntStream.iterate(1, i -> i + 1)
            .limit(until)
            .sum();

        int sum2 = IntStream.iterate(1, i -> i + 1)
            .limit(until)
            .reduce(0, Integer::sum);

        assert sum1==sum2;

        String sumExpression = IntStream.iterate(1, i -> i + 1)
            .limit(10)
            .mapToObj(Integer::toString)
            .reduce("", (a, b) -> a + " + " + b);

        return sumExpression + " = " + sum1;
    }

    /**
     * @param numbers1 an array of numbers
     * @param numbers2 a list of numbers
     * @return The list of numbers appearing in both lists
     * Usage:
     * int[] numbers1 = {5, 4, 1, 3, 9, 8, 6, 7, 2, 0};
     * List<Integer> numbers2 = Stream.iterate(0, j -> j + 2).toList();
     * System.out.println(intersect(numbers1, numbers2));
     */
    // מציאת האיברים הנמצאים בשתי רשימות
    public static List<Integer> intersect(int[] numbers1, List<Integer> numbers2) {  //14
        return IntStream.of(numbers1)
            .filter(i -> (numbers2.stream()
                .limit(4)
                .anyMatch(k -> i == k)))
            .boxed()
            .collect(Collectors.toList());
    }

    /**
     * @param numbers An array of numbers
     * @return A decreasing sorted list, containing the elements of the input array
     * Usage:
     * int[] numbers = {5, 4, 1, 3, 9, 8, 6, 7, 2, 0};
     * System.out.println(sortIntegerArray(numbers));
     */
    public static List<Integer> sortIntegerArray(int[] numbers) {  //
        List<Integer> list1 = Arrays.stream(numbers)
            .boxed()
            .sorted(Comparator.reverseOrder())
            .collect(Collectors.toList());


        List<Integer> list2 = Arrays.stream(numbers)
            .boxed()
            .sorted((a, b) -> b.compareTo(a))
            .collect(Collectors.toList());

        assert list2.equals(list1);

        String[] strings0 = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        List<String> strings = Arrays.stream(strings0)
            .sorted(Comparator.comparing(String::length))
            .collect(Collectors.toList());

        System.out.println(strings);
        return list1;
    }

    /**
     * @param strings An array of strings
     * @return A decreasing sorted list (based on string length), containing the elements of the input array
     * Usage:
     * String[] strings = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
     * System.out.println(sortStringArray(strings));
     */
    public static List<String> sortStringArray(String[] strings) {
        return Arrays.stream(strings)
            .sorted(Comparator.comparing(String::length))
            .collect(Collectors.toList());
    }

    /**
     * Finds the indices in the array that match the value in the array
     * @param numbers a permutation of [0..n-1]
     * Usage:
     * int[] numbers = {5, 4, 1, 3, 9, 8, 6, 7, 2, 0};
     * isNumInIndex(int[] numbers)
     */
    public static void isNumInIndex(int[] numbers) {  // 12

        Map<Integer, Boolean> isInPlace = IntStream.range(0, numbers.length)
            .boxed()//Transforms IntStream to Stream<Integer>
            // This is required because the IntStream's collect function cannot use the primitive int type.
            .collect(toMap(i->i,i->numbers[i]==i));

        Map<Boolean, List<Integer>> isInPlace2 = IntStream.range(0, numbers.length)
            .boxed()
            .collect(partitioningBy(i->numbers[i]==i));

        System.out.println(isInPlace2);

    }

    /**
     * prints the first n elements of the Fibonacci sequence
     * @param n
     */
    public static void fibo(int n) {

        List<Integer> list = Stream.iterate(new int[]{0, 1}, i -> new int[]{i[1], i[0] + i[1]})
//            .peek(i -> System.out.println(i[0] + " " + i[1]))
            .map(i -> i[0])
            .limit(n)
            .collect(toList());

        System.out.println(list);

//        This implementation is NOT RECOMMENDED as it has side effects (changing the values in the array)
//        int[] numbers = {0, 1, 0, 0, 0, 0, 0, 0, 0, 0};
//
//        IntStream.range(2, numbers.length)
//            .map(index -> numbers[index] = numbers[index - 1] + numbers[index - 2])
//            .sum();
//        System.out.println(Arrays.toString(numbers));
    }

    /**
     *
     * @param matrix a two-dimensional array of integers
     * @return the minimum number over the whole matrix
     */
    //int[][] matrix = {{3, 5, 6, 10}, {1, 2}, {7, 8, 9}};
    //minInMatrix(matrix);
    public static int minInMatrix(int[][] matrix) {
        int min = Arrays.stream(matrix)
            .mapToInt(v -> Arrays.stream(v).min().orElse(Integer.MAX_VALUE))
            .min()
            .orElse(0);

        int min2 = Arrays.stream(matrix)
            .flatMapToInt(v -> Arrays.stream(v))
            .min()
            .orElse(Integer.MAX_VALUE);

        return min2;
    }

    /**
     *
     * @param courseId the course id
     * @param minGrade the minimal grade
     * @return the number of students that have a grade of at least minGrade in the course courseID
     * In this example we create a stream from a list
     */
    public static long countHighGradeStudents(int courseId, int minGrade) {
        // Creating a stream from a list
        return Lists.courseGradesList
            .stream()
            .filter(s -> s.getCourseID() == courseId && s.getGrade() >= minGrade)
            .count();
    }

    /**
     *
     * @param courseId the course id
     * @param maxGrade the maximal grade
     * @return the number of students that have a grade of less than maxGrade in the course courseID
     * In this example we create a stream from a file
     */
    public static long countLowGradeStudents(int courseId, int maxGrade) {
        // Creating a stream from a file
        try {
            return Files.lines(Paths.get("CourseGrade.txt"))
                .map(a -> new CourseGrade(a))
                .filter(s -> s.getCourseID() == courseId && s.getGrade() < maxGrade)
                .count();
        } catch (Exception e) {
            System.out.println("Exception while reding the file");
            return -1;
        }
    }

    /**
     *
     * @param cityName the name of the city
     * @return a list of student names that live in the corresponding city
     */
    // For getting the Jerusalem students
    // getStudentNames("JER")
    public static List<String> getStudentNames(String cityName) {  // jojo

        return Lists.studentList
            .stream()
            .filter(s -> s.getAddress().getCity().equals(cityName))
            .map(s -> s.getName())
            .collect(Collectors.toList());
    }

    /**
     *
     * @param studentID the student id
     * @return the grade average of the student
     */
    public static double getStudentAverage(String studentID) {  // jojo

        return Lists.courseGradesList
            .stream()
            .filter(s -> s.getStudentID().equals(studentID))
            .mapToInt(CourseGrade::getGrade)
            .average()
            .orElse(0);

    }

    /**
     *
     * @param studentID
     * @return the name of the student
     */
    public static String getStudentName(String studentID) {  // jojo

        return Lists.studentList
            .stream()
            .filter(s -> s.getId().equals(studentID))
            .map(s -> s.getName())
            .findFirst()
            .orElse(null);

    }

    /**
     *
     * @return a map of the 3 students that have the highest average and their averages
     */
    public static Map<String, Double> findTop3Average() {

        Map<String, Double> stuAve = Lists.courseGradesList
            .stream()
            .map(s -> s.getStudentID())
            .distinct()
            .collect(toMap(Function.identity(), MainApp::getStudentAverage));

        // דרך נוספת, למתקדמים
        Map<String, Double> stuAve1 = Lists.courseGradesList
            .stream()
            .collect(groupingBy(g -> g.getStudentID(), averagingInt(CourseGrade::getGrade)));

        assert stuAve.equals(stuAve1);

        // מציאת שלושת המצטיינים
        return stuAve1.entrySet().stream()
            .sorted(comparingByValue(reverseOrder()))
            .limit(3)
            .collect(toMap(e -> getStudentName(e.getKey()), e -> e.getValue()));
    }


    public static void main(String[] arg0) {
        List<Integer> list = List.of(5, 4, 1, 3, 9, 8, 6, 7, 2, 0);
        System.out.println(filterLessThan(list,5));

        List<Integer> list1 = List.of(25, 49, 16, 36, 144, 6, 7, 2, 0);
        System.out.println(findSquareRootOfFirstThreeEven(list1));

        int[] permutation = {5, 4, 1, 3, 9, 8, 6, 7, 2, 0};
        String[] strings = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        System.out.println(permuteStrings(strings, permutation));

        System.out.println(sumOfConsecutive(10));

        int[] numbers1 = {5, 4, 1, 3, 9, 8, 6, 7, 2, 0};
        List<Integer> numbers2 = Stream.iterate(0, j -> j + 2).limit(10).toList();
        System.out.println(intersect(numbers1, numbers2));

        int[] numbers = {5, 4, 1, 3, 9, 8, 6, 7, 2, 0};
        System.out.println(sortIntegerArray(numbers));

        String[] strings1 = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        System.out.println(sortStringArray(strings1));

        isNumInIndex(numbers);

        System.out.println(countHighGradeStudents(2010,55));

        System.out.println(findTop3Average());

        fibo(10);
        int[][] matrix = {{3, 5, 6, 10}, {1, 2}, {7, 8, 9}};
        System.out.println(minInMatrix(matrix));
    }
}
