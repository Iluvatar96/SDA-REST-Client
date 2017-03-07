package pl.sda.file;

import javax.jws.soap.SOAPBinding;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class FileOperations {

    public static void main(String[] args) throws IOException {

        String message = "Hello world";

        File file = new File("C:\\Users\\RENT\\Desktop\\Test.txt");



        //exampleReadFromFile(message, file);

        //readNameInOneLine(file);
        //readLinesFromFile(file);
        List<User> users = readUserFromFile(file);
       // users.forEach(e -> System.out.println(e));

        User user = new User();
        user.setFirstName("Gayman");
        user.setLastName("Gryzipior");
        user.setAge(70);

        addUserToFile(user,file);
        users = readUserFromFile(file);

        users.forEach(e -> System.out.println(e));


    }
        public static void addUserToFile(User user, File file) throws IOException {

        try (FileWriter fw = new FileWriter(file, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(user.toString());
        }
    }







    private static List<User> readUserFromFile (File file) throws FileNotFoundException {
        List<User> users = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String nextLine = scanner.nextLine();
                String[] split = nextLine.split(" ");

                User user = new User();
                user.setFirstName(split[0]);
                user.setLastName(split[1]);
                user.setAge(new Integer(split[2]));

                users.add(user);
            }

        }
        return users;
    }


    private static void readLinesFromFile(File file) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String nextLine = scanner.nextLine();
                System.out.println(nextLine);
            }


        }
    }

    private static void readNameInOneLine(File file) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(file)) {
            String nextLine = scanner.nextLine();
            String[] split = nextLine.split(" ");
            for (int i = 0; i < split.length; i++) {
                System.out.println(split[i]);

            }
        }
    }

    private static void exampleReadFromFile(String message, File file) throws IOException {
        try (Scanner scanner = new Scanner(file)) {
            String s = scanner.nextLine();
            System.out.println(s);
        }
    }

    private static void exampleWriteToFile(String message, File file) throws IOException {
        try (FileWriter fw = new FileWriter(file, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(message);
        }
    }

}
