package efs.task.unittests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FitCalculatorTest {

    @Test
    void shouldReturnTrue_whenDietRecommended() {
        //given
        double weight = 89.2;
        double height = 1.72;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertTrue(recommended);
    }

    @Test
    void shouldReturnFalse_whenDietNotRecommended() {
        //given
        double weight = 70.2;
        double height = 1.81;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertFalse(recommended);
    }

    @Test
    void ShouldThrowException_whenHeight_0() {
        //given
        double weight = 70.2;
        double height = 0;

        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> FitCalculator.isBMICorrect(weight, height));
    }

    @ParameterizedTest(name = "Weight {0}")
    @ValueSource(doubles = {70, 80, 90})
    void shouldReturnTrueForAllValues_whenDietRecommended(double weight) {
        //given
        double height = 1.50;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);
        //then
        assertTrue(recommended);
    }

    @ParameterizedTest(name = "Weight {0}, Height {1}")
    @CsvSource({"70, 1.8","60, 1.9", "75, 2.0"})
    void shouldReturnFalseForAllValues_whenDietNotRecommended(double weight, double height) {
        //given

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);
        //then
        assertFalse(recommended);
    }

    @ParameterizedTest(name = "Weight {0}, Height {1}")
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    void shouldReturnFalseForAllValuesFromFile_whenDietNotRecommended(double weight, double height) {
        //given
        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);
        //then
        assertFalse(recommended);
    }


    @Test
    void shouldReturnUserWithWorstBMI() {
        //given
        List<User> users = TestConstants.TEST_USERS_LIST;
        //when
        User worst_user = FitCalculator.findUserWithTheWorstBMI(users);
        //then
        assertEquals(97.3,worst_user.getWeight());
        assertEquals(1.79,worst_user.getHeight());
    }

    @Test
    void shouldReturnNull_whenUsersListEmpty() {
        //given
        List<User> users = new ArrayList<>();
        //when
        User null_user = FitCalculator.findUserWithTheWorstBMI(users);
        //then
        assertNull(null_user);
    }

    @Test
    void shouldReturnCorrectBmiScore() {
        //given
        List<User> users = TestConstants.TEST_USERS_LIST;
        //when
        double[] bmi_scores = FitCalculator.calculateBMIScore(users);
        //then
        assertArrayEquals(TestConstants.TEST_USERS_BMI_SCORE,bmi_scores);
    }

}