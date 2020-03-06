import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ua.com.foxminded.studentsmanager.domain.DataGenerator;

import static org.junit.jupiter.api.Assertions.*;

class DataGeneratorTest {

    @Test
    void generateGroupsShouldReturn10Groups() {
        DataGenerator dataGenerator = new DataGenerator();
        int actual = dataGenerator.generateGroups().length;
        int expected = 10;
        assertEquals(expected, actual);
    }

    @Test
    void createCoursesShouldReturn10Courses() {
        DataGenerator dataGenerator = new DataGenerator();
        int actual = dataGenerator.createCourses().length;
        int expected = 10;
        assertEquals(expected, actual);
    }

    @Test
    void createStudentsShouldReturn200Students() {
        DataGenerator dataGenerator = new DataGenerator();
        int actual = dataGenerator.createStudents().length;
        int expected = 200;
        assertEquals(expected, actual);
    }

    @Test
    void assignCoursesToStudentsShouldReturnStudentsWIthCoursesArray() {
        DataGenerator dataGenerator = new DataGenerator();
        Assertions.assertNotNull(dataGenerator.assignCoursesToStudents());
    }
}