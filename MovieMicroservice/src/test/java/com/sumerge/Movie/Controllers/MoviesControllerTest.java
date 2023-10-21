package com.sumerge.Movie.Controllers;

import com.sumerge.Movie.Controllers.MoviesController;
import com.sumerge.Movie.MovieMicroservice;
import com.sumerge.Movie.Services.MoviesServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = MovieMicroservice.class)
public class MoviesControllerTest {

    @InjectMocks
    private MoviesController moviesController;

    @Mock
    private MoviesServices moviesServices;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllMoviesCorrectWithPage() {
        String page = "1";
        String expected = "Movie List Test";
        when(moviesServices.getMovies(page)).thenReturn(expected);
        String actual = moviesController.getAllMovies(page);
        assertEquals(expected, actual);
    }
    @Test
    public void testGetAllMoviesIncorrectCorrectWithPage() {
        String page = "k";
        String actual = moviesController.getAllMovies(page);
        assertEquals("Incorrect page number", actual);
    }

    @Test
    public void testGetMovieWithCorrectMovieId() {
        String id = "123";
        String expected = "Movie Details";
        when(moviesServices.getMovie(id)).thenReturn(expected);
        String actual = moviesController.getMovie(id);
        assertEquals(expected, actual);
    }
    @Test
    public void testGetMovieWithIncorrectMovieId() {
        String id = "lmwdlmd";
        String actual = moviesController.getMovie(id);
        assertEquals("Incorrect movie id", actual);
    }
}
