package org.dgut.community.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
@Data
public class FourmArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleId;

    @Lob
    @Column(columnDefinition = "text", nullable = false)
    private String articleContent;

    @Column(nullable = false)
    private Long userId;

    private LocalDate articlePublicTime;

    private int articleLike;

    private int articleCollect;

    private String articlePhoto;

    private int articleCommentTimes;
}
