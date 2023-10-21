package com.sumerge.Movie.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

@Service
public class MoviesServices
{
    @Autowired
    private OkHttpClient client;
    public String getMovies(String page){

        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/movie/top_rated?language=en-US&page="+page)
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2ZTE3YTVhZjFkNzQyYjg3NjMyMmMzMjhjNjIwNTgwNSIsInN1YiI6IjY0ZjQ1YTkyN2Q0MWFhMDBlMThhM2M1ZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.ruf0zUH9RVtx5YMmAPSDir_Qk7M6ojRJkywhaxCM7FU")
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
        catch (Exception e) { return "No movies found";}
    }

    public String getMovie(String movieId)
    {
        try {
        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/movie/"+movieId+"?language=en-US")
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2ZTE3YTVhZjFkNzQyYjg3NjMyMmMzMjhjNjIwNTgwNSIsInN1YiI6IjY0ZjQ1YTkyN2Q0MWFhMDBlMThhM2M1ZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.ruf0zUH9RVtx5YMmAPSDir_Qk7M6ojRJkywhaxCM7FU")
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
