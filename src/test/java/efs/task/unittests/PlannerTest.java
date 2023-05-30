package efs.task.unittests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlannerTest {
    Planner planner;

    @BeforeEach
    void init() {
        planner = new Planner();
    }

    @ParameterizedTest(name = "ActivityLevel {0}")
    @EnumSource(ActivityLevel.class)
    void shouldReturnCorrectDailyCaloriesDemand(ActivityLevel level) {
        //given
        User user = TestConstants.TEST_USER;
        //when
        int daily = planner.calculateDailyCaloriesDemand(user, level);
        //then
        assertEquals(TestConstants.CALORIES_ON_ACTIVITY_LEVEL.get(level),daily);
    }

    @Test
    void shouldReturnCorrectDailyIntake() {
        //given
        User user = TestConstants.TEST_USER;
        //when
        DailyIntake daily = planner.calculateDailyIntake(user);
        //then
        assertEquals(TestConstants.TEST_USER_DAILY_INTAKE.getCalories(),daily.getCalories());
        assertEquals(TestConstants.TEST_USER_DAILY_INTAKE.getProtein(),daily.getProtein());
        assertEquals(TestConstants.TEST_USER_DAILY_INTAKE.getFat(),daily.getFat());
        assertEquals(TestConstants.TEST_USER_DAILY_INTAKE.getCarbohydrate(),daily.getCarbohydrate());
    }

}
