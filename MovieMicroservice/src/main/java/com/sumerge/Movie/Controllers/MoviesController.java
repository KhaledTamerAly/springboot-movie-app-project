package com.sumerge.Movie.Controllers;

import com.sumerge.Movie.Services.MoviesServices;
import com.sumerge.Movie.Utils.StringUtils;
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
        if(StringUtils.isInt(page))
            return this.moviesServices.getMovies(page);
        else
            return "Incorrect page number";
    }
    @GetMapping("/movies/{id}")
    public String getMovie(@PathVariable String id)
    {
        if(StringUtils.isInt(id))
            return this.moviesServices.getMovie(id);
        else return "Incorrect movie id";
    }
}
