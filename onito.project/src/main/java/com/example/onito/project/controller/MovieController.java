package com.example.onito.project.controller;

import com.example.onito.project.dto.MovieDto;
import com.example.onito.project.entity.Movie;
import com.example.onito.project.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MovieController {
    private MovieService movieService;

    @GetMapping("/longest-duration-movies")
    public ResponseEntity<List<Movie>> getLongestDurationMovies() {
        List<Movie> longestDurationMovies = movieService.getLongestDurationMovies();
        return ResponseEntity.ok(longestDurationMovies);
    }

    @PostMapping("/new-movie")
    public ResponseEntity<?> saveNewMovie(@RequestBody Movie movie){
        movie = movieService.save(movie);
        return ResponseEntity.ok("success");
    }

    @GetMapping("/top-rated-movies")
    public ResponseEntity<List<MovieDto>> getTopRatedMovies() {
        List<MovieDto> topRatedMovies = movieService.getTopRatedMovies();
        if (topRatedMovies.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(topRatedMovies);
        }
    }

    @GetMapping("/genre/subtotals")
    public ResponseEntity<?> findMovieGenreSubtotals(){
        List<Object[]> genreMoviesWithSubtotals = movieService.getGenreMoviesWithSubtotals();
        return new ResponseEntity<>(genreMoviesWithSubtotals, HttpStatus.OK);
    }

    @PostMapping("/update-runtime-minutes")
    public ResponseEntity<Void> updateRuntimeMinutes() {
        movieService.updateRuntimeMinutes();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Autowired
    public void setMovieService(MovieService movieService) {
        this.movieService = movieService;
    }
}
