package com.example.onito.project.repository;

import com.example.onito.project.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, String> {
    List<Rating> findByAverageRatingGreaterThan(double averageRating);
}
