package com.sumerge.Controllers;

import com.sumerge.Services.MoviesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class MoviesController
{
    @Autowired
    private MoviesServices moviesServices;
    @GetMapping("/movies")
    public String getAllMovies(@RequestParam String page)
    {
        return this.moviesServices.getMovies(page);
    }
    @GetMapping("/movies/{id}")
    public String getMovie(@PathVariable String id)
    {
        return this.moviesServices.getMovie(id);
    }
}
