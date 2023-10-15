package com.sumerge.Movie.Services;

import org.springframework.stereotype.Service;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
public class MoviesServices
{
    public String getMovies(String page){
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/movie/top_rated?language=en-US&page="+page)
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2ZTE3YTVhZjFkNzQyYjg3NjMyMmMzMjhjNjIwNTgwNSIsInN1YiI6IjY0ZjQ1YTkyN2Q0MWFhMDBlMThhM2M1ZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.ruf0zUH9RVtx5YMmAPSDir_Qk7M6ojRJkywhaxCM7FU")
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
        catch (Exception e) { return null;}
    }

    public String getMovie(String movieId)
    {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/movie/"+movieId+"?language=en-US")
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2ZTE3YTVhZjFkNzQyYjg3NjMyMmMzMjhjNjIwNTgwNSIsInN1YiI6IjY0ZjQ1YTkyN2Q0MWFhMDBlMThhM2M1ZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.ruf0zUH9RVtx5YMmAPSDir_Qk7M6ojRJkywhaxCM7FU")
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
        catch (Exception e) { return null;}
    }
}
