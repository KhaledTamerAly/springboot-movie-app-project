package com.sumerge.Movie.Services;

import okhttp3.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MoviesServicesTest {

    @InjectMocks
    private MoviesServices moviesServices;

    @Mock
    private OkHttpClient client;

    @Mock
    private Request request;

    @Mock
    private Response response;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetMoviesNoException() throws Exception {
        String page = "1";
        String expected = "Movies List";
        Call call = mock(Call.class);
        ResponseBody responseBody = mock(ResponseBody.class);
        when(client.newCall(any(Request.class))).thenReturn(call);
        when(call.execute()).thenReturn(response);
        when(response.body()).thenReturn(responseBody);
        when(responseBody.string()).thenReturn(expected);
        String actual = moviesServices.getMovies(page);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetMoviesExceptionThrown() throws Exception {
        String page = "k";
        String expected = "No movies found";
        Call call = mock(Call.class);
        ResponseBody responseBody = mock(ResponseBody.class);
        when(client.newCall(any(Request.class))).thenReturn(call);
        when(call.execute()).thenReturn(response);
        when(response.body()).thenReturn(responseBody);
        when(responseBody.string()).thenReturn(expected);
        when(client.newCall(request).execute()).thenThrow(new IOException("Test exception"));
        String actual = moviesServices.getMovies(page);
        assertEquals("No movies found", actual);
    }

    @Test
    public void testGetMovieNoException() throws Exception {
        String id = "123";
        String expected = "Movie Details";
        Call call = mock(Call.class);
        ResponseBody responseBody = mock(ResponseBody.class);
        when(client.newCall(any(Request.class))).thenReturn(call);
        when(call.execute()).thenReturn(response);
        when(response.body()).thenReturn(responseBody);
        when(responseBody.string()).thenReturn(expected);
        String actual = moviesServices.getMovie(id);
        assertEquals(expected, actual);
    }


    @Test
    public void testGetMovieExceptionThrown() throws Exception {
        String id = "---";
        String expected = "No movie with this id";
        Call call = mock(Call.class);
        ResponseBody responseBody = mock(ResponseBody.class);
        when(client.newCall(any(Request.class))).thenReturn(call);
        when(call.execute()).thenReturn(response);
        when(response.body()).thenReturn(responseBody);
        when(responseBody.string()).thenReturn(expected);
        String actual = moviesServices.getMovie(id);
        assertEquals(expected, actual);
    }
}
