package com.example.movieReco.domain;

import com.example.movieReco.mapper.NaverMovie;
import com.example.movieReco.mapper.NaverMovieItem;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter @Setter
public class Movie {
    @Id
    private String MovieId;

    @Column(name = "MovieTitle")
    private String MovieTitle;

    @Column(name = "Director")
    private String Director;

    @Temporal(TemporalType.DATE)
    private Date ReleaseDate;

    @Column(name = "Rating")
    private float Rating;

    @Column(name = "RecommendCnt")
    private int RecommendCnt;

    public static Movie createMovie(NaverMovieItem nm) {
        Movie movie = new Movie();
        movie.setMovieId(nm.getMovieId());
        movie.setMovieTitle(nm.getTitle());
        movie.setDirector(nm.getDirector());
        movie.setRating(nm.getUserRating());
        movie.setRecommendCnt(1);
        movie.setReleaseDate(nm.getPubDate());
        return movie;
    }

}