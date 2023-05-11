package com.example.onito.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MovieDto {
    private String tConst;
    private String primaryTitle;
    private String genre;
    private double averageRating;
    private int numVotes;
    private int totalNumVotes;

    public MovieDto(String tConst, String primaryTitle, String genre, double averageRating) {
        this.tConst = tConst;
        this.primaryTitle = primaryTitle;
        this.genre = genre;
        this.averageRating = averageRating;
    }

}
