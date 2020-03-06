import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ua.com.foxminded.studentsmanager.domain.FileDataReader;

class FileDataReaderTest {

        @Test
        void getDataFromFileShouldReturnEmptyStringWhenItGetsEmptyFile() {
            FileDataReader fileDataReader = new FileDataReader();
            String actual = fileDataReader.getDataFromFile("emptyTestPath.sql");
            String expected = "";
            Assertions.assertEquals(expected, actual);
        }

        @Test
        void getDataFromFileShouldReturnIllegalArgumentExceptionWhenItGetsWrongFilePath() {
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                FileDataReader fileDataReader = new FileDataReader();
                fileDataReader.getDataFromFile("Bla.sql");
            });
        }

        @Test
        void getDataFromFileShouldReturnTextFromGivenFile() {
            FileDataReader fileDataReader = new FileDataReader();
            String actual = fileDataReader.getDataFromFile("testText.sql");
            String expected = "SELECT * FROM students";
            Assertions.assertEquals(expected, actual);
        }
    }
