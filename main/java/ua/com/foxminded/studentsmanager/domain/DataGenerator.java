package ua.com.foxminded.studentsmanager.domain;

import ua.com.foxminded.studentsmanager.domain.entities.Course;
import ua.com.foxminded.studentsmanager.domain.entities.Group;
import ua.com.foxminded.studentsmanager.domain.entities.Student;
import ua.com.foxminded.studentsmanager.domain.entities.StudentsCourses;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.HashSet;

public class DataGenerator {

    public Group[] generateGroups() {
        Group[] groups = new Group[10];
        Random random;
        try {
            random = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(Constants.CANT_CREATE_RANDOM_NUM + e);
        }
        for (int i = 0; i < groups.length; i++) {
            char firstChar = (char) (random.nextInt(26) + 'a');
            char secondChar = (char) (random.nextInt(26) + 'a');
            int firstInt = random.nextInt(9);
            int secondInt = random.nextInt(9);
            String groupName = String.valueOf(firstChar) + secondChar + "-" + firstInt + secondInt;
            groups[i] = new Group(i, groupName);
        }
        return groups;
    }

    public Course[] createCourses() {
        FileDataReader fileDataReader = new FileDataReader();
        Course[] courses = new Course[10];
        String[][] coursesNamesAndDesc;
        coursesNamesAndDesc = new String[][]{{"Algebra", fileDataReader.getDataFromFile(Constants.DESCRIPTION_ALGEBRA)},
                {"Art", fileDataReader.getDataFromFile(Constants.DESCRIPTION_ART)},
                {"Biology", fileDataReader.getDataFromFile(Constants.DESCRIPTION_BIOLOGY)},
                {"Chemistry", fileDataReader.getDataFromFile(Constants.DESCRIPTION_CHEMISTRY)},
                {"Economics", fileDataReader.getDataFromFile(Constants.DESCRIPTION_ECONOMICS)},
                {"Geology", fileDataReader.getDataFromFile(Constants.DESCRIPTION_GEOLOGY)},
                {"Math", fileDataReader.getDataFromFile(Constants.DESCRIPTION_MATH)},
                {"Physics", fileDataReader.getDataFromFile(Constants.DESCRIPTION_PHYSICS)},
                {"Psychology", fileDataReader.getDataFromFile(Constants.DESCRIPTION_PSYCHOLOGY)},
                {"Literature", fileDataReader.getDataFromFile(Constants.DESCRIPTION_LITERATURE)}};
        for (int i = 0; i < 10; i++) {
            int courseId = i + 1;
            String courseName = coursesNamesAndDesc[i][0];
            String courseDesc = coursesNamesAndDesc[i][1];
            courses[i] = new Course(courseId, courseName, courseDesc);
        }
        return courses;
    }

    public Student[] createStudents() {
        Student[] students = new Student[200];
        for (int i = 0; i < 200; i++) {
            String firstName = createStudentsNames()[0][i];
            String lastName = createStudentsNames()[1][i];
            Map<Integer, Integer> studentsWithGroup = assignGroupToStudent();
            Integer groupId = studentsWithGroup.get(i);
            students[i] = new Student(i + 1, groupId, firstName, lastName);
        }
        return students;
    }

    public ArrayList<StudentsCourses> assignCoursesToStudents() {
        ArrayList<StudentsCourses> studentsCourses = new ArrayList<>();
        for (int i = 1; i <= 200; i++) {
            ArrayList<Integer> randomNumbers = generateRandomNumbers();
            for (int idx = 1; idx <= randomNumbers.size(); idx++) {
                studentsCourses.add(new StudentsCourses(i, randomNumbers.get(idx - 1)));
            }
        }
        return studentsCourses;
    }

    private String[][] createStudentsNames() {
        Random random = new Random();
        String[][] studentsNames = new String[2][200];
        String[] firstNames = {"Sara", "Blake", "Tom", "Martin", "Brian", "Celia", "Carl", "Daniel", "Daisy", "Evan",
                "Gabriella", "Harry", "George", "Howard", "Jasmine", "Katherine", "Leonora", "Mary", "Nicholas", "Rita"
        };
        String[] lastNames = {"Smith", "Morris", "Potter", "Scott", "Long", "Taylor", "Ward", "Butler", "Turner", "Gray",
                "Hill", "Lee", "Lewis", "Torres", "Garcia", "White", "Kelly", "Flores", "Baker", "Anderson"
        };
        for (int i = 0; i < 200; i++) {
            studentsNames[0][i] = firstNames[random.nextInt(20)];
            studentsNames[1][i] = lastNames[random.nextInt(20)];
        }
        return studentsNames;
    }

    private Map<Integer, Integer> assignGroupToStudent() {
        Random random;
        try {
            random = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(Constants.CANT_CREATE_RANDOM_NUM + e);
        }
        Map<Integer, Integer> studentIdGroupId = new HashMap<>();
        int assignedStudents = 0;
        for (int i = 1; i <= 10; i++) {
            int randomNumberOfStudent;
            randomNumberOfStudent = random.nextInt(30);
            for (int idx = 1 + assignedStudents; idx <= randomNumberOfStudent + assignedStudents & idx <= 200; idx++) {
                studentIdGroupId.put(idx, i);
            }
            assignedStudents = assignedStudents + randomNumberOfStudent;
            if (i == 10 & studentIdGroupId.size() < 200) {
                break;
            }
        }
        return studentIdGroupId;
    }

    private ArrayList<Integer> generateRandomNumbers() {
        int numberOfIntegers = 3;
        int max = 11;
        int min = 1;
        Random random;
        try {
            random = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(Constants.CANT_CREATE_RANDOM_NUM + e);
        }
        ArrayList<Integer> uniqueNumbers = new ArrayList<>();
        HashSet<Integer> generated = new HashSet<>();
        while (generated.size() < numberOfIntegers) {
            generated.add(random.nextInt(max - min) + min);
        }
        uniqueNumbers.addAll(generated);
        return uniqueNumbers;
    }
}
