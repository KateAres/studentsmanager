package ua.com.foxminded.studentsmanager.domain;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputService {

    public static String input() {
        String inputNumber;
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Input the letter: ");
            inputNumber = scanner.next();
        } catch (InputMismatchException e) {
            throw new IllegalArgumentException("Your data isn't valid. It should be a letter.", e);
        }
        return inputNumber;
    }

    public static int inputStudentCount() {
        int inputNumber;
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Input number of student count: ");
            inputNumber = scanner.nextInt();
        } catch (InputMismatchException e) {
            throw new IllegalArgumentException(Constants.INVALID_DATA_NUM_MESSAGE, e);
        }
        return inputNumber;
    }

    public static String inputCourseName() {
        String inputNumber;
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Input the name of the course: ");
            inputNumber = scanner.next();
        } catch (InputMismatchException e) {
            throw new IllegalArgumentException("Your data isn't valid. It should be a name of the course.", e);
        }
        return inputNumber;
    }

    public static int inputGroupId() {
        int inputNumber;
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Input group ID: ");
            inputNumber = scanner.nextInt();
        } catch (InputMismatchException e) {
            throw new IllegalArgumentException(Constants.INVALID_DATA_NUM_MESSAGE, e);
        }
        if (inputNumber < 1 || inputNumber > 10) {
            throw new IllegalArgumentException("The group_id is invalid");
        }
        return inputNumber;
    }

    public static String inputFirstName() {
        String inputNumber;
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Input first name of a student: ");
            inputNumber = scanner.next();
        } catch (InputMismatchException e) {
            throw new IllegalArgumentException(Constants.INVALID_DATA_STRING_MESSAGE, e);
        }
        return inputNumber;
    }

    public static String inputLastName() {
        String inputNumber;
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Input last name of a student: ");
            inputNumber = scanner.next();
        } catch (InputMismatchException e) {
            throw new IllegalArgumentException(Constants.INVALID_DATA_STRING_MESSAGE, e);
        }
        return inputNumber;
    }

    public static int inputStudentId() {
        int inputNumber;
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Input student ID: ");
            inputNumber = scanner.nextInt();
        } catch (InputMismatchException e) {
            throw new IllegalArgumentException(Constants.INVALID_DATA_NUM_MESSAGE, e);
        }
        return inputNumber;
    }

    public static int inputCourseId() {
        int inputNumber;
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Input course ID: ");
            inputNumber = scanner.nextInt();
        } catch (InputMismatchException e) {
            throw new IllegalArgumentException(Constants.INVALID_DATA_NUM_MESSAGE, e);
        }
        return inputNumber;
    }
}
