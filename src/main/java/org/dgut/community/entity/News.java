package org.dgut.community.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
@Data
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long newsId;

    @Column(nullable = false)
    private String newsTitle;

    private String newsReprinted;

    @Column(nullable = false)
    private String userName;

    @Lob
    @Column(columnDefinition = "text", nullable = false)
    private String newsContent;

    private LocalDate publicTime;

    private String newsPicture;

    private int newsLike;

    private int newsCollect;

    private int newsViewTimes;
}
