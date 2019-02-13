package com.assignment.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;

import java.util.List;


@Entity
public class Subject implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="subjectId")
    private Integer id ;
    @Column
    private String subtitle;
    @Column
    private int durationInHours ;

    @Column
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name="subjectId")
    private List<Book> books=new ArrayList<>();

    public Subject(){

    }

    public Subject(String subtitle, int durationInHours){
        this.subtitle=subtitle;
        this.durationInHours=durationInHours;
    }

    public Subject(Integer id, String subtitle, int durationInHours){
        this.id=id;
        this.subtitle=subtitle;
        this.durationInHours=durationInHours;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public int getDurationInHours() {
        return durationInHours;
    }

    public void setDurationInHours(int durationInHours) {
        this.durationInHours = durationInHours;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }


}
