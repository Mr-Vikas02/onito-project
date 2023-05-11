package com.example.onito.project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity(name = "ratings")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Rating {
    @Id
    @Column(name = "tconst",nullable = false)
    private String tConst;
    @Column(name = "averageRating")
    private double averageRating;
    @Column(name = "numVotes")
    private int numVotes;
}
