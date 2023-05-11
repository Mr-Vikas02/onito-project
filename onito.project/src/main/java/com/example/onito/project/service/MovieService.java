package com.example.onito.project.service;

import com.example.onito.project.dto.MovieDto;
import com.example.onito.project.entity.Movie;
import com.example.onito.project.entity.Rating;
import com.example.onito.project.repository.MovieRepository;
import com.example.onito.project.repository.RatingRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final RatingRepository ratingRepository;
    @Autowired
    public MovieService(MovieRepository movieRepository, RatingRepository ratingRepository) {
        this.movieRepository = movieRepository;
        this.ratingRepository = ratingRepository;
    }
    public List<Movie> getLongestDurationMovies(){
        return movieRepository.findAll(Sort.by(Sort.Direction.DESC, "runTimeMinutes")).subList(0, 10);
    }

    @Transactional
    public Movie save(Movie movie){
        return movieRepository.save(movie);
    }

    public List<MovieDto> getTopRatedMovies(){
        List<Rating> topRatings = ratingRepository.findByAverageRatingGreaterThan(6.0);
        List<String> tConsts = topRatings.stream().map(Rating::getTConst).collect(Collectors.toList());
        List<Movie> topMovies = movieRepository.findAllById(tConsts);

        //** Mapping all movies whose tConst is equals to rating tConst. **//
        List<MovieDto> sortedMovies = topRatings.stream().map(rating -> {
            Movie movie = topMovies.stream()
                    .filter(m -> m.getTConst().equals(rating.getTConst()))
                    .findFirst().orElse(null);
            if (movie != null) {
                return new MovieDto(movie.getTConst(), movie.getPrimaryTitle(), movie.getGenres(), rating.getAverageRating());
            } else {
                return null;
            }
        }).filter(Objects::nonNull).sorted(Comparator.comparing(MovieDto::getAverageRating).reversed()).collect(Collectors.toList());

        return sortedMovies;
    }

    public List<Object[]> getGenreMoviesWithSubtotals() {
        List<Object[]> movieGenresAndNumVotes = movieRepository.findMovieGenreSubtotals();
        return movieGenresAndNumVotes;
    }

    public void updateRuntimeMinutes() {
        movieRepository.updateRuntimeMinutes();
    }

}
