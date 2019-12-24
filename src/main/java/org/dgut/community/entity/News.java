package org.dgut.community.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table
@Data
public class News implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long newsId;

    @Column(nullable = false)
    private String newsTitle;

    private String newsReprinted;

//    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userName", referencedColumnName = "userName")
    private User user;

    @Lob
    @Column(columnDefinition = "text", nullable = false)
    private String newsContent;

    private LocalDate publicTime;

    private String newsPicture;

    private int newsLike;

    private int newsCollect;

    private int newsViewTimes;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "news")
    private Set<NewsComment> comments;
}
