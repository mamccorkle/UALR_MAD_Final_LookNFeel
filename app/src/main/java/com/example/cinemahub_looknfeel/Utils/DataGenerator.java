package com.example.cinemahub_looknfeel.Utils;

import android.content.Context;

import com.example.cinemahub_looknfeel.model.Genre;
import com.example.cinemahub_looknfeel.model.Rating;
import com.example.cinemahub_looknfeel.model.Movie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataGenerator
{

    public static List<Movie> generateMovieData( Context ctx )
    {
        List<Movie> returnList = new ArrayList<>();

        // Black Adam:
        Movie tempMovie = new Movie("Black Adam",
                                    "2022",
                                    Rating.PG13,
                                    6.8,
                                    125,
                                    "Nearly 5,000 years after he was bestowed with the almighty powers of the Egyptian gods--and imprisoned just as quickly--Black Adam is freed from his earthly tomb, ready to unleash his unique form of justice on the modern world.",
                                    "Jaume Collet-Serra",
                                    Arrays.asList("Dwayne Johnson", "Aldis Hodge", "Pierce Brosnan"),
                                    Genre.Action,
                                    Arrays.asList("7 PM", "7:30 PM", "8 PM", "8:30 PM", "9 PM", "9:30 PM"),
                                    0,
                         "X0tOpBuYasI");
        returnList.add(tempMovie);

        // Halloween Ends:
        tempMovie = new Movie("Halloween Ends",
                                "2022",
                                Rating.R,
                                5.0,
                                111,
                                "The saga of Michael Myers and Laurie Strode comes to a spine-chilling climax in the final installment of this trilogy.",
                                "David Gordon Green",
                                Arrays.asList("Jamie Lee Curtis", "Andi Matichak", "James Jude Courtney"),
                                Genre.Horror,
                                Arrays.asList("7:01 PM", "7:31 PM", "8:01 PM", "8:31 PM", "9:01 PM", "9:31 PM"),
                                1,
                                "i_mAWKyfj6c");
        returnList.add(tempMovie);

        // One Piece Film: Red:
        tempMovie = new Movie("One Piece Film: Red",
                                "2022",
                                Rating.PG13,
                                6.9,
                                115,
                                "For the first time ever, Uta - the most beloved singer in the world - will reveal herself to the world at a live concert. The voice that the whole world has been waiting for is about to resound.",
                                "Gorô Taniguchi",
                                Arrays.asList("Mayumi Tanaka", "Kazuya Nakai", "Akemi Okamura"),
                                Genre.Animation,
                                Arrays.asList("7:02 PM", "7:32 PM", "8:02 PM", "8:32 PM", "9:02 PM", "9:32 PM"),
                                2,
                                "89JWRYEIG-s");
        returnList.add(tempMovie);

        // Prey for the Devil:
        tempMovie = new Movie("Prey for the Devil",
                                "2022",
                                Rating.PG13,
                                5.2,
                                93,
                                "A nun prepares to perform an exorcism and comes face to face with a demonic force with mysterious ties to her past.",
                                "Daniel Stamm",
                                Arrays.asList("Virginia Madsen", "Jacqueline Byers", "Colin Salmon"),
                                Genre.Horror,
                                Arrays.asList("7:03 PM", "7:33 PM", "8:03 PM", "8:33 PM", "9:03 PM", "9:33 PM"),
                                3,
                                "u_jJiZ2oZgs");
        returnList.add(tempMovie);

        // Smile:
        tempMovie = new Movie(  "Smile",
                                "2022",
                                Rating.R,
                                6.7,
                                115,
                                "After witnessing a bizarre, traumatic incident involving a patient, Dr. Rose Cotter starts experiencing frightening occurrences that she can't explain. Rose must confront her troubling past in order to survive and escape her horrifying new reality.",
                                "Parker Finn",
                                Arrays.asList("Sosie Bacon", "Jessie T. Usher", "Kyle Gallner"),
                                Genre.Horror,
                                Arrays.asList("7:04 PM", "7:34 PM", "8:04 PM", "8:34 PM", "9:04 PM", "9:34 PM"),
                                4,
                                "BcDK7lkzzsU");
        returnList.add(tempMovie);

        // Ticket to Paradise:
        tempMovie = new Movie("Ticket to Paradise",
                                "2022",
                                Rating.PG13,
                                6.2,
                                104,
                                "A divorced couple teams up and travels to Bali to stop their daughter from making the same mistake they think they made 25 years ago.",
                                "Ol Parker",
                                Arrays.asList("George Clooney", "Sean Lynch", "Julia Roberts"),
                                Genre.Comedy,
                                Arrays.asList("7:05 PM", "7:35 PM", "8:05 PM", "8:35 PM", "9:05 PM", "9:35 PM"),
                                5,
                                "hkP4tVTdsz8");
        returnList.add(tempMovie);

        // Test Movie #1:
        tempMovie = new Movie("Test Movie #1",
                                "1900",
                                Rating.NC17,
                                10,
                                999,
                                "blah blah blah",
                                "Me",
                                Arrays.asList("Not Me", "Not You", "Not Him"),
                                Genre.Comedy,
                                Arrays.asList("7:15 PM", "7:35 PM"),
                                6,
                     "toP_Iz9R6GM");
        returnList.add(tempMovie);

        // Test Movie #2:
        tempMovie = new Movie("Test Movie #2",
                                "1800",
                                Rating.G,
                                1,
                                1,
                                "blah blah blah",
                                "Me",
                                Arrays.asList("Not Me", "Not You", "Not Him"),
                                Genre.Comedy,
                                Arrays.asList("7:25 PM", "7:45 PM"),
                                6,
                     "TybjwLS1VtA");
        returnList.add(tempMovie);

        return returnList;
    }

}
