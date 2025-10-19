package org.example;


//{
//        "movies": [
//        {
//        "title": "Lord Of The Rings",
//        "durationInMinutes":  120
//        },
//        {
//        "title": "Back To The Future",
//        "durationInMinutes":  90
//        }
//        ],
//        "screenings": [
//        {
//        "title": "Lord Of The Rings",
//        "startTime": 660
//        },
//        {
//        "title": "Lord Of The Rings",
//        "startTime": 840
//        },
//        {
//        "title": "Back To The Future",
//        "startTime": 1020
//        },
//        {
//        "title": "Lord Of The Rings",
//        "startTime": 1200
//        }
//        ]
//        }

//{
//        "title": "Terminator",
//        "durationInMinutes":  100
//}

// 1000 - 840
// 1200/

// 10am - 11pm
// 600 - 1380
//
import java.util.*;

// 10 am in the morning cinema starts until 11 pm
// 600, 1800, 2300
public class Atlassian {

    public static class Movie {
        private final String title;
        private final Integer duration;

        public Movie(String title, Integer duration) {
            this.title = title;
            this.duration = duration;
        }

        @Override
        public String toString() {
            return "Movie{" +
                    "title='" + title + '\'' +
                    ", duration=" + duration +
                    '}';
        }
    }

    public static class MovieTimeEntry {
        private final String title;
        private final Integer startTime;
        private final Integer duration;
        private final Integer revenue;

        public MovieTimeEntry(String title, Integer startTime, Integer duration, Integer revenue) {
            this.title = title;
            this.startTime = startTime;
            this.duration = duration;
            this.revenue = revenue;
        }

        public MovieTimeEntry(String title, Integer startTime, Integer duration) {
            this.title = title;
            this.startTime = startTime;
            this.duration = duration;
            this.revenue = null;
        }

        @Override
        public String toString() {
            return "MovieTimeEntry{" +
                    "title='" + title + '\'' +
                    ", startTime=" + startTime +
                    ", duration=" + duration +
                    '}';
        }
    }

    public static class MovieSchedule {
        private final Queue<MovieTimeEntry> dailyTimeline;

        public MovieSchedule() {
            this.dailyTimeline = new PriorityQueue<>(Comparator.comparingInt(a -> a.startTime));
        }

        public void add(MovieTimeEntry entry) {
            dailyTimeline.add(entry);
        }

        public List<MovieTimeEntry> getDailyTimeline() {
            return this.dailyTimeline.stream().toList();
        }

        public boolean isEmpty() {
            return dailyTimeline.isEmpty();
        }

        @Override
        public String toString() {
            return getDailyTimeline().toString();
        }
    }

    private static final Integer END_TIME_FOR_CINEMA_IN_MINUTES = 1380;
    private static final Integer START_TIME_FOR_CINEMA_IN_MINUTES = 600;


    public int totalRevenue(MovieSchedule schedule) {
        return schedule.isEmpty() ? 0 : schedule.getDailyTimeline().stream().mapToInt(elem -> elem.revenue).sum();
    }

    public boolean canSchedule(Movie movie, MovieSchedule schedule) {

        if (movie.duration > END_TIME_FOR_CINEMA_IN_MINUTES - START_TIME_FOR_CINEMA_IN_MINUTES) return false;

        if (schedule.isEmpty()) return true;

        List<MovieTimeEntry> timeline = new ArrayList<>();
        timeline.add(new MovieTimeEntry("Start time", START_TIME_FOR_CINEMA_IN_MINUTES, 0));
        timeline.addAll(schedule.getDailyTimeline());
        timeline.add(new MovieTimeEntry("End time", END_TIME_FOR_CINEMA_IN_MINUTES, 0));

        for (int i=1;i<timeline.size();i++) {
            MovieTimeEntry entry1 = timeline.get(i-1);
            MovieTimeEntry entry2 = timeline.get(i);

            if (movie.duration <= (entry2.startTime - (entry1.startTime + entry1.duration))) {
                return true;
            }
        }

        return false;
    }

}
