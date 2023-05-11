package com.example.onito.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "movies")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Movie {
    @Id
    @Column(name = "tconst",nullable = false)
    private String tConst;
    @Column(name = "titleType")
    private String titleType;
    @Column(name = "primaryTitle")
    private String primaryTitle;
    @Column(name = "runtime_minutes")
    private int runTimeMinutes;

    private String genres;
}
