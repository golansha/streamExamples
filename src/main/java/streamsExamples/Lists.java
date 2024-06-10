package streamsExamples;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Lists
{

    static String[] departments= {"Computers","Electric","Nursing"};
    static String[] cities= {"Jerusalem","Tel-Aviv","Nof-hagalil"};

    //  static public List<CourseGrade> courseGradesList= Arrays.asList(
    //          new CourseGrade(2000,"1000",88),
    //          new CourseGrade(2000,"1001",70),
    //          new CourseGrade(2001,"1000",90),
    //          new CourseGrade(2001,"1001",100),
    //          new CourseGrade(2001,"1002",60)
    //          );

    //    static public List<Student> studentList= Arrays.asList(
    //            new Student("1000","name500",new Address("street0",cities[0]),"100000",departments[0], 1, StudentStatus.NORMAL),
    //            new Student("1001","name499",new Address("street1",cities[1]),"100001",departments[0],2, StudentStatus.NORMAL),
    //            new Student("1002","name498",new Address("street0",cities[0]),"100002",departments[0],3, StudentStatus.NORMAL)
    //    );

    public static List<CourseGrade> courseGradesList= getFromFileCourseGrade();

    public static List<Student> studentList=getFromFileStudent();





    public static List<CourseGrade> getFromFileCourseGrade()
    {
        List<CourseGrade> list=null;
        try
        {
            list = Files.lines(Paths.get("data/CourseGrade.txt"))
                    .map(a->new CourseGrade(a))
                    .collect(Collectors.toList());
        } catch (Exception e ){
            System.out.println("Failed reading from file");
        }
        return list;

    }

    public static List<Student> getFromFileStudent()
    {
        List<Student> list=null;
        try
        {
            list = Files.lines(Paths.get("data/Student.txt"))
                    .map(s-> new Student(s))
                    .collect(Collectors.toList());
        } catch (Exception e ){
            System.out.println("Failed reading from file");
        }
        return list;

    }

    public static void writeTofileCourseGrade()
    {
        Random rand=new Random();

        try {
            FileWriter myWriter = new FileWriter("data/CourseGrade.txt");
            for (int i = 0; i <20 ; i++)
            {
                myWriter.write((2000+rand.nextInt(11)) + " "
                        + (1000+rand.nextInt(11)) + " " + rand.nextInt(101) + '\n' );

            }

            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public static void writeTofileStudent()
    {
        Random rand=new Random();

        try {
            FileWriter myWriter = new FileWriter("data/Student.txt");
            for (int i = 0; i <30 ; i++)
            {
                myWriter.write( "id= " + (1000+i) + " " +
                        "name= "+ "name"+(500-i)+" " +
                        "street= " + "street"+rand.nextInt(20)+" "+
                        "city= "+ cities[rand.nextInt(cities.length)]+" " +
                        "phone= "+   (100000+i)+" " +
                        "department= "+ departments[rand.nextInt(departments.length)]+" " +
                        "year= "+   rand.nextInt(4)+" " +
                        "status= "+   StudentStatus.values()[rand.nextInt(StudentStatus.values().length)]+" " +
                        '\n' );

            }

            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

}
