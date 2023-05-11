package com.example.onito.project.repository;

import com.example.onito.project.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {
    @Query(value = "SELECT m.genres, m.primary_title, SUM(r.num_votes) FROM movies m " +
            "JOIN ratings r ON m.tconst = r.tconst " +
            "GROUP BY m.genres, m.primary_title",
            nativeQuery = true)
    List<Object[]> findMovieGenreSubtotals();

    @Modifying
    @Query(value = "UPDATE movies m " +
            "SET m.runTimeMinutes = CASE " +
            "WHEN m.genres = 'Documentary' THEN m.runTimeMinutes + 15 " +
            "WHEN m.genres = 'Animation' THEN m.runTimeMinutes + 30 " +
            "ELSE m.runTimeMinutes + 45 " +
            "END")
    void updateRuntimeMinutes();

}
