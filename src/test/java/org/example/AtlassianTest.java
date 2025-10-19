package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AtlassianTest {

    private static Atlassian testObj;

    @BeforeAll
    public static void setup() {
        testObj = new Atlassian();
    }

    @Test
    public void testWithEmptyCinema() {
        Atlassian.Movie movie = new Atlassian.Movie("Terminator", 100);
        Atlassian.MovieSchedule schedule = new Atlassian.MovieSchedule();

        Assertions.assertTrue(testObj.canSchedule(movie, schedule));
    }

    @Test
    public void testWithSuccessfulandFailedMoviePlacement() {
        Atlassian.Movie movie = new Atlassian.Movie("Terminator", 100);
        Atlassian.MovieSchedule schedule = new Atlassian.MovieSchedule();
        schedule.add(
                new Atlassian.MovieTimeEntry("Movie A", 600, 120)
        );
        schedule.add(
                new Atlassian.MovieTimeEntry("Movie B", 840, 120)
        );
        schedule.add(
                new Atlassian.MovieTimeEntry("Movie C", 1080, 120)
        );
        schedule.add(
                new Atlassian.MovieTimeEntry("Movie D", 1320, 60)
        );

        Assertions.assertTrue(testObj.canSchedule(movie, schedule));

        Atlassian.Movie movieThatCantFitIn = new Atlassian.Movie("Terminator", 180);
        Assertions.assertFalse(testObj.canSchedule(movieThatCantFitIn, schedule));
    }
}
